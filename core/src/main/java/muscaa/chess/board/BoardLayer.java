package muscaa.chess.board;

import java.util.Objects;

import com.badlogic.gdx.graphics.Color;

import fluff.vecmath.gen._int.vector.Vec2i;
import muscaa.chess.ChessGame;
import muscaa.chess.assets.Fonts;
import muscaa.chess.assets.Textures;
import muscaa.chess.config.Theme;
import muscaa.chess.layer.ILayer;
import muscaa.chess.render.Screen;
import muscaa.chess.render.Shapes;
import muscaa.chess.shared.board.ChessColor;
import muscaa.chess.shared.board.ChessPieceMatrix;

public class BoardLayer implements ILayer {
	
    private float tileSize;
    private float boardX, boardY;
    
	public BoardLayer() {
        tileSize = Math.min(Screen.WIDTH, Screen.HEIGHT) / ChessPieceMatrix.SIZE;
        
        boardX = (Screen.WIDTH - (tileSize * ChessPieceMatrix.SIZE)) / 2;
        boardY = (Screen.HEIGHT - (tileSize * ChessPieceMatrix.SIZE)) / 2;
	}
	
	@Override
	public void render(int mouseX, int mouseY, float delta) {
		ClientBoard board = ChessGame.INSTANCE.getBoard();
		if (board.getMatrix() == null) return;
		
		ClientChessPiece hoveredPiece = null;
		int hoveredRow = -1;
		int hoveredCol = -1;
		
		for (Vec2i cell : board.getMatrix()) {
        	Color color = (cell.x + cell.y) % 2 == 0 ? Theme.BOARD_CELL_LIGHT : Theme.BOARD_CELL_DARK;
            float x = boardX + cell.x * tileSize;
            float y = boardY + (ChessPieceMatrix.SIZE - cell.y - 1) * tileSize;
            float off = tileSize / 32;
            
            Vec2i niceCell = new Vec2i(cell);
    		if (board.getColor() == ChessColor.BLACK && Theme.INVERT_TABLE_IF_BLACK) {
    			niceCell = new Vec2i(ChessPieceMatrix.SIZE - cell.x - 1, ChessPieceMatrix.SIZE - cell.y - 1);
    		}
    		
    		ClientChessPiece piece = board.getMatrix().get(niceCell);
        	
            if (mouseX >= x && mouseY >= y && mouseX < x + tileSize && mouseY < y + tileSize) {
            	color = Color.RED;
            	hoveredPiece = piece;
            	hoveredRow = niceCell.y;
            	hoveredCol = niceCell.x;
            }
            
            Shapes.rect(x, y, tileSize, tileSize, color);
            
            if (piece != null && piece != ClientChessPiece.EMPTY) {
            	Textures.draw(piece.getTexture(), x + off, y + off, tileSize - off * 2, tileSize - off * 2);
            }
		}
        
        Object[] debug = new Object[] {
        		board.getColor(),
        		hoveredPiece,
        		hoveredRow + " : " + hoveredCol,
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
		return ChessGame.INSTANCE.getBoard().getMatrix() != null;
	}
	
	@Override
	public boolean mouseDown(int mouseX, int mouseY, int pointer, int button) {
		ClientBoard board = ChessGame.INSTANCE.getBoard();
		if (board.getMatrix() == null) return false;
		
		for (Vec2i cell : board.getMatrix()) {
            float x = boardX + cell.x * tileSize;
            float y = boardY + (ChessPieceMatrix.SIZE - cell.y - 1) * tileSize;
            
            Vec2i niceCell = new Vec2i(cell);
    		if (board.getColor() == ChessColor.BLACK && Theme.INVERT_TABLE_IF_BLACK) {
    			niceCell = new Vec2i(ChessPieceMatrix.SIZE - cell.x - 1, ChessPieceMatrix.SIZE - cell.y - 1);
    		}
            
            if (mouseX >= x && mouseY >= y && mouseX < x + tileSize && mouseY < y + tileSize) {
            	board.click(niceCell);
            	return true;
            }
		}
		
		return false;
	}
}
