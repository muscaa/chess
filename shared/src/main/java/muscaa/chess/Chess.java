package muscaa.chess;

import java.io.IOException;
import java.io.InputStream;

import muscaa.chess.utils.Namespace;

public class Chess {
	
	public static final Namespace NAMESPACE = Namespace.of("chess");
	public static final String VERSION = getVersion();
	
	private static String getVersion() {
		try (InputStream in = Chess.class.getResourceAsStream("/version.txt")) {
			return new String(in.readAllBytes());
		} catch (IOException e) {}
		return "unknown";
	}
}
