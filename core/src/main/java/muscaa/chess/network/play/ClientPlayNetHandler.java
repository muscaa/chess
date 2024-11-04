package muscaa.chess.network.play;

import muscaa.chess.ChessGame;
import muscaa.chess.board.ClientBoard;
import muscaa.chess.network.common.ClientCommonNetHandler;
import muscaa.chess.network.play.packets.PacketEndGame;
import muscaa.chess.network.play.packets.PacketMove;
import muscaa.chess.network.play.packets.PacketSelectCell;
import muscaa.chess.network.play.packets.PacketStartGame;
import muscaa.chess.task.TaskManager;

public class ClientPlayNetHandler extends ClientCommonNetHandler implements IClientPlayNetHandler {
	
	@Override
	public void onPacketStartGame(PacketStartGame packet) {
		ChessGame.INSTANCE.getBoardLayer().setBoard(new ClientBoard(packet));
		
		TaskManager.render(() -> {
			ChessGame.INSTANCE.getGuiLayer().setScreen(null);
		});
	}
	
	@Override
	public void onPacketEndGame(PacketEndGame packet) {
		ChessGame.INSTANCE.getBoardLayer().getBoard().endGame(packet);
	}
	
	@Override
	public void onPacketMove(PacketMove packet) {
		ChessGame.INSTANCE.getBoardLayer().getBoard().move(packet);
	}
	
	@Override
	public void onPacketSelectCell(PacketSelectCell packet) {
		ChessGame.INSTANCE.getBoardLayer().getBoard().selectCell(packet);
	}
}
