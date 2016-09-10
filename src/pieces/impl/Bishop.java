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

}
