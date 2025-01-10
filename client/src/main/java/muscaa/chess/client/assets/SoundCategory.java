package muscaa.chess.client.assets;

import fluff.functions.gen.FloatFunc;
import muscaa.chess.client.Client;
import muscaa.chess.registry.IRegistryEntry;
import muscaa.chess.utils.NamespacePath;

public class SoundCategory implements IRegistryEntry {
	
	private final NamespacePath id;
	private FloatFunc func;
	
	public SoundCategory(NamespacePath id, FloatFunc func) {
		this.id = id;
		this.func = func;
	}
	
	public float getVolume() {
		if (func == null) return 0.0F;
		
		return Client.INSTANCE.getSettings().masterVolume.get() * func.invoke();
	}
	
	@Override
	public NamespacePath getID() {
		return id;
	}
}
