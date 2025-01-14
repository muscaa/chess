package muscaa.chess.client.assets;

import muscaa.chess.Chess;
import muscaa.chess.registry.Registries;
import muscaa.chess.registry.Registry;
import muscaa.chess.registry.RegistryKey;

public class FontRegistry {
	
	public static final Registry<FontValue> REG = Registries.create(Chess.NAMESPACE.path("fonts"));
	
	public static final RegistryKey<FontValue> NULL = REG.register(Chess.NULL,
			key -> new FontValue(key, null, 0));
	public static final RegistryKey<FontValue> VARELA_18 = REG.register(Chess.NAMESPACE.path("varela_18"),
			key -> new FontValue(key, "fonts/VarelaRound-Regular.ttf", 18));
	public static final RegistryKey<FontValue> VARELA_24 = REG.register(Chess.NAMESPACE.path("varela_24"),
			key -> new FontValue(key, "fonts/VarelaRound-Regular.ttf", 24));
	public static final RegistryKey<FontValue> MONTEZ_128 = REG.register(Chess.NAMESPACE.path("montez_128"),
			key -> new FontValue(key, "fonts/Montez-Regular.ttf", 128));
	
	public static void init() {
		REG.init();
	}
	
	public static void dispose() {
		REG.forEach(FontValue::dispose);
	}
}
