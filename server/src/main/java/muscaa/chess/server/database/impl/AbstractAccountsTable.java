package muscaa.chess.server.database.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;

import muscaa.chess.server.database.JDBCDatabase;
import muscaa.chess.server.database.JDBCDatabaseTable;
import muscaa.chess.server.hash.HashAlgorithmRegistry;

public abstract class AbstractAccountsTable<V extends JDBCDatabase> extends JDBCDatabaseTable<V> implements IAccountsTable {
	
	@Override
	protected void init() throws Exception {
		try (Statement s = connection.createStatement()) {
			s.execute("""
					CREATE TABLE IF NOT EXISTS accounts (
					    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
					    name VARCHAR(50) NOT NULL UNIQUE,
					    hash_algorithm VARCHAR(20) NOT NULL,
					    password_hash VARCHAR(256) NOT NULL,
					    display_name VARCHAR(50) NOT NULL,
					    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
					)
					""");
		}
	}
	
	@Override
	public void addAccount(String name, String password, String displayName) throws Exception {
		try (PreparedStatement ps = connection.prepareStatement("""
				INSERT INTO accounts (name, hash_algorithm, password_hash, display_name)
				VALUES (?, ?, ?, ?)
				""")) {
			ps.setString(1, name);
			ps.setString(2, HashAlgorithmRegistry.ARGON2.getID().toString());
			ps.setString(3, HashAlgorithmRegistry.ARGON2.get().hash(password));
			ps.setString(4, displayName);
			
			ps.executeUpdate();
		}
	}
}
