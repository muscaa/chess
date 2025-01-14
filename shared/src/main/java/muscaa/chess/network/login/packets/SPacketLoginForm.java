package muscaa.chess.network.login.packets;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;
import muscaa.chess.form.Form;
import muscaa.chess.network.PacketOutputUtils;

public class SPacketLoginForm implements IPacketOutbound {
	
	private Form form;
	
	public SPacketLoginForm(Form form) {
		this.form = form;
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		PacketOutputUtils.form(out, form);
	}
}
