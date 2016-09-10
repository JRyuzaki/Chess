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

}
