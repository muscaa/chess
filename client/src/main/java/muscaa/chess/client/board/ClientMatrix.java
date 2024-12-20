package muscaa.chess.client.board;

import muscaa.chess.board.matrix.AbstractMatrix;

public class ClientMatrix extends AbstractMatrix<TexturedPiece> {
	
	public ClientMatrix(int width, int height) {
		super(width, height);
	}
}
