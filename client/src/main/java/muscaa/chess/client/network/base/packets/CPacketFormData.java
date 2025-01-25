package muscaa.chess.client.network.base.packets;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;
import muscaa.chess.form.FormData;
import muscaa.chess.network.PacketOutputUtils;

public class CPacketFormData implements IPacketOutbound {
	
	private FormData formData;
	
	public CPacketFormData(FormData formData) {
		this.formData = formData;
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		PacketOutputUtils.formData(out, formData);
	}
}
