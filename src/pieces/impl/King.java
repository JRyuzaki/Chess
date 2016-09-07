package pieces.impl;

import java.util.ArrayList;

import core.ChessBoard;
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
		ArrayList<Position> moves = new ArrayList<Position>();
		
		for(int y = -1; y <= 1; ++y){
			for(int x = -1; x <= 1; ++x){
				Position newPosition = new Position(origin.x + x, origin.y + y);
				if(!(newPosition.equals(origin) && ChessBoard.isOutOfBounds(newPosition))){
					moves.add(newPosition);
				}
			}
		}
		
		return moves;
	}

	@Override
	public String toString() {
		return "K";
	}
}
