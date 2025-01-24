package muscaa.chess.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import muscaa.chess.Chess;
import muscaa.chess.client.assets.FontRegistry;
import muscaa.chess.client.assets.SoundCategoryRegistry;
import muscaa.chess.client.assets.SoundRegistry;
import muscaa.chess.client.assets.TextureRegistry;
import muscaa.chess.client.board.BoardLayer;
import muscaa.chess.client.board.piece.ClientPieceRegistry;
import muscaa.chess.client.chat.ChatLayer;
import muscaa.chess.client.config.ServersConfig;
import muscaa.chess.client.config.SettingsConfig;
import muscaa.chess.client.config.Theme;
import muscaa.chess.client.gui.GuiLayer;
import muscaa.chess.client.gui.GuiScreen;
import muscaa.chess.client.gui.screens.MainMenuScreen;
import muscaa.chess.client.layer.LayerManager;
import muscaa.chess.client.mod.IClientModInitializer;
import muscaa.chess.client.network.ClientContextRegistry;
import muscaa.chess.client.player.AbstractClientPlayer;
import muscaa.chess.client.utils.Screen;
import muscaa.chess.client.utils.Shapes;
import muscaa.chess.client.utils.TaskManager;
import muscaa.chess.mod.ChessModLoader;
import muscaa.chess.mod.ModException;
import muscaa.chess.mod.ModInfo;

public class Client {
	
	public static final ChessModLoader<IClientModInitializer> MOD_LOADER = new ChessModLoader<>(
			IClientModInitializer.class,
			ModInfo::getClientMain,
			IClientModInitializer::onPreInitializeClient,
			IClientModInitializer::onPostInitializeClient
			);
	public static final Client INSTANCE = new Client();
	
	public final ServersConfig serversConfig;
	public final SettingsConfig settingsConfig;
	
	public final LayerManager layerManager;
	private final BoardLayer boardLayer;
	public final ChatLayer chatLayer;
	private final GuiLayer guiLayer;
	
	private GuiScreen screen;
	private AbstractClientPlayer player;
	
	private Client() {
		try {
			MOD_LOADER.loadPre();
		} catch (ModException e) {
			throw new RuntimeException(e);
		}
		
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		
		Screen.init(width, height);
		
		settingsConfig = new SettingsConfig(this);
		serversConfig = new ServersConfig();
		
		layerManager = new LayerManager();
		Gdx.input.setInputProcessor(new ClientInputProcessor(this));
		
		boardLayer = new BoardLayer(this);
		layerManager.register(boardLayer);
		
		chatLayer = new ChatLayer(this);
		layerManager.register(chatLayer);
		
		guiLayer = new GuiLayer(this);
		layerManager.register(guiLayer);
	}
	
	public void init() {
		Chess.init();
		
    	FontRegistry.init();
    	TextureRegistry.init();
    	SoundCategoryRegistry.init();
    	SoundRegistry.init();
    	ClientPieceRegistry.init();
    	ClientContextRegistry.init();
    	
    	guiLayer.init();
    	chatLayer.init();
		
    	returnToMainMenu();
		
    	settingsConfig.load();
    	//serversConfig.load();
    	
		try {
			MOD_LOADER.loadPost();
		} catch (ModException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void render() {
    	ScreenUtils.clear(0.0F, 0.0F, 0.0F, 1.0F);
    	
    	Vector2 mouse = Screen.VIEWPORT.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
    	float delta = Gdx.graphics.getDeltaTime();
    	
    	Screen.beginScreen();
    	Shapes.rect(0, 0, Screen.WIDTH, Screen.HEIGHT, Theme.WINDOW_BACKGROUND);
    	layerManager.render((int) mouse.x, (int) mouse.y, delta);
    	Screen.endScreen();
    	
    	TaskManager.executeRender();
	}
	
	public void resize(int width, int height) {
		Screen.init(width, height);
		
		layerManager.resize(width, height);
	}
	
	public GuiScreen getScreen() {
		return screen;
	}
	
	public void setScreen(GuiScreen newScreen) {
		GuiScreen oldScreen = screen;
		if (oldScreen != null) {
			oldScreen.dispose();
		}
		
		screen = newScreen;
		if (screen == null) {
			if (player != null) return;
			
			screen = new MainMenuScreen();
		}
		
		screen.init(this, guiLayer, Screen.VIEWPORT);
	}
	
	public AbstractClientPlayer getPlayer() {
		return player;
	}
	
	public void setPlayer(AbstractClientPlayer newPlayer) {
		chatLayer.clear();
		
		AbstractClientPlayer oldplayer = player;
		if (oldplayer != null) {
			oldplayer.dispose();
		}
		
		player = newPlayer;
		if (player != null) {
			SoundRegistry.AMBIENT.get().stop();
			Client.INSTANCE.setScreen(null);
			
			player.init(this, chatLayer, boardLayer);
		} else {
			if (!SoundRegistry.AMBIENT.get().isPlaying()) {
                SoundRegistry.AMBIENT.get().loopSingle();
			}
		}
	}
	
	/*public AbstractClientBoard getBoard() {
		return board;
	}
	
	public void setBoard(AbstractClientBoard newBoard) {
		AbstractClientBoard oldBoard = board;
		if (oldBoard != null) {
			oldBoard.dispose();
		}
		
		board = newBoard;
		if (board != null) {
			SoundRegistry.AMBIENT.get().stop();
			
			board.init(this, boardLayer);
		} else {
			if (!SoundRegistry.AMBIENT.get().isPlaying()) {
				SoundRegistry.AMBIENT.get().loopSingle();
			}
		}
	}*/
	
	public void returnToMainMenu() {
		//setBoard(null);
		setPlayer(null);
		setScreen(new MainMenuScreen());
	}
	
	public void dispose() {
		settingsConfig.save();
		
		layerManager.dispose();
		
		FontRegistry.dispose();
		TextureRegistry.dispose();
		SoundRegistry.dispose();
		
		Screen.dispose();
	}
}