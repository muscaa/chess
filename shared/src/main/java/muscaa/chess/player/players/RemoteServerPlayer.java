package muscaa.chess.player.players;

import java.util.List;

import muscaa.chess.board.AbstractServerBoard;
import muscaa.chess.board.Highlight;
import muscaa.chess.board.TeamValue;
import muscaa.chess.board.matrix.ServerMatrix;
import muscaa.chess.network.ChessClientConnection;
import muscaa.chess.network.DisconnectReasonValue;
import muscaa.chess.network.common.packets.SPacketChatLine;
import muscaa.chess.network.common.packets.SPacketJoinBoard;
import muscaa.chess.network.common.packets.SPacketLeaveBoard;
import muscaa.chess.network.play.packets.SPacketBoard;
import muscaa.chess.network.play.packets.SPacketGameEnd;
import muscaa.chess.network.play.packets.SPacketGameStart;
import muscaa.chess.network.play.packets.SPacketHighlightCells;
import muscaa.chess.network.play.packets.SPacketTeam;
import muscaa.chess.network.play.packets.SPacketTurn;
import muscaa.chess.player.AbstractServerPlayer;

public class RemoteServerPlayer extends AbstractServerPlayer {
	
	protected final ChessClientConnection connection;
	
	public RemoteServerPlayer(ChessClientConnection connection) {
        this.connection = connection;
	}
	
	@Override
	public void addChatLine(String line) {
		connection.send(new SPacketChatLine(line));
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
		connection.send(new SPacketTurn(turn));
	}
	
	@Override
	public void endGame(TeamValue winner) {
		connection.send(new SPacketGameEnd(winner));
	}
	
	@Override
	public void disconnect(DisconnectReasonValue reason, String message) {
		super.disconnect(reason, message);
		
		connection.disconnect(reason, message);
	}
	
	@Override
	public void setTeam(TeamValue team) {
		super.setTeam(team);
		
		connection.send(new SPacketTeam(team));
	}
	
	@Override
	public void setHighlights(List<Highlight> highlights) {
		super.setHighlights(highlights);
		
		connection.send(new SPacketHighlightCells(highlights));
	}
	
	@Override
	public void setBoard(AbstractServerBoard board) {
		if (board != null && this.board == null) {
			connection.send(new SPacketJoinBoard());
		} else if (board == null && this.board != null) {
            connection.send(new SPacketLeaveBoard());
		}
		
		super.setBoard(board);
	}
}
