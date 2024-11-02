package muscaa.chess.server.network.login;

import fluff.network.AbstractServerNetHandler;
import fluff.network.NetworkException;
import muscaa.chess.server.GameServer;
import muscaa.chess.server.board.ServerBoard;
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
		
		ServerBoard board = GameServer.INSTANCE.getBoard();
		
		int size = board.players.size();
		ChessColor color = size == 0 ? board.turn : board.turn.invert();
		
		board.players.put(color, connection);
		board.colors.put(connection, color);
		
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
