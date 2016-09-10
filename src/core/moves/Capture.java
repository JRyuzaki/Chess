package core.moves;

import pieces.AbstractPiece;
import util.Position;

public class Capture extends Move{
	private AbstractPiece capturedPiece;
	
	public Capture(){
		super();
		this.type = MoveType.CAPTURE;
		this.capturedPiece = null;
	}

	public Capture(AbstractPiece movedPiece, Position from, Position to, AbstractPiece capturedPiece) {
		super(movedPiece, from, to);
		this.type = MoveType.CAPTURE;
		this.capturedPiece = capturedPiece;
	}

	public AbstractPiece getCapturedPiece() {
		return capturedPiece;
	}

	public void setCapturedPiece(AbstractPiece capturedPiece) {
		this.capturedPiece = capturedPiece;
	}
}
