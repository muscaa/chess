package muscaa.chess.board;

public class Highlight {
	
	private final Cell cell;
	private final HighlightType type;
	
	public Highlight(Cell cell, HighlightType type) {
		this.cell = cell;
        this.type = type;
	}
	
	public Cell getCell() {
		return cell;
	}
	
	public HighlightType getType() {
		return type;
	}
}
