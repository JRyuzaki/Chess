package core;

import core.moves.Move;
import util.Position;

public interface ChessLogic {
	void nextTurn();
	boolean checkForCheckmate();
	boolean checkForTie();
	void makeMove(Move move);
}
