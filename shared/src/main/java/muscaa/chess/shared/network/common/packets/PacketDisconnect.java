package muscaa.chess.shared.network.common.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacket;

public class PacketDisconnect implements IPacket {
	
	private String message;
	
	public PacketDisconnect(String message) {
		this.message = message;
	}
	
	public PacketDisconnect() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		message = in.LenString();
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.LenString(message);
	}
	
	public String getMessage() {
		return message;
	}
}
