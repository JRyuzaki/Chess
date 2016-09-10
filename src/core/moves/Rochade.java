package core.moves;

import pieces.AbstractPiece;
import util.Position;

public class Rochade extends Move{
	private AbstractPiece movedRook;
	private Position rookOrigin;
	private Position rookPosition;
	
	public Rochade(){
		super();
		this.type = MoveType.ROCHADE;
		this.movedRook = null;
		this.rookOrigin = null;
		this.rookPosition = null;
	}

	public Rochade(AbstractPiece movedPiece, Position from, Position to, AbstractPiece capturedPiece, AbstractPiece movedRook, Position rookOrigin, Position rookPosition) {
		super(movedPiece, from, to);
		this.type = MoveType.ROCHADE;
		this.movedRook = movedRook;
		this.rookOrigin = rookOrigin;
		this.rookPosition = rookPosition;
	}

	public AbstractPiece getMovedRook() {
		return movedRook;
	}

	public void setMovedRook(AbstractPiece movedRook) {
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
