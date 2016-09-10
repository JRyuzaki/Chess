package pieces;

import java.util.List;

import core.ChessBoard;
import core.moves.Move;
import util.Position;

public interface Piece {
	List<Move> getMoves(ChessBoard chessboard, Position origin);
	boolean isMoveValid(Move move);
}
