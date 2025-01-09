package muscaa.chess.events;

import fluff.events.IEventListener;
import muscaa.chess.board.HighlightType;
import muscaa.chess.registry.Registry;

public interface IRegisterHighlightsEventListener extends IEventListener {
	
	void onRegisterHighlightsEvent(RegisterHighlightsEvent event);
	
	class RegisterHighlightsEvent extends RegistryEvent<HighlightType> {
		
		public RegisterHighlightsEvent(Registry<HighlightType> reg) {
			super(reg);
		}
	}
}
