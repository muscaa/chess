package muscaa.chess.network;

import fluff.network.packet.PacketContext;
import muscaa.chess.network.connection.IClientConnectionNetHandler;
import muscaa.chess.network.connection.packets.CPacketEncrypt;
import muscaa.chess.network.connection.packets.CPacketHandshake;
import muscaa.chess.network.login.IClientLoginNetHandler;
import muscaa.chess.network.login.packets.CPacketLogin;
import muscaa.chess.network.login.packets.CPacketLoginSuccess;
import muscaa.chess.network.play.IClientPlayNetHandler;
import muscaa.chess.network.play.packets.CPacketBoard;
import muscaa.chess.network.play.packets.CPacketClickCell;
import muscaa.chess.network.play.packets.CPacketEndGame;
import muscaa.chess.network.play.packets.CPacketHighlightCells;
import muscaa.chess.network.play.packets.CPacketStartGame;
import muscaa.chess.network.play.packets.CPacketTeam;
import muscaa.chess.shared.network.SharedContexts;

public class ClientContexts {
	
	public static final PacketContext<IClientConnectionNetHandler> CONNECTION_CONTEXT =
			new PacketContext<IClientConnectionNetHandler>(SharedContexts.CONNECTION_CONTEXT)
			.registerOutbound(100, CPacketEncrypt.class)
			.registerInbound(101, CPacketHandshake::new, IClientConnectionNetHandler::onPacketHandshake)
			;
	
	public static final PacketContext<IClientLoginNetHandler> LOGIN_CONTEXT =
			new PacketContext<IClientLoginNetHandler>(SharedContexts.LOGIN_CONTEXT)
			.registerOutbound(200, CPacketLogin.class)
			.registerInbound(201, CPacketLoginSuccess::new, IClientLoginNetHandler::onPacketLoginSuccess)
			;
	
	public static final PacketContext<IClientPlayNetHandler> PLAY_CONTEXT =
			new PacketContext<IClientPlayNetHandler>(SharedContexts.PLAY_CONTEXT)
			.registerInbound(300, CPacketStartGame::new, IClientPlayNetHandler::onPacketStartGame)
			.registerInbound(301, CPacketBoard::new, IClientPlayNetHandler::onPacketBoard)
			.registerInbound(302, CPacketTeam::new, IClientPlayNetHandler::onPacketTeam)
			.registerOutbound(303, CPacketClickCell.class)
			.registerInbound(304, CPacketHighlightCells::new, IClientPlayNetHandler::onPacketHighlightCells)
			.registerInbound(305, CPacketEndGame::new, IClientPlayNetHandler::onPacketEndGame)
			;
}
