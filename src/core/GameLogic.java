package core;

import util.Position;

public interface GameLogic {
	void nextTurn();
	void makeMove(Position from, Position to);
}
