package muscaa.chess.events;

import fluff.events.IEventListener;
import muscaa.chess.board.piece.AbstractPiece;
import muscaa.chess.board.piece.PieceEntry;
import muscaa.chess.registry.Registry;

public interface IRegisterPiecesEventListener extends IEventListener {
	
	void onRegisterPiecesEvent(RegisterPiecesEvent event);
	
	class RegisterPiecesEvent extends RegistryEvent<PieceEntry<? extends AbstractPiece>> {
		
		public RegisterPiecesEvent(Registry<PieceEntry<? extends AbstractPiece>> reg) {
			super(reg);
		}
	}
}
