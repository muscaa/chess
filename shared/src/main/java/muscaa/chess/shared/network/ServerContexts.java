package muscaa.chess.shared.network;

import fluff.network.packet.PacketContext;
import muscaa.chess.shared.network.connection.IServerConnectionNetHandler;
import muscaa.chess.shared.network.connection.packets.PacketEncrypt;
import muscaa.chess.shared.network.connection.packets.PacketHandshake;
import muscaa.chess.shared.network.login.IServerLoginNetHandler;
import muscaa.chess.shared.network.login.packets.PacketLogin;
import muscaa.chess.shared.network.login.packets.PacketLoginSuccess;
import muscaa.chess.shared.network.play.IServerPlayNetHandler;
import muscaa.chess.shared.network.play.packets.PacketBoard;
import muscaa.chess.shared.network.play.packets.PacketClickCell;
import muscaa.chess.shared.network.play.packets.PacketTeam;
import muscaa.chess.shared.network.play.packets.PacketEndGame;
import muscaa.chess.shared.network.play.packets.PacketHighlightCells;
import muscaa.chess.shared.network.play.packets.PacketStartGame;

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
