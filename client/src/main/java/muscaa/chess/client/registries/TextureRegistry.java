package muscaa.chess.client.registries;

import java.util.Map;

import muscaa.chess.Chess;
import muscaa.chess.client.assets.TextureAsset;
import muscaa.chess.registry.Registries;
import muscaa.chess.registry.Registry;
import muscaa.chess.utils.NamespacePath;

public class TextureRegistry {
	
	public static final Registry<TextureAsset> REG = Registries.create(Chess.NAMESPACE.path("textures"));
	
	public static final TextureAsset NULL = REG.register(new TextureAsset(Chess.NAMESPACE.path("null"), null));
	public static final TextureAsset WHITE_KING = REG.register(new TextureAsset(Chess.NAMESPACE.path("white_king"), "pieces/white_king.png"));
	public static final TextureAsset WHITE_QUEEN = REG.register(new TextureAsset(Chess.NAMESPACE.path("white_queen"), "pieces/white_queen.png"));
	public static final TextureAsset WHITE_BISHOP = REG.register(new TextureAsset(Chess.NAMESPACE.path("white_bishop"), "pieces/white_bishop.png"));
	public static final TextureAsset WHITE_KNIGHT = REG.register(new TextureAsset(Chess.NAMESPACE.path("white_knight"), "pieces/white_knight.png"));
	public static final TextureAsset WHITE_ROOK = REG.register(new TextureAsset(Chess.NAMESPACE.path("white_rook"), "pieces/white_rook.png"));
	public static final TextureAsset WHITE_PAWN = REG.register(new TextureAsset(Chess.NAMESPACE.path("white_pawn"), "pieces/white_pawn.png"));
	public static final TextureAsset BLACK_KING = REG.register(new TextureAsset(Chess.NAMESPACE.path("black_king"), "pieces/black_king.png"));
	public static final TextureAsset BLACK_QUEEN = REG.register(new TextureAsset(Chess.NAMESPACE.path("black_queen"), "pieces/black_queen.png"));
	public static final TextureAsset BLACK_BISHOP = REG.register(new TextureAsset(Chess.NAMESPACE.path("black_bishop"), "pieces/black_bishop.png"));
	public static final TextureAsset BLACK_KNIGHT = REG.register(new TextureAsset(Chess.NAMESPACE.path("black_knight"), "pieces/black_knight.png"));
	public static final TextureAsset BLACK_ROOK = REG.register(new TextureAsset(Chess.NAMESPACE.path("black_rook"), "pieces/black_rook.png"));
	public static final TextureAsset BLACK_PAWN = REG.register(new TextureAsset(Chess.NAMESPACE.path("black_pawn"), "pieces/black_pawn.png"));
	
	public static final TextureAsset WALLPAPER = REG.register(new TextureAsset(Chess.NAMESPACE.path("wallpaper"), "wallpaper.png"));
	public static final TextureAsset WALLPAPER_BLUR = REG.register(new TextureAsset(Chess.NAMESPACE.path("wallpaper_blur"), "wallpaper_blur.png"));
	
	public static void init() {
		REG.lock();
	}
	
	public static void dispose() {
		for (Map.Entry<NamespacePath, TextureAsset> assetEntry : REG.getContents().entrySet()) {
			assetEntry.getValue().dispose();
		}
	}
}
