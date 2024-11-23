package muscaa.chess.board;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.audio.Sound;

import muscaa.chess.ChessGame;
import muscaa.chess.assets.Sounds;
import muscaa.chess.gui.screens.DisconnectedScreen;
import muscaa.chess.network.play.packets.PacketClickCell;
import muscaa.chess.network.play.packets.PacketEndGame;
import muscaa.chess.network.play.packets.PacketMove;
import muscaa.chess.network.play.packets.PacketSelectCell;
import muscaa.chess.network.play.packets.PacketStartGame;
import muscaa.chess.shared.board.AbstractBoard;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessMove;
import muscaa.chess.task.TaskManager;

public class ClientBoard extends AbstractBoard<ClientChessPiece> {
	
	private final ChessColor color;
	private final ChessCell selectedCell = new ChessCell();
	private final ChessCell checkCell = new ChessCell();
	private final List<ChessMove> moves = Collections.synchronizedList(new LinkedList<>());
	
	public ClientBoard(PacketStartGame packet) {
		color = packet.getColor();
		matrix = packet.getMatrix();
		selectedCell.set(ChessCell.INVALID);
	}
	
	@Override
	public void click(ChessCell cell) {
		ChessGame.INSTANCE.getNetwork().send(new PacketClickCell(cell));
	}
	
	public ChessColor getColor() {
		return color;
	}
	
	public synchronized void endGame(PacketEndGame packet) {
		ChessGame.INSTANCE.getNetwork().disconnect();
		
		TaskManager.render(() -> {
			ChessGame.INSTANCE.getGuiLayer().setScreen(new DisconnectedScreen(packet.getWinner() == color ? "You win" : "Opponent wins"));
		});
	}
	
	public synchronized void move(PacketMove packet) {
		matrix.set(packet.getFrom(), packet.getFromPiece());
		matrix.set(packet.getTo(), packet.getToPiece());
		checkCell.set(packet.getCheckCell());
		
		Sound sound = Sounds.MOVE;
		if (!packet.getCheckCell().equals(ChessCell.INVALID)) {
			sound = Sounds.CHECK;
		} else if (!packet.getCapturePiece().equals(ClientChessPiece.EMPTY)) {
			sound = Sounds.CAPTURE;
		}
		
		sound.play();
	}
	
	public synchronized void selectCell(PacketSelectCell packet) {
		moves.clear();
		
		selectedCell.set(packet.getCell());
		moves.addAll(packet.getMoves());
	}
	
	public ChessCell getSelectedCell() {
		return selectedCell;
	}
	
	public List<ChessMove> getMoves() {
		return moves;
	}
	
	public ChessCell getCheckCell() {
		return checkCell;
	}
}
