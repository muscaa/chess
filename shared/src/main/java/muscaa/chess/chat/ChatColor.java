package muscaa.chess.chat;

public enum ChatColor {
	BLACK('0', 0, 0, 0),
	DARK_BLUE('1', 0, 0, 170),
	DARK_GREEN('2', 0, 170, 0),
	DARK_AQUA('3', 0, 170, 170),
	DARK_RED('4', 170, 0, 0),
	DARK_PURPLE('5', 170, 0, 170),
	GOLD('6', 255, 170, 0),
	GRAY('7', 170, 170, 170),
	DARK_GRAY('8', 85, 85, 85),
	BLUE('9', 85, 85, 255),
	GREEN('a', 85, 255, 85),
	AQUA('b', 85, 255, 255),
	RED('c', 255, 85, 85),
	LIGHT_PURPLE('d', 255, 85, 255),
	YELLOW('e', 255, 255, 85),
	WHITE('f', 255, 255, 255),
	;
	
	private final char code;
	private final int red;
	private final int green;
	private final int blue;
	private final float floatBits;
	
	private ChatColor(char code, int red, int green, int blue) {
		this.code = code;
		this.red = red;
		this.green = green;
		this.blue = blue;
		
		int color = (255 << 24) | (blue << 16) | (green << 8) | (red);
		this.floatBits = Float.intBitsToFloat(color & 0xfeffffff);
	}
	
	public char getCode() {
		return code;
	}
	
	public int getRed() {
		return red;
	}
	
	public int getGreen() {
		return green;
	}
	
	public int getBlue() {
		return blue;
	}
	
	public float getFloatBits() {
		return floatBits;
	}
	
	public static ChatColor of(char code) {
		if (code >= '0' && code <= '9') {
			return values()[code - '0'];
		}
		if (code >= 'a' && code <= 'f') {
			return values()[code - 'a' + 10];
		}
		return WHITE;
	}
}
