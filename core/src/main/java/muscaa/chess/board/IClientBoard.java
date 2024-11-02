package muscaa.chess.board;

import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.IBoard;

public interface IClientBoard extends IBoard<ClientChessPiece> {
	
	ChessColor getColor();
}
