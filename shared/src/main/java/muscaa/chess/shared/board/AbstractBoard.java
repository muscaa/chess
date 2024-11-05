package muscaa.chess.shared.board;

public abstract class AbstractBoard<P extends IChessPiece> {
	
	protected ChessPieceMatrix<P> matrix;
	
	public abstract void click(ChessCell cell);
	
	public ChessPieceMatrix<P> getMatrix() {
		return matrix;
	}
}
