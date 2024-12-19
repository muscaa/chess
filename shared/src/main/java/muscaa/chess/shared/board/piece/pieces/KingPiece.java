package muscaa.chess.shared.board.piece.pieces;

import java.util.Map;

import muscaa.chess.shared.board.Cell;
import muscaa.chess.shared.board.Team;
import muscaa.chess.shared.board.matrix.Matrix;
import muscaa.chess.shared.board.piece.AbstractPiece;
import muscaa.chess.shared.board.piece.PieceEntry;
import muscaa.chess.shared.board.piece.PieceUtils;
import muscaa.chess.shared.board.piece.move.AbstractMove;

public class KingPiece extends AbstractPiece {
	
	public KingPiece(PieceEntry<KingPiece> regEntry, Team team) {
		super(regEntry, team);
	}
	
	@Override
	public void findMoves(Map<Cell, AbstractMove> moves, Matrix matrix, Cell from) {
		Cell[] offsets = {
				// left
				new Cell(-1, -1),
				new Cell(-1, 0),
				new Cell(-1, 1),
				
				// middle
				new Cell(0, -1),
				new Cell(0, 1),
				
				// right
				new Cell(1, -1),
				new Cell(1, 0),
				new Cell(1, 1)
		};
		
		for (Cell off : offsets) {
			Cell cell = from.copy().add(off);
			
			PieceUtils.basicCapture(this, moves, matrix, cell);
		}
		
		// add castling
	}
	
	@Override
	public boolean isCheckable() {
		return true;
	}
}
