package muscaa.chess.client.chat;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Stage;

import muscaa.chess.client.Client;
import muscaa.chess.client.assets.FontRegistry;
import muscaa.chess.client.gui.GuiScreen;
import muscaa.chess.client.gui.widgets.WLabel;
import muscaa.chess.client.gui.widgets.WPanel;
import muscaa.chess.client.gui.widgets.WScrollPane;
import muscaa.chess.client.gui.widgets.WTable;
import muscaa.chess.client.gui.widgets.WTextField;
import muscaa.chess.client.layer.ILayer;
import muscaa.chess.client.layer.ILayerWrapper;
import muscaa.chess.client.layer.LayerUtils;
import muscaa.chess.client.utils.Screen;
import muscaa.chess.client.utils.TaskManager;

public class ChatLayer implements ILayerWrapper {
	
	public static final int WIDTH = 350;
	public static final int HISTORY_SIZE = 100;
	
	private Client chess;
	
	private Stage stage;
	private ILayer stageLayer;
	
	private WTable main;
	private WTable chat;
	private WScrollPane chatScroll;
	private List<WLabel> lineHistory;
	private List<String> messageHistory;
	private int messageIndex;
	private WTextField chatInput;
	
	public ChatLayer(Client chess) {
		this.chess = chess;
	}
	
	public void init() {
		stage = new Stage(Screen.VIEWPORT);
		stageLayer = LayerUtils.fromGdx(stage);
		
		main = new WPanel();
		main.setSize(WIDTH, Screen.VIEWPORT.getScreenHeight());
		main.defaults().pad(GuiScreen.PAD_SMALL).minHeight(35);
		
		chat = new WTable();
		chat.bottom();
		lineHistory = new ArrayList<>(HISTORY_SIZE);
		messageHistory = new ArrayList<>(HISTORY_SIZE);
		
		chatScroll = new WScrollPane(chat);
		main.add(chatScroll).grow();
		main.row();
		
		chatInput = new WTextField();
		chatInput.setFont(GuiScreen.FONT_SMALL);
		main.add(chatInput).growX();
		main.row();
		
		stage.addActor(main);
		stage.setScrollFocus(chatScroll);
	}
	
	public void addLine(String line) {
		if (lineHistory.size() >= HISTORY_SIZE) {
			lineHistory.remove(0);
			chat.removeActorAt(0, true);
			chat.getCells().removeIndex(0);
		}
		
		WLabel label = new WLabel(line, FontRegistry.VARELA_18.get());
		label.setWrap(true);
		chat.add(label).growX().pad(GuiScreen.PAD_SMALL);
		chat.row();
		
		lineHistory.add(label);
	}
	
	public void clear() {
		lineHistory.clear();
		messageHistory.clear();
		chat.clearChildren();
		chat.getCells().clear();
	}
	
	@Override
	public void render(int mouseX, int mouseY, float delta) {
		stage.act(delta);
		stage.draw();
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if (chatInput.hasKeyboardFocus()) {
			if (keycode == Keys.ENTER && !chatInput.getText().isEmpty()) {
				boolean atBottom = chatScroll.isBottomEdge();
				
				String text = chatInput.getText();
				chatInput.setText("");
				
				addLine(text); // TODO send message to server
				
				if (messageHistory.size() >= HISTORY_SIZE) {
					messageHistory.remove(0);
				}
				messageHistory.add(text);
				messageIndex = messageHistory.size();
				
				TaskManager.render(() -> {
					if (atBottom) {
						chatScroll.setScrollPercentY(1.0F);
					}
				});
				return true;
			} else if (keycode == Keys.UP) {
				if (messageIndex > 0) {
					messageIndex--;
					chatInput.setText(messageHistory.get(messageIndex));
					chatInput.setCursorPosition(chatInput.getText().length());
					return true;
				}
			} else if (keycode == Keys.DOWN) {
				if (messageIndex < messageHistory.size() - 1) {
					messageIndex++;
					chatInput.setText(messageHistory.get(messageIndex));
					chatInput.setCursorPosition(chatInput.getText().length());
					return true;
				}
			}
		}
		
		return ILayerWrapper.super.keyDown(keycode);
	}
	
	@Override
	public void resize(int width, int height) {
		main.setHeight(height);
		
		ILayerWrapper.super.resize(width, height);
	}
	
	@Override
	public boolean hover(int mouseX, int mouseY) {
		return mouseX < WIDTH;
	}
	
	@Override
	public ILayer getWrappedLayer() {
		return stageLayer;
	}
	
	@Override
	public void dispose() {
		stage.dispose();
	}
}
