package muscaa.chess.network.base.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;
import muscaa.chess.form.FormData;
import muscaa.chess.network.PacketInputUtils;

public class SPacketFormData implements IPacketInbound {
	
	private FormData formData;
	
	public SPacketFormData() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		formData = PacketInputUtils.formData(in);
	}
	
	public FormData getFormData() {
		return formData;
	}
}
