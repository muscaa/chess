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
import muscaa.chess.server.network.play.packet.PacketSelectCell;
import muscaa.chess.server.network.play.packet.PacketStartGame;
import muscaa.chess.shared.board.AbstractBoard;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessMoves;
import muscaa.chess.shared.board.ChessPieceMatrix;

public class ServerBoard extends AbstractBoard<AbstractServerChessPiece> {
	
	private final Map<ChessColor, ChessClientConnection> players = new HashMap<>();
	private final Map<ChessClientConnection, ChessColor> colors = new HashMap<>();
	
	private ChessColor turn;
	private final ChessCell selectedCell = new ChessCell();
	
	public ServerBoard() {
		reset();
	}
	
	@Override
	public void click(ChessCell cell) {
		ChessClientConnection player = players.get(turn);
		AbstractServerChessPiece piece = matrix.get(cell);
		
		if (selectedCell.equals(ChessCell.INVALID)) {
			if (!piece.equals(ServerEmptyChessPiece.INSTANCE) && piece.getColor() == turn) {
				selectCell(player, cell);
			}
			return;
		} else {
			if (!piece.equals(ServerEmptyChessPiece.INSTANCE) && piece.getColor() == turn) {
				selectCell(player, selectedCell.equals(cell) ? ChessCell.INVALID : cell);
				return;
			}
			
			ChessMoves<AbstractServerChessPiece> moves = getMoves(selectedCell);
			
			boolean validMove = false;
			for (ChessCell move : moves.getList()) {
				if (move.equals(cell)) {
					validMove = true;
					break;
				}
			}
			
			if (validMove) {
				AbstractServerChessPiece selectedPiece = matrix.get(selectedCell);
				matrix.set(selectedCell, ServerEmptyChessPiece.INSTANCE);
				matrix.set(cell, selectedPiece);
				
				send(new PacketMove(selectedCell, ServerEmptyChessPiece.INSTANCE, cell, selectedPiece, piece));
				
				turn = turn.invert();
			}
			
			selectCell(player, ChessCell.INVALID);
		}
	}
	
	public void selectCell(ChessClientConnection player, ChessCell cell) {
		selectedCell.set(cell);
		
		ChessMoves<AbstractServerChessPiece> moves = getMoves(selectedCell);
		player.send(new PacketSelectCell(selectedCell, moves.getList()));
	}
	
	public ChessMoves<AbstractServerChessPiece> getMoves(ChessCell cell) {
		ChessMoves<AbstractServerChessPiece> moves = new ChessMoves<>(matrix);
		
		if (!cell.equals(ChessCell.INVALID)) {
			matrix.get(cell).findMoves(moves, cell);
		}
		
		return moves;
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
		selectedCell.set(ChessCell.INVALID);
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
		if (players.size() != 2) return;
		
		ChessColor clientColor = colors.get(connection);
		if (clientColor != turn) return;
		
		ChessCell cell = packet.getCell();
		if (cell.x < 0 || cell.y < 0 || cell.x >= ChessPieceMatrix.SIZE || cell.y >= ChessPieceMatrix.SIZE) return;
		
		click(cell);
	}
}
