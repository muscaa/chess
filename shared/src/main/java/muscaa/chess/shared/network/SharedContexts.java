package muscaa.chess.shared.network;

import fluff.network.packet.PacketContext;
import muscaa.chess.shared.network.common.ICommonNetHandler;
import muscaa.chess.shared.network.common.packets.PacketDisconnect;
import muscaa.chess.shared.network.connection.IConnectionNetHandler;
import muscaa.chess.shared.network.login.ILoginNetHandler;
import muscaa.chess.shared.network.play.IPlayNetHandler;

public class SharedContexts {
	
	public static final PacketContext<ICommonNetHandler> COMMON_CONTEXT =
			new PacketContext<ICommonNetHandler>("common_context")
			.register(0, PacketDisconnect.class, PacketDisconnect::new, ICommonNetHandler::onPacketDisconnect)
			;
	
	public static final PacketContext<IConnectionNetHandler> CONNECTION_CONTEXT =
			new PacketContext<IConnectionNetHandler>("connection_context")
			.extend(COMMON_CONTEXT)
			;
	
	public static final PacketContext<ILoginNetHandler> LOGIN_CONTEXT =
			new PacketContext<ILoginNetHandler>("login_context")
			.extend(COMMON_CONTEXT)
			;
	
	public static final PacketContext<IPlayNetHandler> PLAY_CONTEXT =
			new PacketContext<IPlayNetHandler>("play_context")
			.extend(COMMON_CONTEXT)
			;
}
