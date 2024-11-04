package muscaa.chess.server.network;

import fluff.network.packet.PacketContext;
import muscaa.chess.server.network.connection.IServerConnectionNetHandler;
import muscaa.chess.server.network.connection.packets.PacketEncrypt;
import muscaa.chess.server.network.connection.packets.PacketHandshake;
import muscaa.chess.server.network.login.IServerLoginNetHandler;
import muscaa.chess.server.network.login.packets.PacketLogin;
import muscaa.chess.server.network.login.packets.PacketLoginSuccess;
import muscaa.chess.server.network.play.IServerPlayNetHandler;
import muscaa.chess.server.network.play.packet.PacketClickCell;
import muscaa.chess.server.network.play.packet.PacketEndGame;
import muscaa.chess.server.network.play.packet.PacketMove;
import muscaa.chess.server.network.play.packet.PacketSelectCell;
import muscaa.chess.server.network.play.packet.PacketStartGame;
import muscaa.chess.shared.network.SharedContexts;

public class ServerContexts {
	
	public static final PacketContext<IServerConnectionNetHandler> CONNECTION_CONTEXT =
			new PacketContext<IServerConnectionNetHandler>(SharedContexts.CONNECTION_CONTEXT)
			.registerInbound(100, PacketEncrypt::new, IServerConnectionNetHandler::onPacketEncrypt)
			.registerOutbound(101, PacketHandshake.class)
			;
	
	public static final PacketContext<IServerLoginNetHandler> LOGIN_CONTEXT =
			new PacketContext<IServerLoginNetHandler>(SharedContexts.LOGIN_CONTEXT)
			.registerInbound(200, PacketLogin::new, IServerLoginNetHandler::onPacketLogin)
			.registerOutbound(201, PacketLoginSuccess.class)
			;
	
	public static final PacketContext<IServerPlayNetHandler> PLAY_CONTEXT =
			new PacketContext<IServerPlayNetHandler>(SharedContexts.PLAY_CONTEXT)
			.registerInbound(300, PacketClickCell::new, IServerPlayNetHandler::onPacketClickCell)
			.registerOutbound(301, PacketStartGame.class)
			.registerOutbound(302, PacketEndGame.class)
			.registerOutbound(303, PacketMove.class)
			.registerOutbound(304, PacketSelectCell.class)
			;
}
