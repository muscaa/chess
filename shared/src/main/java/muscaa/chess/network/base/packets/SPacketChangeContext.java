package muscaa.chess.network.base.packets;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;
import muscaa.chess.network.PacketOutputUtils;
import muscaa.chess.network.ServerContextValue;

public class SPacketChangeContext implements IPacketOutbound {
	
	private ServerContextValue<?> context;
	
	public SPacketChangeContext(ServerContextValue<?> context) {
		this.context = context;
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		PacketOutputUtils.regValue(out, context);
	}
}
