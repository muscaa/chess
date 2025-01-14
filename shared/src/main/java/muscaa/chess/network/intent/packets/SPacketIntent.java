package muscaa.chess.network.intent.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;
import muscaa.chess.network.IntentRegistry;
import muscaa.chess.network.IntentValue;
import muscaa.chess.network.PacketInputUtils;

public class SPacketIntent implements IPacketInbound {
	
	private IntentValue intent;
	
	public SPacketIntent() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		intent = PacketInputUtils.regValue(in, IntentRegistry.REG);
	}
	
	public IntentValue getIntent() {
		return intent;
	}
}
