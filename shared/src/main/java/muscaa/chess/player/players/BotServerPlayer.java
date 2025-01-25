package muscaa.chess.player.players;

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
import muscaa.chess.board.piece.move.MoveUtils;
import muscaa.chess.board.piece.pieces.NullPiece;
import muscaa.chess.network.DisconnectReasonValue;
import muscaa.chess.player.AbstractServerPlayer;

public class BotServerPlayer extends AbstractServerPlayer {
	
	protected static final double[][] PAWN_EVAL_WHITE = {
			{  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0 },
			{  5.0,  5.0,  5.0,  5.0,  5.0,  5.0,  5.0,  5.0 },
			{  1.0,  1.0,  2.0,  3.0,  3.0,  2.0,  1.0,  1.0 },
			{  0.5,  0.5,  1.0,  2.5,  2.5,  1.0,  0.5,  0.5 },
			{  0.0,  0.0,  0.0,  2.0,  2.0,  0.0,  0.0,  0.0 },
			{  0.5, -0.5, -1.0,  0.0,  0.0, -1.0, -0.5,  0.5 },
			{  0.5,  1.0,  1.0, -2.0, -2.0,  1.0,  1.0,  0.5 },
			{  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0 }
	};
	protected static final double[][] PAWN_EVAL_BLACK = reverseArray(PAWN_EVAL_WHITE);
	protected static final double[][] KNIGHT_EVAL = {
			{ -5.0, -4.0, -3.0, -3.0, -3.0, -3.0, -4.0, -5.0 },
			{ -4.0, -2.0,  0.0,  0.0,  0.0,  0.0, -2.0, -4.0 },
			{ -3.0,  0.0,  1.0,  1.5,  1.5,  1.0,  0.0, -3.0 },
			{ -3.0,  0.5,  1.5,  2.0,  2.0,  1.5,  0.5, -3.0 },
			{ -3.0,  0.0,  1.5,  2.0,  2.0,  1.5,  0.0, -3.0 },
			{ -3.0,  0.5,  1.0,  1.5,  1.5,  1.0,  0.5, -3.0 },
			{ -4.0, -2.0,  0.0,  0.5,  0.5,  0.0, -2.0, -4.0 },
			{ -5.0, -4.0, -3.0, -3.0, -3.0, -3.0, -4.0, -5.0 }
	};
	protected static final double[][] BISHOP_EVAL_WHITE = {
			{ -2.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -2.0 },
			{ -1.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -1.0 },
			{ -1.0,  0.0,  0.5,  1.0,  1.0,  0.5,  0.0, -1.0 },
			{ -1.0,  0.5,  0.5,  1.0,  1.0,  0.5,  0.5, -1.0 },
			{ -1.0,  0.0,  1.0,  1.0,  1.0,  1.0,  0.0, -1.0 },
			{ -1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0, -1.0 },
			{ -1.0,  0.5,  0.0,  0.0,  0.0,  0.0,  0.5, -1.0 },
			{ -2.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -2.0 }
	};
	protected static final double[][] BISHOP_EVAL_BLACK = reverseArray(BISHOP_EVAL_WHITE);
	protected static final double[][] ROOK_EVAL_WHITE = {
			{  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0 },
			{  0.5,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  0.5 },
			{ -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5 },
			{ -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5 },
			{ -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5 },
			{ -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5 },
			{ -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5 },
			{  0.0,   0.0, 0.0,  0.5,  0.5,  0.0,  0.0,  0.0 }
	};
	protected static final double[][] ROOK_EVAL_BLACK = reverseArray(ROOK_EVAL_WHITE);
	protected static final double[][] QUEEN_EVAL = {
			{ -2.0, -1.0, -1.0, -0.5, -0.5, -1.0, -1.0, -2.0 },
			{ -1.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -1.0 },
			{ -1.0,  0.0,  0.5,  0.5,  0.5,  0.5,  0.0, -1.0 },
			{ -0.5,  0.0,  0.5,  0.5,  0.5,  0.5,  0.0, -0.5 },
			{  0.0,  0.0,  0.5,  0.5,  0.5,  0.5,  0.0, -0.5 },
			{ -1.0,  0.5,  0.5,  0.5,  0.5,  0.5,  0.0, -1.0 },
			{ -1.0,  0.0,  0.5,  0.0,  0.0,  0.0,  0.0, -1.0 },
			{ -2.0, -1.0, -1.0, -0.5, -0.5, -1.0, -1.0, -2.0 }
	};
	protected static final double[][] KING_EVAL_WHITE = {
			{ -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0 },
			{ -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0 },
			{ -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0 },
			{ -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0 },
			{ -2.0, -3.0, -3.0, -4.0, -4.0, -3.0, -3.0, -2.0 },
			{ -1.0, -2.0, -2.0, -2.0, -2.0, -2.0, -2.0, -1.0 },
			{  2.0,  2.0,  0.0,  0.0,  0.0,  0.0,  2.0,  2.0 },
			{  2.0,  3.0,  1.0,  0.0,  0.0,  1.0,  3.0,  2.0 }
	};
	protected static final double[][] KING_EVAL_BLACK = reverseArray(KING_EVAL_WHITE);
	
	protected static double[][] reverseArray(double[][] array) {
		double[][] copy = new double[array.length][array.length];

		for (int i = 0; i < array.length; i++) {
			copy[i] = array[array.length - 1 - i];
		}

		return copy;
	}
	
	protected final ExecutorService executor;
	
	public BotServerPlayer() {
		executor = Executors.newSingleThreadExecutor();
		
		setName("Bot");
	}
	
	@Override
	public void addChatLine(String line) {}
	
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
		executor.shutdownNow();
	}
	
	@Override
	public void disconnect(DisconnectReasonValue reason, String message) {
		super.disconnect(reason, message);
		
		executor.shutdownNow();
	}
	
	protected void doMove() {
		if (board.getTurn() != team) return;
		
		MoveEntry bestMove = null;
		
		List<MoveEntry> bestMoves = minimaxRoot(board.matrix, 3, true);
		if (!bestMoves.isEmpty()) {
			Random random = new Random();
			bestMove = bestMoves.get(random.nextInt(bestMoves.size()));
		} else {
			bestMove = getRandomMove(board.matrix, board.allMoves);
		}
		
		if (bestMove == null) {
			board.endGame(team.invert());
			return;
		}
		
		board.doMove(bestMove.move, bestMove.from, bestMove.to);
	}
	
	protected MoveEntry getRandomMove(ServerMatrix matrix, Map<Cell, Map<Cell, AbstractMoveValue>> allMoves) {
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
			int index1 = random.nextInt(list.size());
			
			Map.Entry<Cell, Map<Cell, AbstractMoveValue>> entry1 = list.get(index1);
			int index2 = random.nextInt(entry1.getValue().size());
			
			for (Map.Entry<Cell, AbstractMoveValue> entry2 : entry1.getValue().entrySet()) {
				index2--;
				
				if (index2 < 0) {
					return new MoveEntry(entry1.getKey(), entry2.getKey(), entry2.getValue());
				}
			}
		}
		
		return null;
	}
	
	protected List<MoveEntry> minimaxRoot(ServerMatrix matrix, int depth, boolean isMaximisingPlayer) {
		List<MoveEntry> bestMoves = new LinkedList<>();
		double bestMove = -9999;
		
		for (Cell from : matrix) {
			AbstractServerPiece piece = matrix.get(from);
			if (piece == NullPiece.INSTANCE) continue;
			if (piece.getTeam() != team) continue;
			
			Map<Cell, AbstractMoveValue> moves = new HashMap<>();
			piece.findMoves(moves, matrix, from);
			
			for (Map.Entry<Cell, AbstractMoveValue> moveEntry : moves.entrySet()) {
				Cell to = moveEntry.getKey();
				AbstractMoveValue move = moveEntry.getValue();
				
				matrix.begin();
				move.doMove(matrix, from, to);
				matrix.end();
				
				boolean inCheck = !MoveUtils.getInCheck(board.matrix, piece.getTeam()).isEmpty();
				if (inCheck) {
					matrix.undo(1);
					continue;
				}
				
				double value = minimax(matrix, depth - 1, -10000, 10000, !isMaximisingPlayer);
				matrix.undo(1);
				
				if (value > bestMove) {
					bestMove = value;
					
					bestMoves.clear();
					bestMoves.add(new MoveEntry(from, to, move));
				} else if (value == bestMove) {
					bestMoves.add(new MoveEntry(from, to, move));
				}
			}
		}
		
		return bestMoves;
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
			return -evaluateMatrix(matrix);
		}
		
		// TODO: stop sacrificing pieces
		
		if (isMaximisingPlayer) {
			double bestMove = -9999;
			
			for (Cell from : matrix) {
				AbstractServerPiece piece = matrix.get(from);
				if (piece == NullPiece.INSTANCE) continue;
				if (piece.getTeam() != team) continue;
				
				Map<Cell, AbstractMoveValue> moves = new HashMap<>();
				piece.findMoves(moves, matrix, from);
				
				for (Map.Entry<Cell, AbstractMoveValue> moveEntry : moves.entrySet()) {
					Cell to = moveEntry.getKey();
					AbstractMoveValue move = moveEntry.getValue();
					
					matrix.begin();
					move.doMove(matrix, from, to);
					matrix.end();
					
					boolean inCheck = !MoveUtils.getInCheck(board.matrix, piece.getTeam()).isEmpty();
					if (inCheck) {
						matrix.undo(1);
						continue;
					}
					
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
			
			for (Cell from : matrix) {
				AbstractServerPiece piece = matrix.get(from);
				if (piece == NullPiece.INSTANCE) continue;
				if (piece.getTeam() != team) continue;
				
				Map<Cell, AbstractMoveValue> moves = new HashMap<>();
				piece.findMoves(moves, matrix, from);
				
				for (Map.Entry<Cell, AbstractMoveValue> moveEntry : moves.entrySet()) {
					Cell to = moveEntry.getKey();
					AbstractMoveValue move = moveEntry.getValue();
					
					matrix.begin();
					move.doMove(matrix, from, to);
					matrix.end();
					
					boolean inCheck = !MoveUtils.getInCheck(board.matrix, piece.getTeam()).isEmpty();
					if (inCheck) {
						matrix.undo(1);
						continue;
					}
					
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
	
	protected double evaluateMatrix(ServerMatrix matrix) {
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
			return randomPower(10) + (piece.getTeam() == TeamRegistry.WHITE.get() ? PAWN_EVAL_WHITE[cell.y][cell.x] : PAWN_EVAL_BLACK[cell.y][cell.x]);
		} else if (piece.getRegistryValue() == ServerPieceRegistry.ROOK.get()) {
			return randomPower(50) + (piece.getTeam() == TeamRegistry.WHITE.get() ? ROOK_EVAL_WHITE[cell.y][cell.x] : ROOK_EVAL_BLACK[cell.y][cell.x]);
		} else if (piece.getRegistryValue() == ServerPieceRegistry.KNIGHT.get()) {
			return randomPower(30) + KNIGHT_EVAL[cell.y][cell.x];
		} else if (piece.getRegistryValue() == ServerPieceRegistry.BISHOP.get()) {
			return randomPower(30) + (piece.getTeam() == TeamRegistry.WHITE.get() ? BISHOP_EVAL_WHITE[cell.y][cell.x] : BISHOP_EVAL_BLACK[cell.y][cell.x]);
		} else if (piece.getRegistryValue() == ServerPieceRegistry.QUEEN.get()) {
			return randomPower(90) + QUEEN_EVAL[cell.y][cell.x];
		} else if (piece.getRegistryValue() == ServerPieceRegistry.KING.get()) {
			return randomPower(900) + (piece.getTeam() == TeamRegistry.WHITE.get() ? KING_EVAL_WHITE[cell.y][cell.x] : KING_EVAL_BLACK[cell.y][cell.x]);
		}
		throw new IllegalArgumentException("Invalid piece!");
	}
	
	protected int randomPower(int base) {
		Random random = new Random();
		return base + random.nextInt(base / 2);
	}
}
