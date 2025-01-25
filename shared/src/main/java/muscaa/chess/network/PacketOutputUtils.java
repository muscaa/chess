package muscaa.chess.network;

import java.io.IOException;

import fluff.bin.IBinaryOutput;
import muscaa.chess.board.Cell;
import muscaa.chess.board.piece.IPiece;
import muscaa.chess.form.Form;
import muscaa.chess.form.FormData;
import muscaa.chess.registry.IRegistryValue;

public class PacketOutputUtils {
	
	public static void regValue(IBinaryOutput out, IRegistryValue regEntry) throws IOException {
		out.LenString(regEntry.getKey().getID().toString());
	}
	
	public static void cell(IBinaryOutput out, Cell cell) throws IOException {
		out.Int(cell.x);
		out.Int(cell.y);
	}
	
	public static void piece(IBinaryOutput out, IPiece piece) throws IOException {
		regValue(out, piece.getTeam());
		regValue(out, piece.getRegistryValue());
	}
	
	public static void form(IBinaryOutput out, Form form) throws IOException {
		out.Data(form);
	}
	
	public static void formData(IBinaryOutput out, FormData formData) throws IOException {
		out.Data(formData);
	}
}
