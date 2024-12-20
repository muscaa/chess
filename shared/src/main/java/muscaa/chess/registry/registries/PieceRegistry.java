package muscaa.chess.registry.registries;

import muscaa.chess.Chess;
import muscaa.chess.board.piece.AbstractPiece;
import muscaa.chess.board.piece.PieceEntry;
import muscaa.chess.board.piece.pieces.BishopPiece;
import muscaa.chess.board.piece.pieces.KingPiece;
import muscaa.chess.board.piece.pieces.KnightPiece;
import muscaa.chess.board.piece.pieces.NullPiece;
import muscaa.chess.board.piece.pieces.PawnPiece;
import muscaa.chess.board.piece.pieces.QueenPiece;
import muscaa.chess.board.piece.pieces.RookPiece;
import muscaa.chess.registry.Registries;
import muscaa.chess.registry.Registry;

public class PieceRegistry {
	
	public static final Registry<PieceEntry<? extends AbstractPiece>> REG = Registries.create(Chess.NAMESPACE.path("pieces"));
	
	public static final PieceEntry<NullPiece> NULL = REG.register(new PieceEntry<>(Chess.NAMESPACE.path("null"), (e, c) -> NullPiece.INSTANCE));
	
	public static final PieceEntry<PawnPiece> PAWN = REG.register(new PieceEntry<>(Chess.NAMESPACE.path("pawn"), PawnPiece::new));
	public static final PieceEntry<RookPiece> ROOK = REG.register(new PieceEntry<>(Chess.NAMESPACE.path("rook"), RookPiece::new));
	public static final PieceEntry<KnightPiece> KNIGHT = REG.register(new PieceEntry<>(Chess.NAMESPACE.path("knight"), KnightPiece::new));
	public static final PieceEntry<BishopPiece> BISHOP = REG.register(new PieceEntry<>(Chess.NAMESPACE.path("bishop"), BishopPiece::new));
	public static final PieceEntry<QueenPiece> QUEEN = REG.register(new PieceEntry<>(Chess.NAMESPACE.path("queen"), QueenPiece::new));
	public static final PieceEntry<KingPiece> KING = REG.register(new PieceEntry<>(Chess.NAMESPACE.path("king"), KingPiece::new));
	
	public static void init() {}
}
