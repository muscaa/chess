package muscaa.chess.client.player.players;

import java.util.LinkedList;
import java.util.List;

import muscaa.chess.board.Cell;
import muscaa.chess.board.Highlight;
import muscaa.chess.board.TeamValue;
import muscaa.chess.board.matrix.ServerMatrix;
import muscaa.chess.board.piece.AbstractServerPiece;
import muscaa.chess.client.board.AbstractClientBoard;
import muscaa.chess.client.board.piece.ClientPiece;
import muscaa.chess.client.board.piece.ClientPieceRegistry;
import muscaa.chess.client.player.AbstractClientPlayer;
import muscaa.chess.network.DisconnectReasonValue;
import muscaa.chess.player.AbstractServerPlayer;

public class LocalServerPlayer extends AbstractServerPlayer {
	
	public AbstractClientPlayer clientPlayer;
	public AbstractClientBoard clientBoard;
	
	@Override
	public void addChatLine(String line) {
		clientPlayer.onAddChatLine(line);
	}
	
	@Override
	public void startGame() {
		clientBoard.onStartGame();
	}
	
	@Override
	public void updateBoard(ServerMatrix matrix) {
		List<ClientPiece> pieces = new LinkedList<>();
		for (Cell cell : matrix) {
			AbstractServerPiece piece = matrix.get(cell);
			ClientPiece clientPiece = ClientPieceRegistry.REG
					.get(piece.getRegistryValue().getKey().getID())
					.get()
					.create(piece.getTeam());
			
			pieces.add(clientPiece);
		}
		
		clientBoard.onUpdateBoard(matrix.getWidth(), matrix.getHeight(), pieces);
	}
	
	@Override
	public void updateTurn(TeamValue turn) {
		clientBoard.onUpdateTurn(turn);
	}
	
	@Override
	public void endGame(TeamValue winner) {
		clientBoard.onEndGame(winner);
	}
	
	@Override
	public void disconnect(DisconnectReasonValue reason, String message) {
		clientPlayer.onDisconnect(reason, message);
	}
	
	@Override
	public void setTeam(TeamValue team) {
		super.setTeam(team);
		
		clientBoard.setTeam(team);
	}
	
	@Override
	public void setHighlights(List<Highlight> highlights) {
		super.setHighlights(highlights);
		
		clientBoard.setHighlights(highlights);
	}
}
