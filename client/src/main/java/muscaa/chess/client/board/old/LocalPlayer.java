package muscaa.chess.client.board.old;

public class LocalPlayer /*extends AbstractPlayer*/ {
	
	/*protected final CAbstractBoard board;
	
	public LocalPlayer(CAbstractBoard board) {
		this.board = board;
	}
	
	@Override
	public void startGame() {
		board.startGame();
	}
	
	@Override
	public void updateBoard(ServerMatrix matrix) {
		List<ClientPiece> pieces = new LinkedList<>();
		for (Cell cell : matrix) {
			AbstractServerPiece piece = matrix.get(cell);
			ClientPiece clientPiece = ClientPieceRegistry.REG
					.get(piece.getRegistryValue().getKey().getID())
					.get()
					.create(piece.getTeam());
			
			pieces.add(clientPiece);
		}
		
		board.updateBoard(matrix.getWidth(), matrix.getHeight(), pieces);
	}
	
	@Override
	public void updateTurn(TeamValue turn) {
		// TODO
	}
	
	@Override
	public void endGame(TeamValue winner) {
		board.endGame(winner);
	}
	
	@Override
	public void updateTeam(TeamValue team) {
		super.updateTeam(team);
		
		board.setTeam(team);
	}
	
	@Override
	public void updateHighlights(List<Highlight> highlights) {
		super.updateHighlights(highlights);
		
		board.setHighlights(highlights);
	}*/
}
