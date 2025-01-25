package muscaa.chess.client.network.base.packets;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;
import muscaa.chess.form.Form;
import muscaa.chess.network.PacketInputUtils;

public class CPacketForm implements IPacketInbound {
	
	private Form form;
	
	public CPacketForm() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		form = PacketInputUtils.form(in);
	}
	
	public Form getForm() {
		return form;
	}
}
