package muscaa.chess.network.play;

import fluff.network.client.IClient;
import muscaa.chess.Server;
import muscaa.chess.board.player.AbstractPlayer;
import muscaa.chess.board.player.RemotePlayer;
import muscaa.chess.network.common.ServerCommonNetHandler;
import muscaa.chess.network.play.packets.SPacketClickCell;

public class ServerPlayNetHandler extends ServerCommonNetHandler implements IServerPlayNetHandler {
	
	@Override
	public void onInit(IClient client) {
		super.onInit(client);
		
		AbstractPlayer player = connection.getPlayer();
		if (player != null) return;
		
		player = new RemotePlayer(connection);
		boolean joined = Server.INSTANCE.getLobby().join(player, false);
		if (!joined) {
			//Server.INSTANCE.getLobby().join(player, true);
			
			connection.disconnect("Server is full!");
			return;
		}
		
		connection.setPlayer(player);
		
		//Server.INSTANCE.getLobby().onConnect(connection);
	}
	
	@Override
	public void onDisconnect() {
		AbstractPlayer player = connection.getPlayer();
		if (player == null) return;
		
		Server.INSTANCE.getLobby().leave(player);
		connection.setPlayer(null);
		
		//Server.INSTANCE.getLobby().onDisconnect(connection);
	}
	
	@Override
	public void onPacketClickCell(SPacketClickCell packet) {
		AbstractPlayer player = connection.getPlayer();
		if (player == null) return;
		
		player.click(packet.getCell());
		
		//Server.INSTANCE.getLobby().onPacketClickCell(connection, packet);
	}
}
