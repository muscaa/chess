package muscaa.chess.network.play;

import fluff.network.AbstractClientNetHandler;
import muscaa.chess.ChessGame;
import muscaa.chess.network.ChessClient;
import muscaa.chess.network.play.packets.PacketEndGame;
import muscaa.chess.network.play.packets.PacketMove;
import muscaa.chess.network.play.packets.PacketStartGame;

public class ClientPlayNetHandler extends AbstractClientNetHandler<ChessClient> implements IClientPlayNetHandler {
	
	@Override
	public void onPacketStartGame(PacketStartGame packet) {
		ChessGame.INSTANCE.getBoard().onPacketStartGame(packet);
	}
	
	@Override
	public void onPacketEndGame(PacketEndGame packet) {
		ChessGame.INSTANCE.getBoard().onPacketEndGame(packet);
	}
	
	@Override
	public void onPacketMove(PacketMove packet) {
		ChessGame.INSTANCE.getBoard().onPacketMove(packet);
	}
}
