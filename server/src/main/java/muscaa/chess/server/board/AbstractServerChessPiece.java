package muscaa.chess.server.board;

import java.util.Map;

import fluff.functions.gen.obj.Func1;
import muscaa.chess.server.board.pieces.ServerKingChessPiece;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessMoves;
import muscaa.chess.shared.board.IChessPiece;
import muscaa.chess.shared.board.IValidator;
import muscaa.chess.shared.board.Validators;

public abstract class AbstractServerChessPiece<P extends AbstractServerChessPiece<P>> implements IChessPiece {
	
	protected final int id;
	protected final ChessColor color;
	protected final Func1<P, ChessColor> newInstanceFunc;
	
	protected int totalMoves = 0;
	
	public AbstractServerChessPiece(int id, ChessColor color, Func1<P, ChessColor> newInstanceFunc) {
		this.id = color == ChessColor.WHITE ? id : -id;
		this.color = color;
		this.newInstanceFunc = newInstanceFunc;
	}
	
	public abstract void findMoves(ChessMoves<AbstractServerChessPiece> moves, ChessCell cell);
	
	public abstract void simulateMoves(ChessMoves<AbstractServerChessPiece> moves, ChessCell cell);
	
	protected AbstractServerChessPiece onMove(ChessCell cell) {
		totalMoves++;
		
		return this;
	}
	
	@Override
	public int getID() {
		return id;
	}
	
	@Override
	public ChessColor getColor() {
		return color;
	}
	
	protected void copy(P newInstance) {
		newInstance.totalMoves = totalMoves;
	}
	
	@Override
	public IChessPiece copy() {
		P copy = newInstanceFunc.invoke(color);
		copy(copy);
		return copy;
	}
	
	public static IValidator findValidator() {
		return Validators.and(
				Validators.IN_BOUNDS,
				Validators.count(1, Map.of(
						Validators.EMPTY, 0,
						Validators.DIFFERENT_COLOR, 1
				))
		);
	}
	
	public static IValidator simulateValidator() {
		return Validators.and(
				Validators.IN_BOUNDS,
				Validators.count(1, Map.of(
						Validators.EMPTY, 0,
						Validators.when(piece -> piece instanceof ServerKingChessPiece), 0,
						Validators.NOT_EMPTY, 1
				))
		);
	}
}
