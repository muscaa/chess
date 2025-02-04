package muscaa.chess.board.old;

public class Lobby {
	
	/*public final LinkedList<AbstractPlayer> players = new LinkedList<>();
	public final HashMap<TeamValue, AbstractPlayer> teams = new HashMap<>();
	
	public ServerMatrix matrix;
	public TeamValue turn;
	public Cell selectedCell;
	public List<Cell> inCheckCells;
	public List<Cell> lastMoveCells;
	public Map<Cell, Map<Cell, AbstractMoveValue>> allMoves;
	private boolean started;
	
	public void reset() {
		matrix = new ServerMatrix(8, 8);
		
		matrix.begin();
		for (Cell cell : matrix) {
			TeamValue team = cell.y >= matrix.getHeight() / 2 ? TeamRegistry.WHITE.get() : TeamRegistry.BLACK.get();
			
			if (cell.y == 0 || cell.y == matrix.getHeight() - 1) {
				if (cell.x == 0 || cell.x == matrix.getWidth() - 1) {
					matrix.set(cell, ServerPieceRegistry.ROOK.get().create(team));
				} else if (cell.x == 1 || cell.x == matrix.getWidth() - 2) {
					matrix.set(cell, ServerPieceRegistry.KNIGHT.get().create(team));
				} else if (cell.x == 2 || cell.x == matrix.getWidth() - 3) {
					matrix.set(cell, ServerPieceRegistry.BISHOP.get().create(team));
				} else if (cell.x == 3) {
					matrix.set(cell, ServerPieceRegistry.QUEEN.get().create(team));
				} else if (cell.x == matrix.getWidth() - 4) {
					matrix.set(cell, ServerPieceRegistry.KING.get().create(team));
				} else {
					matrix.set(cell, NullPiece.INSTANCE);
				}
			} else if (cell.y == 1 || cell.y == matrix.getHeight() - 2) {
				matrix.set(cell, ServerPieceRegistry.PAWN.get().create(team));
			} else {
				matrix.set(cell, NullPiece.INSTANCE);
			}
		}
		matrix.end();
		
		turn = TeamRegistry.WHITE.get();
		selectedCell = Cell.INVALID;
		inCheckCells = new LinkedList<>();
		lastMoveCells = new LinkedList<>();
		allMoves = new HashMap<>();
	}
	
	public void startGame() {
		reset();
		findAllMoves();
		
		for (AbstractPlayer p : players) {
			p.updateBoard(matrix);
		}
		
		started = true;
		
		for (AbstractPlayer p : players) {
			p.startGame();
		}
	}
	
	public void click(Cell cell) {
		synchronized (players) {
			AbstractPlayer player = teams.get(turn);
			AbstractServerPiece piece = matrix.get(cell);
			
			if (selectedCell.equals(Cell.INVALID)) {
				if (!piece.equals(NullPiece.INSTANCE) && piece.getTeam() == turn) {
					selectCell(player, null, cell);
				}
				return;
			}
			
			if (!piece.equals(NullPiece.INSTANCE) && piece.getTeam() == turn) {
				selectCell(player, null, selectedCell.equals(cell) ? Cell.INVALID : cell);
				return;
			}
			
			Map<Cell, AbstractMoveValue> moves = allMoves.get(selectedCell);
			
			AbstractMoveValue move = moves.get(cell);
			if (move == null) {
				selectCell(player, null, Cell.INVALID);
				return;
			}
			
			doMove(move, selectedCell, cell);
		}
	}
	
	public void doMove(AbstractMoveValue move, Cell from, Cell to) {
		AbstractServerPiece selectedPiece = matrix.get(from);
		AbstractPlayer player = teams.get(turn);
		AbstractPlayer opponent = teams.get(turn.invert());
		
		matrix.begin();
		selectedPiece.onPreMove(matrix, from, to);
		move.doMove(matrix, from, to);
		selectedPiece.onPostMove(matrix, from, to);
		matrix.end();
		
		for (AbstractPlayer p : players) {
			p.updateBoard(matrix);
		}
		findAllMoves();
		
		inCheckCells.clear();
		inCheckCells.addAll(getInCheck(turn.invert()));
		
		lastMoveCells.clear();
		lastMoveCells.add(from);
		lastMoveCells.add(to);
		
		selectCell(player, opponent, Cell.INVALID);
		
		Map<Cell, Map<Cell, AbstractMoveValue>> remainingMoves = getMoves(turn.invert());
		int remainingMovesCount = 0;
		for (Map.Entry<Cell, Map<Cell, AbstractMoveValue>> e : remainingMoves.entrySet()) {
			remainingMovesCount += e.getValue().size();
		}
		
		if (remainingMovesCount == 0) {
			if (!inCheckCells.isEmpty()) {
				endGame(turn);
			} else {
				endGame(TeamRegistry.NULL.get());
			}
			return;
		}
		
		turn = turn.invert();
		
		for (AbstractPlayer p : players) {
            p.updateTurn(turn);
		}
	}
	
	public void endGame(TeamValue winner) {
		if (!started) return;
		started = false;
		
		System.out.println("Winner: " + winner.getKey().getID());
		
		for (AbstractPlayer p : players) {
			p.endGame(winner);
		}
	}
	
	public void selectCell(AbstractPlayer player, AbstractPlayer opponent, Cell cell) {
		selectedCell = cell;
		
		List<Highlight> highlights = new LinkedList<>();
		List<Highlight> opponentHighlights = new LinkedList<>();
		
		for (Cell inCheckCell : inCheckCells) {
			Highlight h = new Highlight(inCheckCell, HighlightRegistry.CHECK.get());
			
			highlights.add(h);
			opponentHighlights.add(h);
		}
		
		for (Cell lastMoveCell : lastMoveCells) {
			Highlight h = new Highlight(lastMoveCell, HighlightRegistry.LAST_MOVE.get());
			
			highlights.add(h);
			opponentHighlights.add(h);
		}
		
		if (!selectedCell.equals(Cell.INVALID)) {
			highlights.add(new Highlight(selectedCell, HighlightRegistry.SELECTED.get()));
			
			Map<Cell, AbstractMoveValue> moves = allMoves.get(selectedCell);
			for (Map.Entry<Cell, AbstractMoveValue> e : moves.entrySet()) {
				highlights.add(new Highlight(e.getKey(), HighlightRegistry.MOVE_AVAILABLE.get()));
			}
		}
		
		player.updateHighlights(highlights);
		if (opponent != null) {
			opponent.updateHighlights(opponentHighlights);
		}
	}
	
	public void findAllMoves() {
		allMoves.clear();
		
		for (Cell from : matrix) {
			AbstractServerPiece piece = matrix.get(from);
			
			Map<Cell, AbstractMoveValue> moves = new HashMap<>();
			piece.findMoves(moves, matrix, from);
			
			Map<Cell, AbstractMoveValue> validMoves = new HashMap<>();
			for (Map.Entry<Cell, AbstractMoveValue> moveEntry : moves.entrySet()) {
				Cell to = moveEntry.getKey();
				AbstractMoveValue move = moveEntry.getValue();
				
				matrix.begin();
				move.doMove(matrix, from, to);
				matrix.end();
				
				boolean inCheck = !getInCheck(piece.getTeam()).isEmpty();
				matrix.undo(1);
				
				if (inCheck) continue;
				
				validMoves.put(to, move);
			}
			
			allMoves.put(from, validMoves);
		}
	}
	
	public List<Cell> getInCheck(TeamValue team) {
		List<Cell> inCheck = new LinkedList<>();
		for (Cell opponentFrom : matrix) {
			AbstractServerPiece opponentPiece = matrix.get(opponentFrom);
			if (opponentPiece.getTeam() == team) continue;
			
			Map<Cell, AbstractMoveValue> opponentMoves = new HashMap<>();
			opponentPiece.findMoves(opponentMoves, matrix, opponentFrom);
			
			for (Cell checkable : matrix) {
				AbstractServerPiece checkablePiece = matrix.get(checkable);
				if (!checkablePiece.isCheckable()) continue;
				if (checkablePiece.getTeam() != team) continue;
				
				AbstractMoveValue opponentMove = opponentMoves.get(checkable);
				if (opponentMove instanceof CaptureMove) {
					inCheck.add(checkable);
				}
			}
		}
		return inCheck;
	}
	
	public Map<Cell, Map<Cell, AbstractMoveValue>> getMoves(TeamValue team) {
		Map<Cell, Map<Cell, AbstractMoveValue>> moves = new HashMap<>();
		for (Map.Entry<Cell, Map<Cell, AbstractMoveValue>> e : allMoves.entrySet()) {
			Cell from = e.getKey();
			AbstractServerPiece piece = matrix.get(from);
			if (piece.getTeam() != team) continue;
			
			moves.put(from, e.getValue());
		}
		return moves;
	}
	
	public boolean join(AbstractPlayer player, boolean spectator) {
		synchronized (players) {
			players.add(player);
			player.init(this);
			
			if (spectator) {
				player.updateTeam(TeamRegistry.SPECTATOR.get());
				return true;
			}
			
			AbstractPlayer white = teams.get(TeamRegistry.WHITE.get());
			AbstractPlayer black = teams.get(TeamRegistry.BLACK.get());
			
			if (white == null) {
				teams.put(TeamRegistry.WHITE.get(), player);
				player.updateTeam(TeamRegistry.WHITE.get());
				
				if (black != null) {
					startGame();
				}
				return true;
			}
			
			if (black == null) {
				teams.put(TeamRegistry.BLACK.get(), player);
				player.updateTeam(TeamRegistry.BLACK.get());
				
				if (white != null) {
					startGame();
				}
				return true;
			}
			
			return false;
		}
	}
	
	public void leave(AbstractPlayer player) {
		synchronized (players) {
			players.remove(player);
			
			player = teams.remove(player.getTeam());
			if (player != null) {
				endGame(player.getTeam().invert());
			}
		}
	}
	
	public void close() {
		synchronized (players) {
			if (teams.isEmpty()) return;
			
			endGame(TeamRegistry.NULL.get());
			players.clear();
			teams.clear();
		}
	}*/
}
