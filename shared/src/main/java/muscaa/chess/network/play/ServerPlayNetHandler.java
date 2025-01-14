package muscaa.chess.network.play;

import fluff.network.client.IClient;
import muscaa.chess.board.Lobby;
import muscaa.chess.board.player.AbstractPlayer;
import muscaa.chess.board.player.RemotePlayer;
import muscaa.chess.network.common.ServerCommonNetHandler;
import muscaa.chess.network.play.packets.SPacketClickCell;

public class ServerPlayNetHandler extends ServerCommonNetHandler implements IServerPlayNetHandler {
	
	protected final Lobby lobby;
	protected AbstractPlayer player;
	
	public ServerPlayNetHandler(Lobby lobby) {
		this.lobby = lobby;
	}
	
	@Override
	public void onInit(IClient client) {
		super.onInit(client);
		
		if (player != null) {
			connection.disconnect("Already connected!");
			return;
		}
		
		player = new RemotePlayer(connection);
		boolean joined = lobby.join(player, false);
		if (!joined) {
			connection.disconnect("Lobby is full!");
			return;
		}
	}
	
	@Override
	public void onDisconnect() {
		if (player == null) return;
		
		lobby.leave(player);
		player = null;
	}
	
	@Override
	public void onPacketClickCell(SPacketClickCell packet) {
		if (player == null) return;
		
		player.click(packet.getCell());
	}
}
