package muscaa.chess.server.database;

public abstract class AbstractDatabaseTable<V extends AbstractDatabase> implements IDatabaseTable {
	
	protected V database;
	
	protected abstract void init() throws Exception;
	
	@Override
	public void init(AbstractDatabase database) throws Exception {
		this.database = (V) database;
		
        init();
	}
}
