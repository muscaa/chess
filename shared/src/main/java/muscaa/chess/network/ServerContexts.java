package muscaa.chess.network;

import fluff.network.packet.PacketContext;
import muscaa.chess.network.connection.IServerConnectionNetHandler;
import muscaa.chess.network.connection.packets.SPacketEncrypt;
import muscaa.chess.network.connection.packets.SPacketHandshake;
import muscaa.chess.network.intent.IServerIntentNetHandler;
import muscaa.chess.network.intent.packets.SPacketIntent;
import muscaa.chess.network.intent.packets.SPacketIntentResponse;
import muscaa.chess.network.login.IServerLoginNetHandler;
import muscaa.chess.network.login.packets.SPacketLogin;
import muscaa.chess.network.login.packets.SPacketLoginSuccess;
import muscaa.chess.network.play.IServerPlayNetHandler;
import muscaa.chess.network.play.packets.SPacketBoard;
import muscaa.chess.network.play.packets.SPacketClickCell;
import muscaa.chess.network.play.packets.SPacketGameEnd;
import muscaa.chess.network.play.packets.SPacketHighlightCells;
import muscaa.chess.network.play.packets.SPacketGameStart;
import muscaa.chess.network.play.packets.SPacketTeam;

public class ServerContexts {
	
	public static final PacketContext<IServerIntentNetHandler> INTENT_CONTEXT =
			new PacketContext<IServerIntentNetHandler>(SharedContexts.INTENT_CONTEXT)
			.registerInbound(100, SPacketIntent::new, IServerIntentNetHandler::onPacketIntent)
			.registerOutbound(101, SPacketIntentResponse.class)
			;
	
	public static final PacketContext<IServerConnectionNetHandler> CONNECTION_CONTEXT =
			new PacketContext<IServerConnectionNetHandler>(SharedContexts.CONNECTION_CONTEXT)
			.registerInbound(200, SPacketEncrypt::new, IServerConnectionNetHandler::onPacketEncrypt)
			.registerOutbound(201, SPacketHandshake.class)
			;
	
	public static final PacketContext<IServerLoginNetHandler> LOGIN_CONTEXT =
			new PacketContext<IServerLoginNetHandler>(SharedContexts.LOGIN_CONTEXT)
			.registerInbound(300, SPacketLogin::new, IServerLoginNetHandler::onPacketLogin)
			.registerOutbound(301, SPacketLoginSuccess.class)
			;
	
	public static final PacketContext<IServerPlayNetHandler> PLAY_CONTEXT =
			new PacketContext<IServerPlayNetHandler>(SharedContexts.PLAY_CONTEXT)
			.registerOutbound(400, SPacketGameStart.class)
			.registerOutbound(401, SPacketBoard.class)
			.registerOutbound(402, SPacketTeam.class)
			.registerInbound(403, SPacketClickCell::new, IServerPlayNetHandler::onPacketClickCell)
			.registerOutbound(404, SPacketHighlightCells.class)
			.registerOutbound(405, SPacketGameEnd.class)
			;
}
