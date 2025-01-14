package muscaa.chess.network;

import fluff.functions.gen.Func;
import fluff.network.packet.PacketContext;
import muscaa.chess.registry.RegistryKey;

public class ServerContextValue<V extends IServerNetHandler> extends AbstractContextValue<ServerContextValue<V>, V> {
	
	public ServerContextValue(RegistryKey<ServerContextValue<V>> key, PacketContext<V> context, Func<V> handlerFunc) {
		super(key, context, handlerFunc);
	}
}
