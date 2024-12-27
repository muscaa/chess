package muscaa.chess.client.bootstrap;

import java.io.File;
import java.lang.reflect.Method;

import fluff.loader.RuntimeClassLoader;

public class DefaultBootstrap extends Bootstrap {
	
	public DefaultBootstrap() throws Exception {
		super(
				new File(DefaultBootstrap.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile(),
				new RuntimeClassLoader(DefaultBootstrap.class.getClassLoader())
				);
	}
	
	@Override
	public void launch(String[] args) throws Exception {
		File libsDir = new File(dir, "libs");
		
		if (libsDir.exists()) {
			for (File lib : libsDir.listFiles()) {
				if (lib.isDirectory() || !lib.getName().endsWith(".jar")) continue;
				
				System.out.println("Loading " + lib.getName());
				loader.addJar(lib);
			}
		}
		
		String libJars = System.getProperty("lib.jars");
		if (libJars != null) {
			for (String jar : libJars.split(";")) {
				File file = new File(jar);
				
				if (file.isDirectory()) {
					for (File lib : file.listFiles()) {
						if (lib.isDirectory() || !lib.getName().endsWith(".jar")) continue;
						
						loader.addJar(lib);
					}
				} else {
					loader.addJar(file);
				}
			}
		}
		
		String libClasses = System.getProperty("lib.classes");
		if (libClasses != null) {
			for (String classes : libClasses.split(";")) {
				File file = new File(classes);
				
				if (file.isDirectory()) {
					loader.addFolder(file);
				}
			}
		}
		
		Class<?> mainClass = loader.loadClass("muscaa.chess.client.lwjgl.Lwjgl3Launcher");
		Method main = mainClass.getDeclaredMethod("main", String[].class);
		main.setAccessible(true);
		main.invoke(main, (Object) args);
	}
}
