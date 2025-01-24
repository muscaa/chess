package muscaa.chess.network.base.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacket;
import muscaa.chess.network.DisconnectReasonRegistry;
import muscaa.chess.network.DisconnectReasonValue;
import muscaa.chess.network.PacketInputUtils;
import muscaa.chess.network.PacketOutputUtils;

public class PacketDisconnect implements IPacket {
	
	private DisconnectReasonValue reason;
	private String message;
	
	public PacketDisconnect(DisconnectReasonValue reason, String message) {
		this.reason = reason;
		this.message = message;
	}
	
	public PacketDisconnect() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		reason = PacketInputUtils.regValue(in, DisconnectReasonRegistry.REG);
		message = in.LenString();
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		PacketOutputUtils.regValue(out, reason);
		out.LenString(message);
	}
	
	public DisconnectReasonValue getReason() {
		return reason;
	}
	
	public String getMessage() {
		return message;
	}
}
