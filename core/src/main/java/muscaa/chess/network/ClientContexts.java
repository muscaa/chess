package muscaa.chess.network;

import fluff.network.packet.PacketContext;
import muscaa.chess.network.connection.IClientConnectionNetHandler;
import muscaa.chess.network.connection.packets.PacketEncrypt;
import muscaa.chess.network.connection.packets.PacketHandshake;
import muscaa.chess.network.login.IClientLoginNetHandler;
import muscaa.chess.network.login.packets.PacketLogin;
import muscaa.chess.network.login.packets.PacketLoginSuccess;
import muscaa.chess.network.play.IClientPlayNetHandler;
import muscaa.chess.network.play.packets.PacketClickCell;
import muscaa.chess.network.play.packets.PacketEndGame;
import muscaa.chess.network.play.packets.PacketMove;
import muscaa.chess.network.play.packets.PacketSelectCell;
import muscaa.chess.network.play.packets.PacketStartGame;
import muscaa.chess.shared.network.SharedContexts;

public class ClientContexts {
	
	public static final PacketContext<IClientConnectionNetHandler> CONNECTION_CONTEXT =
			new PacketContext<IClientConnectionNetHandler>(SharedContexts.CONNECTION_CONTEXT)
			.registerOutbound(100, PacketEncrypt.class)
			.registerInbound(101, PacketHandshake::new, IClientConnectionNetHandler::onPacketHandshake)
			;
	
	public static final PacketContext<IClientLoginNetHandler> LOGIN_CONTEXT =
			new PacketContext<IClientLoginNetHandler>(SharedContexts.LOGIN_CONTEXT)
			.registerOutbound(200, PacketLogin.class)
			.registerInbound(201, PacketLoginSuccess::new, IClientLoginNetHandler::onPacketLoginSuccess)
			;
	
	public static final PacketContext<IClientPlayNetHandler> PLAY_CONTEXT =
			new PacketContext<IClientPlayNetHandler>(SharedContexts.PLAY_CONTEXT)
			.registerOutbound(300, PacketClickCell.class)
			.registerInbound(301, PacketStartGame::new, IClientPlayNetHandler::onPacketStartGame)
			.registerInbound(302, PacketEndGame::new, IClientPlayNetHandler::onPacketEndGame)
			.registerInbound(303, PacketMove::new, IClientPlayNetHandler::onPacketMove)
			.registerInbound(304, PacketSelectCell::new, IClientPlayNetHandler::onPacketSelectCell)
			;
}
