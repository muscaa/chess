package muscaa.chess.network.play.packets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import fluff.bin.IBinaryOutput;
import fluff.network.packet.IPacketOutbound;
import muscaa.chess.board.Highlight;
import muscaa.chess.network.PacketOutputUtils;

public class PacketHighlightCells implements IPacketOutbound {
	
	private List<Highlight> highlights = new LinkedList<>();
	
	public PacketHighlightCells(List<Highlight> highlights) {
		this.highlights = highlights;
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.Int(highlights.size());
		for (Highlight highlight : highlights) {
			PacketOutputUtils.cell(out, highlight.getCell());
			PacketOutputUtils.regEntry(out, highlight.getType());
		}
	}
}
