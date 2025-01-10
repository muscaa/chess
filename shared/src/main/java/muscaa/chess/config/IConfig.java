package muscaa.chess.config;

public interface IConfig {
	
	<T extends IValue<T>> T value(T value);
	
	void load();
	
	void save();
}
