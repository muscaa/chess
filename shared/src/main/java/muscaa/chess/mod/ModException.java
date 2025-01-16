package muscaa.chess.mod;

public class ModException extends Exception {
	
	private static final long serialVersionUID = -4120712714781037906L;
	
	public ModException() {
        super();
    }
	
    public ModException(String message) {
        super(message);
    }
    
    public ModException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ModException(Throwable cause) {
        super(cause);
    }
}
