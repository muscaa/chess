package muscaa.chess.network;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import muscaa.chess.board.Cell;
import muscaa.chess.board.TeamRegistry;
import muscaa.chess.board.TeamValue;
import muscaa.chess.board.piece.AbstractPieceValue;
import muscaa.chess.board.piece.IPiece;
import muscaa.chess.registry.IRegistryValue;
import muscaa.chess.registry.Registry;
import muscaa.chess.utils.NamespacePath;

public class PacketInputUtils {
	
	public static <V extends IRegistryValue> V regEntry(IBinaryInput in, Registry<V> reg) throws IOException {
		return reg.get(NamespacePath.of(in.LenString())).get();
	}
	
	public static Cell cell(IBinaryInput in) throws IOException {
		return new Cell(in.Int(), in.Int());
	}
	
	public static <V extends AbstractPieceValue<V, P>, P extends IPiece> P piece(IBinaryInput in, Registry<V> reg) throws IOException {
		TeamValue team = regEntry(in, TeamRegistry.REG);
		P piece = regEntry(in, reg).create(team);
		return piece;
	}
}
