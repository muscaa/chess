package muscaa.chess.network;

import fluff.network.packet.PacketContext;
import muscaa.chess.network.common.ICommonNetHandler;
import muscaa.chess.network.common.packets.PacketDisconnect;
import muscaa.chess.network.connection.IConnectionNetHandler;
import muscaa.chess.network.intent.IIntentNetHandler;
import muscaa.chess.network.login.ILoginNetHandler;
import muscaa.chess.network.play.IPlayNetHandler;

public class SharedContexts {
	
	public static final PacketContext<ICommonNetHandler> COMMON_CONTEXT =
			new PacketContext<ICommonNetHandler>("common_context")
			.register(100, PacketDisconnect.class, PacketDisconnect::new, ICommonNetHandler::onPacketDisconnect)
			;
	
	public static final PacketContext<IIntentNetHandler> INTENT_CONTEXT =
			new PacketContext<IIntentNetHandler>("intent_context")
			.extend(COMMON_CONTEXT)
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
