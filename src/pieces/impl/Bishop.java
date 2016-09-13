package pieces.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import core.ChessBoard;
import core.moves.Capture;
import core.moves.Move;
import pieces.AbstractPiece;
import pieces.PieceType;
import pieces.Player;
import util.Position;

public class Bishop extends AbstractPiece {

		public Bishop(Player player) {
			super(PieceType.BISHOP, player);
		}

		@Override
		public List<Move> getMoves(ChessBoard chessboard, Position origin) {
			List<Move> moves = new ArrayList<>();
			AbstractPiece movingPiece = chessboard.getPiece(origin);

			for (int y = -1; y <= 1; y = y + 2) {
				for (int x = -1; x <= 1; x = x + 2) {
					for (int i = 1; i < 8; i++) {
							Position current = new Position(origin.x + x * i, origin.y + y * i);
							if (ChessBoard.isOutOfBounds(current)) break;
							AbstractPiece piece = chessboard.getPiece(current);
							if (piece == null) {
								moves.add(new Move(movingPiece, origin, current));
							} else {
								if (piece.getPlayer() != movingPiece.getPlayer()) moves.add(new Capture(movingPiece, origin, current, piece));
								break;
							}
					}
				}
			}

			return moves;
		}

		@Override
		public String toString() {
			String visualization = "~";

			switch (this.getPlayer()) {
			case PLAYER_ONE:
				visualization = "B";
				break;
			case PLAYER_TWO:
				visualization = "b";
				break;
			}

			return visualization;
		}
}
