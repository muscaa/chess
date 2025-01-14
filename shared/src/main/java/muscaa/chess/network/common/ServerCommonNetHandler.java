package muscaa.chess.network.common;

import fluff.network.AbstractServerNetHandler;
import muscaa.chess.network.ChessClientConnection;
import muscaa.chess.network.ChessServer;
import muscaa.chess.network.common.packets.PacketDisconnect;

public class ServerCommonNetHandler extends AbstractServerNetHandler<ChessServer, ChessClientConnection> implements IServerCommonNetHandler {
	
	@Override
	public void onPacketDisconnect(PacketDisconnect packet) {
		connection.disconnect();
		
		// handle lines?
	}
}
