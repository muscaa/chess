package muscaa.chess.client.board.matrix;

import muscaa.chess.board.matrix.AbstractMatrix;
import muscaa.chess.client.board.piece.ClientPiece;

public class ClientMatrix extends AbstractMatrix<ClientPiece> {
	
	public ClientMatrix(int width, int height) {
		super(width, height);
	}
}
