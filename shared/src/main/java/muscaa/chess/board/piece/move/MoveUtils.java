package muscaa.chess.board.piece.move;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import muscaa.chess.board.Cell;
import muscaa.chess.board.TeamValue;
import muscaa.chess.board.matrix.ServerMatrix;
import muscaa.chess.board.piece.AbstractServerPiece;
import muscaa.chess.board.piece.move.moves.CaptureMove;

public class MoveUtils {
	
	public static void findAllMoves(ServerMatrix matrix, Map<Cell, Map<Cell, AbstractMoveValue>> allMoves) {
		allMoves.clear();
		
		for (Cell from : matrix) {
			AbstractServerPiece piece = matrix.get(from);
			
			Map<Cell, AbstractMoveValue> moves = new HashMap<>();
			piece.findMoves(moves, matrix, from);
			
			Map<Cell, AbstractMoveValue> validMoves = new HashMap<>();
			for (Map.Entry<Cell, AbstractMoveValue> moveEntry : moves.entrySet()) {
				Cell to = moveEntry.getKey();
				AbstractMoveValue move = moveEntry.getValue();
				
				matrix.begin();
				move.doMove(matrix, from, to);
				matrix.end();
				
				boolean inCheck = !getInCheck(matrix, piece.getTeam()).isEmpty();
				matrix.undo(1);
				
				if (inCheck) continue;
				
				validMoves.put(to, move);
			}
			
			allMoves.put(from, validMoves);
		}
	}
	
	public static List<Cell> getInCheck(ServerMatrix matrix, TeamValue team) {
		List<Cell> inCheck = new LinkedList<>();
		for (Cell opponentFrom : matrix) {
			AbstractServerPiece opponentPiece = matrix.get(opponentFrom);
			if (opponentPiece.getTeam() == team) continue;
			
			Map<Cell, AbstractMoveValue> opponentMoves = new HashMap<>();
			opponentPiece.findMoves(opponentMoves, matrix, opponentFrom);
			
			for (Cell checkable : matrix) {
				AbstractServerPiece checkablePiece = matrix.get(checkable);
				if (!checkablePiece.isCheckable()) continue;
				if (checkablePiece.getTeam() != team) continue;
				
				AbstractMoveValue opponentMove = opponentMoves.get(checkable);
				if (opponentMove instanceof CaptureMove) {
					inCheck.add(checkable);
				}
			}
		}
		return inCheck;
	}
	
	public static Map<Cell, Map<Cell, AbstractMoveValue>> getMoves(ServerMatrix matrix, Map<Cell, Map<Cell, AbstractMoveValue>> allMoves, TeamValue team) {
		Map<Cell, Map<Cell, AbstractMoveValue>> moves = new HashMap<>();
		for (Map.Entry<Cell, Map<Cell, AbstractMoveValue>> e : allMoves.entrySet()) {
			Cell from = e.getKey();
			AbstractServerPiece piece = matrix.get(from);
			if (piece.getTeam() != team) continue;
			
			moves.put(from, e.getValue());
		}
		return moves;
	}
}
