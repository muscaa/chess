package muscaa.chess.client.registries;

import muscaa.chess.Chess;
import muscaa.chess.board.piece.PieceEntry;
import muscaa.chess.client.board.TexturedPiece;
import muscaa.chess.client.events.IRegisterTexturedPiecesEventListener;
import muscaa.chess.registry.Registries;
import muscaa.chess.registry.Registry;

public class TexturedPieceRegistry {
	
	public static final Registry<PieceEntry<? extends TexturedPiece>> REG = Registries.create(Chess.NAMESPACE.path("client_pieces"));
	
	public static final PieceEntry<TexturedPiece> NULL = REG.register(new PieceEntry<>(Chess.NAMESPACE.path("null"), TexturedPiece::new));
	
	public static final PieceEntry<TexturedPiece> PAWN = REG.register(new PieceEntry<>(Chess.NAMESPACE.path("pawn"), TexturedPiece::new));
	public static final PieceEntry<TexturedPiece> ROOK = REG.register(new PieceEntry<>(Chess.NAMESPACE.path("rook"), TexturedPiece::new));
	public static final PieceEntry<TexturedPiece> KNIGHT = REG.register(new PieceEntry<>(Chess.NAMESPACE.path("knight"), TexturedPiece::new));
	public static final PieceEntry<TexturedPiece> BISHOP = REG.register(new PieceEntry<>(Chess.NAMESPACE.path("bishop"), TexturedPiece::new));
	public static final PieceEntry<TexturedPiece> QUEEN = REG.register(new PieceEntry<>(Chess.NAMESPACE.path("queen"), TexturedPiece::new));
	public static final PieceEntry<TexturedPiece> KING = REG.register(new PieceEntry<>(Chess.NAMESPACE.path("king"), TexturedPiece::new));
	
	public static void init() {
		Chess.EVENTS.call(
				IRegisterTexturedPiecesEventListener.class,
				IRegisterTexturedPiecesEventListener::onRegisterTexturedPiecesEvent,
				new IRegisterTexturedPiecesEventListener.RegisterTexturedPiecesEvent(
						REG
						)
				);
		
		REG.lock();
	}
}
