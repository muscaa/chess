package muscaa.chess.server.board;

import static muscaa.chess.server.board.ServerChessPiece.BLACK_BISHOP;
import static muscaa.chess.server.board.ServerChessPiece.BLACK_KING;
import static muscaa.chess.server.board.ServerChessPiece.BLACK_KNIGHT;
import static muscaa.chess.server.board.ServerChessPiece.BLACK_PAWN;
import static muscaa.chess.server.board.ServerChessPiece.BLACK_QUEEN;
import static muscaa.chess.server.board.ServerChessPiece.BLACK_ROOK;
import static muscaa.chess.server.board.ServerChessPiece.WHITE_BISHOP;
import static muscaa.chess.server.board.ServerChessPiece.WHITE_KING;
import static muscaa.chess.server.board.ServerChessPiece.WHITE_KNIGHT;
import static muscaa.chess.server.board.ServerChessPiece.WHITE_PAWN;
import static muscaa.chess.server.board.ServerChessPiece.WHITE_QUEEN;
import static muscaa.chess.server.board.ServerChessPiece.WHITE_ROOK;

import java.util.HashMap;
import java.util.Map;

import fluff.network.packet.IPacketOutbound;
import fluff.vecmath.gen._int.vector.Vec2i;
import muscaa.chess.server.network.ChessClientConnection;
import muscaa.chess.server.network.play.packet.PacketMove;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.IBoard;

public class ServerBoard implements IBoard<ServerChessPiece> {
	
	private final ServerChessPiece[][] pieces;
	
	public ChessColor turn = ChessColor.WHITE;
	public Map<ChessColor, ChessClientConnection> players = new HashMap<>();
	public Map<ChessClientConnection, ChessColor> colors = new HashMap<>();
	
	private Vec2i selectedCell;
	
	public ServerBoard() {
		this.pieces = new ServerChessPiece[][] {
			{ BLACK_ROOK, BLACK_KNIGHT, BLACK_BISHOP, BLACK_QUEEN, BLACK_KING, BLACK_BISHOP, BLACK_KNIGHT, BLACK_ROOK },
			{ BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN },
			{ null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null },
			{ WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN },
			{ WHITE_ROOK, WHITE_KNIGHT, WHITE_BISHOP, WHITE_QUEEN, WHITE_KING, WHITE_BISHOP, WHITE_KNIGHT, WHITE_ROOK },
		};
	}
	
	@Override
	public ServerChessPiece getPiece(Vec2i cell) {
		return pieces[cell.y][cell.x];
	}
	
	public void click(Vec2i cell, ChessClientConnection client) {
		ChessColor clientColor = colors.get(client);
		if (clientColor != turn) return;
		
		ServerChessPiece p = getPiece(cell);
		
		if (selectedCell == null) {
			if (p != null && p.getColor() == turn) {
				selectedCell = cell;
			}
			return;
		} else {
			if (p != null && p.getColor() == turn) {
				selectedCell = cell;
				return;
			}
			
			// TODO check if piece move is valid
			
			ServerChessPiece piece = getPiece(selectedCell);
			ServerChessPiece capture = p;
			
			setPiece(selectedCell, null);
			setPiece(cell, piece);
			
			send(new PacketMove(selectedCell, cell, piece, capture));
			
			selectedCell = null;
			turn = turn.invert();
		}
	}
	
	public void setPiece(Vec2i cell, ServerChessPiece piece) {
		pieces[cell.y][cell.x] = piece;
	}
	
	public ServerChessPiece[][] getPieces() {
		return pieces;
	}
	
	public void send(IPacketOutbound packet) {
		for (Map.Entry<ChessColor, ChessClientConnection> e : players.entrySet()) {
			e.getValue().send(packet);
		}
	}
}
