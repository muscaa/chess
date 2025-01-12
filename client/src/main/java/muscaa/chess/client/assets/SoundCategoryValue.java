package muscaa.chess.client.assets;

import fluff.functions.gen.FloatFunc;
import muscaa.chess.client.Client;
import muscaa.chess.registry.IRegistryValue;
import muscaa.chess.registry.RegistryKey;

public class SoundCategoryValue implements IRegistryValue<SoundCategoryValue> {
	
	private final RegistryKey<SoundCategoryValue> key;
	private FloatFunc func;
	
	public SoundCategoryValue(RegistryKey<SoundCategoryValue> key, FloatFunc func) {
		this.key = key;
		this.func = func;
	}
	
	public float getVolume() {
		if (func == null) return 0.0F;
		
		return Client.INSTANCE.settingsConfig.masterVolume.get() * func.invoke();
	}
	
	@Override
	public RegistryKey<SoundCategoryValue> getKey() {
		return key;
	}
}
