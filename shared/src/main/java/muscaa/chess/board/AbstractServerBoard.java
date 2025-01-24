package muscaa.chess.board;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import muscaa.chess.AbstractServer;
import muscaa.chess.board.matrix.ServerMatrix;
import muscaa.chess.board.piece.move.AbstractMoveValue;
import muscaa.chess.player.AbstractServerPlayer;

public abstract class AbstractServerBoard extends AbstractBoard {
	
	public final LinkedList<AbstractServerPlayer> players = new LinkedList<>();
	public final HashMap<TeamValue, AbstractServerPlayer> teams = new HashMap<>();
	
	protected AbstractServer server;
	protected int id;
	
	protected boolean started;
	
	public Map<Cell, Map<Cell, AbstractMoveValue>> allMoves;
	public ServerMatrix matrix;
	public TeamValue turn;
	
	public void init(AbstractServer server, int id) {
		this.server = server;
		this.id = id;
	}
	
	public abstract void onClick(Cell cell);
	
	public void startGame() {
		if (started) return;
		started = true;
		
		for (AbstractServerPlayer p : players) {
			p.startGame();
		}
	}
	
	public void updateBoard() {
		for (AbstractServerPlayer p : players) {
			p.updateBoard(matrix);
		}
	}
	
	public abstract void doMove(AbstractMoveValue move, Cell from, Cell to);
	
	public void endGame(TeamValue winner) {
		if (!started) return;
		started = false;
		
		for (AbstractServerPlayer p : players) {
			p.endGame(winner);
		}
	}
	
	public boolean join(AbstractServerPlayer player, boolean spectator) {
		synchronized (players) {
			players.add(player);
			player.setBoard(this);
			
			if (spectator) {
				player.setTeam(TeamRegistry.SPECTATOR.get());
				return true;
			}
			
			AbstractServerPlayer white = teams.get(TeamRegistry.WHITE.get());
			AbstractServerPlayer black = teams.get(TeamRegistry.BLACK.get());
			
			if (white == null) {
				teams.put(TeamRegistry.WHITE.get(), player);
				player.setTeam(TeamRegistry.WHITE.get());
				
				if (black != null) {
					startGame();
				}
				return true;
			}
			
			if (black == null) {
				teams.put(TeamRegistry.BLACK.get(), player);
				player.setTeam(TeamRegistry.BLACK.get());
				
				if (white != null) {
					startGame();
				}
				return true;
			}
			
			return false;
		}
	}
	
	public void leave(AbstractServerPlayer player) {
		synchronized (players) {
			AbstractServerPlayer teamPlayer = teams.remove(player.getTeam());
			if (teamPlayer != null) {
				endGame(teamPlayer.getTeam().invert());
			}
			
			if (players.remove(player)) {
				player.setBoard(null);
			}
		}
	}
	
	public void close() {
		synchronized (players) {
			endGame(TeamRegistry.NULL.get());
			
			for (AbstractServerPlayer p : players) {
				leave(p);
			}
			
			server.removeBoard(this);
		}
	}
	
	public TeamValue getTurn() {
		return turn;
	}
	
	public void setTurn(TeamValue turn) {
		this.turn = turn;
		
		for (AbstractServerPlayer p : players) {
            p.updateTurn(turn);
		}
	}
	
	public int getID() {
		return id;
	}
	
	public boolean isStarted() {
		return started;
	}
}
