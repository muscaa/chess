package muscaa.chess.network;

import fluff.network.INetHandler;
import fluff.network.packet.PacketContext;
import muscaa.chess.registry.RegistryKey;

public class SharedContextValue<V extends INetHandler> extends AbstractContextValue<SharedContextValue<V>, V> {
	
	public SharedContextValue(RegistryKey<SharedContextValue<V>> key, PacketContext<V> context) {
		super(key, context, null);
	}
}
