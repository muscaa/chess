package muscaa.chess.shared.board;

public interface IValidator<P extends IChessPiece> {
	
	ValidationResult validate(ChessMoves<P> moves, ChessCell cell);
}
