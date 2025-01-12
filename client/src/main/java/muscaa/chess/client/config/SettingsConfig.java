package muscaa.chess.client.config;

import java.io.File;

import muscaa.chess.client.Client;
import muscaa.chess.client.utils.WindowUtils;
import muscaa.chess.config.values.IValueBoolean;
import muscaa.chess.config.values.IValueFloat;
import muscaa.chess.config.yaml.YamlConfig;

public class SettingsConfig extends YamlConfig {
	
	public final IValueBoolean fullscreen = Boolean("fullscreen", false)
			.addListener(v -> WindowUtils.setFullscreen(v.get()));
	public final IValueFloat masterVolume = Float("masterVolume", 1.0F);
	public final IValueFloat musicVolume = Float("musicVolume", 1.0F);
	public final IValueFloat soundVolume = Float("soundVolume", 1.0F);
	
	public SettingsConfig(Client chess) {
		super(new File("settings.yml"));
	}
}
