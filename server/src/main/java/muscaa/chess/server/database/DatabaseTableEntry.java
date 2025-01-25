package muscaa.chess.server.database;

import fluff.functions.gen.Func;

public class DatabaseTableEntry<V extends AbstractDatabase> {
	
	public final Class<V> dbClass;
	public final Func<? extends AbstractDatabaseTable<V>> tableFunc;
	
	DatabaseTableEntry(Class<V> dbClass, Func<? extends AbstractDatabaseTable<V>> tableFunc) {
        this.dbClass = dbClass;
        this.tableFunc = tableFunc;
	}
}
