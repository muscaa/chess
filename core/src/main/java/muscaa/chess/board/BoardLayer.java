package muscaa.chess.board;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.badlogic.gdx.graphics.Color;

import muscaa.chess.Core;
import muscaa.chess.assets.Sounds;
import muscaa.chess.assets.TextureAsset;
import muscaa.chess.config.Theme;
import muscaa.chess.gui.screens.DisconnectedScreen;
import muscaa.chess.layer.ILayer;
import muscaa.chess.network.play.packets.CPacketBoard;
import muscaa.chess.network.play.packets.CPacketClickCell;
import muscaa.chess.network.play.packets.CPacketEndGame;
import muscaa.chess.network.play.packets.CPacketHighlightCells;
import muscaa.chess.network.play.packets.CPacketStartGame;
import muscaa.chess.network.play.packets.CPacketTeam;
import muscaa.chess.render.Screen;
import muscaa.chess.render.Shapes;
import muscaa.chess.shared.board.Cell;
import muscaa.chess.shared.board.Highlight;
import muscaa.chess.shared.board.Team;
import muscaa.chess.shared.registry.registries.HighlightRegistry;
import muscaa.chess.shared.registry.registries.TeamRegistry;
import muscaa.chess.task.TaskManager;

public class BoardLayer implements ILayer {
    
	private boolean inGame;
    private ClientMatrix matrix;
    private Team team;
    private Map<Cell, Highlight> highlights = new HashMap<>();
    
    private float tileSize;
    private float boardX, boardY;
	
	@Override
	public void render(int mouseX, int mouseY, float delta) {
		if (!isReady()) return;
		
		// chess table
		for (Cell cell : matrix) {
        	Color color = (cell.x + cell.y) % 2 == 0 ? Theme.BOARD_CELL_LIGHT : Theme.BOARD_CELL_DARK;
            float x = boardX + cell.x * tileSize;
            float y = boardY + (matrix.getHeight() - cell.y - 1) * tileSize;
            
            Shapes.rect(x, y, tileSize, tileSize, color);
		}
		
		// chess highlights under
		for (Map.Entry<Cell, Highlight> highlightEntry : highlights.entrySet()) {
			Cell niceCell = highlightEntry.getKey().copy(team == TeamRegistry.BLACK && Theme.INVERT_TABLE_IF_BLACK);
            float x = boardX + niceCell.x * tileSize;
            float y = boardY + (matrix.getHeight() - niceCell.y - 1) * tileSize;
            
            Highlight highlight = highlightEntry.getValue();
            
            Color color = null;
            if (highlight == HighlightRegistry.SELECTED) {
            	color = Theme.BOARD_CELL_SELECTED;
            } else if (highlight == HighlightRegistry.CHECK) {
            	color = Theme.BOARD_CELL_CHECK;
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
            
            Cell niceCell = cell.copy(team == TeamRegistry.BLACK && Theme.INVERT_TABLE_IF_BLACK);
    		TexturedPiece piece = matrix.get(niceCell);
        	TextureAsset texture = piece.getTexture();
        	
        	texture.draw(x + off, y + off, tileSize - off * 2, tileSize - off * 2);
		}
		
		// chess highlights above
		for (Map.Entry<Cell, Highlight> highlightEntry : highlights.entrySet()) {
			Cell niceCell = highlightEntry.getKey().copy(team == TeamRegistry.BLACK && Theme.INVERT_TABLE_IF_BLACK);
            float x = boardX + niceCell.x * tileSize;
            float y = boardY + (matrix.getHeight() - niceCell.y - 1) * tileSize;
            
            Highlight highlight = highlightEntry.getValue();
            
            Color color = null;
            if (highlight == HighlightRegistry.MOVE_AVAILABLE) {
            	color = Theme.BOARD_CELL_MOVE_AVAILABLE;
            }
            
			if (color != null) {
				Shapes.circle(x + tileSize / 2, y + tileSize / 2, tileSize / 6, color);
			}
		}
	}
	
	@Override
	public void resize(int width, int height) {
		if (matrix == null) return;
		
        tileSize = Math.min(width, height) / Math.max(matrix.getWidth(), matrix.getHeight());
        
        boardX = (width - (tileSize * matrix.getWidth())) / 2;
        boardY = (height - (tileSize * matrix.getHeight())) / 2;
	}
	
	@Override
	public boolean hover(int mouseX, int mouseY) {
		return isReady();
	}
	
	@Override
	public boolean mouseDown(int mouseX, int mouseY, int pointer, int button) {
		if (!isReady()) return false;
		
		for (Cell cell : matrix) {
            float x = boardX + cell.x * tileSize;
            float y = boardY + (matrix.getHeight() - cell.y - 1) * tileSize;
            
            Cell niceCell = cell.copy(team == TeamRegistry.BLACK && Theme.INVERT_TABLE_IF_BLACK);
            if (mouseX >= x && mouseY >= y && mouseX < x + tileSize && mouseY < y + tileSize) {
            	Core.INSTANCE.getNetwork().send(new CPacketClickCell(niceCell));
            	return true;
            }
		}
		
		return false;
	}
	
	public void onPacketStartGame(CPacketStartGame packet) {
		inGame = true;
		
		TaskManager.render(() -> {
			Core.INSTANCE.getGuiLayer().setScreen(null);
		});
	}
	
	public void onPacketBoard(CPacketBoard packet) {
		if (matrix == null) {
			matrix = new ClientMatrix(packet.getWidth(), packet.getHeight());
		} else if (matrix.getWidth() != packet.getWidth() || matrix.getHeight() != packet.getHeight()) {
			matrix.reset(packet.getWidth(), packet.getHeight());
		}
		
		Iterator<TexturedPiece> it = packet.getPieces().iterator();
		for (Cell cell : matrix) {
			TexturedPiece piece = it.next();
			
			matrix.set(cell, piece);
		}
		
		resize(Screen.WIDTH, Screen.HEIGHT);
		
		Sounds.MOVE.play();
	}
	
	public void onPacketTeam(CPacketTeam packet) {
		team = packet.getTeam();
	}
	
	public void onPacketHighlightCells(CPacketHighlightCells packet) {
		highlights = packet.getHighlights();
	}
	
	public void onPacketEndGame(CPacketEndGame packet) {
		System.out.println(packet.getWinner().getID());
		
		TaskManager.render(() -> {
			System.out.println("why this not getting called?");
			Core.INSTANCE.getGuiLayer().setScreen(new DisconnectedScreen(packet.getWinner() == TeamRegistry.NULL ? "Stalemate"
					: packet.getWinner() == team ? "You win"
							: "Opponent wins"));
		});
		
		disconnect();
	}
	
	public void disconnect() {
		Core.INSTANCE.getNetwork().disconnect();
		
		inGame = false;
		matrix = null;
		team = null;
		highlights = new HashMap<>();
	}
	
	public boolean isInGame() {
		return inGame;
	}
	
	public boolean isReady() {
		return matrix != null && team != null;
	}
}
