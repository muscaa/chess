package muscaa.chess.events;

import fluff.events.IEventListener;
import muscaa.chess.board.Team;
import muscaa.chess.registry.Registry;

public interface IRegisterTeamsEventListener extends IEventListener {
	
	void onRegisterTeamsEvent(RegisterTeamsEvent event);
	
	class RegisterTeamsEvent extends RegistryEvent<Team> {
		
		public RegisterTeamsEvent(Registry<Team> reg) {
			super(reg);
		}
	}
}
