package pieces.impl;

import java.util.ArrayList;

import core.ChessBoard;
import pieces.AbstractPiece;
import pieces.PieceType;
import pieces.Player;
import util.Position;

public class Pawn extends AbstractPiece {

	public Pawn(Player player) {
		super(PieceType.PAWN, player);
	}

	@Override
	public ArrayList<Position> getValidMoves(final Position origin) {
		ArrayList<Position> moves = new ArrayList<Position>();

		if (player == Player.PLAYER_ONE) {
			Position pos = new Position(origin.x, origin.y - 1);
			if(!ChessBoard.isOutOfBounds(pos))
				moves.add(pos);
		} else {
			Position pos = new Position(origin.x, origin.y + 1);
			if(!ChessBoard.isOutOfBounds(pos))
				moves.add(pos);
		}

		return moves;
	}

	@Override
	public String toString() {
		return "P";
	}	
}
