package muscaa.chess.server.board;

import fluff.functions.gen.obj.Func1;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessMoves;
import muscaa.chess.shared.board.IChessPiece;
import muscaa.chess.shared.board.IValidator;

import static muscaa.chess.shared.board.Validators.*;
import static muscaa.chess.shared.board.IPair.*;

public abstract class AbstractServerChessPiece<P extends AbstractServerChessPiece<P>> implements IChessPiece {
	
	protected final int id;
	protected final ChessColor color;
	protected final boolean checkable;
	protected final Func1<P, ChessColor> newInstanceFunc;
	
	protected int totalMoves = 0;
	
	public AbstractServerChessPiece(int id, ChessColor color, boolean checkable, Func1<P, ChessColor> newInstanceFunc) {
		this.id = color == ChessColor.WHITE ? id : -id;
		this.color = color;
		this.checkable = checkable;
		this.newInstanceFunc = newInstanceFunc;
	}
	
	public AbstractServerChessPiece(int id, ChessColor color, Func1<P, ChessColor> newInstanceFunc) {
		this(id, color, false, newInstanceFunc);
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
	
	@Override
	public boolean isCheckable() {
		return checkable;
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
		return and(
				IN_BOUNDS,
				count(1,
						of(0, EMPTY),
						of(1, DIFFERENT_COLOR)
				)
		);
	}
	
	public static IValidator simulateValidator() {
		return and(
				IN_BOUNDS,
				count(1,
						of(0, EMPTY),
						of(0, and(
								CHECKABLE,
								DIFFERENT_COLOR
								)),
						of(1, DIFFERENT_COLOR)
				)
		);
	}
}
