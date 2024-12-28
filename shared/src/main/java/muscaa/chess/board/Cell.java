package muscaa.chess.board;

import fluff.vecmath.gen._int.vector.AbstractIntVec2;
import muscaa.chess.board.matrix.AbstractMatrix;

public final class Cell extends AbstractIntVec2<Cell> {
	
	public static final Cell INVALID = new Cell(-1, -1);
	
	public static final Cell ZERO_ZERO = new Cell(0, 0);
	public static final Cell ONE_ONE = new Cell(1, 1);
	public static final Cell ONE_ZERO = new Cell(1, 0);
	public static final Cell ZERO_ONE = new Cell(0, 1);
	
	public final int x;
	public final int y;
	
	public Cell(int x, int y) {
		this.x = x;
        this.y = y;
	}
	
	public Cell invert(AbstractMatrix<?> matrix) {
		return set(matrix.getWidth() - x - 1, matrix.getHeight() - y - 1);
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
    protected Cell set(int proj_1, int proj_2) {
        return new Cell(proj_1, proj_2);
    }
	
    @Deprecated
	@Override
	public Cell copy() {
		throw new UnsupportedOperationException();
	}
}
