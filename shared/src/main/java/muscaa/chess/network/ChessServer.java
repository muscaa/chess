package muscaa.chess.network;

import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;

import fluff.network.NetworkException;
import fluff.network.packet.channels.DefaultPacketChannel;
import fluff.network.server.AbstractClientConnection;
import fluff.network.server.AbstractServer;
import fluff.network.server.ServerModule;
import muscaa.chess.network.base.packets.PacketDisconnect;

public class ChessServer extends AbstractServer {
	
	public ChessServer(int port) {
		super(port);
		
		addModule(new TimeoutModule(30000));
		
		setDefaultContext(ServerContextRegistry.INTENT.get().getContext(), ServerContextRegistry.INTENT.get().getHandlerFunc());
		setDefaultChannel(DefaultPacketChannel::new);
	}
	
	@Override
	protected AbstractClientConnection createConnection() {
		return new ChessClientConnection(this, defaultContext, defaultHandlerFunc.invoke(), defaultChannelFunc.invoke());
	}
	
	@Override
	protected void onConnect(AbstractClientConnection connection) throws NetworkException {
		super.onConnect(connection);
	}
	
	@Override
	protected void onDisconnect(AbstractClientConnection connection) {
		super.onDisconnect(connection);
	}
	
	@Override
	public void disconnectAll() {
		sendAll(new PacketDisconnect(DisconnectReasonRegistry.SERVER_STOP.get(), "Server stopped!"));
		super.disconnectAll();
	}
	
	public int getTotalPlayers() {
		return connections.size();
	}
	
	public static class TimeoutModule extends ServerModule {
		
		public static final long DEFAULT_TIMEOUT_DELAY = 3000;
		public static final long DEFAULT_SLEEP_DELAY = 500;
		
		protected final Queue<TimeoutConnection> pending = new LinkedList<>();
		
	    protected final long timeoutDelay;
	    protected final long sleepDelay;
	    
	    /**
	     * Constructs a new timeout module with the specified timeout delay and sleep delay.
	     * 
	     * @param timeoutDelay the timeout delay before disconnecting the client
	     * @param sleepDelay the sleep delay between checks
	     */
	    public TimeoutModule(long timeoutDelay, long sleepDelay) {
	        this.timeoutDelay = timeoutDelay;
	        this.sleepDelay = sleepDelay;
	    }
	    
	    /**
	     * Constructs a new timeout module with the specified timeout delay.
	     * 
	     * @param timeoutDelay the timeout delay before disconnecting the client
	     */
	    public TimeoutModule(long timeoutDelay) {
	        this(timeoutDelay, DEFAULT_SLEEP_DELAY);
	    }
	    
	    /**
	     * Constructs a new timeout module with default delays.
	     */
	    public TimeoutModule() {
	    	this(DEFAULT_TIMEOUT_DELAY);
		}
		
	    /**
	     * The timeout loop that waits for client UUIDs.
	     */
	    protected void loop() {
	    	while (server.isRunning()) {
	    		TimeoutConnection tc = pending.peek();
	    		
	    		if (tc == null || (tc.connection.isConnected() && tc.connection.getUUID() == null && System.currentTimeMillis() < tc.connectionTime + timeoutDelay)) {
	        		try {
	    				Thread.sleep(sleepDelay);
	    			} catch (InterruptedException e) {}
	        		continue;
	    		}
	    		
	    		synchronized (pending) {
	    			pending.poll();
				}
	    		
	    		ChessClientConnection connection = tc.connection;
				if (!connection.isConnected()) continue;
	    		
	    		if (connection.getUUID() != null) {
	        		try {
	        			connection.onConnect();
	        			continue;
	        		} catch (NetworkException e) {}
	    		}
	    		
	    		connection.disconnect(DisconnectReasonRegistry.KICK.get(), "Connection timed out!");
	    	}
	    }
	    
	    @Override
	    public void onStart(ServerSocket serverSocket, boolean async) {
	        Thread t = new Thread(this::loop);
	        t.setName("Timeout Loop");
	        t.setDaemon(true);
	        t.start();
	    }
	    
	    @Override
	    public boolean onPreConnect(AbstractClientConnection connection) throws NetworkException {
	    	UUID uuid = connection.getUUID();
	    	if (uuid == null) {
	    		synchronized (pending) {
	    			pending.offer(new TimeoutConnection((ChessClientConnection) connection, System.currentTimeMillis()));
				}
	    		return true;
	    	}
	    	return false;
	    }
	    
	    @Override
	    public void onDisconnectAll() {
	        for (TimeoutConnection tc : pending) {
	        	tc.connection.disconnect();
	        }
	        synchronized (pending) {
	        	pending.clear();
			}
	    }
	    
	    private class TimeoutConnection {
	    	
	    	private final ChessClientConnection connection;
	    	private final long connectionTime;
	    	
	    	public TimeoutConnection(ChessClientConnection connection, long connectionTime) {
	    		this.connection = connection;
	    		this.connectionTime = connectionTime;
	    	}
	    }
	}
}
