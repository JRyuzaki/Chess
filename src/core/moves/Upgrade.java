package core.moves;

import pieces.AbstractPiece;
import util.Position;

public class Upgrade extends Move{
	private AbstractPiece newPiece;
	
	public Upgrade(AbstractPiece pawn, Position from, Position to, AbstractPiece newPiece) {
		super(pawn, from, to);
		this.type = MoveType.UPGRADE;
		this.setNewPiece(newPiece);
	}

	public AbstractPiece getNewPiece() {
		return newPiece;
	}

	public void setNewPiece(AbstractPiece newPiece) {
		this.newPiece = newPiece;
	}
}
