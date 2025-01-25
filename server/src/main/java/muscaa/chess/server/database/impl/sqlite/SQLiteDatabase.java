package muscaa.chess.server.database.impl.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;

import muscaa.chess.server.Server;
import muscaa.chess.server.config.SettingsConfig;
import muscaa.chess.server.database.JDBCDatabase;

public class SQLiteDatabase extends JDBCDatabase {
	
	public SQLiteDatabase() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected Connection createConnection() throws Exception {
		SettingsConfig config = Server.INSTANCE.settingsConfig;
		
		return DriverManager.getConnection(config.databaseUrl.get());
	}
}
