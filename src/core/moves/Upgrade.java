package core.moves;

import pieces.AbstractPiece;
import util.Position;

public class Upgrade extends Move{
	private AbstractPiece newPiece;
	private AbstractPiece capturedPiece;
	
	public Upgrade(AbstractPiece pawn, Position from, Position to, AbstractPiece newPiece, AbstractPiece capturedPiece) {
		super(pawn, from, to);
		this.type = MoveType.UPGRADE;
		this.setNewPiece(newPiece);
		this.setCapturedPiece(capturedPiece);
	}

	public AbstractPiece getNewPiece() {
		return newPiece;
	}

	public void setNewPiece(AbstractPiece newPiece) {
		this.newPiece = newPiece;
	}

	public AbstractPiece getCapturedPiece() {
		return capturedPiece;
	}

	public void setCapturedPiece(AbstractPiece capturedPiece) {
		this.capturedPiece = capturedPiece;
	}
}
