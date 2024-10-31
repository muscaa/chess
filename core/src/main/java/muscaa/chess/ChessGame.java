package muscaa.chess;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import muscaa.chess.assets.Fonts;
import muscaa.chess.assets.Textures;
import muscaa.chess.board.BoardLayer;
import muscaa.chess.gui.GuiLayer;
import muscaa.chess.gui.screens.MainMenuScreen;
import muscaa.chess.layer.LayerManager;
import muscaa.chess.render.Screen;

public class ChessGame implements ApplicationListener {
	
	public static final ChessGame INSTANCE = new ChessGame();
	
	private LayerManager layerManager;
	
	private BoardLayer boardLayer;
	private GuiLayer guiLayer;
	
	@Override
	public void create() {
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		
		Screen.init(width, height);
		
    	layerManager = new LayerManager();
    	Gdx.input.setInputProcessor(new ChessInputProcessor());
    	
    	Fonts.init();
    	Textures.init();
    	
    	boardLayer = new BoardLayer();
    	layerManager.register(boardLayer);
    	
    	guiLayer = new GuiLayer();
    	layerManager.register(guiLayer);
    	guiLayer.setScreen(new MainMenuScreen());
	}
	
	@Override
	public void render() {
    	ScreenUtils.clear(0.0F, 0.0F, 0.0F, 1.0F);
    	
    	Vector2 mouse = Screen.VIEWPORT.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
    	float delta = Gdx.graphics.getDeltaTime();
    	
    	Screen.beginScreen();
    	layerManager.render((int) mouse.x, (int) mouse.y, delta);
    	Screen.endScreen();
	}
	
	@Override
	public void resize(int width, int height) {
		Screen.init(width, height);
		
		layerManager.resize(width, height);
	}
	
	@Override
	public void pause() {}
	
	@Override
	public void resume() {}
	
	@Override
	public void dispose() {
		layerManager.dispose();
		
		Fonts.dispose();
		Textures.dispose();
		
		Screen.dispose();
	}
	
	public LayerManager getLayerManager() {
		return layerManager;
	}
	
	public BoardLayer getBoardLayer() {
		return boardLayer;
	}
	
	public GuiLayer getGuiLayer() {
		return guiLayer;
	}
}