package muscaa.chess.shared;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacket;
import fluff.network.packet.PacketContext;

public class PacketMessage implements IPacket {
	
	public static final PacketContext<IMessageNetHandler> COMMON_CONTEXT = new PacketContext<IMessageNetHandler>("common_context")
			.register(1, PacketMessage.class, PacketMessage::new, IMessageNetHandler::onPacketMessage)
			;
	
	private String message;
	
	public PacketMessage(String message) {
		this.message = message;
	}
	
	public PacketMessage() {}
	
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
