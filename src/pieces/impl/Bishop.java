package pieces.impl;

import java.util.ArrayList;

import pieces.AbstractPiece;
import pieces.PieceType;
import pieces.Player;
import util.Position;

public class Bishop extends AbstractPiece{

	public Bishop(Player player) {
		super(PieceType.BISHOP, player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Position> getValidMoves(Position origin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "B";
	}
}
