package muscaa.chess.server.database;

import java.util.HashMap;
import java.util.Map;

import fluff.functions.gen.Func;

public abstract class DatabaseTables {
	
	private final Map<Class<? extends IDatabaseTable>, Map<Class<? extends AbstractDatabase>, Func<? extends IDatabaseTable>>> reg = new HashMap<>();
	
	public abstract void init(AbstractDatabase database);
	
	public <V extends IDatabaseTable> V create(AbstractDatabase database, Class<? extends IDatabaseTable> tableClass) {
		if (!reg.containsKey(tableClass)) {
			throw new IllegalArgumentException("No such table: " + tableClass.getName());
		}
		
		Map<Class<? extends AbstractDatabase>, Func<? extends IDatabaseTable>> map = reg.get(tableClass);
		if (!map.containsKey(database.getClass())) {
			throw new IllegalArgumentException("No such table: " + tableClass + " for database: " + database.getClass().getName());
		}
		
		V table = (V) map.get(database.getClass()).invoke();
		try {
			table.init(database);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return table;
	}
	
	public void register(Class<? extends IDatabaseTable> tableClass, Class<? extends AbstractDatabase> dbClass, Func<? extends IDatabaseTable> tableFunc) {
		Map<Class<? extends AbstractDatabase>, Func<? extends IDatabaseTable>> map = getOrCreate(tableClass);
		
		map.put(dbClass, tableFunc);
	}
	
	public void register(Class<? extends IDatabaseTable> tableClass, DatabaseTableEntry<?>... entries) {
		Map<Class<? extends AbstractDatabase>, Func<? extends IDatabaseTable>> map = getOrCreate(tableClass);
		
		for (DatabaseTableEntry<?> entry : entries) {
			map.put(entry.dbClass, entry.tableFunc);
        }
	}
	
	protected Map<Class<? extends AbstractDatabase>, Func<? extends IDatabaseTable>> getOrCreate(Class<? extends IDatabaseTable> tableClass) {
		if (reg.containsKey(tableClass)) return reg.get(tableClass);
		
		Map<Class<? extends AbstractDatabase>, Func<? extends IDatabaseTable>> map = new HashMap<>();
		reg.put(tableClass, map);
		return map;
	}
	
	public static <V extends AbstractDatabase> DatabaseTableEntry<V> entry(Class<V> dbClass, Func<? extends AbstractDatabaseTable<V>> tableFunc) {
		return new DatabaseTableEntry<>(dbClass, tableFunc);
	}
}
