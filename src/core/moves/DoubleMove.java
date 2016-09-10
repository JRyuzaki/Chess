package core.moves;

import pieces.AbstractPiece;
import util.Position;

public class DoubleMove extends Move{

	public DoubleMove(AbstractPiece movedPiece, Position from, Position to) {
		super(movedPiece, from, to);
		this.type = MoveType.DOUBLE_MOVE;
	}
	
}
