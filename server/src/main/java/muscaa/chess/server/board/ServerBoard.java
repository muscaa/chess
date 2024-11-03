package muscaa.chess.server.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fluff.network.packet.IPacketOutbound;
import muscaa.chess.server.board.pieces.ServerBishopChessPiece;
import muscaa.chess.server.board.pieces.ServerEmptyChessPiece;
import muscaa.chess.server.board.pieces.ServerKingChessPiece;
import muscaa.chess.server.board.pieces.ServerKnightChessPiece;
import muscaa.chess.server.board.pieces.ServerPawnChessPiece;
import muscaa.chess.server.board.pieces.ServerQueenChessPiece;
import muscaa.chess.server.board.pieces.ServerRookChessPiece;
import muscaa.chess.server.network.ChessClientConnection;
import muscaa.chess.server.network.play.packet.PacketClickCell;
import muscaa.chess.server.network.play.packet.PacketMove;
import muscaa.chess.server.network.play.packet.PacketStartGame;
import muscaa.chess.shared.board.AbstractBoard;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessPieceMatrix;

public class ServerBoard extends AbstractBoard<AbstractServerChessPiece> {
	
	private final Map<ChessColor, ChessClientConnection> players = new HashMap<>();
	private final Map<ChessClientConnection, ChessColor> colors = new HashMap<>();
	
	private ChessColor turn;
	private ChessCell selectedCell;
	
	public ServerBoard() {
		reset();
	}
	
	@Override
	public void click(ChessCell cell) {
		AbstractServerChessPiece p = matrix.get(cell);
		
		if (selectedCell == null) {
			if (!p.equals(ServerEmptyChessPiece.INSTANCE) && p.getColor() == turn) {
				selectedCell = cell;
				// TODO send PacketSelectCell
			}
			return;
		} else {
			if (!p.equals(ServerEmptyChessPiece.INSTANCE) && p.getColor() == turn) {
				selectedCell = cell;
				// TODO send PacketSelectCell
				return;
			}
			
			// TODO check if piece move is valid
			
			AbstractServerChessPiece piece = matrix.get(selectedCell);
			AbstractServerChessPiece capture = p;
			
			matrix.set(selectedCell, ServerEmptyChessPiece.INSTANCE);
			matrix.set(cell, piece);
			
			send(new PacketMove(selectedCell, ServerEmptyChessPiece.INSTANCE, cell, piece, capture));
			
			selectedCell = null;
			turn = turn.invert();
		}
	}
	
	@Override
	public List<ChessCell> getMoves(ChessCell cell) {
		return null;
	}
	
	private void reset() {
		matrix = new ChessPieceMatrix<>();
		for (ChessCell cell : matrix) {
			ChessColor color = cell.y >= ChessPieceMatrix.SIZE / 2 ? ChessColor.WHITE : ChessColor.BLACK;
			
			if (cell.y == 0 || cell.y == ChessPieceMatrix.SIZE - 1) {
				if (cell.x == 0 || cell.x == ChessPieceMatrix.SIZE - 1) {
					matrix.set(cell, new ServerRookChessPiece(color));
				} else if (cell.x == 1 || cell.x == ChessPieceMatrix.SIZE - 2) {
					matrix.set(cell, new ServerKnightChessPiece(color));
				} else if (cell.x == 2 || cell.x == ChessPieceMatrix.SIZE - 3) {
					matrix.set(cell, new ServerBishopChessPiece(color));
				} else if (cell.x == 3) {
					matrix.set(cell, new ServerQueenChessPiece(color));
				} else if (cell.x == ChessPieceMatrix.SIZE - 4) {
					matrix.set(cell, new ServerKingChessPiece(color));
				}
			} else if (cell.y == 1 || cell.y == ChessPieceMatrix.SIZE - 2) {
				matrix.set(cell, new ServerPawnChessPiece(color));
			} else {
				matrix.set(cell, ServerEmptyChessPiece.INSTANCE);
			}
		}
		turn = ChessColor.WHITE;
		selectedCell = null;
	}
	
	private void send(IPacketOutbound packet) {
		for (Map.Entry<ChessColor, ChessClientConnection> e : players.entrySet()) {
			e.getValue().send(packet);
		}
	}
	
	public synchronized void onConnect(ChessClientConnection connection) {
		ChessColor color = players.isEmpty() || !players.containsKey(turn) ? turn : turn.invert();
		players.put(color, connection);
		colors.put(connection, color);
		
		if (players.size() == 2) {
			players.get(ChessColor.WHITE).send(new PacketStartGame(ChessColor.WHITE, matrix));
			players.get(ChessColor.BLACK).send(new PacketStartGame(ChessColor.BLACK, matrix));
		}
	}
	
	public synchronized void onDisconnect(ChessClientConnection connection) {
		ChessColor color = colors.remove(connection);
		players.remove(color);
		
		reset();
	}
	
	public synchronized void onPacketClickCell(ChessClientConnection connection, PacketClickCell packet) {
		ChessColor clientColor = colors.get(connection);
		if (clientColor != turn) return;
		
		click(packet.getCell());
	}
}
