package muscaa.chess.network.intent.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;
import muscaa.chess.network.Intent;
import muscaa.chess.network.PacketInputUtils;
import muscaa.chess.registry.registries.IntentRegistry;

public class SPacketIntent implements IPacketInbound {
	
	private Intent intent;
	
	public SPacketIntent() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		intent = PacketInputUtils.regEntry(in, IntentRegistry.REG);
	}
	
	public Intent getIntent() {
		return intent;
	}
}
