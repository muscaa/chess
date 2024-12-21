package muscaa.chess.client.network;

import fluff.network.packet.PacketContext;
import muscaa.chess.client.network.connection.IClientConnectionNetHandler;
import muscaa.chess.client.network.connection.packets.CPacketEncrypt;
import muscaa.chess.client.network.connection.packets.CPacketHandshake;
import muscaa.chess.client.network.login.IClientLoginNetHandler;
import muscaa.chess.client.network.login.packets.CPacketLogin;
import muscaa.chess.client.network.login.packets.CPacketLoginSuccess;
import muscaa.chess.client.network.play.IClientPlayNetHandler;
import muscaa.chess.client.network.play.packets.CPacketBoard;
import muscaa.chess.client.network.play.packets.CPacketClickCell;
import muscaa.chess.client.network.play.packets.CPacketEndGame;
import muscaa.chess.client.network.play.packets.CPacketHighlightCells;
import muscaa.chess.client.network.play.packets.CPacketStartGame;
import muscaa.chess.client.network.play.packets.CPacketTeam;
import muscaa.chess.network.SharedContexts;

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