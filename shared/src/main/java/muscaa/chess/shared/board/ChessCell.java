package muscaa.chess.shared.board;

import java.io.IOException;

import fluff.bin.IBinaryInput;
import fluff.bin.IBinaryOutput;
import fluff.bin.data.IBinaryData;
import fluff.vecmath.gen._int.vector.AbstractIntVec2;

public class ChessCell extends AbstractIntVec2<ChessCell> implements IBinaryData {
	
	public static final ChessCell INVALID = new ChessCell(-1, -1);
	
	public static final ChessCell ZERO_ZERO = new ChessCell(0, 0);
	public static final ChessCell ONE_ONE = new ChessCell(1, 1);
	public static final ChessCell ONE_ZERO = new ChessCell(1, 0);
	public static final ChessCell ZERO_ONE = new ChessCell(0, 1);
	
	public static final ChessCell SIZE_ZERO = new ChessCell(ChessPieceMatrix.SIZE, 0);
	public static final ChessCell ZERO_SIZE = new ChessCell(0, ChessPieceMatrix.SIZE);
	public static final ChessCell SIZE_SIZE = new ChessCell(ChessPieceMatrix.SIZE, ChessPieceMatrix.SIZE);
	
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
	
    @Override
    protected int proj_1() {
        return x;
    }
    
    @Override
    protected int proj_2() {
        return y;
    }
    
    @Override
    public ChessCell set(int proj_1, int proj_2) {
        this.x = proj_1;
        this.y = proj_2;
        return this;
    }
    
	public void set(ChessCell cell) {
		set(cell.x, cell.y);
	}
	
	@Override
	public ChessCell copy() {
		return copy(false);
	}
	
	public ChessCell copy(boolean invert) {
		return invert ? new ChessCell(ChessPieceMatrix.SIZE - x - 1, ChessPieceMatrix.SIZE - y - 1)
				: new ChessCell(this);
	}
	
	public boolean isValid() {
		return !this.equals(INVALID);
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
}
