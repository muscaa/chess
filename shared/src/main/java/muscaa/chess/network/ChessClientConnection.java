package muscaa.chess.network;

import java.util.UUID;

import fluff.network.INetHandler;
import fluff.network.packet.IPacketChannel;
import fluff.network.packet.PacketContext;
import fluff.network.server.AbstractClientConnection;
import fluff.network.server.AbstractServer;
import muscaa.chess.network.common.packets.PacketDisconnect;
import muscaa.chess.network.common.packets.SPacketChangeContext;

public class ChessClientConnection extends AbstractClientConnection {
	
	private UUID uuid;
	
	public ChessClientConnection(AbstractServer server, PacketContext<?> context, INetHandler handler, IPacketChannel channel) {
		super(server);
		
		setContextUnsafe(context, handler);
        setChannel(channel);
	}
	
	public <V extends IServerNetHandler> void setContext(ServerContextValue<V> context) {
		setContext(context, context.getHandlerFunc().invoke());
	}
	
	public <V extends IServerNetHandler> void setContext(ServerContextValue<V> context, V handler) {
		send(new SPacketChangeContext(context));
		setContextUnsafe(context.getContext(), handler);
	}
	
	public void login(String name) {
		if (uuid != null) return;
		
		uuid = UUID.nameUUIDFromBytes(name.getBytes());
	}
	
	public void disconnect(String message) {
		send(new PacketDisconnect(message));
		disconnect();
	}
	
	@Override
	public UUID getUUID() {
		return uuid;
	}
}
