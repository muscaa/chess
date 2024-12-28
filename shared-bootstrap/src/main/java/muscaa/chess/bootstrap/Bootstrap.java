package muscaa.chess.bootstrap;

import java.io.File;
import java.util.function.Consumer;

import fluff.loader.RuntimeClassLoader;

public abstract class Bootstrap {
	
	public static Bootstrap INSTANCE;
	
	public final File dir;
	public final RuntimeClassLoader loader;
	
	public Bootstrap(File dir, RuntimeClassLoader loader) {
		this.dir = dir;
		this.loader = loader;
	}
	
	public abstract void launch(String[] args) throws Exception;
	
	protected void getLibs(Consumer<File> consumer, File libsDir) {
		if (!libsDir.exists() || !libsDir.isDirectory()) return;
		
		for (File lib : libsDir.listFiles()) {
			if (lib.isDirectory() || !lib.getName().endsWith(".jar")) continue;
			
			consumer.accept(lib);
		}
	}
	
	protected void getJars(Consumer<File> consumer, String jars) {
		if (jars == null || jars.isBlank()) return;
		
		for (String path : jars.split(";")) {
			File file = new File(path);
			if (!file.exists()) continue;
			
			if (file.isDirectory()) {
				getLibs(consumer, file);
			} else if (file.getName().endsWith(".jar")) {
				consumer.accept(file);
			}
		}
	}
	
	protected void getClasses(Consumer<File> consumer, String classes) {
		if (classes == null || classes.isBlank()) return;
		
		for (String path : classes.split(";")) {
            File file = new File(path);
            if (!file.exists()) continue;
            
			if (file.isDirectory()) {
				consumer.accept(file);
			}
		}
	}
}
