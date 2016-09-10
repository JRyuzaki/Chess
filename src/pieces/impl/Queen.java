package pieces.impl;

import java.util.ArrayList;
import java.util.List;

import core.ChessBoard;
import core.moves.Move;
import pieces.AbstractPiece;
import pieces.PieceType;
import pieces.Player;
import util.Position;

public class Queen extends AbstractPiece {

	public Queen(Player player) {
		super(PieceType.QUEEN, player);
	}

	@Override
	public List<Move> getMoves(ChessBoard chessboard, Position origin) {
		List<Move> moves = new ArrayList<>();
		
		AbstractPiece rook = new Rook(getPlayer());
		List<Move> rookmoves = rook.getMoves(chessboard, origin);
		moves.addAll(rookmoves);
		
		AbstractPiece bishop = new Bishop(getPlayer());
		List<Move> bishopmoves = bishop.getMoves(chessboard, origin);
		moves.addAll(bishopmoves);
		
		return moves;
	}

	@Override
	public String toString() {
		String visualization = "~";

		switch (this.getPlayer()) {
		case PLAYER_ONE:
			visualization = "Q";
			break;
		case PLAYER_TWO:
			visualization = "q";
			break;
		}

		return visualization;
	}
}
