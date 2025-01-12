package muscaa.chess.board.piece.pieces;

import java.util.Map;

import muscaa.chess.board.Cell;
import muscaa.chess.board.TeamValue;
import muscaa.chess.board.matrix.ServerMatrix;
import muscaa.chess.board.piece.AbstractServerPiece;
import muscaa.chess.board.piece.ServerPieceRegistry;
import muscaa.chess.board.piece.PieceUtils;
import muscaa.chess.board.piece.move.AbstractMoveValue;

public class KnightPiece extends AbstractServerPiece {
	
	public KnightPiece(TeamValue team) {
		super(ServerPieceRegistry.KNIGHT.get(), team);
	}
	
	@Override
	public void findMoves(Map<Cell, AbstractMoveValue> moves, ServerMatrix matrix, Cell from) {
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
			Cell cell = from.add(off);
			
			PieceUtils.basicCapture(this, moves, matrix, cell);
		}
	}
}
