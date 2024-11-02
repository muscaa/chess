package muscaa.chess.server.network;

import fluff.network.packet.PacketContext;
import muscaa.chess.server.network.connection.IServerConnectionNetHandler;
import muscaa.chess.server.network.connection.packets.PacketEncrypt;
import muscaa.chess.server.network.connection.packets.PacketHandshake;
import muscaa.chess.server.network.login.IServerLoginNetHandler;
import muscaa.chess.server.network.login.packets.PacketLogin;
import muscaa.chess.server.network.login.packets.PacketLoginSuccess;
import muscaa.chess.server.network.play.IServerPlayNetHandler;
import muscaa.chess.server.network.play.packet.PacketBoard;
import muscaa.chess.server.network.play.packet.PacketClickCell;
import muscaa.chess.server.network.play.packet.PacketMove;
import muscaa.chess.shared.network.CommonContexts;

public class ServerContexts {
	
	public static final PacketContext<IServerConnectionNetHandler> CONNECTION_CONTEXT =
			new PacketContext<IServerConnectionNetHandler>(CommonContexts.CONNECTION_CONTEXT)
			.registerInbound(0, PacketEncrypt::new, IServerConnectionNetHandler::onPacketEncrypt)
			.registerOutbound(1, PacketHandshake.class)
			;
	
	public static final PacketContext<IServerLoginNetHandler> LOGIN_CONTEXT =
			new PacketContext<IServerLoginNetHandler>(CommonContexts.LOGIN_CONTEXT)
			.registerInbound(0, PacketLogin::new, IServerLoginNetHandler::onPacketLogin)
			.registerOutbound(1, PacketLoginSuccess.class)
			;
	
	public static final PacketContext<IServerPlayNetHandler> PLAY_CONTEXT =
			new PacketContext<IServerPlayNetHandler>(CommonContexts.PLAY_CONTEXT)
			.registerInbound(0, PacketClickCell::new, IServerPlayNetHandler::onPacketClickCell)
			.registerOutbound(1, PacketBoard.class)
			.registerOutbound(2, PacketMove.class)
			;
}
