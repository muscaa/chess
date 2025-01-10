package muscaa.chess.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;

import org.yaml.snakeyaml.Yaml;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import muscaa.chess.Chess;
import muscaa.chess.client.board.BoardLayer;
import muscaa.chess.client.config.ServersConfig;
import muscaa.chess.client.config.Settings;
import muscaa.chess.client.gui.GuiLayer;
import muscaa.chess.client.gui.GuiScreen;
import muscaa.chess.client.gui.screens.MainMenuScreen;
import muscaa.chess.client.layer.LayerManager;
import muscaa.chess.client.network.ChessClient;
import muscaa.chess.client.registries.FontRegistry;
import muscaa.chess.client.registries.SoundRegistry;
import muscaa.chess.client.registries.TextureRegistry;
import muscaa.chess.client.utils.Screen;
import muscaa.chess.client.utils.Shapes;
import muscaa.chess.client.utils.TaskManager;

public class Client {
	
	public static final Client INSTANCE = new Client();
	
    private int lastWidth;
    private int lastHeight;
	
	public final LayerManager layerManager;
	
	public final BoardLayer boardLayer;
	private final GuiLayer guiLayer;
	
	public final ChessClient networkClient;
	
	public final ServersConfig serversConfig;
	private Settings settings;
	
	private GuiScreen screen;
	
	Client() {
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		
		Screen.init(width, height);
		
    	layerManager = new LayerManager();
    	Gdx.input.setInputProcessor(new ClientInputProcessor(this));
    	
    	Chess.init();
    	
    	FontRegistry.init();
    	TextureRegistry.init();
    	SoundRegistry.init();
    	
    	boardLayer = new BoardLayer(this);
    	layerManager.register(boardLayer);
    	
    	guiLayer = new GuiLayer(this);
    	layerManager.register(guiLayer);
    	
    	networkClient = new ChessClient();
    	
    	serversConfig = new ServersConfig();
    	serversConfig.load();
    	
    	//settingsYaml = new Yaml(new Constructor(Settings.class, new LoaderOptions()));
    	loadSettings();
	}
	
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
	
	public void resize(int width, int height) {
		Screen.init(width, height);
		
		layerManager.resize(width, height);
	}
	
	public void dispose() {
		saveSettings();
		
		layerManager.dispose();
		
		FontRegistry.dispose();
		TextureRegistry.dispose();
		SoundRegistry.dispose();
		
		Screen.dispose();
	}
	
	public GuiScreen getScreen() {
		return screen;
	}
	
	public void setScreen(GuiScreen newScreen) {
		if (screen != null) {
			screen.dispose();
		}
		
		screen = newScreen;
		if (screen == null) {
			if (boardLayer.isInGame()) return;
			
			screen = new MainMenuScreen();
		}
		
		screen.init(this, Screen.VIEWPORT);
	}
	
	public void returnToMainMenu() {
		//SoundRegistry.AMBIENT.play();
		
		networkClient.disconnect();
		boardLayer.disconnect();
		setScreen(new MainMenuScreen());
	}
	
	public void toggleFullscreen() {
    	if (!Gdx.graphics.supportsDisplayModeChange()) return;
    	
    	if (Gdx.graphics.isFullscreen()) {
    		Gdx.graphics.setWindowedMode(lastWidth, lastHeight);
    		
    		settings.fullscreen = false;
    	} else {
    		lastWidth = Gdx.graphics.getWidth();
    		lastHeight = Gdx.graphics.getHeight();
    		
    		Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
    		
    		settings.fullscreen = true;
    	}
	}
	
	public void exit() {
		Gdx.app.exit();
	}
	
	public Settings getSettings() {
		return settings;
	}
	
	public void loadSettings() {
		File file = new File("settings.yml");
		if (!file.exists()) {
			settings = new Settings();
			settings.onLoad(this);
			saveSettings();
			return;
		}
		
		try (FileInputStream fis = new FileInputStream(file)) {
			Yaml yaml = new Yaml();
			settings = yaml.loadAs(fis, Settings.class);
		} catch (Exception e) {
			e.printStackTrace();
			settings = new Settings();
		}
		settings.onLoad(this);
	}
	
	public void saveSettings() {
		File file = new File("settings.yml");
		try (PrintWriter pw = new PrintWriter(file)) {
			Yaml yaml = new Yaml();
			settings.onSave(this);
			pw.println(yaml.dumpAsMap(settings));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}