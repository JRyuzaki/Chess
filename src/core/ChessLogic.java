package core;

import java.util.List;

import core.moves.Move;

public interface ChessLogic {
	void nextTurn();
	boolean checkForCheckmate();
	TieType checkForTie();
	void makeMove(ChessBoard chessboard, Move move);
	List<Move> validateMoves(List<Move> moves);
}
