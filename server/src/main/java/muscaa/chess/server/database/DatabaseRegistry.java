package muscaa.chess.server.database;

import muscaa.chess.Chess;
import muscaa.chess.registry.Registries;
import muscaa.chess.registry.Registry;
import muscaa.chess.registry.RegistryKey;
import muscaa.chess.server.database.impl.mysql.MySQLDatabase;
import muscaa.chess.server.database.impl.sqlite.SQLiteDatabase;

public class DatabaseRegistry {
	
	public static final Registry<DatabaseValue> REG = Registries.create(Chess.NAMESPACE.path("databases"));
	
	public static final RegistryKey<DatabaseValue> SQLITE = REG.register(Chess.NAMESPACE.path("sqlite"),
			key -> new DatabaseValue(key, SQLiteDatabase::new));
	public static final RegistryKey<DatabaseValue> MYSQL = REG.register(Chess.NAMESPACE.path("mysql"),
			key -> new DatabaseValue(key, MySQLDatabase::new));
	
	public static void init() {
		REG.init();
	}
}
