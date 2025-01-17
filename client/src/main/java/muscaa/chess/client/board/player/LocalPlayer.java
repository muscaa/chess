package muscaa.chess.client.board.player;

import java.util.LinkedList;
import java.util.List;

import muscaa.chess.board.Cell;
import muscaa.chess.board.Highlight;
import muscaa.chess.board.TeamValue;
import muscaa.chess.board.matrix.ServerMatrix;
import muscaa.chess.board.piece.AbstractServerPiece;
import muscaa.chess.board.player.AbstractPlayer;
import muscaa.chess.client.board.AbstractBoard;
import muscaa.chess.client.board.piece.ClientPiece;
import muscaa.chess.client.board.piece.ClientPieceRegistry;

public class LocalPlayer extends AbstractPlayer {
	
	protected final AbstractBoard board;
	
	public LocalPlayer(AbstractBoard board) {
		this.board = board;
	}
	
	@Override
	public void startGame() {
		board.startGame();
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
		
		board.updateBoard(matrix.getWidth(), matrix.getHeight(), pieces);
	}
	
	@Override
	public void updateTurn(TeamValue turn) {
		// TODO
	}
	
	@Override
	public void endGame(TeamValue winner) {
		board.endGame(winner);
	}
	
	@Override
	public void updateTeam(TeamValue team) {
		super.updateTeam(team);
		
		board.setTeam(team);
	}
	
	@Override
	public void updateHighlights(List<Highlight> highlights) {
		super.updateHighlights(highlights);
		
		board.setHighlights(highlights);
	}
}
