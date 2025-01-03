package muscaa.chess.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import muscaa.chess.Chess;
import muscaa.chess.client.assets.Fonts;
import muscaa.chess.client.assets.Sounds;
import muscaa.chess.client.board.BoardLayer;
import muscaa.chess.client.config.ServersConfig;
import muscaa.chess.client.gui.GuiLayer;
import muscaa.chess.client.gui.screens.MainMenuScreen;
import muscaa.chess.client.layer.LayerManager;
import muscaa.chess.client.network.ChessClient;
import muscaa.chess.client.registries.TextureRegistry;
import muscaa.chess.client.utils.Screen;
import muscaa.chess.client.utils.Shapes;
import muscaa.chess.client.utils.TaskManager;

public class Client implements ApplicationListener {
	
	public static final Client INSTANCE = new Client();
	
	private LayerManager layerManager;
	
	private BoardLayer boardLayer;
	private GuiLayer guiLayer;
	
	private ChessClient networkClient;
	
	private ServersConfig serversConfig;
	
	@Override
	public void create() {
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		
		Screen.init(width, height);
		
    	layerManager = new LayerManager();
    	Gdx.input.setInputProcessor(new ClientInputProcessor());
    	
    	Chess.init();
    	
    	Fonts.init();
    	TextureRegistry.init();
    	Sounds.init();
    	
    	boardLayer = new BoardLayer();
    	layerManager.register(boardLayer);
    	
    	guiLayer = new GuiLayer();
    	layerManager.register(guiLayer);
    	
    	networkClient = new ChessClient();
    	
    	serversConfig = new ServersConfig();
    	serversConfig.load();
    	
    	returnToMainMenu();
	}
	
	@Override
	public void render() {
    	ScreenUtils.clear(0.0F, 0.0F, 0.0F, 1.0F);
    	
    	Vector2 mouse = Screen.VIEWPORT.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
    	float delta = Gdx.graphics.getDeltaTime();
    	
    	Screen.beginScreen();
    	Shapes.rect(0, 0, Screen.WIDTH, Screen.HEIGHT, Color.BLACK);
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
		networkClient.disconnect();
		boardLayer.disconnect();
		guiLayer.setScreen(new MainMenuScreen());
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
	
	public ChessClient getNetworkClient() {
		return networkClient;
	}
	
	public ServersConfig getServersConfig() {
		return serversConfig;
	}
}