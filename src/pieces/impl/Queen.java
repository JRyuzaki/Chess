package pieces.impl;

import java.util.ArrayList;
import java.util.List;

import core.ChessBoard;
import core.moves.Move;
import pieces.AbstractPiece;
import pieces.PieceType;
import pieces.Player;
import util.Position;

public class Queen extends AbstractPiece{

	public Queen(Player player) {
		super(PieceType.QUEEN, player);
	}

	@Override
	public List<Move> getMoves(ChessBoard chessboard, Position origin) {
		List<Move> moves = new ArrayList<>();
		moves.addAll(getDiagonalMoves(chessboard, origin));
		moves.addAll(getStraightMoves(chessboard, origin));
		return moves;
	}

}
