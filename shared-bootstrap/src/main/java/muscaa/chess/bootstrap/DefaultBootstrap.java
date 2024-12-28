package muscaa.chess.bootstrap;

import java.io.File;
import java.lang.reflect.Method;

import fluff.loader.RuntimeClassLoader;

public class DefaultBootstrap extends Bootstrap {
	
	public static final String LIB_JARS = System.getProperty("lib.jars");
	public static final String LIB_CLASSES = System.getProperty("lib.classes");
	public static final String MOD_JARS = System.getProperty("mod.jars");
	public static final String MOD_CLASSES = System.getProperty("mod.classes");
	
	private final String mainClassName;
	
	public DefaultBootstrap(String mainClassName) throws Exception {
		super(
				new File(DefaultBootstrap.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile(),
				new RuntimeClassLoader(DefaultBootstrap.class.getClassLoader())
				);
		this.mainClassName = mainClassName;
	}
	
	@Override
	public void launch(String[] args) throws Exception {
		getLibs(loader::addJar, new File(dir, "libs"));
		getJars(loader::addJar, LIB_JARS);
		getClasses(loader::addFolder, LIB_CLASSES);
		
		Class<?> mainClass = loader.loadClass(mainClassName);
		Method main = mainClass.getDeclaredMethod("main", String[].class);
		main.setAccessible(true);
		main.invoke(main, (Object) args);
	}
}
