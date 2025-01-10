package muscaa.chess.network;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import muscaa.chess.board.Cell;
import muscaa.chess.board.piece.IPiece;
import muscaa.chess.registry.IRegistryValue;

public class PacketOutputUtils {
	
	public static void regEntry(IBinaryOutput out, IRegistryValue regEntry) throws IOException {
		out.LenString(regEntry.getKey().getID().toString());
	}
	
	public static void cell(IBinaryOutput out, Cell cell) throws IOException {
		out.Int(cell.x);
		out.Int(cell.y);
	}
	
	public static void piece(IBinaryOutput out, IPiece piece) throws IOException {
		regEntry(out, piece.getTeam());
		regEntry(out, piece.getRegistryValue());
	}
}
