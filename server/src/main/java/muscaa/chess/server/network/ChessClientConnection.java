package muscaa.chess.server.network;

import java.io.IOException;
import java.net.Socket;
import java.util.UUID;

import fluff.network.INetHandler;
import fluff.network.NetworkException;
import fluff.network.packet.IPacketChannel;
import fluff.network.packet.PacketContext;
import fluff.network.server.AbstractClientConnection;
import fluff.network.server.AbstractServer;

public class ChessClientConnection extends AbstractClientConnection {
	
	private UUID uuid;
	
	public ChessClientConnection(AbstractServer server, Socket socket, PacketContext<?> context,
			INetHandler handler, IPacketChannel channel) throws IOException, NetworkException {
		super(server);
		
		setContextUnsafe(context, handler);
        setChannel(channel);
        openConnection(socket);
	}
	
	public void login(String name) {
		if (uuid != null) return;
		
		uuid = UUID.nameUUIDFromBytes(name.getBytes());
	}
	
	@Override
	public UUID getUUID() {
		return uuid;
	}
}
