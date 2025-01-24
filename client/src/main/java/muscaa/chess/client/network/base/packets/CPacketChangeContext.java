package muscaa.chess.client.network.base.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;
import muscaa.chess.client.network.ClientContextRegistry;
import muscaa.chess.client.network.ClientContextValue;
import muscaa.chess.network.PacketInputUtils;

public class CPacketChangeContext implements IPacketInbound {
	
	private ClientContextValue<?> context;
	
	public CPacketChangeContext() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		context = PacketInputUtils.regValue(in, ClientContextRegistry.REG);
	}
	
	public ClientContextValue<?> getContext() {
		return context;
	}
}
