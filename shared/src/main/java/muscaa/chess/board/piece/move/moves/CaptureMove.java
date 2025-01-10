package muscaa.chess.board.piece.move.moves;

import muscaa.chess.board.piece.move.AbstractMoveValue;
import muscaa.chess.registry.RegistryKey;

public class CaptureMove extends BasicMove {
	
	public CaptureMove(RegistryKey<AbstractMoveValue> key) {
		super(key);
	}
}
