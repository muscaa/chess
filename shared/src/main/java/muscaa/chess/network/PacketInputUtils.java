package muscaa.chess.network;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import muscaa.chess.board.Cell;
import muscaa.chess.board.TeamRegistry;
import muscaa.chess.board.TeamValue;
import muscaa.chess.board.piece.AbstractPieceValue;
import muscaa.chess.board.piece.IPiece;
import muscaa.chess.form.Form;
import muscaa.chess.form.FormData;
import muscaa.chess.form.field.FormField;
import muscaa.chess.form.field.FormFieldData;
import muscaa.chess.form.field.FormFieldRegistry;
import muscaa.chess.form.field.FormFieldValue;
import muscaa.chess.registry.IRegistryValue;
import muscaa.chess.registry.Registry;
import muscaa.chess.utils.NamespacePath;

public class PacketInputUtils {
	
	public static <V extends IRegistryValue> V regValue(IBinaryInput in, Registry<V> reg) throws IOException {
		return reg.get(NamespacePath.of(in.LenString())).get();
	}
	
	public static Cell cell(IBinaryInput in) throws IOException {
		return new Cell(in.Int(), in.Int());
	}
	
	public static <V extends AbstractPieceValue<V, P>, P extends IPiece> P piece(IBinaryInput in, Registry<V> reg) throws IOException {
		TeamValue team = regValue(in, TeamRegistry.REG);
		P piece = regValue(in, reg).create(team);
		return piece;
	}
	
	public static Form form(IBinaryInput in) throws IOException {
		Form form = new Form(in.LenString(), in.LenString(), in.LenString());
		
		int size = in.Int();
		for (int i = 0; i < size; i++) {
			String id = in.LenString();
			FormFieldValue fieldType = regValue(in, FormFieldRegistry.REG);
			String name = in.LenString();
			
			form.add(new FormField(id, fieldType, name));
		}
		
		return form;
	}
	
	public static FormData formData(IBinaryInput in) throws IOException {
		FormData formData = new FormData(in.LenString());
		
		int size = in.Int();
		for (int i = 0; i < size; i++) {
			String id = in.LenString();
			String value = in.LenString();
			
			formData.add(new FormFieldData(id, value));
		}
		
		return formData;
	}
}
