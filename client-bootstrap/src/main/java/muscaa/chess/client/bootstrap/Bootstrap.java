package muscaa.chess.client.bootstrap;

import java.io.File;

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
}
