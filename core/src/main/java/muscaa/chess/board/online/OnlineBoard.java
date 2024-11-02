package muscaa.chess.board.online;

import fluff.vecmath.gen._int.vector.Vec2i;
import muscaa.chess.ChessGame;
import muscaa.chess.board.ClientChessPiece;
import muscaa.chess.board.IClientBoard;
import muscaa.chess.network.play.packets.PacketClickCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.IBoard;

public class OnlineBoard implements IClientBoard {
	
	private final ChessColor color;
	
	private ClientChessPiece[][] pieces;
	
	public OnlineBoard(ChessColor color) {
		this.color = color;
		this.pieces = new ClientChessPiece[IBoard.SIZE][IBoard.SIZE];
	}
	
	@Override
	public void click(Vec2i cell) {
		ChessGame.INSTANCE.getNetwork().getClient().send(new PacketClickCell(cell));
	}
	
	@Override
	public ClientChessPiece getPiece(Vec2i cell) {
		return pieces[cell.y][cell.x];
	}
	
	@Override
	public void setPieces(ClientChessPiece[][] pieces) {
		this.pieces = pieces;
	}
	
	@Override
	public ChessColor getColor() {
		return color;
	}
}
