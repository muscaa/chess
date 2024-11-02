package muscaa.chess.network;

import fluff.network.packet.PacketContext;
import muscaa.chess.network.connection.IClientConnectionNetHandler;
import muscaa.chess.network.connection.packets.PacketEncrypt;
import muscaa.chess.network.connection.packets.PacketHandshake;
import muscaa.chess.network.login.IClientLoginNetHandler;
import muscaa.chess.network.login.packets.PacketLogin;
import muscaa.chess.network.login.packets.PacketLoginSuccess;
import muscaa.chess.network.play.IClientPlayNetHandler;
import muscaa.chess.network.play.packets.PacketBoard;
import muscaa.chess.network.play.packets.PacketClickCell;
import muscaa.chess.shared.network.NetworkShared;

public class NetworkClient {
	
	public static final PacketContext<IClientConnectionNetHandler> CONNECTION_CONTEXT =
			new PacketContext<IClientConnectionNetHandler>(NetworkShared.CONNECTION_CONTEXT)
			.registerOutbound(0, PacketEncrypt.class)
			.registerInbound(1, PacketHandshake::new, IClientConnectionNetHandler::onPacketHandshake)
			;
	
	public static final PacketContext<IClientLoginNetHandler> LOGIN_CONTEXT =
			new PacketContext<IClientLoginNetHandler>(NetworkShared.LOGIN_CONTEXT)
			.registerOutbound(0, PacketLogin.class)
			.registerInbound(1, PacketLoginSuccess::new, IClientLoginNetHandler::onPacketLoginSuccess)
			;
	
	public static final PacketContext<IClientPlayNetHandler> PLAY_CONTEXT =
			new PacketContext<IClientPlayNetHandler>(NetworkShared.PLAY_CONTEXT)
			.registerOutbound(0, PacketClickCell.class)
			.registerInbound(1, PacketBoard::new, IClientPlayNetHandler::onPacketBoard)
			;
}
