package muscaa.chess.server.database;

import java.sql.Connection;

public abstract class JDBCDatabase extends AbstractDatabase {
	
	protected Connection connection;
	
	protected abstract Connection createConnection() throws Exception;
	
	public Connection getConnection() {
		return connection;
	}
	
	@Override
	public void open() {
		try {
			connection = createConnection();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void close() {
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
