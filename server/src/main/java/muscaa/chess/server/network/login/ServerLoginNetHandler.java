package muscaa.chess.server.network.login;

import fluff.network.AbstractServerNetHandler;
import fluff.network.NetworkException;
import muscaa.chess.server.GameServer;
import muscaa.chess.server.network.ChessClientConnection;
import muscaa.chess.server.network.ChessServer;
import muscaa.chess.server.network.ServerContexts;
import muscaa.chess.server.network.login.packets.PacketLogin;
import muscaa.chess.server.network.login.packets.PacketLoginSuccess;
import muscaa.chess.server.network.play.ServerPlayNetHandler;
import muscaa.chess.server.network.play.packet.PacketBoard;
import muscaa.chess.shared.board.ChessColor;

public class ServerLoginNetHandler extends AbstractServerNetHandler<ChessServer, ChessClientConnection> implements IServerLoginNetHandler {
	
	@Override
	public void onConnect() throws NetworkException {
		System.out.println("Logged in");
		
		int size = GameServer.INSTANCE.players.size();
		ChessColor color = size == 0 ? GameServer.INSTANCE.turn : GameServer.INSTANCE.turn.invert();
		
		GameServer.INSTANCE.players.put(color, connection);
		GameServer.INSTANCE.colors.put(connection, color);
		
		connection.send(new PacketLoginSuccess(color));
		connection.setContext(ServerContexts.PLAY_CONTEXT, new ServerPlayNetHandler());
		
		connection.send(new PacketBoard(GameServer.INSTANCE.getBoard().getPieces()));
	}
	
	@Override
	public void onPacketLogin(PacketLogin packet) {
		System.out.println("Received login");
		
		connection.login(packet.getName());
	}
}
