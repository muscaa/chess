package muscaa.chess.network.base;

import fluff.network.AbstractServerNetHandler;
import muscaa.chess.network.ChessClientConnection;
import muscaa.chess.network.ChessServer;
import muscaa.chess.network.base.packets.PacketDisconnect;

public abstract class ServerBaseNetHandler extends AbstractServerNetHandler<ChessServer, ChessClientConnection> implements IServerBaseNetHandler {
	
	@Override
	public void onPacketDisconnect(PacketDisconnect packet) {
		connection.disconnect();
		
		// handle lines?
	}
}
