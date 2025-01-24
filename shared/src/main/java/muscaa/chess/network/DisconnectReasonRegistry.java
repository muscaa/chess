package muscaa.chess.network;

import muscaa.chess.Chess;
import muscaa.chess.registry.Registries;
import muscaa.chess.registry.Registry;
import muscaa.chess.registry.RegistryKey;

public class DisconnectReasonRegistry {
	
	public static final Registry<DisconnectReasonValue> REG = Registries.create(Chess.NAMESPACE.path("disconnect_reasons"));
	
	public static final RegistryKey<DisconnectReasonValue> NULL = REG.register(Chess.NULL, DisconnectReasonValue::new);
	public static final RegistryKey<DisconnectReasonValue> SERVER_STOP = REG.register(Chess.NAMESPACE.path("server_stop"), DisconnectReasonValue::new);
	public static final RegistryKey<DisconnectReasonValue> KICK = REG.register(Chess.NAMESPACE.path("kick"), DisconnectReasonValue::new);
	
	public static void init() {
		REG.init();
	}
}
