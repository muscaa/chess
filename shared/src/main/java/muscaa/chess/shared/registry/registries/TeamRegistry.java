package muscaa.chess.shared.registry.registries;

import muscaa.chess.shared.Chess;
import muscaa.chess.shared.board.Team;
import muscaa.chess.shared.registry.Registries;
import muscaa.chess.shared.registry.Registry;

public class TeamRegistry {
	
	public static final Registry<Team> REG = Registries.create(Chess.NAMESPACE.path("teams"));
	
	public static final Team NULL = REG.register(new Team(Chess.NAMESPACE.path("null")));
	public static final Team WHITE = REG.register(new Team(Chess.NAMESPACE.path("white")));
	public static final Team BLACK = REG.register(new Team(Chess.NAMESPACE.path("black")));
	
	public static Team invert(Team team) {
		if (team.equals(WHITE)) return BLACK;
		else if (team.equals(BLACK)) return WHITE;
		return NULL;
	}
	
	public static void init() {}
}
