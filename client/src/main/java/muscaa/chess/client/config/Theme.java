package muscaa.chess.client.config;

import com.badlogic.gdx.graphics.Color;

import muscaa.chess.client.utils.RenderUtils;

public class Theme {
	
	public static final boolean INVERT_TABLE_IF_BLACK = true; // rotate table
	
	public static final Color BOARD_CELL_LIGHT = RenderUtils.color(235, 236, 208);
	public static final Color BOARD_CELL_DARK = RenderUtils.color(115, 149, 82);
	public static final Color BOARD_CELL_SELECTED = RenderUtils.color(235, 236, 104);
	public static final Color BOARD_CELL_CHECK = RenderUtils.color(235, 82, 82);
	public static final Color BOARD_CELL_MOVE_AVAILABLE = RenderUtils.color(100, 103, 106, 200);
	public static final Color BOARD_CELL_LAST_MOVE = RenderUtils.color(82, 82, 235);
	
	public static final Color GUISCREEN_BACKGROUND = RenderUtils.color(20, 20, 20, 150);
	public static final Color PANEL = RenderUtils.color(30, 30, 30, 100);
}
