package pieces.impl;

import java.util.List;

import core.ChessBoard;
import core.moves.Move;
import pieces.AbstractPiece;
import pieces.PieceType;
import pieces.Player;
import util.Position;

public class Rook extends AbstractPiece{

	public Rook(Player player) {
		super(PieceType.ROOK, player);
	}

	@Override
	public List<Move> getMoves(ChessBoard chessboard, Position origin) {
		return getStraightMoves(chessboard, origin);
	}

	@Override
	public String toString() {
		String visualization = "~";
		
		switch(this.getPlayer()){
		case PLAYER_ONE:
			visualization = "R";
			break;
		case PLAYER_TWO:
			visualization = "r";
			break;
		}
		
		return visualization;
	}
}
