package muscaa.chess.board;

public class Highlight {
	
	private final Cell cell;
	private final HighlightValue type;
	
	public Highlight(Cell cell, HighlightValue type) {
		this.cell = cell;
        this.type = type;
	}
	
	public Cell getCell() {
		return cell;
	}
	
	public HighlightValue getType() {
		return type;
	}
}
