package muscaa.chess.network;

import fluff.network.packet.PacketContext;
import muscaa.chess.network.connection.IServerConnectionNetHandler;
import muscaa.chess.network.connection.packets.PacketEncrypt;
import muscaa.chess.network.connection.packets.PacketHandshake;
import muscaa.chess.network.login.IServerLoginNetHandler;
import muscaa.chess.network.login.packets.PacketLogin;
import muscaa.chess.network.login.packets.PacketLoginSuccess;
import muscaa.chess.network.play.IServerPlayNetHandler;
import muscaa.chess.network.play.packets.PacketBoard;
import muscaa.chess.network.play.packets.PacketClickCell;
import muscaa.chess.network.play.packets.PacketEndGame;
import muscaa.chess.network.play.packets.PacketHighlightCells;
import muscaa.chess.network.play.packets.PacketStartGame;
import muscaa.chess.network.play.packets.PacketTeam;

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
			.registerOutbound(300, PacketStartGame.class)
			.registerOutbound(301, PacketBoard.class)
			.registerOutbound(302, PacketTeam.class)
			.registerInbound(303, PacketClickCell::new, IServerPlayNetHandler::onPacketClickCell)
			.registerOutbound(304, PacketHighlightCells.class)
			.registerOutbound(305, PacketEndGame.class)
			;
}
