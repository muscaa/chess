package muscaa.chess.board.piece.move;

import muscaa.chess.Chess;
import muscaa.chess.board.piece.move.moves.BasicMove;
import muscaa.chess.board.piece.move.moves.CaptureMove;
import muscaa.chess.board.piece.move.moves.CastleMove;
import muscaa.chess.board.piece.move.moves.PromoteMove;
import muscaa.chess.registry.Registries;
import muscaa.chess.registry.Registry;
import muscaa.chess.registry.RegistryKey;

public class MoveRegistry {
	
	public static final Registry<AbstractMoveValue> REG = Registries.create(Chess.NAMESPACE.path("moves"));
	
	public static final RegistryKey<AbstractMoveValue> BASIC = REG.register(Chess.NAMESPACE.path("basic"), BasicMove::new);
	public static final RegistryKey<AbstractMoveValue> CAPTURE = REG.register(Chess.NAMESPACE.path("capture"), CaptureMove::new);
	public static final RegistryKey<AbstractMoveValue> PROMOTE = REG.register(Chess.NAMESPACE.path("promote"), PromoteMove::new);
	public static final RegistryKey<AbstractMoveValue> CASTLE = REG.register(Chess.NAMESPACE.path("castle"), CastleMove::new);
	
	public static void init() {
		REG.init();
	}
}
