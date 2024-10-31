package muscaa.chess.board;

import com.badlogic.gdx.graphics.Color;

import muscaa.chess.assets.Textures;
import muscaa.chess.layer.ILayer;
import muscaa.chess.render.RenderUtils;
import muscaa.chess.render.Screen;
import muscaa.chess.render.Shapes;

public class BoardLayer implements ILayer {
	
    private int boardSize = 8;
    private float tileSize;
    private float boardX, boardY;
    
    private Board board = new Board();
	
	public BoardLayer() {
        tileSize = Math.min(Screen.VIEWPORT.getWorldWidth(), Screen.VIEWPORT.getWorldHeight()) / boardSize;
        
        boardX = (Screen.VIEWPORT.getWorldWidth() - (tileSize * boardSize)) / 2;
        boardY = (Screen.VIEWPORT.getWorldHeight() - (tileSize * boardSize)) / 2;
	}
	
	@Override
	public void render(int mouseX, int mouseY, float delta) {
        tileSize = Math.min(Screen.VIEWPORT.getWorldWidth(), Screen.VIEWPORT.getWorldHeight()) / boardSize;
        
        boardX = (Screen.VIEWPORT.getWorldWidth() - (tileSize * boardSize)) / 2;
        boardY = (Screen.VIEWPORT.getWorldHeight() - (tileSize * boardSize)) / 2;
		
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
            	Color color = null;
            	
                if ((row + col) % 2 == 0) {
                	color = RenderUtils.color(115, 149, 82);
                } else {
                	color = RenderUtils.color(235, 236, 208);
                }
                
                float x = boardX + col * tileSize;
                float y = boardY + row * tileSize;
                
                /*if (mouseX >= x && mouseY >= y && mouseX < x + tileSize && mouseY < y + tileSize) {
                	color = Color.RED;
                }*/
                
                Shapes.rect(x, y, tileSize, tileSize, color);
                
                BoardPieceType pieceType = board.getPieces()[row][col];
                if (pieceType != null) {
                	float off = tileSize / 32;
                	
                	Textures.draw(pieceType.getTexture(), x + off, y + off, tileSize - off * 2, tileSize - off * 2);
                }
            }
        }
	}
	
	@Override
	public boolean hover(int mouseX, int mouseY) {
		return board != null;
	}
	
	public Board getBoard() {
		return board;
	}
}
