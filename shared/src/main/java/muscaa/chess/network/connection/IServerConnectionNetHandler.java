package muscaa.chess.network.connection;

import muscaa.chess.network.common.IServerCommonNetHandler;
import muscaa.chess.network.connection.packets.SPacketEncrypt;

public interface IServerConnectionNetHandler extends IServerCommonNetHandler, IConnectionNetHandler {
	
	void onPacketEncrypt(SPacketEncrypt packet);
}
