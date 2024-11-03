package muscaa.chess.shared.board;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.bin.IBinaryOutput;
import fluff.bin.data.IBinaryData;

public class ChessCell implements IBinaryData {
	
	public static final ChessCell INVALID = new ChessCell(-1, -1);
	
	public int x;
	public int y;
	
	public ChessCell(int x, int y) {
		set(x, y);
	}
	
	public ChessCell(ChessCell cell) {
		set(cell);
	}
	
	public ChessCell() {
		set(INVALID);
	}
	
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void set(ChessCell cell) {
		set(cell.x, cell.y);
	}
	
	public boolean isValid() {
		return !this.equals(INVALID);
	}
	
	public ChessCell copy(boolean invert) {
		return invert ? new ChessCell(ChessPieceMatrix.SIZE - x - 1, ChessPieceMatrix.SIZE - y - 1)
				: new ChessCell(this);
	}
	
	public ChessCell copy() {
		return copy(false);
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
	
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ChessCell cell) {
            return x == cell.x &&
                    y == cell.y
                    ;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        long bits = 1L;
        bits = 31L * bits + x;
        bits = 31L * bits + y;
        return (int) (bits ^ (bits >> 32));
    }
}
