package muscaa.chess.board;

import java.util.Objects;

import com.badlogic.gdx.graphics.Color;

import muscaa.chess.assets.Fonts;
import muscaa.chess.assets.Textures;
import muscaa.chess.config.Theme;
import muscaa.chess.layer.ILayer;
import muscaa.chess.render.Screen;
import muscaa.chess.render.Shapes;
import muscaa.chess.shared.board.ChessCell;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessMove;
import muscaa.chess.shared.board.ChessPieceMatrix;

public class BoardLayer implements ILayer {
	
    private float tileSize;
    private float boardX, boardY;
    
    private ClientBoard board;
    
	public BoardLayer() {
        tileSize = Math.min(Screen.WIDTH, Screen.HEIGHT) / ChessPieceMatrix.SIZE;
        
        boardX = (Screen.WIDTH - (tileSize * ChessPieceMatrix.SIZE)) / 2;
        boardY = (Screen.HEIGHT - (tileSize * ChessPieceMatrix.SIZE)) / 2;
	}
	
	@Override
	public void render(int mouseX, int mouseY, float delta) {
		if (board == null) return;
		
		// chess table
		for (ChessCell cell : board.getMatrix()) {
        	Color color = (cell.x + cell.y) % 2 == 0 ? Theme.BOARD_CELL_LIGHT : Theme.BOARD_CELL_DARK;
            float x = boardX + cell.x * tileSize;
            float y = boardY + (ChessPieceMatrix.SIZE - cell.y - 1) * tileSize;
            
            ChessCell niceCell = cell.copy(board.getColor() == ChessColor.BLACK && Theme.INVERT_TABLE_IF_BLACK);
    		
    		if (board.getSelectedCell().equals(niceCell)) {
    			color = Theme.BOARD_CELL_SELECTED;
    		} else if (board.getCheckCell().equals(niceCell)) {
    			color = Theme.BOARD_CELL_CHECK;
    		}
            
            Shapes.rect(x, y, tileSize, tileSize, color);
		}
		
		// chess pieces
		for (ChessCell cell : board.getMatrix()) {
            float x = boardX + cell.x * tileSize;
            float y = boardY + (ChessPieceMatrix.SIZE - cell.y - 1) * tileSize;
            float off = tileSize / 32;
            
            ChessCell niceCell = cell.copy(board.getColor() == ChessColor.BLACK && Theme.INVERT_TABLE_IF_BLACK);
    		ClientChessPiece piece = board.getMatrix().get(niceCell);
            
            if (!piece.equals(ClientChessPiece.EMPTY)) {
            	Textures.draw(piece.getTexture(), x + off, y + off, tileSize - off * 2, tileSize - off * 2);
            }
		}
		
		// chess moves
		synchronized (board.getMoves()) {
			for (ChessMove move : board.getMoves()) {
				ChessCell niceCell = move.getCell().copy(board.getColor() == ChessColor.BLACK && Theme.INVERT_TABLE_IF_BLACK);
	            float x = boardX + niceCell.x * tileSize;
	            float y = boardY + (ChessPieceMatrix.SIZE - niceCell.y - 1) * tileSize;
	            
	            Shapes.circle(x + tileSize / 2, y + tileSize / 2, tileSize / 6, Theme.BOARD_CELL_MOVE_AVAILABLE);
			}
		}
        
        Object[] debug = new Object[] {
		};
        
        float y = Screen.HEIGHT - 10;
        for (Object info : debug) {
        	Fonts.draw(Fonts.VARELA_18, Objects.toString(info), 10, y, Color.WHITE);
        	
        	y -= Fonts.VARELA_18.getLineHeight();
        }
	}
	
	@Override
	public void resize(int width, int height) {
		tileSize = Math.min(Screen.WIDTH, Screen.HEIGHT) / ChessPieceMatrix.SIZE;
        
        boardX = (Screen.WIDTH - (tileSize * ChessPieceMatrix.SIZE)) / 2;
        boardY = (Screen.HEIGHT - (tileSize * ChessPieceMatrix.SIZE)) / 2;
	}
	
	@Override
	public boolean hover(int mouseX, int mouseY) {
		return board != null;
	}
	
	@Override
	public boolean mouseDown(int mouseX, int mouseY, int pointer, int button) {
		if (board == null) return false;
		
		for (ChessCell cell : board.getMatrix()) {
            float x = boardX + cell.x * tileSize;
            float y = boardY + (ChessPieceMatrix.SIZE - cell.y - 1) * tileSize;
            
            ChessCell niceCell = cell.copy(board.getColor() == ChessColor.BLACK && Theme.INVERT_TABLE_IF_BLACK);
            if (mouseX >= x && mouseY >= y && mouseX < x + tileSize && mouseY < y + tileSize) {
            	board.click(niceCell);
            	return true;
            }
		}
		
		return false;
	}
	
	public ClientBoard getBoard() {
		return board;
	}
	
	public void setBoard(ClientBoard board) {
		this.board = board;
	}
}
