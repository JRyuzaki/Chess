package pieces.impl;

import java.util.ArrayList;

import pieces.AbstractPiece;
import pieces.PieceType;
import pieces.Player;
import util.Position;

public class King extends AbstractPiece{

	public King(Player player) {
		super(PieceType.KING, player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Position> getValidMoves(Position origin) {
		// TODO Auto-generated method stub
		return null;
	}

}
