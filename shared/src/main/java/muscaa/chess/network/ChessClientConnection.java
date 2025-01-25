package muscaa.chess.network;

import java.util.UUID;

import fluff.network.INetHandler;
import fluff.network.packet.IPacketChannel;
import fluff.network.packet.PacketContext;
import fluff.network.server.AbstractClientConnection;
import fluff.network.server.AbstractServer;
import muscaa.chess.network.base.packets.PacketDisconnect;
import muscaa.chess.network.base.packets.SPacketChangeContext;
import muscaa.chess.player.AbstractServerPlayer;

public class ChessClientConnection extends AbstractClientConnection {
	
	private UUID uuid;
	public AbstractServerPlayer player;
	
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
	
	public void login(UUID uuid) {
		if (this.uuid != null) return;
		
		this.uuid = uuid;
	}
	
	public void disconnect(DisconnectReasonValue reason, String message) {
		send(new PacketDisconnect(reason, message));
		disconnect();
	}
	
	@Override
	public UUID getUUID() {
		return uuid;
	}
}
