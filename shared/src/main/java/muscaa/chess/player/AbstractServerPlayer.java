package muscaa.chess.player;

import java.util.List;

import muscaa.chess.AbstractServer;
import muscaa.chess.board.AbstractServerBoard;
import muscaa.chess.board.Cell;
import muscaa.chess.board.Highlight;
import muscaa.chess.board.TeamValue;
import muscaa.chess.board.matrix.ServerMatrix;
import muscaa.chess.command.ICommandSource;
import muscaa.chess.network.DisconnectReasonValue;

public abstract class AbstractServerPlayer extends AbstractPlayer implements ICommandSource {
	
	protected AbstractServer server;
	protected AbstractServerBoard board;
	
	protected TeamValue team;
	protected List<Highlight> highlights;
	
	public void init(AbstractServer server) {
		this.server = server;
	}
	
	public void onSendChatMessage(String message) {
		server.onSendChatMessage(this, message);
	}
	
	public void onClick(Cell cell) {
		if (!board.isStarted()) return;
		if (board.teams.size() != 2) return;
		if (board.getTurn() != team) return;
		if (!board.matrix.isInBounds(cell)) return;
		
		board.onClick(cell);
	}
	
	public abstract void startGame();
	
	public abstract void updateBoard(ServerMatrix matrix);
	
	public abstract void updateTurn(TeamValue turn);
	
	public abstract void endGame(TeamValue winner);
	
	public void disconnect(DisconnectReasonValue reason, String message) {
		server.removePlayer(this);
	}
	
	public AbstractServerBoard getBoard() {
		return board;
	}
	
	public void setBoard(AbstractServerBoard board) {
		this.board = board;
	}
	
	public TeamValue getTeam() {
		return team;
	}
	
	public void setTeam(TeamValue team) {
		this.team = team;
	}
	
	public List<Highlight> getHighlights() {
		return highlights;
	}
	
	public void setHighlights(List<Highlight> highlights) {
		this.highlights = highlights;
	}
}
