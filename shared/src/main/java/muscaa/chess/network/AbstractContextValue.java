package muscaa.chess.network;

import fluff.functions.gen.Func;
import fluff.network.INetHandler;
import fluff.network.packet.PacketContext;
import muscaa.chess.registry.IRegistryValue;
import muscaa.chess.registry.RegistryKey;

public abstract class AbstractContextValue<V extends AbstractContextValue<V, H>, H extends INetHandler> implements IRegistryValue<V> {
	
	private final RegistryKey<V> key;
	private PacketContext<H> context;
	private Func<? extends H> handlerFunc;
	
	public AbstractContextValue(RegistryKey<V> key, PacketContext<H> context, Func<H> handlerFunc) {
		this.key = key;
		this.context = context;
		this.handlerFunc = handlerFunc;
	}
	
    public PacketContext<H> getContext() {
		return context;
	}
    
    public void setContext(PacketContext<H> context) {
		this.context = context;
	}
    
    public Func<? extends H> getHandlerFunc() {
		return handlerFunc;
	}
    
    public void setHandlerFunc(Func<? extends H> handlerFunc) {
		this.handlerFunc = handlerFunc;
	}
	
	@Override
	public RegistryKey<V> getKey() {
		return key;
	}
}
