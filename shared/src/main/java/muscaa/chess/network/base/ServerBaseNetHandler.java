package muscaa.chess.network.base;

import fluff.network.AbstractServerNetHandler;
import muscaa.chess.network.ChessClientConnection;
import muscaa.chess.network.ChessServer;
import muscaa.chess.network.DisconnectReasonRegistry;
import muscaa.chess.network.base.packets.PacketDisconnect;
import muscaa.chess.network.base.packets.SPacketFormData;

public abstract class ServerBaseNetHandler extends AbstractServerNetHandler<ChessServer, ChessClientConnection> implements IServerBaseNetHandler {
	
	@Override
	public void onPacketDisconnect(PacketDisconnect packet) {
		connection.disconnect();
		
		// handle lines?
	}
	
	@Override
	public void onPacketFormData(SPacketFormData packet) {
		connection.disconnect(DisconnectReasonRegistry.KICK.get(), "Invalid form data!");
	}
}
