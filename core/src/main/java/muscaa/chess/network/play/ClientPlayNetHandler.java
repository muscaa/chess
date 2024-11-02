package muscaa.chess.network.play;

import fluff.network.AbstractClientNetHandler;
import muscaa.chess.ChessGame;
import muscaa.chess.assets.Sounds;
import muscaa.chess.network.ChessClient;
import muscaa.chess.network.play.packets.PacketBoard;
import muscaa.chess.network.play.packets.PacketMove;

public class ClientPlayNetHandler extends AbstractClientNetHandler<ChessClient> implements IClientPlayNetHandler {
	
	@Override
	public void onPacketBoard(PacketBoard packet) {
		ChessGame.INSTANCE.getBoardLayer().getBoard().setPieces(packet.getPieces());
	}
	
	@Override
	public void onPacketMove(PacketMove packet) {
		ChessGame.INSTANCE.getBoardLayer().getBoard().setPiece(packet.getFrom(), null);
		ChessGame.INSTANCE.getBoardLayer().getBoard().setPiece(packet.getTo(), packet.getPiece());
		
		if (packet.getCapture() != null) {
			Sounds.CAPTURE.play();
		} else {
			Sounds.MOVE.play();
		}
	}
}
