package muscaa.chess.server.network.login;

import fluff.network.NetworkException;
import muscaa.chess.server.network.ServerContexts;
import muscaa.chess.server.network.common.ServerCommonNetHandler;
import muscaa.chess.server.network.login.packets.PacketLogin;
import muscaa.chess.server.network.login.packets.PacketLoginSuccess;
import muscaa.chess.server.network.play.ServerPlayNetHandler;

public class ServerLoginNetHandler extends ServerCommonNetHandler implements IServerLoginNetHandler {
	
	@Override
	public void onConnect() throws NetworkException {
		System.out.println("Logged in");
		
		connection.send(new PacketLoginSuccess());
		connection.setContext(ServerContexts.PLAY_CONTEXT, new ServerPlayNetHandler());
	}
	
	@Override
	public void onPacketLogin(PacketLogin packet) {
		System.out.println("Received login");
		
		connection.login(packet.getName());
	}
}
