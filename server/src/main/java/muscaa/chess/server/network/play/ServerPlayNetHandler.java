package muscaa.chess.server.network.play;

import fluff.network.AbstractServerNetHandler;
import muscaa.chess.server.network.ChessClientConnection;
import muscaa.chess.server.network.ChessServer;
import muscaa.chess.server.network.play.packet.PacketClickCell;

public class ServerPlayNetHandler extends AbstractServerNetHandler<ChessServer, ChessClientConnection> implements IServerPlayNetHandler {
	
	@Override
	public void onPacketClickCell(PacketClickCell packet) {
		System.out.println(packet.getCell());
	}
}
