package muscaa.chess.server.network.login;

import fluff.network.AbstractServerNetHandler;
import fluff.network.NetworkException;
import muscaa.chess.server.network.ChessClientConnection;
import muscaa.chess.server.network.ChessServer;
import muscaa.chess.server.network.NetworkServer;
import muscaa.chess.server.network.login.packets.PacketLogin;
import muscaa.chess.server.network.play.ServerPlayNetHandler;

public class ServerLoginNetHandler extends AbstractServerNetHandler<ChessServer, ChessClientConnection> implements IServerLoginNetHandler {
	
	@Override
	public void onConnect() throws NetworkException {
		System.out.println("Logged in");
		
		connection.setContext(NetworkServer.PLAY_CONTEXT, new ServerPlayNetHandler());
	}
	
	@Override
	public void onPacketLogin(PacketLogin packet) {
		System.out.println("Received login");
		
		connection.login(packet.getName());
	}
}
