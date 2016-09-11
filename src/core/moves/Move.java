package core.moves;

import pieces.AbstractPiece;
import util.Position;

public class Move {
	private AbstractPiece movedPiece;
	private Position from;
	private Position to;
	protected MoveType type;

	public Move(AbstractPiece movedPiece, Position from, Position to) {
		this.type = MoveType.MOVE;
		this.movedPiece = movedPiece;
		this.from = from;
		this.to = to;
	}


	public AbstractPiece getMovedPiece() {
		return movedPiece;
	}

	public void setMovedPiece(AbstractPiece movedPiece) {
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

	@Override
	public String toString() {
		String type;
		switch(getType()){
		case CAPTURE:
			type = "(Capture)";
			break;
		case ROCHADE:
			type = "(Rochade)";
			break;
		case UPGRADE:
			type = "(Upgrade)";
			break;
		case DOUBLE_MOVE:
			type = "(Double move)";
			break;
		case EN_PESSANTE:
			type = "(En Passante)";
			break;
		default:
			type = "";
		}
		return this.from+" -> "+this.to+" "+type;
	}
}
