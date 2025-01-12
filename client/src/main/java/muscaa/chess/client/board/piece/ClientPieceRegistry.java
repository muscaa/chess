package muscaa.chess.client.board.piece;

import muscaa.chess.Chess;
import muscaa.chess.registry.Registries;
import muscaa.chess.registry.Registry;
import muscaa.chess.registry.RegistryKey;

public class ClientPieceRegistry {
	
	public static final Registry<ClientPieceValue> REG = Registries.create(Chess.NAMESPACE.path("client_pieces"));
	
	public static final RegistryKey<ClientPieceValue> NULL = REG.register(Chess.NAMESPACE.path("null"), ClientPieceValue::new);
	public static final RegistryKey<ClientPieceValue> PAWN = REG.register(Chess.NAMESPACE.path("pawn"), ClientPieceValue::new);
	public static final RegistryKey<ClientPieceValue> ROOK = REG.register(Chess.NAMESPACE.path("rook"), ClientPieceValue::new);
	public static final RegistryKey<ClientPieceValue> KNIGHT = REG.register(Chess.NAMESPACE.path("knight"), ClientPieceValue::new);
	public static final RegistryKey<ClientPieceValue> BISHOP = REG.register(Chess.NAMESPACE.path("bishop"), ClientPieceValue::new);
	public static final RegistryKey<ClientPieceValue> QUEEN = REG.register(Chess.NAMESPACE.path("queen"), ClientPieceValue::new);
	public static final RegistryKey<ClientPieceValue> KING = REG.register(Chess.NAMESPACE.path("king"), ClientPieceValue::new);
	
	public static void init() {
		REG.init();
	}
}
