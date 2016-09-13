package core.moves;

import pieces.AbstractPiece;
import util.Position;

public class Rochade extends Move{
	private AbstractPiece movedRook;
	private Position rookOrigin;
	private Position rookPosition;
	private AbstractPiece movedKing;
	private Position kingOrigin;
	private Position kingPosition;
	
	public Rochade(AbstractPiece movedPiece, Position from, Position to, AbstractPiece movedRook, Position rookOrigin, Position rookPosition) {
		super(movedPiece, from, to);
		this.type = MoveType.ROCHADE;
		this.movedRook = movedRook;
		this.rookOrigin = rookOrigin;
		this.rookPosition = rookPosition;
	}

	public AbstractPiece getMovedKing() {
		return movedKing;
	}

	public void setMovedKing(AbstractPiece movedKing) {
		this.movedKing = movedKing;
	}

	public Position getKingOrigin() {
		return kingOrigin;
	}

	public void setKingOrigin(Position kingOrigin) {
		this.kingOrigin = kingOrigin;
	}

	public Position getKingPosition() {
		return kingPosition;
	}

	public void setKingPosition(Position kingPosition) {
		this.kingPosition = kingPosition;
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
