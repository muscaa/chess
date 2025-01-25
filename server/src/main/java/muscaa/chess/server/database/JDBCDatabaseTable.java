package muscaa.chess.server.database;

import java.sql.Connection;

public abstract class JDBCDatabaseTable<V extends JDBCDatabase> extends AbstractDatabaseTable<V> {
	
	protected Connection connection;
	
	@Override
	public void init(AbstractDatabase database) throws Exception {
		this.connection = ((V) database).getConnection();
		
		super.init(database);
	}
}
