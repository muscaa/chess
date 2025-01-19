package muscaa.chess.client.network;

import fluff.network.packet.PacketContext;
import muscaa.chess.Chess;
import muscaa.chess.client.network.common.IClientCommonNetHandler;
import muscaa.chess.client.network.common.packets.CPacketChangeContext;
import muscaa.chess.client.network.connection.ClientConnectionNetHandler;
import muscaa.chess.client.network.connection.IClientConnectionNetHandler;
import muscaa.chess.client.network.connection.packets.CPacketEncrypt;
import muscaa.chess.client.network.connection.packets.CPacketHandshake;
import muscaa.chess.client.network.intent.IClientIntentNetHandler;
import muscaa.chess.client.network.intent.packets.CPacketIntent;
import muscaa.chess.client.network.login.ClientLoginNetHandler;
import muscaa.chess.client.network.login.IClientLoginNetHandler;
import muscaa.chess.client.network.login.packets.CPacketLogin;
import muscaa.chess.client.network.login.packets.CPacketLoginForm;
import muscaa.chess.client.network.ping.ClientPingNetHandler;
import muscaa.chess.client.network.ping.IClientPingNetHandler;
import muscaa.chess.client.network.ping.packets.CPacketPing;
import muscaa.chess.client.network.play.ClientPlayNetHandler;
import muscaa.chess.client.network.play.IClientPlayNetHandler;
import muscaa.chess.client.network.play.packets.CPacketBoard;
import muscaa.chess.client.network.play.packets.CPacketClickCell;
import muscaa.chess.client.network.play.packets.CPacketGameEnd;
import muscaa.chess.client.network.play.packets.CPacketGameStart;
import muscaa.chess.client.network.play.packets.CPacketHighlightCells;
import muscaa.chess.client.network.play.packets.CPacketTeam;
import muscaa.chess.network.SharedContextRegistry;
import muscaa.chess.registry.Registries;
import muscaa.chess.registry.Registry;
import muscaa.chess.registry.RegistryKey;

public class ClientContextRegistry {
	
	public static final Registry<ClientContextValue<?>> REG = Registries.create(Chess.NAMESPACE.path("client_contexts"));
	
	public static final RegistryKey<ClientContextValue<IClientCommonNetHandler>> NULL = REG.register(Chess.NULL,
			key -> new ClientContextValue<>(key, null, null));
	
	private static final PacketContext<IClientCommonNetHandler> COMMON_CONTEXT =
			new PacketContext<IClientCommonNetHandler>(SharedContextRegistry.COMMON.get().getContext())
					.registerInbound(1, CPacketChangeContext::new, IClientCommonNetHandler::onPacketChangeContext)
			;
	
	private static final PacketContext<IClientIntentNetHandler> INTENT_CONTEXT =
			new PacketContext<IClientIntentNetHandler>(SharedContextRegistry.INTENT.get().getContext())
					.extend(COMMON_CONTEXT)
					.registerOutbound(100, CPacketIntent.class)
			;
	public static final RegistryKey<ClientContextValue<IClientIntentNetHandler>> INTENT = REG.register(Chess.NAMESPACE.path("intent"),
			key -> new ClientContextValue<>(key, INTENT_CONTEXT, null));
	
	private static final PacketContext<IClientPingNetHandler> PING_CONTEXT =
			new PacketContext<IClientPingNetHandler>(SharedContextRegistry.PING.get().getContext())
			        .extend(COMMON_CONTEXT)
					.registerInbound(200, CPacketPing::new, IClientPingNetHandler::onPacketPing)
			;
	public static final RegistryKey<ClientContextValue<IClientPingNetHandler>> PING = REG.register(Chess.NAMESPACE.path("ping"),
			key -> new ClientContextValue<>(key, PING_CONTEXT, ClientPingNetHandler::new));
	
	private static final PacketContext<IClientConnectionNetHandler> CONNECTION_CONTEXT =
			new PacketContext<IClientConnectionNetHandler>(SharedContextRegistry.CONNECTION.get().getContext())
			        .extend(COMMON_CONTEXT)
					.registerOutbound(200, CPacketEncrypt.class)
					.registerInbound(201, CPacketHandshake::new, IClientConnectionNetHandler::onPacketHandshake)
			;
	public static final RegistryKey<ClientContextValue<IClientConnectionNetHandler>> CONNECTION = REG.register(Chess.NAMESPACE.path("connection"),
			key -> new ClientContextValue<>(key, CONNECTION_CONTEXT, ClientConnectionNetHandler::new));
	
	private static final PacketContext<IClientLoginNetHandler> LOGIN_CONTEXT =
			new PacketContext<IClientLoginNetHandler>(SharedContextRegistry.LOGIN.get().getContext())
			        .extend(COMMON_CONTEXT)
					.registerInbound(300, CPacketLoginForm::new, IClientLoginNetHandler::onPacketLoginForm)
					.registerOutbound(301, CPacketLogin.class)
			;
	public static final RegistryKey<ClientContextValue<IClientLoginNetHandler>> LOGIN = REG.register(Chess.NAMESPACE.path("login"),
			key -> new ClientContextValue<>(key, LOGIN_CONTEXT, ClientLoginNetHandler::new));
	
	private static final PacketContext<IClientPlayNetHandler> PLAY_CONTEXT =
			new PacketContext<IClientPlayNetHandler>(SharedContextRegistry.PLAY.get().getContext())
			        .extend(COMMON_CONTEXT)
					.registerInbound(400, CPacketGameStart::new, IClientPlayNetHandler::onPacketStartGame)
					.registerInbound(401, CPacketBoard::new, IClientPlayNetHandler::onPacketBoard)
					.registerInbound(402, CPacketTeam::new, IClientPlayNetHandler::onPacketTeam)
					.registerOutbound(403, CPacketClickCell.class)
					.registerInbound(404, CPacketHighlightCells::new, IClientPlayNetHandler::onPacketHighlightCells)
					.registerInbound(405, CPacketGameEnd::new, IClientPlayNetHandler::onPacketEndGame)
			;
	public static final RegistryKey<ClientContextValue<IClientPlayNetHandler>> PLAY = REG.register(Chess.NAMESPACE.path("play"),
			key -> new ClientContextValue<>(key, PLAY_CONTEXT, ClientPlayNetHandler::new));
	
	public static void init() {
		REG.init();
	}
}
