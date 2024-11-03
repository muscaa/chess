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
import muscaa.chess.network.play.packets.PacketStartGame;
import muscaa.chess.shared.network.CommonContexts;

public class ClientContexts {
	
	public static final PacketContext<IClientConnectionNetHandler> CONNECTION_CONTEXT =
			new PacketContext<IClientConnectionNetHandler>(CommonContexts.CONNECTION_CONTEXT)
			.registerOutbound(0, PacketEncrypt.class)
			.registerInbound(1, PacketHandshake::new, IClientConnectionNetHandler::onPacketHandshake)
			;
	
	public static final PacketContext<IClientLoginNetHandler> LOGIN_CONTEXT =
			new PacketContext<IClientLoginNetHandler>(CommonContexts.LOGIN_CONTEXT)
			.registerOutbound(0, PacketLogin.class)
			.registerInbound(1, PacketLoginSuccess::new, IClientLoginNetHandler::onPacketLoginSuccess)
			;
	
	public static final PacketContext<IClientPlayNetHandler> PLAY_CONTEXT =
			new PacketContext<IClientPlayNetHandler>(CommonContexts.PLAY_CONTEXT)
			.registerOutbound(0, PacketClickCell.class)
			.registerInbound(1, PacketStartGame::new, IClientPlayNetHandler::onPacketStartGame)
			.registerInbound(2, PacketEndGame::new, IClientPlayNetHandler::onPacketEndGame)
			.registerInbound(3, PacketMove::new, IClientPlayNetHandler::onPacketMove)
			;
}
