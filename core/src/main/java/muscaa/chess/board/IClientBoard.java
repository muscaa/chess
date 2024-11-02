package muscaa.chess.board;

import fluff.vecmath.gen._int.vector.Vec2i;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.IBoard;

public interface IClientBoard extends IBoard<ClientChessPiece> {
	
	void click(Vec2i cell);
	
	void setPieces(ClientChessPiece[][] pieces);
	
	void setPiece(Vec2i cell, ClientChessPiece piece);
	
	ChessColor getColor();
}
