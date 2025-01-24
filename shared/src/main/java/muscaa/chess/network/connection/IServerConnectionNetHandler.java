package muscaa.chess.network.connection;

import muscaa.chess.network.base.IServerBaseNetHandler;
import muscaa.chess.network.connection.packets.SPacketEncrypt;

public interface IServerConnectionNetHandler extends IServerBaseNetHandler, IConnectionNetHandler {
	
	void onPacketEncrypt(SPacketEncrypt packet);
}
