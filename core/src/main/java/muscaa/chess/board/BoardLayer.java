package muscaa.chess.board;

import java.util.Objects;

import com.badlogic.gdx.graphics.Color;

import fluff.vecmath.gen._int.vector.Vec2i;
import muscaa.chess.assets.Fonts;
import muscaa.chess.assets.Textures;
import muscaa.chess.config.Theme;
import muscaa.chess.layer.ILayer;
import muscaa.chess.render.Screen;
import muscaa.chess.render.Shapes;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.IBoard;

public class BoardLayer implements ILayer {
	
    private float tileSize;
    private float boardX, boardY;
    
    private IClientBoard board;
	
	public BoardLayer() {
        tileSize = Math.min(Screen.WIDTH, Screen.HEIGHT) / IBoard.SIZE;
        
        boardX = (Screen.WIDTH - (tileSize * IBoard.SIZE)) / 2;
        boardY = (Screen.HEIGHT - (tileSize * IBoard.SIZE)) / 2;
	}
	
	@Override
	public void render(int mouseX, int mouseY, float delta) {
		if (board == null) return;
		
		ClientChessPiece hoveredPiece = null;
		int hoveredRow = -1;
		int hoveredCol = -1;
		
        for (int row = 0; row < IBoard.SIZE; row++) {
            for (int col = 0; col < IBoard.SIZE; col++) {
            	Color color = (row + col) % 2 == 0 ? Theme.BOARD_CELL_LIGHT : Theme.BOARD_CELL_DARK;
            	
            	Vec2i cell = new Vec2i(col, row);
                float x = boardX + col * tileSize;
                float y = boardY + (IBoard.SIZE - row - 1) * tileSize;
                float off = tileSize / 32;
            	
        		if (board.getColor() == ChessColor.BLACK && Theme.INVERT_TABLE_IF_BLACK) {
        			//cell = new Vec2i(IBoard.SIZE - col - 1, IBoard.SIZE - row - 1);
        			x = boardX + (IBoard.SIZE - col - 1) * tileSize;
                    y = boardY + row * tileSize;
        		}
        		
        		ClientChessPiece piece = board.getPiece(cell);
            	
                if (mouseX >= x && mouseY >= y && mouseX < x + tileSize && mouseY < y + tileSize) {
                	color = Color.RED;
                	hoveredPiece = piece;
                	hoveredRow = row;
                	hoveredCol = col;
                }
                
                Shapes.rect(x, y, tileSize, tileSize, color);
                
                if (piece != null) {
                	Textures.draw(piece.getTexture(), x + off, y + off, tileSize - off * 2, tileSize - off * 2);
                }
            }
        }
        
        Object[] debug = new Object[] {
        		hoveredPiece,
        		hoveredRow + " : " + hoveredCol,
        		board.getColor()
		};
        
        float y = Screen.HEIGHT - 10;
        for (Object info : debug) {
        	Fonts.draw(Fonts.VARELA_18, Objects.toString(info), 10, y, Color.WHITE);
        	
        	y -= Fonts.VARELA_18.getLineHeight();
        }
	}
	
	@Override
	public void resize(int width, int height) {
		tileSize = Math.min(Screen.WIDTH, Screen.HEIGHT) / IBoard.SIZE;
        
        boardX = (Screen.WIDTH - (tileSize * IBoard.SIZE)) / 2;
        boardY = (Screen.HEIGHT - (tileSize * IBoard.SIZE)) / 2;
	}
	
	@Override
	public boolean hover(int mouseX, int mouseY) {
		return board != null;
	}
	
	@Override
	public boolean mouseDown(int mouseX, int mouseY, int pointer, int button) {
		if (board == null) return false;
		
        for (int row = 0; row < IBoard.SIZE; row++) {
            for (int col = 0; col < IBoard.SIZE; col++) {
                float x = boardX + col * tileSize;
                float y = boardY + (IBoard.SIZE - row - 1) * tileSize;
            	
        		if (board.getColor() == ChessColor.BLACK && Theme.INVERT_TABLE_IF_BLACK) {
        			x = boardX + (IBoard.SIZE - col - 1) * tileSize;
                    y = boardY + row * tileSize;
        		}
                
                if (mouseX >= x && mouseY >= y && mouseX < x + tileSize && mouseY < y + tileSize) {
                	board.click(new Vec2i(col, row));
                	return true;
                }
            }
        }
		
		return false;
	}
	
	public IClientBoard getBoard() {
		return board;
	}
	
	public void setBoard(IClientBoard board) {
		this.board = board;
	}
}
