package muscaa.chess.client.board.old;

public abstract class CAbstractBoard {
	
	/*protected Client chess;
	protected BoardLayer layer;
	
	protected ClientMatrix matrix;
	protected TeamValue team;
	protected List<Highlight> highlights = new LinkedList<>();
    
    public abstract void click(Cell cell);
    
	public void startGame() {
		highlights.clear();
		
		TaskManager.render(() -> {
			chess.setScreen(null);
		});
	}
	
	public void updateBoard(int width, int height, List<ClientPiece> pieces) {
		if (matrix == null) {
			matrix = new ClientMatrix(width, height);
		} else if (matrix.getWidth() != width || matrix.getHeight() != height) {
			matrix.reset(width, height);
		}
		
		Iterator<ClientPiece> it = pieces.iterator();
		for (Cell cell : matrix) {
			ClientPiece piece = it.next();
			
			matrix.set(cell, piece);
		}
		
		layer.resize(Screen.WIDTH, Screen.HEIGHT);
		
		SoundRegistry.MOVE.get().play();
	}
	
	public void endGame(TeamValue winner) {
		TaskManager.render(() -> {
			chess.setScreen(new DisconnectedScreen(
					winner == TeamRegistry.NULL.get() ? "Stalemate"
					: winner == team ? "You win"
							: "Opponent wins"));
			
			chess.setBoard(null);
		});
	}
	
	public void init(Client chess, BoardLayer layer) {
		this.chess = chess;
		this.layer = layer;
	}
	
	public void dispose() {}
	
	public ClientMatrix getMatrix() {
		return matrix;
	}
	
	public TeamValue getTeam() {
		return team;
	}
	
	public void setTeam(TeamValue team) {
		this.team = team;
	}
	
	public List<Highlight> getHighlights() {
		return highlights;
	}
	
	public void setHighlights(List<Highlight> highlights) {
		this.highlights = highlights;
	}*/
}
