package muscaa.chess.server.database.impl;

import muscaa.chess.server.database.AbstractDatabase;
import muscaa.chess.server.database.DatabaseTables;
import muscaa.chess.server.database.impl.mysql.MySQLAccountsTable;
import muscaa.chess.server.database.impl.mysql.MySQLDatabase;
import muscaa.chess.server.database.impl.sqlite.SQLiteAccountsTable;
import muscaa.chess.server.database.impl.sqlite.SQLiteDatabase;

public class MainTables extends DatabaseTables {
	
	private IAccountsTable accounts;
	
	public MainTables() {
		register(IAccountsTable.class,
				entry(SQLiteDatabase.class, SQLiteAccountsTable::new),
				entry(MySQLDatabase.class, MySQLAccountsTable::new)
				);
	}
	
	@Override
	public void init(AbstractDatabase database) {
		accounts = create(database, IAccountsTable.class);
	}
	
	public IAccountsTable getAccounts() {
		return accounts;
	}
}
