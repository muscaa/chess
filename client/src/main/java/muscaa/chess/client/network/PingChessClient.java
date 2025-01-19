package muscaa.chess.client.network;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import fluff.network.NetworkException;
import muscaa.chess.client.config.ServersConfig;
import muscaa.chess.client.network.ping.packets.CPacketPing;
import muscaa.chess.network.IntentRegistry;

public class PingChessClient extends AbstractChessClient {
	
	public CompletableFuture<CPacketPing> pingFuture;
	
	public CompletableFuture<CPacketPing> ping(ServersConfig.Server server) {
		pingFuture = new CompletableFuture<>();
		
		try {
			connect(server.address, server.port, IntentRegistry.PING.get());
		} catch (IOException | NetworkException e) {
			pingFuture.completeExceptionally(e);
		}
		
		return pingFuture;
	}
}
