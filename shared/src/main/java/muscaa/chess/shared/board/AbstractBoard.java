package muscaa.chess.shared.board;

import java.util.List;

import fluff.vecmath.gen._int.vector.Vec2i;

public abstract class AbstractBoard<P extends IChessPiece> {
	
	protected ChessPieceMatrix<P> matrix;
	
	public abstract void click(Vec2i cell);
	
	public abstract List<Vec2i> getMoves(Vec2i cell);
	
	public ChessPieceMatrix<P> getMatrix() {
		return matrix;
	}
}
