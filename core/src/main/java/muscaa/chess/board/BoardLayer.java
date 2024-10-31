package muscaa.chess.board;

import java.util.Objects;

import com.badlogic.gdx.graphics.Color;

import muscaa.chess.assets.Fonts;
import muscaa.chess.assets.Textures;
import muscaa.chess.layer.ILayer;
import muscaa.chess.render.RenderUtils;
import muscaa.chess.render.Screen;
import muscaa.chess.render.Shapes;

public class BoardLayer implements ILayer {
	
    private float tileSize;
    private float boardX, boardY;
    
    private Board board = new Board();
    private int x1 = -1;
    private int y1 = -1;
	
	public BoardLayer() {
        tileSize = Math.min(Screen.VIEWPORT.getWorldWidth(), Screen.VIEWPORT.getWorldHeight()) / Board.SIZE;
        
        boardX = (Screen.VIEWPORT.getWorldWidth() - (tileSize * Board.SIZE)) / 2;
        boardY = (Screen.VIEWPORT.getWorldHeight() - (tileSize * Board.SIZE)) / 2;
	}
	
	@Override
	public void render(int mouseX, int mouseY, float delta) {
		Shapes.rect(0, 0, Screen.VIEWPORT.getWorldWidth(), Screen.VIEWPORT.getWorldHeight(), Color.DARK_GRAY);
		
		ChessPieceType hoveredPiece = null;
		int hoveredRow = -1;
		int hoveredCol = -1;
		
        for (int row = 0; row < Board.SIZE; row++) {
            for (int col = 0; col < Board.SIZE; col++) {
            	Color color = (row + col) % 2 == 0 ? RenderUtils.color(235, 236, 208)
            			: RenderUtils.color(115, 149, 82);
            	ChessPieceType pieceType = board.getPiece(col, row);
                float x = boardX + col * tileSize;
                float y = boardY + (Board.SIZE - row - 1) * tileSize;
                float off = tileSize / 32;
                
                if (mouseX >= x && mouseY >= y && mouseX < x + tileSize && mouseY < y + tileSize) {
                	color = Color.RED;
                	hoveredPiece = pieceType;
                	hoveredRow = row;
                	hoveredCol = col;
                }
                
                Shapes.rect(x, y, tileSize, tileSize, color);
                
                if (pieceType != null) {
                	Textures.draw(pieceType.getTexture(), x + off, y + off, tileSize - off * 2, tileSize - off * 2);
                }
            }
        }
        
        String[] debug = new String[] {
        		Objects.toString(hoveredPiece),
        		hoveredRow + " : " + hoveredCol,
		};
        
        float y = Screen.VIEWPORT.getWorldHeight() - 10;
        for (String info : debug) {
        	Fonts.draw(Fonts.SONO_24, info, 10, y, Color.WHITE);
        	
        	y -= Fonts.SONO_24.getLineHeight();
        }
	}
	
	@Override
	public void resize(int width, int height) {
		tileSize = Math.min(Screen.VIEWPORT.getWorldWidth(), Screen.VIEWPORT.getWorldHeight()) / Board.SIZE;
        
        boardX = (Screen.VIEWPORT.getWorldWidth() - (tileSize * Board.SIZE)) / 2;
        boardY = (Screen.VIEWPORT.getWorldHeight() - (tileSize * Board.SIZE)) / 2;
	}
	
	@Override
	public boolean hover(int mouseX, int mouseY) {
		return board != null;
	}
	
	@Override
	public boolean mouseDown(int mouseX, int mouseY, int pointer, int button) {
        for (int row = 0; row < Board.SIZE; row++) {
            for (int col = 0; col < Board.SIZE; col++) {
                float x = boardX + col * tileSize;
                float y = boardY + (Board.SIZE - row - 1) * tileSize;
                
                if (mouseX >= x && mouseY >= y && mouseX < x + tileSize && mouseY < y + tileSize) {
                	if (x1 != -1 && y1 != -1) {
                		board.move(x1, y1, col, row);
                		x1 = -1;
                		y1 = -1;
                	} else {
                		x1 = col;
                		y1 = row;
                	}
                	return true;
                }
            }
        }
		
		return false;
	}
	
	public Board getBoard() {
		return board;
	}
}
