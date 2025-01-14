package muscaa.chess.client.network.intent.packets;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;
import muscaa.chess.network.IntentValue;
import muscaa.chess.network.PacketOutputUtils;

public class CPacketIntent implements IPacketOutbound {
	
	private IntentValue intent;
	
	public CPacketIntent(IntentValue intent) {
		this.intent = intent;
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		PacketOutputUtils.regValue(out, intent);
	}
}
