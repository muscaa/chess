package muscaa.chess.client.config;

import muscaa.chess.client.Client;

public class Settings {
	
	public boolean fullscreen = false;
	
	public void onLoad(Client instance) {
		if (fullscreen) {
			instance.toggleFullscreen();
		}
	}
	
	public void onSave(Client instance) {
		
	}
}
