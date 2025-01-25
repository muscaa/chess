package muscaa.chess.server.database.impl.mysql;

import java.sql.Connection;
import java.sql.DriverManager;

import muscaa.chess.server.Server;
import muscaa.chess.server.config.SettingsConfig;
import muscaa.chess.server.database.JDBCDatabase;

public class MySQLDatabase extends JDBCDatabase {
	
	public MySQLDatabase() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected Connection createConnection() throws Exception {
		SettingsConfig config = Server.INSTANCE.settingsConfig;
		
		if (!config.databaseUsername.isSet() || !config.databasePassword.isSet()) {
			return DriverManager.getConnection(config.databaseUrl.get());
		}
		
		return DriverManager.getConnection(config.databaseUrl.get(), config.databaseUsername.get(), config.databasePassword.get());
	}
}
