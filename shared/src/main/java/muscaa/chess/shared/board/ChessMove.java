package muscaa.chess.shared.board;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.bin.IBinaryOutput;
import fluff.bin.data.IBinaryData;

public class ChessMove implements IBinaryData {
	
	private ChessCell cell;
	private ChessMoveType type;
	
	public ChessMove(ChessCell cell, ChessMoveType type) {
		this.cell = cell;
		this.type = type;
	}
	
	public ChessMove() {
		this(new ChessCell(), ChessMoveType.UNDEFINED);
	}
	
	public ChessCell getCell() {
		return cell;
	}
	
	public ChessMoveType getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return type + " " + cell;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ChessMove move)) return false;
		
		return cell.equals(move.cell) && type == move.type;
	}
	
	@Override
	public int hashCode() {
        long bits = 1L;
        bits = 31L * bits + cell.x;
        bits = 31L * bits + cell.y;
        bits = 31L * bits + type.getID();
        return (int) (bits ^ (bits >> 32));
	}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		in.Data(cell);
		type = ChessMoveType.of(in.Int());
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.Data(cell);
		out.Int(type.getID());
	}
}
