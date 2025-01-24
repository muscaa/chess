package muscaa.chess.network.play;

import fluff.network.client.IClient;
import muscaa.chess.AbstractServer;
import muscaa.chess.board.AbstractServerBoard;
import muscaa.chess.network.DisconnectReasonRegistry;
import muscaa.chess.network.common.ServerCommonNetHandler;
import muscaa.chess.network.play.packets.SPacketClickCell;
import muscaa.chess.player.AbstractServerPlayer;

public class ServerPlayNetHandler extends ServerCommonNetHandler implements IServerPlayNetHandler {
	
	protected final AbstractServerBoard board;
	
	public ServerPlayNetHandler(AbstractServer gameServer, AbstractServerBoard board) {
		super(gameServer);
		
		this.board = board;
	}
	
	@Override
	public void onInit(IClient client) {
		super.onInit(client);
		
		if (connection.player == null) {
			connection.disconnect(DisconnectReasonRegistry.KICK.get(), "Not logged in!");
			return;
		}
		
		boolean joined = board.join(connection.player, false);
		if (!joined) {
			connection.player.disconnect(DisconnectReasonRegistry.KICK.get(), "Lobby is full!");
			return;
		}
	}
	
	@Override
	public void onDisconnect() {
		if (connection.player == null) return;
		
		AbstractServerPlayer player = connection.player;
		connection.player = null;
		
		board.leave(player);
		gameServer.removePlayer(player);
	}
	
	@Override
	public void onPacketClickCell(SPacketClickCell packet) {
		if (connection.player == null) return;
		
		connection.player.onClick(packet.getCell());
	}
}
