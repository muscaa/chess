package muscaa.chess.shared.board;

import fluff.vecmath.gen._int.vector.Vec2i;

public interface IBoard<P extends IChessPiece> {
	
	int SIZE = 8;
	
	P getPiece(Vec2i cell);
}
