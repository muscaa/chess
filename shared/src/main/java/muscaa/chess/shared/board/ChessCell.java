package muscaa.chess.shared.board;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.bin.IBinaryOutput;
import fluff.bin.data.IBinaryData;

public class ChessCell implements IBinaryData {
	
	public int x;
	public int y;
	
	public ChessCell(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public ChessCell() {
		this(-1, -1);
	}
	
	@Override
	public void readData(IBinaryInput in) throws IOException {
		x = in.Int();
		y = in.Int();
	}
	
	@Override
	public void writeData(IBinaryOutput out) throws IOException {
		out.Int(x);
		out.Int(y);
	}
	
	public boolean isInBounds() {
		return x >= 0 && x < ChessPieceMatrix.SIZE && y >= 0 && y < ChessPieceMatrix.SIZE;
	}
	
	public ChessCell copy() {
		return new ChessCell(x, y);
	}
}
