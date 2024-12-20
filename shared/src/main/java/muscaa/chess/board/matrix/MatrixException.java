package muscaa.chess.board.matrix;

public class MatrixException extends RuntimeException {
	
	private static final long serialVersionUID = 1647492894856982916L;
	
	public MatrixException() {
        super();
    }
	
    public MatrixException(String message) {
        super(message);
    }
    
    public MatrixException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public MatrixException(Throwable cause) {
        super(cause);
    }
}
