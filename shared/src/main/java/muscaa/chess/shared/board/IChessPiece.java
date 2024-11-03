package muscaa.chess.shared.board;

public interface IChessPiece {
	
	int getID();
	
	ChessColor getColor();
	
	IChessPiece copy();
}
