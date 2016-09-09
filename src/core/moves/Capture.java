package core.moves;

import pieces.Piece;
import util.Position;

public class Capture extends Move{
	private Piece capturedPiece;
	
	public Capture(){
		super();
		this.type = MoveType.CAPTURE;
		this.capturedPiece = null;
	}

	public Capture(Piece movedPiece, Position from, Position to, Piece capturedPiece) {
		super(movedPiece, from, to);
		this.type = MoveType.CAPTURE;
		this.capturedPiece = capturedPiece;
	}

	public Piece getCapturedPiece() {
		return capturedPiece;
	}

	public void setCapturedPiece(Piece capturedPiece) {
		this.capturedPiece = capturedPiece;
	}
}
