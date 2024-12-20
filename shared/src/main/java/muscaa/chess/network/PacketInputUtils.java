package muscaa.chess.network;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import muscaa.chess.board.Cell;
import muscaa.chess.board.Team;
import muscaa.chess.board.piece.IPiece;
import muscaa.chess.board.piece.PieceEntry;
import muscaa.chess.registry.IRegistryEntry;
import muscaa.chess.registry.Registry;
import muscaa.chess.registry.registries.TeamRegistry;
import muscaa.chess.utils.NamespacePath;

public class PacketInputUtils {
	
	public static <V extends IRegistryEntry> V regEntry(IBinaryInput in, Registry<V> reg) throws IOException {
		return reg.get(NamespacePath.of(in.LenString()));
	}
	
	public static Cell cell(IBinaryInput in) throws IOException {
		return new Cell(in.Int(), in.Int());
	}
	
	public static <P extends IPiece> P piece(IBinaryInput in, Registry<PieceEntry<? extends P>> reg) throws IOException {
		Team team = regEntry(in, TeamRegistry.REG);
		P piece = regEntry(in, reg).create(team);
		//in.Data(piece);
		return piece;
	}
}
