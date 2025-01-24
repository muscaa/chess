package muscaa.chess.client.board;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import muscaa.chess.board.AbstractBoard;
import muscaa.chess.board.Cell;
import muscaa.chess.board.Highlight;
import muscaa.chess.board.TeamRegistry;
import muscaa.chess.board.TeamValue;
import muscaa.chess.chat.ChatUtils;
import muscaa.chess.client.Client;
import muscaa.chess.client.assets.SoundRegistry;
import muscaa.chess.client.board.matrix.ClientMatrix;
import muscaa.chess.client.board.piece.ClientPiece;
import muscaa.chess.client.utils.Screen;
import muscaa.chess.client.utils.TaskManager;

public abstract class AbstractClientBoard extends AbstractBoard {
	
	protected Client chess;
	protected BoardLayer layer;
	
	protected ClientMatrix matrix;
	protected TeamValue team;
	protected List<Highlight> highlights = new LinkedList<>();
	
	public void init(Client chess, BoardLayer layer) {
		this.chess = chess;
		this.layer = layer;
	}
	
	public abstract void click(Cell cell);
	
	public void onStartGame() {
		highlights.clear();
		
		TaskManager.render(() -> {
			chess.setScreen(null);
		});
	}
    
	public void onUpdateBoard(int width, int height, List<ClientPiece> pieces) {
		if (matrix == null) {
			matrix = new ClientMatrix(width, height);
		} else if (matrix.getWidth() != width || matrix.getHeight() != height) {
			matrix.reset(width, height);
		}
		
		Iterator<ClientPiece> it = pieces.iterator();
		for (Cell cell : matrix) {
			ClientPiece piece = it.next();
			
			matrix.set(cell, piece);
		}
		
		layer.resize(Screen.WIDTH, Screen.HEIGHT);
		
		SoundRegistry.MOVE.get().play();
	}
	
	public void onUpdateTurn(TeamValue turn) {
	}
	
	public void onEndGame(TeamValue winner) {
		/*TaskManager.render(() -> {
			chess.setScreen(new DisconnectedScreen(
					winner == TeamRegistry.NULL.get() ? "Stalemate"
					: winner == team ? "You win"
							: "Opponent wins"));
			
			chess.setBoard(null);
		});*/
		String text = winner == TeamRegistry.NULL.get() ? "&7Stalemate"
				: winner == team ? "&aYou win"
						: "&cOpponent wins";
		
		chess.chatLayer.addLine(ChatUtils.format(text)); // TODO on server
	}
	
	public void dispose() {
		highlights.clear();
	}
	
	public ClientMatrix getMatrix() {
		return matrix;
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
