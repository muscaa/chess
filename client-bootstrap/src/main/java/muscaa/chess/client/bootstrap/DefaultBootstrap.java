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
		File libs = new File(dir, "libs");
		
		for (File lib : libs.listFiles()) {
			if (lib.isDirectory() || !lib.getName().endsWith(".jar")) continue;
			
			loader.addJar(lib);
		}
		
		Class<?> mainClass = loader.loadClass("muscaa.chess.client.lwjgl.Lwjgl3Launcher");
		Method main = mainClass.getDeclaredMethod("main", String[].class);
		main.setAccessible(true);
		main.invoke(main, (Object) args);
	}
}
