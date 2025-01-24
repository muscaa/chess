package muscaa.chess.network;

import muscaa.chess.registry.IRegistryValue;
import muscaa.chess.registry.RegistryKey;

public class DisconnectReasonValue implements IRegistryValue<DisconnectReasonValue> {
	
	private final RegistryKey<DisconnectReasonValue> key;
	
	public DisconnectReasonValue(RegistryKey<DisconnectReasonValue> key) {
		this.key = key;
	}
	
	@Override
	public RegistryKey<DisconnectReasonValue> getKey() {
		return key;
	}
}
