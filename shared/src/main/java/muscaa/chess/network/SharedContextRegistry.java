package muscaa.chess.network;

import fluff.network.packet.PacketContext;
import muscaa.chess.Chess;
import muscaa.chess.network.common.ICommonNetHandler;
import muscaa.chess.network.common.packets.PacketDisconnect;
import muscaa.chess.network.connection.IConnectionNetHandler;
import muscaa.chess.network.intent.IIntentNetHandler;
import muscaa.chess.network.login.ILoginNetHandler;
import muscaa.chess.network.play.IPlayNetHandler;
import muscaa.chess.registry.Registries;
import muscaa.chess.registry.Registry;
import muscaa.chess.registry.RegistryKey;

public class SharedContextRegistry {
	
	public static final Registry<SharedContextValue<?>> REG = Registries.create(Chess.NAMESPACE.path("shared_contexts"));
	
	private static final PacketContext<ICommonNetHandler> COMMON_CONTEXT =
			new PacketContext<ICommonNetHandler>("common_context")
					.register(100, PacketDisconnect.class, PacketDisconnect::new, ICommonNetHandler::onPacketDisconnect)
			;
	public static final RegistryKey<SharedContextValue<ICommonNetHandler>> COMMON = REG.register(Chess.NAMESPACE.path("common"),
			key -> new SharedContextValue<>(key, COMMON_CONTEXT));
	
	private static final PacketContext<IIntentNetHandler> INTENT_CONTEXT =
			new PacketContext<IIntentNetHandler>("intent_context")
					.extend(COMMON_CONTEXT)
			;
	public static final RegistryKey<SharedContextValue<IIntentNetHandler>> INTENT = REG.register(Chess.NAMESPACE.path("intent"),
			key -> new SharedContextValue<>(key, INTENT_CONTEXT));
	
	private static final PacketContext<IConnectionNetHandler> CONNECTION_CONTEXT =
			new PacketContext<IConnectionNetHandler>("connection_context")
					.extend(COMMON_CONTEXT)
			;
	public static final RegistryKey<SharedContextValue<IConnectionNetHandler>> CONNECTION = REG.register(Chess.NAMESPACE.path("connection"),
			key -> new SharedContextValue<>(key, CONNECTION_CONTEXT));
	
	private static final PacketContext<ILoginNetHandler> LOGIN_CONTEXT =
			new PacketContext<ILoginNetHandler>("login_context")
					.extend(COMMON_CONTEXT)
			;
	public static final RegistryKey<SharedContextValue<ILoginNetHandler>> LOGIN = REG.register(Chess.NAMESPACE.path("login"),
			key -> new SharedContextValue<>(key, LOGIN_CONTEXT));
	
	private static final PacketContext<IPlayNetHandler> PLAY_CONTEXT =
			new PacketContext<IPlayNetHandler>("play_context")
					.extend(COMMON_CONTEXT)
			;
	public static final RegistryKey<SharedContextValue<IPlayNetHandler>> PLAY = REG.register(Chess.NAMESPACE.path("play"),
			key -> new SharedContextValue<>(key, PLAY_CONTEXT));
	
	public static void init() {
		REG.init();
	}
}
