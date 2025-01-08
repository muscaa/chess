package muscaa.chess.client.board;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;

import muscaa.chess.board.Cell;
import muscaa.chess.board.Highlight;
import muscaa.chess.board.HighlightType;
import muscaa.chess.board.Team;
import muscaa.chess.client.Client;
import muscaa.chess.client.assets.Sounds;
import muscaa.chess.client.assets.TextureAsset;
import muscaa.chess.client.config.Theme;
import muscaa.chess.client.gui.screens.DisconnectedScreen;
import muscaa.chess.client.layer.ILayer;
import muscaa.chess.client.network.play.packets.CPacketBoard;
import muscaa.chess.client.network.play.packets.CPacketClickCell;
import muscaa.chess.client.network.play.packets.CPacketEndGame;
import muscaa.chess.client.network.play.packets.CPacketHighlightCells;
import muscaa.chess.client.network.play.packets.CPacketStartGame;
import muscaa.chess.client.network.play.packets.CPacketTeam;
import muscaa.chess.client.utils.Screen;
import muscaa.chess.client.utils.Shapes;
import muscaa.chess.client.utils.TaskManager;
import muscaa.chess.registry.registries.HighlightRegistry;
import muscaa.chess.registry.registries.TeamRegistry;

public class BoardLayer implements ILayer {
    
	private boolean inGame;
    private ClientMatrix matrix;
    private Team team;
    private List<Highlight> highlights = new LinkedList<>();
    
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
		for (Highlight highlight : highlights) {
			Cell niceCell = highlight.getCell();
			if (team == TeamRegistry.BLACK && Theme.INVERT_TABLE_IF_BLACK) {
				niceCell = niceCell.invert(matrix);
			}
			
            float x = boardX + niceCell.x * tileSize;
            float y = boardY + (matrix.getHeight() - niceCell.y - 1) * tileSize;
            
            HighlightType type = highlight.getType();
            
            Color color = null;
            if (type == HighlightRegistry.SELECTED) {
            	color = Theme.BOARD_CELL_SELECTED;
            } else if (type == HighlightRegistry.CHECK) {
            	color = Theme.BOARD_CELL_CHECK;
            } else if (type == HighlightRegistry.LAST_MOVE) {
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
            if (team == TeamRegistry.BLACK && Theme.INVERT_TABLE_IF_BLACK) {
            	niceCell = cell.invert(matrix);
            }
            
    		TexturedPiece piece = matrix.get(niceCell);
        	TextureAsset texture = piece.getTexture();
        	
        	texture.draw(x + off, y + off, tileSize - off * 2, tileSize - off * 2);
		}
		
		// chess highlights above
		for (Highlight highlight : highlights) {
			Cell niceCell = highlight.getCell();
			if (team == TeamRegistry.BLACK && Theme.INVERT_TABLE_IF_BLACK) {
				niceCell = niceCell.invert(matrix);
			}
			
            float x = boardX + niceCell.x * tileSize;
            float y = boardY + (matrix.getHeight() - niceCell.y - 1) * tileSize;
            
            HighlightType type = highlight.getType();
            
            Color color = null;
            if (type == HighlightRegistry.MOVE_AVAILABLE) {
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
            
            Cell niceCell = cell;
            if (team == TeamRegistry.BLACK && Theme.INVERT_TABLE_IF_BLACK) {
            	niceCell = cell.invert(matrix);
            }
            
            if (mouseX >= x && mouseY >= y && mouseX < x + tileSize && mouseY < y + tileSize) {
            	Client.INSTANCE.getNetworkClient().send(new CPacketClickCell(niceCell));
            	return true;
            }
		}
		
		return false;
	}
	
	public void onPacketStartGame(CPacketStartGame packet) {
		inGame = true;
		highlights.clear();
		
		TaskManager.render(() -> {
			Client.INSTANCE.getGuiLayer().setScreen(null);
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
			Client.INSTANCE.getGuiLayer().setScreen(new DisconnectedScreen(packet.getWinner() == TeamRegistry.NULL ? "Stalemate"
					: packet.getWinner() == team ? "You win"
							: "Opponent wins"));
		});
		
		disconnect();
	}
	
	public void disconnect() {
		Client.INSTANCE.getNetworkClient().disconnect();
		
		inGame = false;
		matrix = null;
		team = null;
		highlights = new LinkedList<>();
	}
	
	public boolean isInGame() {
		return inGame;
	}
	
	public boolean isReady() {
		return matrix != null && team != null;
	}
}
