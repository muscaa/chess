package muscaa.chess.client.network;

import fluff.network.packet.PacketContext;
import muscaa.chess.Chess;
import muscaa.chess.client.network.connection.ClientConnectionNetHandler;
import muscaa.chess.client.network.connection.IClientConnectionNetHandler;
import muscaa.chess.client.network.connection.packets.CPacketEncrypt;
import muscaa.chess.client.network.connection.packets.CPacketHandshake;
import muscaa.chess.client.network.intent.IClientIntentNetHandler;
import muscaa.chess.client.network.intent.packets.CPacketIntent;
import muscaa.chess.client.network.intent.packets.CPacketIntentResponse;
import muscaa.chess.client.network.login.ClientLoginNetHandler;
import muscaa.chess.client.network.login.IClientLoginNetHandler;
import muscaa.chess.client.network.login.packets.CPacketLogin;
import muscaa.chess.client.network.login.packets.CPacketLoginForm;
import muscaa.chess.client.network.login.packets.CPacketLoginSuccess;
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
	
	private static final PacketContext<IClientIntentNetHandler> INTENT_CONTEXT =
			new PacketContext<IClientIntentNetHandler>(SharedContextRegistry.INTENT.get().getContext())
					.registerOutbound(100, CPacketIntent.class)
					.registerInbound(101, CPacketIntentResponse::new, IClientIntentNetHandler::onPacketIntentResponse)
			;
	public static final RegistryKey<ClientContextValue<IClientIntentNetHandler>> INTENT = REG.register(Chess.NAMESPACE.path("intent"),
			key -> new ClientContextValue<>(key, INTENT_CONTEXT, null));
	
	private static final PacketContext<IClientConnectionNetHandler> CONNECTION_CONTEXT =
			new PacketContext<IClientConnectionNetHandler>(SharedContextRegistry.CONNECTION.get().getContext())
					.registerOutbound(200, CPacketEncrypt.class)
					.registerInbound(201, CPacketHandshake::new, IClientConnectionNetHandler::onPacketHandshake)
			;
	public static final RegistryKey<ClientContextValue<IClientConnectionNetHandler>> CONNECTION = REG.register(Chess.NAMESPACE.path("connection"),
			key -> new ClientContextValue<>(key, CONNECTION_CONTEXT, ClientConnectionNetHandler::new));
	
	private static final PacketContext<IClientLoginNetHandler> LOGIN_CONTEXT =
			new PacketContext<IClientLoginNetHandler>(SharedContextRegistry.LOGIN.get().getContext())
					.registerInbound(300, CPacketLoginForm::new, IClientLoginNetHandler::onPacketLoginForm)
					.registerOutbound(301, CPacketLogin.class)
					.registerInbound(302, CPacketLoginSuccess::new, IClientLoginNetHandler::onPacketLoginSuccess)
			;
	public static final RegistryKey<ClientContextValue<IClientLoginNetHandler>> LOGIN = REG.register(Chess.NAMESPACE.path("login"),
			key -> new ClientContextValue<>(key, LOGIN_CONTEXT, ClientLoginNetHandler::new));
	
	private static final PacketContext<IClientPlayNetHandler> PLAY_CONTEXT =
			new PacketContext<IClientPlayNetHandler>(SharedContextRegistry.PLAY.get().getContext())
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
