package muscaa.chess.server.database.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import muscaa.chess.server.database.IDatabaseTable;

public interface IAccountsTable extends IDatabaseTable {
	
	record Account(UUID uuid, String name, String hashAlgorithm, String passwordHash, String displayName, LocalDateTime createdAt) {}
	
	Account get(UUID uuid) throws Exception;
	
	Account register(String name, String password, String repeatPassword, String displayName) throws Exception;
	
	Account login(String name, String password) throws Exception;
}
