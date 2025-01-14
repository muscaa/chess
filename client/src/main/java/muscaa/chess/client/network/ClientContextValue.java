package muscaa.chess.client.network;

import fluff.functions.gen.Func;
import fluff.network.packet.PacketContext;
import muscaa.chess.network.AbstractContextValue;
import muscaa.chess.registry.RegistryKey;

public class ClientContextValue<V extends IClientNetHandler> extends AbstractContextValue<ClientContextValue<V>, V> {
	
	public ClientContextValue(RegistryKey<ClientContextValue<V>> key, PacketContext<V> context, Func<V> handlerFunc) {
		super(key, context, handlerFunc);
	}
}
