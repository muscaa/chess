package muscaa.chess.client.network.login.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;
import muscaa.chess.form.Form;
import muscaa.chess.network.PacketInputUtils;

public class CPacketLoginForm implements IPacketInbound {
	
	private Form form;
	
	public CPacketLoginForm() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		form = PacketInputUtils.form(in);
	}
	
	public Form getForm() {
		return form;
	}
}
