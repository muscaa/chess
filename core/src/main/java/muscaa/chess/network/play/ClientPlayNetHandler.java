package muscaa.chess.network.play;

import fluff.network.AbstractClientNetHandler;
import muscaa.chess.ChessGame;
import muscaa.chess.network.ChessClient;
import muscaa.chess.network.play.packets.PacketBoard;

public class ClientPlayNetHandler extends AbstractClientNetHandler<ChessClient> implements IClientPlayNetHandler {
	
	@Override
	public void onPacketBoard(PacketBoard packet) {
		ChessGame.INSTANCE.getBoardLayer().getBoard().setPieces(packet.getPieces());
	}
}
