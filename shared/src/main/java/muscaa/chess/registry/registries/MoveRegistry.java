package muscaa.chess.registry.registries;

import muscaa.chess.Chess;
import muscaa.chess.board.piece.move.AbstractMove;
import muscaa.chess.board.piece.move.moves.BasicMove;
import muscaa.chess.board.piece.move.moves.CaptureMove;
import muscaa.chess.board.piece.move.moves.PromoteMove;
import muscaa.chess.registry.Registries;
import muscaa.chess.registry.Registry;

public class MoveRegistry {
	
	public static final Registry<AbstractMove> REG = Registries.create(Chess.NAMESPACE.path("moves"));
	
	public static final BasicMove BASIC = REG.register(new BasicMove(Chess.NAMESPACE.path("basic")));
	public static final CaptureMove CAPTURE = REG.register(new CaptureMove(Chess.NAMESPACE.path("capture")));
	public static final PromoteMove PROMOTE = REG.register(new PromoteMove(Chess.NAMESPACE.path("promote")));
	// castle
	
	public static void init() {}
}
