package muscaa.chess.mod;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import fluff.functions.gen.obj.Func1;
import fluff.functions.gen.obj.TVoidFunc1;
import fluff.json.JSON;
import fluff.json.JSONObject;
import muscaa.chess.bootstrap.Bootstrap;
import muscaa.chess.utils.FileUtils;

public class ChessModLoader {
	
	public static final ChessModLoader INSTANCE = new ChessModLoader();
	
	public final Map<String, ModInfo> mods = new HashMap<>();
	public final Map<String, Object> loaded = new HashMap<>();
	
	protected void loadBase() throws ModException {
	    for (File file : FileUtils.MODS.listFiles()) {
	    	if (!file.getName().endsWith(".jar")) continue;
	    	
	    	try {
				JarFile jar = new JarFile(file);
				JarEntry modJson = jar.getJarEntry("mod.json");
				
				JSONObject json;
				try (InputStream in = jar.getInputStream(modJson)) {
					json = JSON.object(new String(in.readAllBytes()));
				}
				
				jar.close();
				
				ModInfo info = ModInfo.of(json);
				if (info.getID() == null) {
					throw new ModException("Mod " + file.getName() + " is missing ID");
				}
				if (info.getClientMain() == null && info.getServerMain() == null) {
					throw new ModException("Mod " + file.getName() + " is missing main class");
				}
				
				if (!Bootstrap.INSTANCE.loader.addJar(file)) {
					throw new IOException("Failed to load jar " + file.getName());
				}
				
				mods.put(info.getID(), info);
			} catch (Exception e) {
				throw new ModException("Failed to load mod " + file.getName(), e);
			}
	    }
	}
	
	protected <V> void load(Class<V> initializerClass, Func1<String, ModInfo> mainFunc, TVoidFunc1<V, Exception> initFunc, boolean pre) throws ModException {
		for (Map.Entry<String, ModInfo> entry : mods.entrySet()) {
			ModInfo info = entry.getValue();
			if (mainFunc.invoke(info) == null) continue;
			
			try {
				Class<?> clazz = Bootstrap.INSTANCE.loader.loadClass(mainFunc.invoke(info));
				if (!initializerClass.isAssignableFrom(clazz)) {
					throw new ModException("Mod main class " + mainFunc.invoke(info) + " does not implement " + initializerClass.getName());
				}
				
				V initializer;
				if (pre) {
					initializer = (V) clazz.getConstructor().newInstance();
					loaded.put(info.getID(), initializer);
				} else {
					initializer = (V) loaded.get(info.getID());
				}
				
				initFunc.invoke(initializer);
			} catch (Exception e) {
				throw new ModException("Failed to load mod main class " + mainFunc.invoke(info), e);
			}
		}
	}
	
	public void loadPreClient() throws ModException {
	    loadBase();
	    load(IClientModInitializer.class, ModInfo::getClientMain, IClientModInitializer::onPreInitializeClient, true);
	}
	
	public void loadPostClient() throws ModException {
		load(IClientModInitializer.class, ModInfo::getClientMain, IClientModInitializer::onPostInitializeClient, false);
	}
	
	public void loadPreServer() throws ModException {
	    loadBase();
	    load(IServerModInitializer.class, ModInfo::getClientMain, IServerModInitializer::onPreInitializeServer, true);
	}
	
	public void loadPostServer() throws ModException {
	    load(IServerModInitializer.class, ModInfo::getClientMain, IServerModInitializer::onPostInitializeServer, false);
	}
}
