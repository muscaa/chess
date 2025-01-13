package muscaa.chess.board.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import muscaa.chess.board.Cell;
import muscaa.chess.board.TeamRegistry;
import muscaa.chess.board.TeamValue;
import muscaa.chess.board.matrix.ServerMatrix;
import muscaa.chess.board.piece.AbstractServerPiece;
import muscaa.chess.board.piece.ServerPieceRegistry;
import muscaa.chess.board.piece.move.AbstractMoveValue;
import muscaa.chess.board.piece.move.moves.CaptureMove;
import muscaa.chess.board.piece.pieces.NullPiece;

public class BotPlayer extends AbstractPlayer {
	
	protected static final double[][] pawnEvalWhite = {
			{  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0 },
			{  5.0,  5.0,  5.0,  5.0,  5.0,  5.0,  5.0,  5.0 },
			{  1.0,  1.0,  2.0,  3.0,  3.0,  2.0,  1.0,  1.0 },
			{  0.5,  0.5,  1.0,  2.5,  2.5,  1.0,  0.5,  0.5 },
			{  0.0,  0.0,  0.0,  2.0,  2.0,  0.0,  0.0,  0.0 },
			{  0.5, -0.5, -1.0,  0.0,  0.0, -1.0, -0.5,  0.5 },
			{  0.5,  1.0,  1.0, -2.0, -2.0,  1.0,  1.0,  0.5 },
			{  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0 }
	};
	protected static final double[][] pawnEvalBlack = reverseArray(pawnEvalWhite);
	protected static final double[][] knightEval = {
			{ -5.0, -4.0, -3.0, -3.0, -3.0, -3.0, -4.0, -5.0 },
			{ -4.0, -2.0,  0.0,  0.0,  0.0,  0.0, -2.0, -4.0 },
			{ -3.0,  0.0,  1.0,  1.5,  1.5,  1.0,  0.0, -3.0 },
			{ -3.0,  0.5,  1.5,  2.0,  2.0,  1.5,  0.5, -3.0 },
			{ -3.0,  0.0,  1.5,  2.0,  2.0,  1.5,  0.0, -3.0 },
			{ -3.0,  0.5,  1.0,  1.5,  1.5,  1.0,  0.5, -3.0 },
			{ -4.0, -2.0,  0.0,  0.5,  0.5,  0.0, -2.0, -4.0 },
			{ -5.0, -4.0, -3.0, -3.0, -3.0, -3.0, -4.0, -5.0 }
	};
	protected static final double[][] bishopEvalWhite = {
			{ -2.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -2.0 },
			{ -1.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -1.0 },
			{ -1.0,  0.0,  0.5,  1.0,  1.0,  0.5,  0.0, -1.0 },
			{ -1.0,  0.5,  0.5,  1.0,  1.0,  0.5,  0.5, -1.0 },
			{ -1.0,  0.0,  1.0,  1.0,  1.0,  1.0,  0.0, -1.0 },
			{ -1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0, -1.0 },
			{ -1.0,  0.5,  0.0,  0.0,  0.0,  0.0,  0.5, -1.0 },
			{ -2.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -2.0 }
	};
	protected static final double[][] bishopEvalBlack = reverseArray(bishopEvalWhite);
	protected static final double[][] rookEvalWhite = {
			{  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0 },
			{  0.5,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  0.5 },
			{ -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5 },
			{ -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5 },
			{ -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5 },
			{ -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5 },
			{ -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5 },
			{  0.0,   0.0, 0.0,  0.5,  0.5,  0.0,  0.0,  0.0 }
	};
	protected static final double[][] rookEvalBlack = reverseArray(rookEvalWhite);
	protected static final double[][] evalQueen = {
			{ -2.0, -1.0, -1.0, -0.5, -0.5, -1.0, -1.0, -2.0 },
			{ -1.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -1.0 },
			{ -1.0,  0.0,  0.5,  0.5,  0.5,  0.5,  0.0, -1.0 },
			{ -0.5,  0.0,  0.5,  0.5,  0.5,  0.5,  0.0, -0.5 },
			{  0.0,  0.0,  0.5,  0.5,  0.5,  0.5,  0.0, -0.5 },
			{ -1.0,  0.5,  0.5,  0.5,  0.5,  0.5,  0.0, -1.0 },
			{ -1.0,  0.0,  0.5,  0.0,  0.0,  0.0,  0.0, -1.0 },
			{ -2.0, -1.0, -1.0, -0.5, -0.5, -1.0, -1.0, -2.0 }
	};
	protected static final double[][] kingEvalWhite = {
			{ -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0 },
			{ -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0 },
			{ -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0 },
			{ -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0 },
			{ -2.0, -3.0, -3.0, -4.0, -4.0, -3.0, -3.0, -2.0 },
			{ -1.0, -2.0, -2.0, -2.0, -2.0, -2.0, -2.0, -1.0 },
			{  2.0,  2.0,  0.0,  0.0,  0.0,  0.0,  2.0,  2.0 },
			{  2.0,  3.0,  1.0,  0.0,  0.0,  1.0,  3.0,  2.0 }
	};
	protected static final double[][] kingEvalBlack = reverseArray(kingEvalWhite);
	
	protected static double[][] reverseArray(double[][] array) {
		double[][] copy = new double[array.length][array.length];

		for (int i = 0; i < array.length; i++) {
			copy[i] = array[array.length - 1 - i];
		}

		return copy;
	}
	
	protected final ExecutorService executor;
	
	public BotPlayer() {
		executor = Executors.newSingleThreadExecutor();
	}
	
	@Override
	public void startGame() {
		executor.execute(this::doMove);
	}
	
	@Override
	public void updateTurn(TeamValue turn) {
		executor.execute(this::doMove);
	}
	
	@Override
	public void updateBoard(ServerMatrix matrix) {}
	
	@Override
	public void endGame(TeamValue winner) {
		executor.shutdown();
	}
	
	protected void doMove() {
		if (lobby.turn != team) return;
		
		MoveEntry bestMove = minimaxRoot(lobby.matrix, 3, true);
		if (bestMove != null) {
			lobby.doMove(bestMove.move, bestMove.from, bestMove.to);
			return;
		}
		
		// fallback
		System.out.println("Random move");
		
		Map.Entry<Cell, Map<Cell, AbstractMoveValue>> from = getRandomFrom(lobby.matrix, lobby.allMoves);
		if (from == null) {
			System.out.println("No moves available from");
			lobby.endGame(team.invert());
			return;
		}
		
		Map.Entry<Cell, AbstractMoveValue> to = getRandomTo(lobby.matrix, from.getValue());
		if (to == null) {
			System.out.println("No moves available to");
			lobby.endGame(team.invert());
			return;
		}
		
		Cell fromCell = from.getKey();
		Cell toCell = to.getKey();
		
		click(fromCell);
		click(toCell);
	}
	
	protected Map.Entry<Cell, Map<Cell, AbstractMoveValue>> getRandomFrom(ServerMatrix matrix, Map<Cell, Map<Cell, AbstractMoveValue>> allMoves) {
		List<Map.Entry<Cell, Map<Cell, AbstractMoveValue>>> list = new ArrayList<>(allMoves.size());
		
		for (Map.Entry<Cell, Map<Cell, AbstractMoveValue>> e : allMoves.entrySet()) {
			Cell cell = e.getKey();
			Map<Cell, AbstractMoveValue> moves = e.getValue();
			if (moves.isEmpty()) continue;
			
			AbstractServerPiece piece = matrix.get(cell);
			
			if (piece.getTeam() == team) {
				list.add(e);
			}
		}
		
		if (!list.isEmpty()) {
			Random random = new Random();
			int index = random.nextInt(list.size());
			
			return list.get(index);
		}
		
		return null;
	}
	
	protected Map.Entry<Cell, AbstractMoveValue> getRandomTo(ServerMatrix matrix, Map<Cell, AbstractMoveValue> from) {
		List<Map.Entry<Cell, AbstractMoveValue>> list = new ArrayList<>(from.size());
		
		for (Map.Entry<Cell, AbstractMoveValue> e : from.entrySet()) {
			list.add(e);
		}
		
		if (!list.isEmpty()) {
			Random random = new Random();
			int index = random.nextInt(list.size());
			
			return list.get(index);
		}
		
		return null;
	}
	
	protected MoveEntry minimaxRoot(ServerMatrix matrix, int depth, boolean isMaximisingPlayer) {
		Map<Cell, Map<Cell, AbstractMoveValue>> myMoves = findMyMoves(matrix);
		MoveEntry bestMoveFound = null;
		double bestMove = -9999;
		
		if (myMoves.isEmpty()) {
			System.out.println("No moves available?");
		}
		
		for (Map.Entry<Cell, Map<Cell, AbstractMoveValue>> fromEntry : myMoves.entrySet()) {
			Cell from = fromEntry.getKey();
			Map<Cell, AbstractMoveValue> moves = fromEntry.getValue();
			
			for (Map.Entry<Cell, AbstractMoveValue> toEntry : moves.entrySet()) {
				Cell to = toEntry.getKey();
				AbstractMoveValue move = toEntry.getValue();
				
				matrix.begin();
				move.doMove(matrix, from, to);
				matrix.end();
				
				double value = minimax(matrix, depth - 1, -10000, 10000, !isMaximisingPlayer);
				matrix.undo(1);
				
				if (value >= bestMove) {
					bestMove = value;
					bestMoveFound = new MoveEntry(from, to, move);
					
					// TODO: randomize equal best moves
				}
			}
		}
		
		return bestMoveFound;
	}
	
	public static class MoveEntry {
		public Cell from;
		public Cell to;
		public AbstractMoveValue move;
		
		public MoveEntry(Cell from, Cell to, AbstractMoveValue move) {
			this.from = from;
			this.to = to;
			this.move = move;
		}
	}
	
	protected double minimax(ServerMatrix matrix, int depth, double alpha, double beta, boolean isMaximisingPlayer) {
		if (depth <= 0) {
			return -evaluateBoard(matrix);
		}
		
		Map<Cell, Map<Cell, AbstractMoveValue>> myMoves = findMyMoves(matrix);
		if (myMoves.isEmpty()) {
			System.out.println("No moves available???");
		}
		
		// TODO: stop sacrificing pieces
		
		if (isMaximisingPlayer) {
			double bestMove = -9999;
			
			for (Map.Entry<Cell, Map<Cell, AbstractMoveValue>> fromEntry : myMoves.entrySet()) {
				Cell from = fromEntry.getKey();
				Map<Cell, AbstractMoveValue> moves = fromEntry.getValue();
				
				for (Map.Entry<Cell, AbstractMoveValue> toEntry : moves.entrySet()) {
					Cell to = toEntry.getKey();
					AbstractMoveValue move = toEntry.getValue();
					
					matrix.begin();
					move.doMove(matrix, from, to);
					matrix.end();
					
					bestMove = Math.max(bestMove, minimax(matrix, depth - 1, alpha, beta, !isMaximisingPlayer));
					alpha = Math.max(alpha, bestMove);
					matrix.undo(1);
					
					if (beta <= alpha) {
						return bestMove;
					}
				}
			}
			
			return bestMove;
		} else {
			double bestMove = 9999;
			
			for (Map.Entry<Cell, Map<Cell, AbstractMoveValue>> fromEntry : myMoves.entrySet()) {
				Cell from = fromEntry.getKey();
				Map<Cell, AbstractMoveValue> moves = fromEntry.getValue();
				
				for (Map.Entry<Cell, AbstractMoveValue> toEntry : moves.entrySet()) {
					Cell to = toEntry.getKey();
					AbstractMoveValue move = toEntry.getValue();
					
					matrix.begin();
					move.doMove(matrix, from, to);
					matrix.end();
					
					bestMove = Math.min(bestMove, minimax(matrix, depth - 1, alpha, beta, !isMaximisingPlayer));
					beta = Math.min(beta, bestMove);
					matrix.undo(1);
					
					if (beta <= alpha) {
						return bestMove;
					}
				}
			}
			
			return bestMove;
		}
	}
	
	protected Map<Cell, Map<Cell, AbstractMoveValue>> findMyMoves(ServerMatrix matrix) {
		// TODO: make it more efficient
		
		Map<Cell, Map<Cell, AbstractMoveValue>> myMoves = new HashMap<>();
		
		for (Cell from : matrix) {
			AbstractServerPiece piece = matrix.get(from);
			if (piece == NullPiece.INSTANCE) continue;
			if (piece.getTeam() != team) continue;
			
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
			
			if (validMoves.isEmpty()) continue;
			
			myMoves.put(from, validMoves);
		}
		
		return myMoves;
	}
	
	protected List<Cell> getInCheck(ServerMatrix matrix, TeamValue team) {
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
	
	protected double evaluateBoard(ServerMatrix matrix) {
		double total = 0.0;
		for (Cell cell : matrix) {
            total += getPieceValue(matrix.get(cell), cell);
		}
		return total;
	}
	
	protected double getPieceValue(AbstractServerPiece piece, Cell cell) {
		if (piece == NullPiece.INSTANCE) {
			return 0;
		}
		
		double absValue = getAbsoluteValue(piece, cell);
		return piece.getTeam() == TeamRegistry.WHITE.get() ? absValue : -absValue;
	}
	
	protected double getAbsoluteValue(AbstractServerPiece piece, Cell cell) {
		if (piece.getRegistryValue() == ServerPieceRegistry.PAWN.get()) {
			return 10 + (piece.getTeam() == TeamRegistry.WHITE.get() ? pawnEvalWhite[cell.y][cell.x] : pawnEvalBlack[cell.y][cell.x]);
		} else if (piece.getRegistryValue() == ServerPieceRegistry.ROOK.get()) {
			return 50 + (piece.getTeam() == TeamRegistry.WHITE.get() ? rookEvalWhite[cell.y][cell.x] : rookEvalBlack[cell.y][cell.x]);
		} else if (piece.getRegistryValue() == ServerPieceRegistry.KNIGHT.get()) {
			return 30 + knightEval[cell.y][cell.x];
		} else if (piece.getRegistryValue() == ServerPieceRegistry.BISHOP.get()) {
			return 30 + (piece.getTeam() == TeamRegistry.WHITE.get() ? bishopEvalWhite[cell.y][cell.x] : bishopEvalBlack[cell.y][cell.x]);
		} else if (piece.getRegistryValue() == ServerPieceRegistry.QUEEN.get()) {
			return 90 + evalQueen[cell.y][cell.x];
		} else if (piece.getRegistryValue() == ServerPieceRegistry.KING.get()) {
			return 900 + (piece.getTeam() == TeamRegistry.WHITE.get() ? kingEvalWhite[cell.y][cell.x] : kingEvalBlack[cell.y][cell.x]);
		}
		throw new IllegalArgumentException("Invalid piece!");
	}
}
