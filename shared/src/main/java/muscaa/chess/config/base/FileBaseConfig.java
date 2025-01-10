package muscaa.chess.config.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class FileBaseConfig<C> extends BaseConfig<C> {
	
	protected File file;
	
	public FileBaseConfig(File file) {
		this.file = file;
	}
	
	protected abstract void load(FileInputStream in) throws IOException;
	
	protected abstract void save(FileOutputStream out) throws IOException;
	
	@Override
	public void load() {
		if (!file.exists()) {
			save();
			return;
		}
		
		try (FileInputStream in = new FileInputStream(file)) {
			load(in);
		} catch (IOException e) {}
	}
	
	@Override
	public void save() {
		try (FileOutputStream out = new FileOutputStream(file)) {
			save(out);
		} catch (IOException e) {}
	}
}
