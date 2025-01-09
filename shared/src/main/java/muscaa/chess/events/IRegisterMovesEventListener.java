package muscaa.chess.events;

import fluff.events.IEventListener;
import muscaa.chess.board.piece.move.AbstractMove;
import muscaa.chess.registry.Registry;

public interface IRegisterMovesEventListener extends IEventListener {
	
	void onRegisterMovesEvent(RegisterMovesEvent event);
	
	class RegisterMovesEvent extends RegistryEvent<AbstractMove> {
		
		public RegisterMovesEvent(Registry<AbstractMove> reg) {
			super(reg);
		}
	}
}
