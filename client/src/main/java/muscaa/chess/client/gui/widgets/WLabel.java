package muscaa.chess.client.gui.widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.VisLabel;

import muscaa.chess.chat.ChatColor;
import muscaa.chess.chat.ChatComponent;
import muscaa.chess.chat.ChatComponentColor;
import muscaa.chess.chat.ChatUtils;
import muscaa.chess.client.assets.FontRegistry;
import muscaa.chess.client.assets.FontValue;

public class WLabel extends VisLabel {
	
	private ChatColor chatColor;
	private ChatComponent component;
	
	public WLabel() {
		this("", FontRegistry.VARELA_24.get(), Align.left, ChatColor.WHITE);
	}
	
	public WLabel(ChatColor chatColor) {
		this("", FontRegistry.VARELA_24.get(), Align.left, chatColor);
	}
	
	public WLabel(CharSequence text) {
		this(text, FontRegistry.VARELA_24.get(), Align.left, ChatColor.WHITE);
	}
	
	public WLabel(CharSequence text, ChatColor chatColor) {
		this(text, FontRegistry.VARELA_24.get(), Align.left, chatColor);
	}
	
	public WLabel(CharSequence text, FontValue font) {
		this(text, font, Align.left, ChatColor.WHITE);
	}
	
	public WLabel(CharSequence text, FontValue font, ChatColor chatColor) {
		this(text, font, Align.left, chatColor);
	}
	
	public WLabel(CharSequence text, int alignment) {
		this(text, FontRegistry.VARELA_24.get(), alignment, ChatColor.WHITE);
	}
	
	public WLabel(CharSequence text, int alignment, ChatColor chatColor) {
		this(text, FontRegistry.VARELA_24.get(), alignment, chatColor);
	}
	
	public WLabel(FontValue font) {
		this("", font, Align.left, ChatColor.WHITE);
	}
	
	public WLabel(FontValue font, ChatColor chatColor) {
		this("", font, Align.left, chatColor);
	}
	
	public WLabel(FontValue font, int alignment) {
		this("", font, alignment, ChatColor.WHITE);
	}
	
	public WLabel(FontValue font, int alignment, ChatColor chatColor) {
		this("", font, alignment, chatColor);
	}
	
	public WLabel(CharSequence text, FontValue font, int alignment, ChatColor chatColor) {
		this.chatColor = chatColor;
		
		setText(text);
		setStyle(new LabelStyle(font.getBitmapFont(), Color.WHITE));
		setAlignment(alignment);
	}
	
	@Override
	public void layout() {
		super.layout();
		
		for (ChatComponentColor componentColor : component.colors()) {
			getBitmapFontCache().setColors(componentColor.color().getFloatBits(), componentColor.from(), componentColor.to());
		}
	}
	
	@Override
	public void setText(CharSequence newText) {
		if (newText == null) newText = "";
		
		component = ChatUtils.parse(newText.toString(), chatColor);
		super.setText(component.text());
	}
	
	public void setTextFormatted(CharSequence newText) {
		if (newText == null) newText = "";
		
		component = ChatUtils.parse(newText.toString(), chatColor, ChatUtils.FORMAT_CHAR);
		super.setText(component.text());
	}
	
	public void setChatColor(ChatColor chatColor) {
		this.chatColor = chatColor;
		
		setText(getText());
	}
	
	public ChatColor getChatColor() {
		return chatColor;
	}
}
