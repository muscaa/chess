package muscaa.chess.shared.board.piece.pieces;

import java.util.Map;

import muscaa.chess.shared.board.Cell;
import muscaa.chess.shared.board.Team;
import muscaa.chess.shared.board.matrix.Matrix;
import muscaa.chess.shared.board.piece.AbstractPiece;
import muscaa.chess.shared.board.piece.PieceEntry;
import muscaa.chess.shared.board.piece.PieceUtils;
import muscaa.chess.shared.board.piece.move.AbstractMove;

public class KnightPiece extends AbstractPiece {
	
	public KnightPiece(PieceEntry<KnightPiece> regEntry, Team team) {
		super(regEntry, team);
	}
	
	@Override
	public void findMoves(Map<Cell, AbstractMove> moves, Matrix matrix, Cell from) {
		Cell[] offsets = {
				// left top
				new Cell(-1, -2),
				new Cell(-2, -1),
				
				// left bottom
				new Cell(-1, 2),
				new Cell(-2, 1),
				
				// right bot
				new Cell(1, 2),
				new Cell(2, 1),
				
				// right top
				new Cell(1, -2),
				new Cell(2, -1)
		};
		
		for (Cell off : offsets) {
			Cell cell = from.copy().add(off);
			
			PieceUtils.basicCapture(this, moves, matrix, cell);
		}
	}
}
