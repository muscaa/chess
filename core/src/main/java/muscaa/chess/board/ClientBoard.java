package muscaa.chess.board;

import java.util.List;

import muscaa.chess.ChessGame;
import muscaa.chess.assets.Sounds;
import muscaa.chess.network.play.packets.PacketClickCell;
import muscaa.chess.network.play.packets.PacketEndGame;
import muscaa.chess.network.play.packets.PacketMove;
import muscaa.chess.network.play.packets.PacketStartGame;
import muscaa.chess.shared.board.AbstractBoard;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.task.TaskManager;

public class ClientBoard extends AbstractBoard<ClientChessPiece> {
	
	private ChessColor color;
	
	@Override
	public void click(ChessCell cell) {
		ChessGame.INSTANCE.getNetwork().getClient().send(new PacketClickCell(cell));
	}
	
	@Override
	public List<ChessCell> getMoves(ChessCell cell) {
		return null;
	}
	
	public ChessColor getColor() {
		return color;
	}
	
	public synchronized void onPacketStartGame(PacketStartGame packet) {
		this.color = packet.getColor();
		this.matrix = packet.getMatrix();
		
		TaskManager.render(() -> {
			ChessGame.INSTANCE.getGuiLayer().setScreen(null);
		});
	}
	
	public synchronized void onPacketEndGame(PacketEndGame packet) {
	}
	
	public synchronized void onPacketMove(PacketMove packet) {
		matrix.set(packet.getFrom(), packet.getFromPiece());
		matrix.set(packet.getTo(), packet.getToPiece());
		
		if (packet.getCapturePiece() != null) {
			Sounds.CAPTURE.play();
		} else {
			Sounds.MOVE.play();
		}
	}
}
