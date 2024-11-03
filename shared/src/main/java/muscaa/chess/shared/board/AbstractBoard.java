package muscaa.chess.shared.board;

import java.util.List;

public abstract class AbstractBoard<P extends IChessPiece> {
	
	protected ChessPieceMatrix<P> matrix;
	
	public abstract void click(ChessCell cell);
	
	public abstract List<ChessCell> getMoves(ChessCell cell);
	
	public ChessPieceMatrix<P> getMatrix() {
		return matrix;
	}
}
