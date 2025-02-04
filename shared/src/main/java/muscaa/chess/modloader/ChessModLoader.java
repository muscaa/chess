package muscaa.chess.modloader;

import java.io.File;
import java.io.FileInputStream;
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
import muscaa.chess.bootstrap.DefaultBootstrap;
import muscaa.chess.utils.FileUtils;

public class ChessModLoader<V> {
	
	public final Map<String, ModInfo> modInfos = new HashMap<>();
	public final Map<String, Object> loaded = new HashMap<>();
	
	protected final Class<V> initializerClass;
	protected final Func1<String, ModInfo> mainClassFunc;
	protected final TVoidFunc1<V, Exception> preInitFunc;
	protected final TVoidFunc1<V, Exception> postInitFunc;
	
	public ChessModLoader(
			Class<V> initializerClass,
			Func1<String, ModInfo> mainClassFunc,
			TVoidFunc1<V, Exception> preInitFunc,
			TVoidFunc1<V, Exception> postInitFunc
			) {
		this.initializerClass = initializerClass;
		this.mainClassFunc = mainClassFunc;
		this.preInitFunc = preInitFunc;
		this.postInitFunc = postInitFunc;
	}
	
	public void loadPre() throws ModException {
	    loadBase();
	    load(preInitFunc, true);
	}
	
	public void loadPost() throws ModException {
		load(postInitFunc, false);
	}
	
	protected void load(TVoidFunc1<V, Exception> initFunc, boolean create) throws ModException {
		for (Map.Entry<String, ModInfo> entry : modInfos.entrySet()) {
			ModInfo info = entry.getValue();
			if (mainClassFunc.invoke(info) == null) continue;
			
			try {
				Class<?> clazz = Bootstrap.INSTANCE.loader.loadClass(mainClassFunc.invoke(info));
				if (!initializerClass.isAssignableFrom(clazz)) {
					throw new ModException("Mod main class " + mainClassFunc.invoke(info) + " does not implement " + initializerClass.getName());
				}
				
				V initializer;
				if (create) {
					initializer = (V) clazz.getConstructor().newInstance();
					loaded.put(info.getID(), initializer);
				} else {
					initializer = (V) loaded.get(info.getID());
				}
				
				initFunc.invoke(initializer);
			} catch (Exception e) {
				throw new ModException("Failed to load mod main class " + mainClassFunc.invoke(info), e);
			}
		}
	}
	
	protected void loadBase() throws ModException {
	    for (File file : FileUtils.MODS.listFiles()) {
	    	loadJar(file);
	    }
	    
	    if (DefaultBootstrap.MOD_JARS != null && !DefaultBootstrap.MOD_JARS.isBlank()) {
			for (String path : DefaultBootstrap.MOD_JARS.split(";")) {
				File file = new File(path);
				if (!file.exists()) continue;
				
				if (!file.isDirectory()) {
				    loadJar(file);
				    continue;
				}
				
			    for (File child : file.listFiles()) {
			    	loadJar(child);
			    }
			}
	    }
		
	    if (DefaultBootstrap.MOD_CLASSES != null && !DefaultBootstrap.MOD_CLASSES.isBlank()) {
			for (String path : DefaultBootstrap.MOD_CLASSES.split(";")) {
	            File file = new File(path);
	            if (!file.exists()) continue;
	            
				loadClasses(file);
			}
	    }
	}
	
	protected void loadJar(File file) throws ModException {
		if (file.isDirectory() || !file.getName().endsWith(".jar")) return;
    	
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
			
			if (modInfos.containsKey(info.getID())) {
				throw new ModException("Duplicate mod ID " + info.getID());
			}
			
			if (!Bootstrap.INSTANCE.loader.addJar(file)) {
				throw new IOException("Failed to load jar " + file.getName());
			}
			
			modInfos.put(info.getID(), info);
		} catch (Exception e) {
			throw new ModException("Failed to load mod " + file.getName(), e);
		}
	}
	
	protected void loadClasses(File dir) throws ModException {
		if (!dir.isDirectory()) return;
    	
    	try {
			File modJson = new File(dir, "mod.json");
			
			if (modJson.exists()) {
				JSONObject json;
				try (InputStream in = new FileInputStream(modJson)) {
					json = JSON.object(new String(in.readAllBytes()));
				}
				
				ModInfo info = ModInfo.of(json);
				if (info.getID() == null) {
					throw new ModException("Mod " + dir.getName() + " is missing ID");
				}
				if (info.getClientMain() == null && info.getServerMain() == null) {
					throw new ModException("Mod " + dir.getName() + " is missing main class");
				}
				
				if (modInfos.containsKey(info.getID())) {
					throw new ModException("Duplicate mod ID " + info.getID());
				}
				
				modInfos.put(info.getID(), info);
			}
			
			if (!Bootstrap.INSTANCE.loader.addFolder(dir)) {
				throw new IOException("Failed to load folder " + dir.getName());
			}
		} catch (Exception e) {
			throw new ModException("Failed to load mod " + dir.getName(), e);
		}
	}
}
