package muscaa.chess.board.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import muscaa.chess.board.Cell;
import muscaa.chess.board.TeamValue;
import muscaa.chess.board.matrix.ServerMatrix;
import muscaa.chess.board.piece.AbstractServerPiece;
import muscaa.chess.board.piece.move.AbstractMoveValue;

public class BotPlayer extends AbstractPlayer {
	
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
}
