package muscaa.chess.shared.board.piece.pieces;

import java.util.Map;

import muscaa.chess.shared.board.Cell;
import muscaa.chess.shared.board.Team;
import muscaa.chess.shared.board.matrix.Matrix;
import muscaa.chess.shared.board.piece.AbstractPiece;
import muscaa.chess.shared.board.piece.PieceEntry;
import muscaa.chess.shared.board.piece.PieceUtils;
import muscaa.chess.shared.board.piece.move.AbstractMove;
import muscaa.chess.shared.registry.registries.TeamRegistry;

public class PawnPiece extends AbstractPiece {
	
	public PawnPiece(PieceEntry<PawnPiece> regEntry, Team team) {
		super(regEntry, team);
	}
	
	@Override
	public void findMoves(Map<Cell, AbstractMove> moves, Matrix matrix, Cell from) {
		Cell direction = team == TeamRegistry.WHITE ? new Cell(0, -1) : new Cell(0, 1);
		
		if (totalMoves == 0) {
			Cell twoSteps = from.copy().add(direction.copy().multiply(2));
			PieceUtils.basicPromote(this, moves, matrix, twoSteps);
		}
		
		Cell oneStep = from.copy().add(direction);
		PieceUtils.basicPromote(this, moves, matrix, oneStep);
		
		Cell left = from.copy().add(direction).add(new Cell(-1, 0));
		PieceUtils.capturePromote(this, moves, matrix, left);
		
		Cell right = from.copy().add(direction).add(new Cell(1, 0));
		PieceUtils.capturePromote(this, moves, matrix, right);
	}
}
