package muscaa.chess.config.yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import muscaa.chess.config.base.BaseValue;
import muscaa.chess.config.base.FileBaseConfig;
import muscaa.chess.config.yaml.values.YamlValueBoolean;
import muscaa.chess.config.yaml.values.YamlValueByte;
import muscaa.chess.config.yaml.values.YamlValueDouble;
import muscaa.chess.config.yaml.values.YamlValueFloat;
import muscaa.chess.config.yaml.values.YamlValueInt;
import muscaa.chess.config.yaml.values.YamlValueLong;
import muscaa.chess.config.yaml.values.YamlValueObject;
import muscaa.chess.config.yaml.values.YamlValueShort;
import muscaa.chess.config.yaml.values.YamlValueString;

public class YamlConfig extends FileBaseConfig<Map<String, Object>> {
	
	protected Yaml yaml = new Yaml();
	
	public YamlConfig(File file) {
		super(file);
		
		putBoolean(YamlValueBoolean::new);
		putByte(YamlValueByte::new);
		putShort(YamlValueShort::new);
		putInt(YamlValueInt::new);
		putFloat(YamlValueFloat::new);
		putLong(YamlValueLong::new);
		putDouble(YamlValueDouble::new);
		putString(YamlValueString::new);
		putObject(YamlValueObject::new);
	}
	
	@Override
	protected void load(FileInputStream in) throws IOException {
		Map<String, Object> context = yaml.loadAs(in, Map.class);
		
		for (BaseValue<?, Map<String, Object>> value : values) {
            value.load(context);
		}
	}
	
	@Override
	protected void save(FileOutputStream out) throws IOException {
		Map<String, Object> context = new HashMap<>();
		
		for (BaseValue<?, Map<String, Object>> value : values) {
			value.save(context);
		}
		
		String yml = yaml.dumpAsMap(context);
		out.write(yml.getBytes());
	}
}
