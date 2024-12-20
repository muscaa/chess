package muscaa.chess.network.play.packets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import fluff.bin.IBinaryInput;
import fluff.network.packet.IPacketInbound;
import muscaa.chess.shared.board.Cell;
import muscaa.chess.shared.board.Highlight;
import muscaa.chess.shared.network.PacketInputUtils;
import muscaa.chess.shared.registry.registries.HighlightRegistry;

public class CPacketHighlightCells implements IPacketInbound {
	
	private Map<Cell, Highlight> highlights = new HashMap<>();
	
	public CPacketHighlightCells() {}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		int len = in.Int();
		for (int i = 0; i < len; i++) {
			Cell cell = PacketInputUtils.cell(in);
			Highlight highlight = PacketInputUtils.regEntry(in, HighlightRegistry.REG);
			
			highlights.put(cell, highlight);
		}
	}
	
	public Map<Cell, Highlight> getHighlights() {
		return highlights;
	}
}
