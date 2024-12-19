package muscaa.chess.shared.registry.registries;

import muscaa.chess.shared.Chess;
import muscaa.chess.shared.board.piece.move.AbstractMove;
import muscaa.chess.shared.board.piece.move.moves.BasicMove;
import muscaa.chess.shared.board.piece.move.moves.CaptureMove;
import muscaa.chess.shared.board.piece.move.moves.PromoteMove;
import muscaa.chess.shared.registry.Registries;
import muscaa.chess.shared.registry.Registry;

public class MoveRegistry {
	
	public static final Registry<AbstractMove> REG = Registries.create(Chess.NAMESPACE.path("moves"));
	
	public static final BasicMove BASIC = REG.register(new BasicMove(Chess.NAMESPACE.path("basic")));
	public static final CaptureMove CAPTURE = REG.register(new CaptureMove(Chess.NAMESPACE.path("capture")));
	public static final PromoteMove PROMOTE = REG.register(new PromoteMove(Chess.NAMESPACE.path("promote")));
	// castle
	
	public static void init() {}
}
