package muscaa.chess.server.network.common;

import fluff.network.AbstractServerNetHandler;
import muscaa.chess.server.network.ChessClientConnection;
import muscaa.chess.server.network.ChessServer;
import muscaa.chess.shared.network.common.ICommonNetHandler;
import muscaa.chess.shared.network.common.packets.PacketDisconnect;

public class ServerCommonNetHandler extends AbstractServerNetHandler<ChessServer, ChessClientConnection> implements ICommonNetHandler {
	
	@Override
	public void onPacketDisconnect(PacketDisconnect packet) {
		connection.disconnect();
		
		// handle lines?
	}
}
