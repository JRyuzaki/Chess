package pieces.impl;

import java.util.List;

import core.ChessBoard;
import core.moves.Move;
import pieces.AbstractPiece;
import pieces.PieceType;
import pieces.Player;
import util.Position;

public class Bishop extends AbstractPiece{

	public Bishop(Player player) {
		super(PieceType.BISHOP, player);
	}

	@Override
	public List<Move> getMoves(ChessBoard chessboard, Position origin) {
		return getDiagonalMoves(chessboard, origin);
	}
	
	@Override
	public String toString() {
		String visualization = "~";
		
		switch(this.getPlayer()){
		case PLAYER_ONE:
			visualization = "B";
		case PLAYER_TWO:
			visualization = "b";
		}
		
		return visualization;
	}
}
