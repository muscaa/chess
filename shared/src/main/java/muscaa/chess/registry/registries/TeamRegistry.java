package muscaa.chess.registry.registries;

import muscaa.chess.Chess;
import muscaa.chess.board.Team;
import muscaa.chess.registry.Registries;
import muscaa.chess.registry.Registry;

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
