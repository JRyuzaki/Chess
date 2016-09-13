package core.moves;

import pieces.AbstractPiece;
import util.Position;

public class EnPessante extends Move{
	private Position capturedPawnPosition;
	public EnPessante(AbstractPiece movedPiece, Position from, Position to, Position capturedPawnPosition) {
		super(movedPiece, from, to);
		this.type = MoveType.EN_PASSANTE;
		this.capturedPawnPosition = capturedPawnPosition;
	}
	public Position getEnemyPawnPosition() {
		return capturedPawnPosition;
	}
	public void setEnemyPawnPosition(Position enemyPawn) {
		this.capturedPawnPosition = enemyPawn;
	}

}
