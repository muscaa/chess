package muscaa.chess.utils;

import java.nio.ByteBuffer;
import java.util.UUID;

public class UUIDUtils {
	
	public static byte[] toBytes(UUID uuid) {
	    ByteBuffer uuidBytes = ByteBuffer.allocate(16);
	    uuidBytes.putLong(uuid.getMostSignificantBits());
	    uuidBytes.putLong(uuid.getLeastSignificantBits());
		return uuidBytes.array();
	}
	
	public static UUID fromBytes(byte[] bytes) {
		ByteBuffer uuidBytes = ByteBuffer.wrap(bytes);
		return new UUID(uuidBytes.getLong(), uuidBytes.getLong());
	}
}
