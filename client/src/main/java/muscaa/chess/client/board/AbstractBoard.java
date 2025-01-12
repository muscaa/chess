package muscaa.chess.client.board;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import fluff.network.NetworkException;
import muscaa.chess.board.Cell;
import muscaa.chess.board.Highlight;
import muscaa.chess.board.TeamRegistry;
import muscaa.chess.board.TeamValue;
import muscaa.chess.client.Client;
import muscaa.chess.client.assets.SoundRegistry;
import muscaa.chess.client.board.matrix.ClientMatrix;
import muscaa.chess.client.board.piece.ClientPiece;
import muscaa.chess.client.config.ServersConfig;
import muscaa.chess.client.gui.screens.DisconnectedScreen;
import muscaa.chess.client.network.ChessClient;
import muscaa.chess.client.network.play.packets.CPacketBoard;
import muscaa.chess.client.network.play.packets.CPacketClickCell;
import muscaa.chess.client.network.play.packets.CPacketGameEnd;
import muscaa.chess.client.network.play.packets.CPacketGameStart;
import muscaa.chess.client.network.play.packets.CPacketHighlightCells;
import muscaa.chess.client.network.play.packets.CPacketTeam;
import muscaa.chess.client.utils.Screen;
import muscaa.chess.client.utils.TaskManager;

public abstract class AbstractBoard {
	
	private final ChessClient networkClient;
	private Client chess;
	private BoardLayer layer;
	
    private ClientMatrix matrix;
    private TeamValue team;
    private List<Highlight> highlights = new LinkedList<>();
    
    public AbstractBoard(ServersConfig.Server server) throws UnknownHostException, IOException, NetworkException {
    	networkClient = new ChessClient();
    	networkClient.connect(server);
	}
    
    public void click(Cell cell) {
    	networkClient.send(new CPacketClickCell(cell));
    }
    
	public void onPacketStartGame(CPacketGameStart packet) {
		highlights.clear();
		
		TaskManager.render(() -> {
			chess.setScreen(null);
		});
	}
	
	public void onPacketBoard(CPacketBoard packet) {
		if (matrix == null) {
			matrix = new ClientMatrix(packet.getWidth(), packet.getHeight());
		} else if (matrix.getWidth() != packet.getWidth() || matrix.getHeight() != packet.getHeight()) {
			matrix.reset(packet.getWidth(), packet.getHeight());
		}
		
		Iterator<ClientPiece> it = packet.getPieces().iterator();
		for (Cell cell : matrix) {
			ClientPiece piece = it.next();
			
			matrix.set(cell, piece);
		}
		
		layer.resize(Screen.WIDTH, Screen.HEIGHT);
		
		SoundRegistry.MOVE.get().play();
	}
	
	public void onPacketTeam(CPacketTeam packet) {
		team = packet.getTeam();
	}
	
	public void onPacketHighlightCells(CPacketHighlightCells packet) {
		highlights = packet.getHighlights();
	}
	
	public void onPacketEndGame(CPacketGameEnd packet) {
		TaskManager.render(() -> {
			chess.setScreen(new DisconnectedScreen(
					packet.getWinner() == TeamRegistry.NULL.get() ? "Stalemate"
					: packet.getWinner() == team ? "You win"
							: "Opponent wins"));
		});
		
		chess.setBoard(null);
	}
	
	public void init(Client chess, BoardLayer layer) {
		this.chess = chess;
		this.layer = layer;
	}
	
	public void dispose() {
		networkClient.disconnect();
	}
	
	public ClientMatrix getMatrix() {
		return matrix;
	}
	
	public TeamValue getTeam() {
		return team;
	}
	
	public List<Highlight> getHighlights() {
		return highlights;
	}
}
