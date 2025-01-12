package muscaa.chess.board.player;

import java.util.List;

import muscaa.chess.board.Cell;
import muscaa.chess.board.Highlight;
import muscaa.chess.board.Lobby;
import muscaa.chess.board.TeamValue;
import muscaa.chess.board.matrix.ServerMatrix;

public abstract class AbstractPlayer {
	
	protected Lobby lobby;
	protected TeamValue team;
	
	public abstract void startGame();
	
	public abstract void updateBoard(ServerMatrix matrix);
	
	public abstract void endGame(TeamValue winner);
	
	public abstract void setHighlights(List<Highlight> highlights);
	
	public void click(Cell cell) {
		if (lobby.teams.size() != 2) return;
		if (lobby.turn != team) return;
		if (!lobby.matrix.isInBounds(cell)) return;
		
		lobby.click(cell);
	}
	
	public void init(Lobby lobby) {
		this.lobby = lobby;
	}
	
	public TeamValue getTeam() {
		return team;
	}
	
	public void setTeam(TeamValue team) {
		this.team = team;
	}
}
