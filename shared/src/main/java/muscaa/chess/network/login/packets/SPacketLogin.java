package muscaa.chess.network.login.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;
import muscaa.chess.form.FormData;
import muscaa.chess.network.PacketInputUtils;

public class SPacketLogin implements IPacketInbound {
	
	private FormData formData;
	
	public SPacketLogin() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		formData = PacketInputUtils.formData(in);
	}
	
	public FormData getFormData() {
		return formData;
	}
}
