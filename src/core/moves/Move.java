package core.moves;

import pieces.Piece;
import util.Position;

public class Move {
	private Piece movedPiece;
	private Position from;
	private Position to;
	protected MoveType type;
	
	public Move(){
		this.type = MoveType.MOVE;
		this.movedPiece = null;
		this.from = null;
		this.to = null;
	}

	public Move(Piece movedPiece, Position from, Position to) {
		super();
		this.movedPiece = movedPiece;
		this.from = from;
		this.to = to;
	}


	public Piece getMovedPiece() {
		return movedPiece;
	}

	public void setMovedPiece(Piece movedPiece) {
		this.movedPiece = movedPiece;
	}

	public Position getFrom() {
		return from;
	}

	public void setFrom(Position from) {
		this.from = from;
	}

	public Position getTo() {
		return to;
	}

	public void setTo(Position to) {
		this.to = to;
	}

	public MoveType getType() {
		return type;
	}
}
