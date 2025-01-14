package muscaa.chess.network;

import fluff.network.packet.PacketContext;
import muscaa.chess.Chess;
import muscaa.chess.network.connection.IServerConnectionNetHandler;
import muscaa.chess.network.connection.ServerConnectionNetHandler;
import muscaa.chess.network.connection.packets.SPacketEncrypt;
import muscaa.chess.network.connection.packets.SPacketHandshake;
import muscaa.chess.network.intent.IServerIntentNetHandler;
import muscaa.chess.network.intent.ServerIntentNetHandler;
import muscaa.chess.network.intent.packets.SPacketIntent;
import muscaa.chess.network.intent.packets.SPacketIntentResponse;
import muscaa.chess.network.login.IServerLoginNetHandler;
import muscaa.chess.network.login.ServerLoginNetHandler;
import muscaa.chess.network.login.packets.SPacketLogin;
import muscaa.chess.network.login.packets.SPacketLoginForm;
import muscaa.chess.network.login.packets.SPacketLoginSuccess;
import muscaa.chess.network.play.IServerPlayNetHandler;
import muscaa.chess.network.play.packets.SPacketBoard;
import muscaa.chess.network.play.packets.SPacketClickCell;
import muscaa.chess.network.play.packets.SPacketGameEnd;
import muscaa.chess.network.play.packets.SPacketGameStart;
import muscaa.chess.network.play.packets.SPacketHighlightCells;
import muscaa.chess.network.play.packets.SPacketTeam;
import muscaa.chess.registry.Registries;
import muscaa.chess.registry.Registry;
import muscaa.chess.registry.RegistryKey;

public class ServerContextRegistry {
	
	public static final Registry<ServerContextValue<?>> REG = Registries.create(Chess.NAMESPACE.path("server_contexts"));
	
	private static final PacketContext<IServerIntentNetHandler> INTENT_CONTEXT =
			new PacketContext<IServerIntentNetHandler>(SharedContextRegistry.INTENT.get().getContext())
					.registerInbound(100, SPacketIntent::new, IServerIntentNetHandler::onPacketIntent)
					.registerOutbound(101, SPacketIntentResponse.class)
			;
	public static final RegistryKey<ServerContextValue<IServerIntentNetHandler>> INTENT = REG.register(Chess.NAMESPACE.path("intent"),
			key -> new ServerContextValue<>(key, INTENT_CONTEXT, ServerIntentNetHandler::new));
	
	private static final PacketContext<IServerConnectionNetHandler> CONNECTION_CONTEXT =
			new PacketContext<IServerConnectionNetHandler>(SharedContextRegistry.CONNECTION.get().getContext())
					.registerInbound(200, SPacketEncrypt::new, IServerConnectionNetHandler::onPacketEncrypt)
					.registerOutbound(201, SPacketHandshake.class)
			;
	public static final RegistryKey<ServerContextValue<IServerConnectionNetHandler>> CONNECTION = REG.register(Chess.NAMESPACE.path("connection"),
			key -> new ServerContextValue<>(key, CONNECTION_CONTEXT, ServerConnectionNetHandler::new));
	
	private static final PacketContext<IServerLoginNetHandler> LOGIN_CONTEXT =
			new PacketContext<IServerLoginNetHandler>(SharedContextRegistry.LOGIN.get().getContext())
					.registerOutbound(300, SPacketLoginForm.class)
					.registerInbound(301, SPacketLogin::new, IServerLoginNetHandler::onPacketLogin)
					.registerOutbound(302, SPacketLoginSuccess.class)
			;
	public static final RegistryKey<ServerContextValue<IServerLoginNetHandler>> LOGIN = REG.register(Chess.NAMESPACE.path("login"),
			key -> new ServerContextValue<>(key, LOGIN_CONTEXT, ServerLoginNetHandler::new));
	
	private static final PacketContext<IServerPlayNetHandler> PLAY_CONTEXT =
			new PacketContext<IServerPlayNetHandler>(SharedContextRegistry.PLAY.get().getContext())
					.registerOutbound(400, SPacketGameStart.class)
					.registerOutbound(401, SPacketBoard.class)
					.registerOutbound(402, SPacketTeam.class)
					.registerInbound(403, SPacketClickCell::new, IServerPlayNetHandler::onPacketClickCell)
					.registerOutbound(404, SPacketHighlightCells.class)
					.registerOutbound(405, SPacketGameEnd.class)
			;
	public static final RegistryKey<ServerContextValue<IServerPlayNetHandler>> PLAY = REG.register(Chess.NAMESPACE.path("play"),
			key -> new ServerContextValue<>(key, PLAY_CONTEXT, null));
	
	public static void init() {
		REG.init();
	}
}
