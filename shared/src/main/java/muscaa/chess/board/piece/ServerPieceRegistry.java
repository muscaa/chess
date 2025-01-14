package muscaa.chess.board.piece;

import muscaa.chess.Chess;
import muscaa.chess.board.piece.pieces.BishopPiece;
import muscaa.chess.board.piece.pieces.KingPiece;
import muscaa.chess.board.piece.pieces.KnightPiece;
import muscaa.chess.board.piece.pieces.NullPiece;
import muscaa.chess.board.piece.pieces.PawnPiece;
import muscaa.chess.board.piece.pieces.QueenPiece;
import muscaa.chess.board.piece.pieces.RookPiece;
import muscaa.chess.registry.Registries;
import muscaa.chess.registry.Registry;
import muscaa.chess.registry.RegistryKey;

public class ServerPieceRegistry {
	
	public static final Registry<ServerPieceValue> REG = Registries.create(Chess.NAMESPACE.path("server_pieces"));
	
	public static final RegistryKey<ServerPieceValue> NULL = REG.register(Chess.NULL,
			key -> new ServerPieceValue(key, team -> NullPiece.INSTANCE));
	public static final RegistryKey<ServerPieceValue> PAWN = REG.register(Chess.NAMESPACE.path("pawn"),
			key -> new ServerPieceValue(key, PawnPiece::new));
	public static final RegistryKey<ServerPieceValue> ROOK = REG.register(Chess.NAMESPACE.path("rook"),
			key -> new ServerPieceValue(key, RookPiece::new));
	public static final RegistryKey<ServerPieceValue> KNIGHT = REG.register(Chess.NAMESPACE.path("knight"),
			key -> new ServerPieceValue(key, KnightPiece::new));
	public static final RegistryKey<ServerPieceValue> BISHOP = REG.register(Chess.NAMESPACE.path("bishop"),
			key -> new ServerPieceValue(key, BishopPiece::new));
	public static final RegistryKey<ServerPieceValue> QUEEN = REG.register(Chess.NAMESPACE.path("queen"),
			key -> new ServerPieceValue(key, QueenPiece::new));
	public static final RegistryKey<ServerPieceValue> KING = REG.register(Chess.NAMESPACE.path("king"),
			key -> new ServerPieceValue(key, KingPiece::new));
	
	public static void init() {
		REG.init();
	}
}
