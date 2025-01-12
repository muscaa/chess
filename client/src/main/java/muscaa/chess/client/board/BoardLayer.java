package muscaa.chess.client.board;

import java.util.List;

import com.badlogic.gdx.graphics.Color;

import muscaa.chess.board.Cell;
import muscaa.chess.board.Highlight;
import muscaa.chess.board.HighlightRegistry;
import muscaa.chess.board.HighlightValue;
import muscaa.chess.board.TeamRegistry;
import muscaa.chess.board.TeamValue;
import muscaa.chess.client.Client;
import muscaa.chess.client.assets.TextureValue;
import muscaa.chess.client.board.matrix.ClientMatrix;
import muscaa.chess.client.board.piece.ClientPiece;
import muscaa.chess.client.config.Theme;
import muscaa.chess.client.layer.ILayer;
import muscaa.chess.client.utils.Shapes;

public class BoardLayer implements ILayer {
    
	private final Client chess;
	
    private float tileSize;
    private float boardX, boardY;
	
    public BoardLayer(Client chess) {
    	this.chess = chess;
	}
    
    public void init() {}
    
	@Override
	public void render(int mouseX, int mouseY, float delta) {
		if (chess.getBoard() == null) return;
		
		AbstractBoard board = chess.getBoard();
		if (board.getMatrix() == null || board.getTeam() == null) return;
		
		ClientMatrix matrix = board.getMatrix();
		TeamValue team = board.getTeam();
		List<Highlight> highlights = board.getHighlights();
		
		// chess table
		for (Cell cell : matrix) {
        	Color color = (cell.x + cell.y) % 2 == 0 ? Theme.BOARD_CELL_LIGHT : Theme.BOARD_CELL_DARK;
            float x = boardX + cell.x * tileSize;
            float y = boardY + (matrix.getHeight() - cell.y - 1) * tileSize;
            
            Shapes.rect(x, y, tileSize, tileSize, color);
		}
		
		// chess highlights under
		for (Highlight highlight : highlights) {
			Cell niceCell = highlight.getCell();
			if (team == TeamRegistry.BLACK.get() && Theme.INVERT_TABLE_IF_BLACK) {
				niceCell = niceCell.invert(matrix);
			}
			
            float x = boardX + niceCell.x * tileSize;
            float y = boardY + (matrix.getHeight() - niceCell.y - 1) * tileSize;
            
            HighlightValue type = highlight.getType();
            
            Color color = null;
            if (type == HighlightRegistry.SELECTED.get()) {
            	color = Theme.BOARD_CELL_SELECTED;
            } else if (type == HighlightRegistry.CHECK.get()) {
            	color = Theme.BOARD_CELL_CHECK;
            } else if (type == HighlightRegistry.LAST_MOVE.get()) {
            	color = Theme.BOARD_CELL_LAST_MOVE;
            }
            
            if (color != null) {
            	Shapes.rect(x, y, tileSize, tileSize, color);
            }
		}
		
		// chess pieces
		for (Cell cell : matrix) {
            float x = boardX + cell.x * tileSize;
            float y = boardY + (matrix.getHeight() - cell.y - 1) * tileSize;
            float off = tileSize / 32;
            
            Cell niceCell = cell;
            if (team == TeamRegistry.BLACK.get() && Theme.INVERT_TABLE_IF_BLACK) {
            	niceCell = cell.invert(matrix);
            }
            
    		ClientPiece piece = matrix.get(niceCell);
        	TextureValue texture = piece.getTexture();
        	
        	texture.draw(x + off, y + off, tileSize - off * 2, tileSize - off * 2);
		}
		
		// chess highlights above
		for (Highlight highlight : highlights) {
			Cell niceCell = highlight.getCell();
			if (team == TeamRegistry.BLACK.get() && Theme.INVERT_TABLE_IF_BLACK) {
				niceCell = niceCell.invert(matrix);
			}
			
            float x = boardX + niceCell.x * tileSize;
            float y = boardY + (matrix.getHeight() - niceCell.y - 1) * tileSize;
            
            HighlightValue type = highlight.getType();
            
            Color color = null;
            if (type == HighlightRegistry.MOVE_AVAILABLE.get()) {
            	color = Theme.BOARD_CELL_MOVE_AVAILABLE;
            }
            
			if (color != null) {
				Shapes.circle(x + tileSize / 2, y + tileSize / 2, tileSize / 6, color);
			}
		}
	}
	
	@Override
	public void resize(int width, int height) {
		if (chess.getBoard() == null) return;
		
		AbstractBoard board = chess.getBoard();
		if (board.getMatrix() == null) return;
		
		ClientMatrix matrix = board.getMatrix();
		
        tileSize = Math.min(width, height) / Math.max(matrix.getWidth(), matrix.getHeight());
        
        boardX = (width - (tileSize * matrix.getWidth())) / 2;
        boardY = (height - (tileSize * matrix.getHeight())) / 2;
	}
	
	@Override
	public boolean hover(int mouseX, int mouseY) {
		return chess.getBoard() != null;
	}
	
	@Override
	public boolean mouseDown(int mouseX, int mouseY, int pointer, int button) {
		if (chess.getBoard() == null) return false;
		
		AbstractBoard board = chess.getBoard();
		if (board.getMatrix() == null || board.getTeam() == null) return false;
		
		ClientMatrix matrix = board.getMatrix();
		TeamValue team = board.getTeam();
		
		for (Cell cell : matrix) {
            float x = boardX + cell.x * tileSize;
            float y = boardY + (matrix.getHeight() - cell.y - 1) * tileSize;
            
            Cell niceCell = cell;
            if (team == TeamRegistry.BLACK.get() && Theme.INVERT_TABLE_IF_BLACK) {
            	niceCell = cell.invert(matrix);
            }
            
            if (mouseX >= x && mouseY >= y && mouseX < x + tileSize && mouseY < y + tileSize) {
            	board.click(niceCell);
            	return true;
            }
		}
		
		return false;
	}
	
	@Override
	public void dispose() {
		chess.setBoard(null);
	}
}
