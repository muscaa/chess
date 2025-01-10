package muscaa.chess.client.config;

import java.io.File;

import muscaa.chess.client.Client;
import muscaa.chess.config.values.IValueBoolean;
import muscaa.chess.config.values.IValueFloat;
import muscaa.chess.config.yaml.YamlConfig;

public class Settings extends YamlConfig {
	
	private Client chess;
	
	public final IValueBoolean fullscreen = Boolean("fullscreen", false)
			.addListener(v -> chess.setFullscreen(v.get()));
	public final IValueFloat masterVolume = Float("masterVolume", 1.0F);
	public final IValueFloat musicVolume = Float("musicVolume", 1.0F);
	public final IValueFloat soundVolume = Float("soundVolume", 1.0F);
	
	public Settings(Client chess) {
		super(new File("settings.yml"));
		
		this.chess = chess;
	}
}
