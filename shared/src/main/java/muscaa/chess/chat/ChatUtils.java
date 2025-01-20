package muscaa.chess.chat;

import java.util.LinkedList;
import java.util.List;

public class ChatUtils {
	
	public static final char FORMAT_CHAR = '&';
	public static final char COLOR_CHAR = 167; // §
	
	public static ChatComponent parse(String text) {
		return parse(text, ChatColor.WHITE, COLOR_CHAR);
	}
	
	public static ChatComponent parse(String text, ChatColor defaultColor) {
		return parse(text, defaultColor, COLOR_CHAR);
	}
	
	public static ChatComponent parse(String text, char colorChar) {
		return parse(text, ChatColor.WHITE, colorChar);
	}
	
	public static ChatComponent parse(String text, ChatColor defaultColor, char colorChar) {
		StringBuilder sb = new StringBuilder();
		List<ChatComponentColor> colors = new LinkedList<>();
		ChatColor lastColor = defaultColor == null ? ChatColor.WHITE : defaultColor;
		int from = 0;
		int to = 0;
		int i = 0;
		while (i < text.length() - 1) {
			char c0 = text.charAt(i);
			char c1 = text.charAt(i + 1);
			
			if (c0 != colorChar || ((c1 < '0' || c1 > '9') && (c1 < 'a' || c1 > 'f'))) {
				sb.append(c0);
				i++;
				to++;
				continue;
			}
			
			colors.add(new ChatComponentColor(lastColor, from, to));
			
			lastColor = ChatColor.of(c1);
			from = to;
			
			i += 2;
		}
		if (i < text.length()) {
			sb.append(text.charAt(i));
			colors.add(new ChatComponentColor(lastColor, from, to + 1));
		}
		return new ChatComponent(sb.toString(), colors);
	}
	
	public static String format(String text) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while (i < text.length() - 1) {
			char c0 = text.charAt(i);
			char c1 = text.charAt(i + 1);
			
			if (c0 != FORMAT_CHAR || ((c1 < '0' || c1 > '9') && (c1 < 'a' || c1 > 'f'))) {
				sb.append(c0);
				i++;
				continue;
			}
			
			sb.append(COLOR_CHAR);
			sb.append(c1);
			i += 2;
		}
		if (i < text.length()) {
			sb.append(text.charAt(i));
		}
		return sb.toString();
	}
}
