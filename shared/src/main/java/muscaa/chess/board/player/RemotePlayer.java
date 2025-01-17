package muscaa.chess.board.player;

import java.util.List;

import muscaa.chess.board.Highlight;
import muscaa.chess.board.TeamValue;
import muscaa.chess.board.matrix.ServerMatrix;
import muscaa.chess.network.ChessClientConnection;
import muscaa.chess.network.play.packets.SPacketBoard;
import muscaa.chess.network.play.packets.SPacketGameEnd;
import muscaa.chess.network.play.packets.SPacketGameStart;
import muscaa.chess.network.play.packets.SPacketHighlightCells;
import muscaa.chess.network.play.packets.SPacketTeam;

public class RemotePlayer extends AbstractPlayer {
	
	protected final ChessClientConnection connection;
	
	public RemotePlayer(ChessClientConnection connection) {
        this.connection = connection;
	}
	
	@Override
	public void startGame() {
		connection.send(new SPacketGameStart());
	}
	
	@Override
	public void updateBoard(ServerMatrix matrix) {
		connection.send(new SPacketBoard(matrix));
	}
	
	@Override
	public void updateTurn(TeamValue turn) {
		// TODO create packet
	}
	
	@Override
	public void endGame(TeamValue winner) {
		connection.send(new SPacketGameEnd(winner));
	}
	
	@Override
	public void updateTeam(TeamValue team) {
		super.updateTeam(team);
		
		connection.send(new SPacketTeam(team));
	}
	
	@Override
	public void updateHighlights(List<Highlight> highlights) {
		super.updateHighlights(highlights);
		
		connection.send(new SPacketHighlightCells(highlights));
	}
}
