package muscaa.chess.network.play.packets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;
import muscaa.chess.board.Cell;
import muscaa.chess.board.Highlight;
import muscaa.chess.network.PacketOutputUtils;

public class PacketHighlightCells implements IPacketOutbound {
	
	private Map<Cell, Highlight> highlights = new HashMap<>();
	
	public PacketHighlightCells(Map<Cell, Highlight> highlights) {
		this.highlights = highlights;
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.Int(highlights.size());
		for (Map.Entry<Cell, Highlight> highlightEntry : highlights.entrySet()) {
			PacketOutputUtils.cell(out, highlightEntry.getKey());
			PacketOutputUtils.regEntry(out, highlightEntry.getValue());
		}
	}
}
