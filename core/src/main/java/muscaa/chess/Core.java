package muscaa.chess;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import muscaa.chess.assets.Fonts;
import muscaa.chess.assets.Sounds;
import muscaa.chess.board.BoardLayer;
import muscaa.chess.config.Theme;
import muscaa.chess.gui.GuiLayer;
import muscaa.chess.gui.screens.MainScreen;
import muscaa.chess.layer.LayerManager;
import muscaa.chess.network.ClientNetwork;
import muscaa.chess.registries.TextureRegistry;
import muscaa.chess.render.Screen;
import muscaa.chess.render.Shapes;
import muscaa.chess.task.TaskManager;

public class Core implements ApplicationListener {
	
	public static final Core INSTANCE = new Core();
	
	private LayerManager layerManager;
	
	private BoardLayer boardLayer;
	private GuiLayer guiLayer;
	
	private ClientNetwork network;
	
	@Override
	public void create() {
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		
		Screen.init(width, height);
		
    	layerManager = new LayerManager();
    	Gdx.input.setInputProcessor(new CoreInputProcessor());
    	
    	Fonts.init();
    	TextureRegistry.init();
    	Sounds.init();
    	
    	boardLayer = new BoardLayer();
    	layerManager.register(boardLayer);
    	
    	guiLayer = new GuiLayer();
    	layerManager.register(guiLayer);
    	
    	network = new ClientNetwork();
    	
    	returnToMainMenu();
	}
	
	@Override
	public void render() {
    	ScreenUtils.clear(0.0F, 0.0F, 0.0F, 1.0F);
    	
    	Vector2 mouse = Screen.VIEWPORT.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
    	float delta = Gdx.graphics.getDeltaTime();
    	
    	Screen.beginScreen();
    	Shapes.rect(0, 0, Screen.WIDTH, Screen.HEIGHT, Theme.BACKGROUND);
    	layerManager.render((int) mouse.x, (int) mouse.y, delta);
    	Screen.endScreen();
    	
    	TaskManager.executeRender();
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
		
		Sounds.dispose();
		Fonts.dispose();
		TextureRegistry.dispose();
		
		Screen.dispose();
	}
	
	public void returnToMainMenu() {
		network.disconnect();
		boardLayer.disconnect();
		guiLayer.setScreen(new MainScreen());
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
	
	public ClientNetwork getNetwork() {
		return network;
	}
}