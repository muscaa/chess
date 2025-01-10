package muscaa.chess.client.network.play.packets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;
import muscaa.chess.board.Cell;
import muscaa.chess.board.Highlight;
import muscaa.chess.board.HighlightRegistry;
import muscaa.chess.board.HighlightValue;
import muscaa.chess.network.PacketInputUtils;

public class CPacketHighlightCells implements IPacketInbound {
	
	private List<Highlight> highlights = new LinkedList<>();
	
	public CPacketHighlightCells() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		int len = in.Int();
		for (int i = 0; i < len; i++) {
			Cell cell = PacketInputUtils.cell(in);
			HighlightValue highlight = PacketInputUtils.regEntry(in, HighlightRegistry.REG);
			
			highlights.add(new Highlight(cell, highlight));
		}
	}
	
	public List<Highlight> getHighlights() {
		return highlights;
	}
}
