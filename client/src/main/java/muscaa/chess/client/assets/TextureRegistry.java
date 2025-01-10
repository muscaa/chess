package muscaa.chess.client.assets;

import muscaa.chess.Chess;
import muscaa.chess.registry.Registries;
import muscaa.chess.registry.Registry;
import muscaa.chess.registry.RegistryKey;

public class TextureRegistry {
	
	public static final Registry<TextureValue> REG = Registries.create(Chess.NAMESPACE.path("textures"));
	
	public static final RegistryKey<TextureValue> NULL = REG.register(Chess.NAMESPACE.path("null"),
			key -> new TextureValue(key, null));
	public static final RegistryKey<TextureValue> WHITE_KING = REG.register(Chess.NAMESPACE.path("white_king"),
			key -> new TextureValue(key, "pieces/white_king.png"));
	public static final RegistryKey<TextureValue> WHITE_QUEEN = REG.register(Chess.NAMESPACE.path("white_queen"),
			key -> new TextureValue(key, "pieces/white_queen.png"));
	public static final RegistryKey<TextureValue> WHITE_BISHOP = REG.register(Chess.NAMESPACE.path("white_bishop"),
			key -> new TextureValue(key, "pieces/white_bishop.png"));
	public static final RegistryKey<TextureValue> WHITE_KNIGHT = REG.register(Chess.NAMESPACE.path("white_knight"),
			key -> new TextureValue(key, "pieces/white_knight.png"));
	public static final RegistryKey<TextureValue> WHITE_ROOK = REG.register(Chess.NAMESPACE.path("white_rook"),
			key -> new TextureValue(key, "pieces/white_rook.png"));
	public static final RegistryKey<TextureValue> WHITE_PAWN = REG.register(Chess.NAMESPACE.path("white_pawn"),
			key -> new TextureValue(key, "pieces/white_pawn.png"));
	public static final RegistryKey<TextureValue> BLACK_KING = REG.register(Chess.NAMESPACE.path("black_king"),
			key -> new TextureValue(key, "pieces/black_king.png"));
	public static final RegistryKey<TextureValue> BLACK_QUEEN = REG.register(Chess.NAMESPACE.path("black_queen"),
			key -> new TextureValue(key, "pieces/black_queen.png"));
	public static final RegistryKey<TextureValue> BLACK_BISHOP = REG.register(Chess.NAMESPACE.path("black_bishop"),
			key -> new TextureValue(key, "pieces/black_bishop.png"));
	public static final RegistryKey<TextureValue> BLACK_KNIGHT = REG.register(Chess.NAMESPACE.path("black_knight"),
			key -> new TextureValue(key, "pieces/black_knight.png"));
	public static final RegistryKey<TextureValue> BLACK_ROOK = REG.register(Chess.NAMESPACE.path("black_rook"),
			key -> new TextureValue(key, "pieces/black_rook.png"));
	public static final RegistryKey<TextureValue> BLACK_PAWN = REG.register(Chess.NAMESPACE.path("black_pawn"),
			key -> new TextureValue(key, "pieces/black_pawn.png"));
	
	public static final RegistryKey<TextureValue> WALLPAPER = REG.register(Chess.NAMESPACE.path("wallpaper"),
			key -> new TextureValue(key, "wallpaper.png"));
	public static final RegistryKey<TextureValue> WALLPAPER_BLUR = REG.register(Chess.NAMESPACE.path("wallpaper_blur"),
			key -> new TextureValue(key, "wallpaper_blur.png"));
	
	public static void init() {
		REG.init();
	}
	
	public static void dispose() {
		REG.forEach(TextureValue::dispose);
	}
}
