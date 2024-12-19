package muscaa.chess.shared.board;

import fluff.vecmath.gen._int.vector.AbstractIntVec2;

public class Cell extends AbstractIntVec2<Cell> {
	
	public static final Cell INVALID = new Cell(-1, -1);
	
	public static final Cell ZERO_ZERO = new Cell(0, 0);
	public static final Cell ONE_ONE = new Cell(1, 1);
	public static final Cell ONE_ZERO = new Cell(1, 0);
	public static final Cell ZERO_ONE = new Cell(0, 1);
	
	/*public static final Cell SIZE_ZERO = new Cell(ChessPieceMatrix.SIZE, 0);
	public static final Cell ZERO_SIZE = new Cell(0, ChessPieceMatrix.SIZE);
	public static final Cell SIZE_SIZE = new Cell(ChessPieceMatrix.SIZE, ChessPieceMatrix.SIZE);*/
	
	public int x;
	public int y;
	
	public Cell(int x, int y) {
		set(x, y);
	}
	
	public Cell(Cell cell) {
		set(cell);
	}
	
	public Cell() {
		set(INVALID);
	}
	
	public void set(Cell cell) {
		set(cell.x, cell.y);
	}
	
	public Cell copyWithX(int x) {
		Cell copy = copy();
		copy.x = x;
		return copy;
	}
	
	public Cell copyWithY(int y) {
		Cell copy = copy();
		copy.y = y;
		return copy;
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
    public Cell set(int proj_1, int proj_2) {
        this.x = proj_1;
        this.y = proj_2;
        return this;
    }
	
	@Override
	public Cell copy() {
		return new Cell(this);
	}
	
	@Deprecated
	public Cell copy(boolean invert) {
		return invert ? new Cell(8 - x - 1, 8 - y - 1)
				: new Cell(this);
	}
}
