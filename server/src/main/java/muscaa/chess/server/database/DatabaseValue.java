package muscaa.chess.server.database;

import fluff.functions.gen.Func;
import muscaa.chess.registry.IRegistryValue;
import muscaa.chess.registry.RegistryKey;

public class DatabaseValue implements IRegistryValue<DatabaseValue> {
	
	private final RegistryKey<DatabaseValue> key;
	private final Func<AbstractDatabase> func;
	
	public DatabaseValue(RegistryKey<DatabaseValue> key, Func<AbstractDatabase> func) {
		this.key = key;
		this.func = func;
	}
	
	public AbstractDatabase create() {
		return func.invoke();
	}
	
	@Override
	public RegistryKey<DatabaseValue> getKey() {
		return key;
	}
}
