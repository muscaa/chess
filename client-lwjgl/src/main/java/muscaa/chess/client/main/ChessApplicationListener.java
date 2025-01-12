package muscaa.chess.client.main;

import com.badlogic.gdx.ApplicationListener;

import muscaa.chess.client.Client;

public class ChessApplicationListener implements ApplicationListener {
	
	@Override
	public void create() {
		Client.INSTANCE.init();
	}
	
	@Override
	public void resize(int width, int height) {
		Client.INSTANCE.resize(width, height);
	}
	
	@Override
	public void render() {
		Client.INSTANCE.render();
	}
	
	@Override
	public void pause() {}
	
	@Override
	public void resume() {}
	
	@Override
	public void dispose() {
		Client.INSTANCE.dispose();
	}
}
