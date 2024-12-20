package muscaa.chess.registry;

public class RegistryException extends RuntimeException {
	
	private static final long serialVersionUID = -4420448969997088977L;
	
	public RegistryException() {
        super();
    }
	
    public RegistryException(String message) {
        super(message);
    }
    
    public RegistryException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public RegistryException(Throwable cause) {
        super(cause);
    }
}
