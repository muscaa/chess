package muscaa.chess.server.database.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;

import muscaa.chess.server.database.JDBCDatabase;
import muscaa.chess.server.database.JDBCDatabaseTable;
import muscaa.chess.server.hash.HashAlgorithmRegistry;
import muscaa.chess.server.hash.HashAlgorithmValue;
import muscaa.chess.utils.NamespacePath;
import muscaa.chess.utils.UUIDUtils;

public abstract class AbstractAccountsTable<V extends JDBCDatabase> extends JDBCDatabaseTable<V> implements IAccountsTable {
	
	@Override
	protected void init() throws Exception {
		try (Statement s = connection.createStatement()) {
			s.execute("""
					CREATE TABLE IF NOT EXISTS accounts (
					    uuid BINARY(16) PRIMARY KEY,
					    name VARCHAR(50) NOT NULL UNIQUE,
					    hash_algorithm VARCHAR(20) NOT NULL,
					    password_hash VARCHAR(255) NOT NULL,
					    display_name VARCHAR(50) NOT NULL,
					    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
					)
					""");
		}
	}
	
	@Override
	public Account get(UUID uuid) throws Exception {
		try (PreparedStatement ps = connection.prepareStatement("""
				SELECT *
				FROM accounts
				WHERE uuid = ?
				""")) {
			ps.setBytes(1, UUIDUtils.toBytes(uuid));
			
			try (ResultSet set = ps.executeQuery()) {
				if (set.next()) {
					return new Account(
							UUIDUtils.fromBytes(set.getBytes("uuid")),
							set.getString("name"),
							set.getString("hash_algorithm"),
							set.getString("password_hash"),
							set.getString("display_name"),
							set.getTimestamp("created_at").toLocalDateTime()
							);
				}
			}
		}
		
		return null;
	}
	
	@Override
	public Account register(String name, String password, String repeatPassword, String displayName) throws Exception {
		if (name.length() < 3 || name.length() > 50) {
			throw new Exception("Name must be between 3 and 50 characters!");
		}
		if (password.length() < 8 || password.length() > 50) {
			throw new Exception("Password must be between 8 and 50 characters!");
		}
		if (!password.equals(repeatPassword)) {
			throw new Exception("Passwords do not match!");
		}
		if (displayName.length() < 3 || displayName.length() > 50) {
			throw new Exception("Display name must be between 3 and 50 characters!");
		}
		
		UUID uuid = UUID.nameUUIDFromBytes(name.getBytes());
		if (get(uuid) != null) {
			throw new Exception("Account already exists!");
		}
		
		try (PreparedStatement ps = connection.prepareStatement("""
				INSERT INTO accounts (uuid, name, hash_algorithm, password_hash, display_name)
				VALUES (?, ?, ?, ?, ?)
				""")) {
			ps.setBytes(1, UUIDUtils.toBytes(uuid));
			ps.setString(2, name);
			ps.setString(3, HashAlgorithmRegistry.ARGON2.getID().toString());
			ps.setString(4, HashAlgorithmRegistry.ARGON2.get().hash(password));
			ps.setString(5, displayName);
			
			int rows = ps.executeUpdate();
			if (rows == 0) {
				throw new Exception("Failed to register account!");
			}
		}
		
		return get(uuid);
	}
	
	@Override
	public Account login(String name, String password) throws Exception {
		if (name.length() < 3 || name.length() > 50) {
			throw new Exception("Name must be between 3 and 50 characters!");
		}
		if (password.length() < 8 || password.length() > 50) {
			throw new Exception("Password must be between 8 and 50 characters!");
		}
		
		UUID uuid = UUID.nameUUIDFromBytes(name.getBytes());
		Account account = get(uuid);
		if (account == null) {
			throw new Exception("Account not found!");
		}
		
		HashAlgorithmValue hashAlgorithm = HashAlgorithmRegistry.REG.get(NamespacePath.of(account.hashAlgorithm())).get();
		if (!hashAlgorithm.verify(account.passwordHash(), password)) {
			throw new Exception("Invalid password!");
		}
		
		return account;
	}
}
