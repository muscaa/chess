package muscaa.chess.client.player;

import muscaa.chess.client.Client;
import muscaa.chess.client.board.AbstractClientBoard;
import muscaa.chess.client.board.BoardLayer;
import muscaa.chess.client.chat.ChatLayer;
import muscaa.chess.client.gui.screens.DisconnectedScreen;
import muscaa.chess.client.utils.TaskManager;
import muscaa.chess.network.DisconnectReasonValue;
import muscaa.chess.player.AbstractPlayer;

public abstract class AbstractClientPlayer extends AbstractPlayer {
	
	protected Client chess;
	protected ChatLayer chatLayer;
	protected BoardLayer boardLayer;
	
	protected AbstractClientBoard board;
	
	public void init(Client chess, ChatLayer chatLayer, BoardLayer boardLayer) {
		this.chess = chess;
		this.chatLayer = chatLayer;
		this.boardLayer = boardLayer;
	}
	
	public abstract void sendChatMessage(String message);
	
	public void onAddChatLine(String line) {
		chatLayer.addLine(line);
	}
	
	public void onDisconnect(DisconnectReasonValue reason, String message) {
		TaskManager.render(() -> {
			//chess.setBoard(null);
			chess.setPlayer(null);
			
			chess.setScreen(new DisconnectedScreen(message));
		});
	}
	
	public AbstractClientBoard getBoard() {
		return board;
	}
	
	public void setBoard(AbstractClientBoard newBoard) {
		AbstractClientBoard oldBoard = board;
		if (oldBoard != null) {
			oldBoard.dispose();
		}
		
		board = newBoard;
		if (board != null) {
			board.init(chess, boardLayer);
		}
	}
	
	public void dispose() {
		setBoard(null);
	}
}
