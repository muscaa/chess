package muscaa.chess.client.events;

import fluff.events.IEventListener;
import muscaa.chess.board.piece.PieceEntry;
import muscaa.chess.client.board.TexturedPiece;
import muscaa.chess.events.RegistryEvent;
import muscaa.chess.registry.Registry;

public interface IRegisterTexturedPiecesEventListener extends IEventListener {
	
	void onRegisterTexturedPiecesEvent(RegisterTexturedPiecesEvent event);
	
	class RegisterTexturedPiecesEvent extends RegistryEvent<PieceEntry<? extends TexturedPiece>> {
		
		public RegisterTexturedPiecesEvent(Registry<PieceEntry<? extends TexturedPiece>> reg) {
			super(reg);
		}
	}
}
