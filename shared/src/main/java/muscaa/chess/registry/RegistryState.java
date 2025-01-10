package muscaa.chess.registry;

public enum RegistryState {
	UNLOCKED(false) {
		@Override
		public <V extends IRegistryValue> V getValue(RegistryKey<V> key) {
			throw new RegistryException("Registry not initialized!");
		}
	},
	REGISTER(false) {
		@Override
		public <V extends IRegistryValue> V getValue(RegistryKey<V> key) {
			throw new RegistryException("Registry initializing!");
		}
	},
	INIT(true) {
		@Override
		public <V extends IRegistryValue> V getValue(RegistryKey<V> key) {
			throw new RegistryException("Registry initializing!");
		}
	},
	LOCKED(true) {
		@Override
		public <V extends IRegistryValue> V getValue(RegistryKey<V> key) {
			return key.value;
		}
	},
	;
	
	private final boolean locked;
	
	private RegistryState(boolean locked) {
		this.locked = locked;
	}
	
	public abstract <V extends IRegistryValue> V getValue(RegistryKey<V> key);
	
	public boolean isLocked() {
		return locked;
	}
}
