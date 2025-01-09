package muscaa.chess.network;

import fluff.network.packet.PacketContext;
import muscaa.chess.network.connection.IServerConnectionNetHandler;
import muscaa.chess.network.connection.packets.PacketEncrypt;
import muscaa.chess.network.connection.packets.PacketHandshake;
import muscaa.chess.network.intent.IServerIntentNetHandler;
import muscaa.chess.network.intent.packets.SPacketIntent;
import muscaa.chess.network.intent.packets.SPacketIntentResponse;
import muscaa.chess.network.login.IServerLoginNetHandler;
import muscaa.chess.network.login.packets.PacketLogin;
import muscaa.chess.network.login.packets.PacketLoginSuccess;
import muscaa.chess.network.play.IServerPlayNetHandler;
import muscaa.chess.network.play.packets.PacketBoard;
import muscaa.chess.network.play.packets.PacketClickCell;
import muscaa.chess.network.play.packets.PacketGameEnd;
import muscaa.chess.network.play.packets.PacketHighlightCells;
import muscaa.chess.network.play.packets.PacketGameStart;
import muscaa.chess.network.play.packets.PacketTeam;

public class ServerContexts {
	
	public static final PacketContext<IServerIntentNetHandler> INTENT_CONTEXT =
			new PacketContext<IServerIntentNetHandler>(SharedContexts.INTENT_CONTEXT)
			.registerInbound(100, SPacketIntent::new, IServerIntentNetHandler::onPacketIntent)
			.registerOutbound(101, SPacketIntentResponse.class)
			;
	
	public static final PacketContext<IServerConnectionNetHandler> CONNECTION_CONTEXT =
			new PacketContext<IServerConnectionNetHandler>(SharedContexts.CONNECTION_CONTEXT)
			.registerInbound(200, PacketEncrypt::new, IServerConnectionNetHandler::onPacketEncrypt)
			.registerOutbound(201, PacketHandshake.class)
			;
	
	public static final PacketContext<IServerLoginNetHandler> LOGIN_CONTEXT =
			new PacketContext<IServerLoginNetHandler>(SharedContexts.LOGIN_CONTEXT)
			.registerInbound(300, PacketLogin::new, IServerLoginNetHandler::onPacketLogin)
			.registerOutbound(301, PacketLoginSuccess.class)
			;
	
	public static final PacketContext<IServerPlayNetHandler> PLAY_CONTEXT =
			new PacketContext<IServerPlayNetHandler>(SharedContexts.PLAY_CONTEXT)
			.registerOutbound(400, PacketGameStart.class)
			.registerOutbound(401, PacketBoard.class)
			.registerOutbound(402, PacketTeam.class)
			.registerInbound(403, PacketClickCell::new, IServerPlayNetHandler::onPacketClickCell)
			.registerOutbound(404, PacketHighlightCells.class)
			.registerOutbound(405, PacketGameEnd.class)
			;
}
