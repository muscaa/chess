package muscaa.chess.server.config;

import java.io.File;

import muscaa.chess.config.values.IValueInt;
import muscaa.chess.config.values.IValueString;
import muscaa.chess.config.yaml.YamlConfig;

public class SettingsConfig extends YamlConfig {
	
	public final IValueString databaseType = String("databaseType", "chess:sqlite");
	public final IValueString databaseUrl = String("databaseUrl", "jdbc:sqlite:chess.db");
	public final IValueString databaseUsername = String("databaseUsername", null);
	public final IValueString databasePassword = String("databasePassword", null);
	
	public final IValueInt port = Int("port", 40755);
	
	public SettingsConfig() {
		super(new File("server.yml"));
	}
}
