package muscaa.chess.board;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import muscaa.chess.ChessGame;
import muscaa.chess.assets.Sounds;
import muscaa.chess.network.play.packets.PacketClickCell;
import muscaa.chess.network.play.packets.PacketEndGame;
import muscaa.chess.network.play.packets.PacketMove;
import muscaa.chess.network.play.packets.PacketSelectCell;
import muscaa.chess.network.play.packets.PacketStartGame;
import muscaa.chess.shared.board.AbstractBoard;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;

public class ClientBoard extends AbstractBoard<ClientChessPiece> {
	
	private final ChessColor color;
	private final ChessCell selectedCell = new ChessCell();
	private final List<ChessCell> moves = Collections.synchronizedList(new LinkedList<>());
	
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
	}
	
	public synchronized void move(PacketMove packet) {
		matrix.set(packet.getFrom(), packet.getFromPiece());
		matrix.set(packet.getTo(), packet.getToPiece());
		
		if (packet.getCapturePiece() != null) {
			Sounds.CAPTURE.play();
		} else {
			Sounds.MOVE.play();
		}
	}
	
	public synchronized void selectCell(PacketSelectCell packet) {
		moves.clear();
		
		selectedCell.set(packet.getCell());
		moves.addAll(packet.getMoves());
	}
	
	public ChessCell getSelectedCell() {
		return selectedCell;
	}
	
	public List<ChessCell> getMoves() {
		return moves;
	}
}
