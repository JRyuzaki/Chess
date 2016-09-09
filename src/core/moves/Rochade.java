package core.moves;

import pieces.Piece;
import util.Position;

public class Rochade extends Move{
	private Piece movedRook;
	private Position rookOrigin;
	private Position rookPosition;
	
	public Rochade(){
		super();
		this.type = MoveType.ROCHADE;
		this.movedRook = null;
		this.rookOrigin = null;
		this.rookPosition = null;
	}

	public Rochade(Piece movedPiece, Position from, Position to, Piece capturedPiece, Piece movedRook, Position rookOrigin, Position rookPosition) {
		super(movedPiece, from, to);
		this.type = MoveType.ROCHADE;
		this.movedRook = movedRook;
		this.rookOrigin = rookOrigin;
		this.rookPosition = rookPosition;
	}

	public Piece getMovedRook() {
		return movedRook;
	}

	public void setMovedRook(Piece movedRook) {
		this.movedRook = movedRook;
	}

	public Position getRookOrigin() {
		return rookOrigin;
	}

	public void setRookOrigin(Position rookOrigin) {
		this.rookOrigin = rookOrigin;
	}

	public Position getRookPosition() {
		return rookPosition;
	}

	public void setRookPosition(Position rookPosition) {
		this.rookPosition = rookPosition;
	}
}
