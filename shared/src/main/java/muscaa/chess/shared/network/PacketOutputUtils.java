package muscaa.chess.shared.network;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import muscaa.chess.shared.board.Cell;
import muscaa.chess.shared.board.piece.AbstractPiece;
import muscaa.chess.shared.registry.IRegistryEntry;

public class PacketOutputUtils {
	
	public static void regEntry(IBinaryOutput out, IRegistryEntry regEntry) throws IOException {
		out.LenString(regEntry.getID().toString());
	}
	
	public static void cell(IBinaryOutput out, Cell cell) throws IOException {
		out.Int(cell.x);
		out.Int(cell.y);
	}
	
	public static void piece(IBinaryOutput out, AbstractPiece piece) throws IOException {
		regEntry(out, piece.getTeam());
		regEntry(out, piece.getRegistryEntry());
		//out.Data(piece);
	}
}
