package muscaa.chess.client.network;

import fluff.network.packet.PacketContext;
import muscaa.chess.client.network.connection.IClientConnectionNetHandler;
import muscaa.chess.client.network.connection.packets.CPacketEncrypt;
import muscaa.chess.client.network.connection.packets.CPacketHandshake;
import muscaa.chess.client.network.intent.IClientIntentNetHandler;
import muscaa.chess.client.network.intent.packets.CPacketIntent;
import muscaa.chess.client.network.intent.packets.CPacketIntentResponse;
import muscaa.chess.client.network.login.IClientLoginNetHandler;
import muscaa.chess.client.network.login.packets.CPacketLogin;
import muscaa.chess.client.network.login.packets.CPacketLoginSuccess;
import muscaa.chess.client.network.play.IClientPlayNetHandler;
import muscaa.chess.client.network.play.packets.CPacketBoard;
import muscaa.chess.client.network.play.packets.CPacketClickCell;
import muscaa.chess.client.network.play.packets.CPacketGameEnd;
import muscaa.chess.client.network.play.packets.CPacketHighlightCells;
import muscaa.chess.client.network.play.packets.CPacketGameStart;
import muscaa.chess.client.network.play.packets.CPacketTeam;
import muscaa.chess.network.SharedContexts;

public class ClientContexts {
	
	/*public static final PacketContext<IClientIntentNetHandler> INTENT_CONTEXT =
			new PacketContext<IClientIntentNetHandler>(SharedContexts.INTENT_CONTEXT)
			.registerOutbound(100, CPacketIntent.class)
			.registerInbound(101, CPacketIntentResponse::new, IClientIntentNetHandler::onPacketIntentResponse)
			;
	
	public static final PacketContext<IClientConnectionNetHandler> CONNECTION_CONTEXT =
			new PacketContext<IClientConnectionNetHandler>(SharedContexts.CONNECTION_CONTEXT)
			.registerOutbound(200, CPacketEncrypt.class)
			.registerInbound(201, CPacketHandshake::new, IClientConnectionNetHandler::onPacketHandshake)
			;
	
	public static final PacketContext<IClientLoginNetHandler> LOGIN_CONTEXT =
			new PacketContext<IClientLoginNetHandler>(SharedContexts.LOGIN_CONTEXT)
			.registerOutbound(300, CPacketLogin.class)
			.registerInbound(301, CPacketLoginSuccess::new, IClientLoginNetHandler::onPacketLoginSuccess)
			;
	
	public static final PacketContext<IClientPlayNetHandler> PLAY_CONTEXT =
			new PacketContext<IClientPlayNetHandler>(SharedContexts.PLAY_CONTEXT)
			.registerInbound(400, CPacketGameStart::new, IClientPlayNetHandler::onPacketStartGame)
			.registerInbound(401, CPacketBoard::new, IClientPlayNetHandler::onPacketBoard)
			.registerInbound(402, CPacketTeam::new, IClientPlayNetHandler::onPacketTeam)
			.registerOutbound(403, CPacketClickCell.class)
			.registerInbound(404, CPacketHighlightCells::new, IClientPlayNetHandler::onPacketHighlightCells)
			.registerInbound(405, CPacketGameEnd::new, IClientPlayNetHandler::onPacketEndGame)
			;*/
}
