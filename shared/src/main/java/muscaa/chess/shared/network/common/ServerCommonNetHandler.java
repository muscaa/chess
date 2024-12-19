package muscaa.chess.shared.network.common;

import fluff.network.AbstractServerNetHandler;
import muscaa.chess.shared.network.ChessClientConnection;
import muscaa.chess.shared.network.ChessServer;
import muscaa.chess.shared.network.common.packets.PacketDisconnect;

public class ServerCommonNetHandler extends AbstractServerNetHandler<ChessServer, ChessClientConnection> implements ICommonNetHandler {
	
	@Override
	public void onPacketDisconnect(PacketDisconnect packet) {
		connection.disconnect();
		
		// handle lines?
	}
}
