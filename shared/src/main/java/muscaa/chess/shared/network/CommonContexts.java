package muscaa.chess.shared.network;

import fluff.network.packet.PacketContext;
import muscaa.chess.shared.network.connection.IConnectionNetHandler;
import muscaa.chess.shared.network.login.ILoginNetHandler;
import muscaa.chess.shared.network.play.IPlayNetHandler;

public class CommonContexts {
	
	public static final PacketContext<IConnectionNetHandler> CONNECTION_CONTEXT =
			new PacketContext<IConnectionNetHandler>("connection_context")
			;
	
	public static final PacketContext<ILoginNetHandler> LOGIN_CONTEXT =
			new PacketContext<ILoginNetHandler>("login_context")
			;
	
	public static final PacketContext<IPlayNetHandler> PLAY_CONTEXT =
			new PacketContext<IPlayNetHandler>("play_context")
			;
}
