package muscaa.chess.network;

import fluff.network.packet.PacketContext;
import muscaa.chess.Chess;
import muscaa.chess.network.base.IServerBaseNetHandler;
import muscaa.chess.network.base.packets.SPacketChangeContext;
import muscaa.chess.network.common.IServerCommonNetHandler;
import muscaa.chess.network.common.packets.SPacketChatLine;
import muscaa.chess.network.common.packets.SPacketChatMessage;
import muscaa.chess.network.common.packets.SPacketJoinBoard;
import muscaa.chess.network.common.packets.SPacketLeaveBoard;
import muscaa.chess.network.connection.IServerConnectionNetHandler;
import muscaa.chess.network.connection.ServerConnectionNetHandler;
import muscaa.chess.network.connection.packets.SPacketEncrypt;
import muscaa.chess.network.connection.packets.SPacketHandshake;
import muscaa.chess.network.intent.IServerIntentNetHandler;
import muscaa.chess.network.intent.ServerIntentNetHandler;
import muscaa.chess.network.intent.packets.SPacketIntent;
import muscaa.chess.network.login.IServerLoginNetHandler;
import muscaa.chess.network.login.packets.SPacketLogin;
import muscaa.chess.network.login.packets.SPacketLoginForm;
import muscaa.chess.network.login.packets.SPacketProfile;
import muscaa.chess.network.ping.IServerPingNetHandler;
import muscaa.chess.network.ping.ServerPingNetHandler;
import muscaa.chess.network.ping.packets.SPacketPing;
import muscaa.chess.network.play.IServerPlayNetHandler;
import muscaa.chess.network.play.packets.SPacketBoard;
import muscaa.chess.network.play.packets.SPacketClickCell;
import muscaa.chess.network.play.packets.SPacketGameEnd;
import muscaa.chess.network.play.packets.SPacketGameStart;
import muscaa.chess.network.play.packets.SPacketHighlightCells;
import muscaa.chess.network.play.packets.SPacketTeam;
import muscaa.chess.network.play.packets.SPacketTurn;
import muscaa.chess.registry.Registries;
import muscaa.chess.registry.Registry;
import muscaa.chess.registry.RegistryKey;

public class ServerContextRegistry {
	
	public static final Registry<ServerContextValue<?>> REG = Registries.create(Chess.NAMESPACE.path("server_contexts"));
	
	public static final RegistryKey<ServerContextValue<IServerBaseNetHandler>> NULL = REG.register(Chess.NULL,
			key -> new ServerContextValue<>(key, null, null));
	
	//
	// BASE
	//
	private static final PacketContext<IServerBaseNetHandler> BASE_CONTEXT =
			new PacketContext<IServerBaseNetHandler>(SharedContextRegistry.BASE.get().getContext())
			        .registerOutbound(1, SPacketChangeContext.class)
			;
	public static final RegistryKey<ServerContextValue<IServerBaseNetHandler>> BASE = REG.register(Chess.NAMESPACE.path("base"),
			key -> new ServerContextValue<>(key, BASE_CONTEXT, null));
	
	//
	// INTENT
	//
	private static final PacketContext<IServerIntentNetHandler> INTENT_CONTEXT =
			new PacketContext<IServerIntentNetHandler>(SharedContextRegistry.INTENT.get().getContext())
					.extend(BASE_CONTEXT)
					.registerInbound(100, SPacketIntent::new, IServerIntentNetHandler::onPacketIntent)
			;
	public static final RegistryKey<ServerContextValue<IServerIntentNetHandler>> INTENT = REG.register(Chess.NAMESPACE.path("intent"),
			key -> new ServerContextValue<>(key, INTENT_CONTEXT, ServerIntentNetHandler::new));
	
	//
	// PING
	//
	private static final PacketContext<IServerPingNetHandler> PING_CONTEXT =
			new PacketContext<IServerPingNetHandler>(SharedContextRegistry.PING.get().getContext())
			        .extend(BASE_CONTEXT)
					.registerOutbound(200, SPacketPing.class)
			;
	public static final RegistryKey<ServerContextValue<IServerPingNetHandler>> PING = REG.register(Chess.NAMESPACE.path("ping"),
			key -> new ServerContextValue<>(key, PING_CONTEXT, ServerPingNetHandler::new));
	
	//
	// CONNECTION
	//
	private static final PacketContext<IServerConnectionNetHandler> CONNECTION_CONTEXT =
			new PacketContext<IServerConnectionNetHandler>(SharedContextRegistry.CONNECTION.get().getContext())
			        .extend(BASE_CONTEXT)
					.registerInbound(200, SPacketEncrypt::new, IServerConnectionNetHandler::onPacketEncrypt)
					.registerOutbound(201, SPacketHandshake.class)
			;
	public static final RegistryKey<ServerContextValue<IServerConnectionNetHandler>> CONNECTION = REG.register(Chess.NAMESPACE.path("connection"),
			key -> new ServerContextValue<>(key, CONNECTION_CONTEXT, ServerConnectionNetHandler::new));
	
	//
	// LOGIN
	//
	private static final PacketContext<IServerLoginNetHandler> LOGIN_CONTEXT =
			new PacketContext<IServerLoginNetHandler>(SharedContextRegistry.LOGIN.get().getContext())
			        .extend(BASE_CONTEXT)
					.registerOutbound(300, SPacketLoginForm.class)
					.registerInbound(301, SPacketLogin::new, IServerLoginNetHandler::onPacketLogin)
					.registerOutbound(302, SPacketProfile.class)
			;
	public static final RegistryKey<ServerContextValue<IServerLoginNetHandler>> LOGIN = REG.register(Chess.NAMESPACE.path("login"),
			key -> new ServerContextValue<>(key, LOGIN_CONTEXT, null));
	
	//
	// COMMON
	//
	private static final PacketContext<IServerCommonNetHandler> COMMON_CONTEXT =
			new PacketContext<IServerCommonNetHandler>(SharedContextRegistry.COMMON.get().getContext())
			        .extend(BASE_CONTEXT)
			        .registerInbound(500, SPacketChatMessage::new, IServerCommonNetHandler::onPacketChatMessage)
			        .registerOutbound(501, SPacketChatLine.class)
			        .registerOutbound(503, SPacketJoinBoard.class)
			        .registerOutbound(504, SPacketLeaveBoard.class)
			;
	public static final RegistryKey<ServerContextValue<IServerCommonNetHandler>> COMMON = REG.register(Chess.NAMESPACE.path("common"),
			key -> new ServerContextValue<>(key, COMMON_CONTEXT, null));
	
	//
	// PLAY
	//
	private static final PacketContext<IServerPlayNetHandler> PLAY_CONTEXT =
			new PacketContext<IServerPlayNetHandler>(SharedContextRegistry.PLAY.get().getContext())
			        .extend(COMMON_CONTEXT)
					.registerOutbound(400, SPacketGameStart.class)
					.registerOutbound(401, SPacketBoard.class)
					.registerOutbound(402, SPacketTeam.class)
					.registerInbound(403, SPacketClickCell::new, IServerPlayNetHandler::onPacketClickCell)
					.registerOutbound(404, SPacketHighlightCells.class)
					.registerOutbound(405, SPacketGameEnd.class)
					.registerOutbound(406, SPacketTurn.class)
			;
	public static final RegistryKey<ServerContextValue<IServerPlayNetHandler>> PLAY = REG.register(Chess.NAMESPACE.path("play"),
			key -> new ServerContextValue<>(key, PLAY_CONTEXT, null));
	
	public static void init() {
		REG.init();
	}
}
