package muscaa.chess.server.database.impl;

import muscaa.chess.server.database.IDatabaseTable;

public interface IAccountsTable extends IDatabaseTable {
	
	void addAccount(String name, String passwordHash, String displayName) throws Exception;
}
