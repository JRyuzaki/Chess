package pieces.impl;

import java.util.List;

import core.ChessBoard;
import core.moves.Move;
import pieces.AbstractPiece;
import pieces.PieceType;
import pieces.Player;
import util.Position;

public class Pawn extends AbstractPiece{

	public Pawn(Player player) {
		super(PieceType.PAWN, player);
	}

	@Override
	public List<Move> getMoves(ChessBoard chessboard, Position origin) {
		// TODO Auto-generated method stub
		return null;
	}

}
