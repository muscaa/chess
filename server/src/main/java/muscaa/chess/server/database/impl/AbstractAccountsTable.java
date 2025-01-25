package muscaa.chess.server.database.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;

import muscaa.chess.server.database.JDBCDatabase;
import muscaa.chess.server.database.JDBCDatabaseTable;

public abstract class AbstractAccountsTable<V extends JDBCDatabase> extends JDBCDatabaseTable<V> implements IAccountsTable {
	
	@Override
	protected void init() throws Exception {
		try (Statement s = connection.createStatement()) {
			s.execute("""
					CREATE TABLE IF NOT EXISTS accounts (
					    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
					    name VARCHAR(50) NOT NULL UNIQUE,
					    password_hash VARCHAR(256) NOT NULL,
					    display_name VARCHAR(50) NOT NULL,
					    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
					)
					""");
		}
	}
	
	@Override
	public void addAccount(String name, String passwordHash, String displayName) throws Exception {
		try (PreparedStatement ps = connection.prepareStatement("""
				INSERT INTO accounts (name, password_hash, display_name)
				VALUES (?, ?, ?)
				""")) {
			ps.setString(1, name);
			ps.setString(2, passwordHash);
			ps.setString(3, displayName);
			
			ps.executeUpdate();
		}
	}
}
