package pieces.impl;

import java.util.ArrayList;
import java.util.List;

import core.ChessBoard;
import core.moves.Capture;
import core.moves.Move;
import pieces.AbstractPiece;
import pieces.PieceType;
import pieces.Player;
import util.Position;

public class Rook extends AbstractPiece {
	private boolean hasMoved;

		public Rook(Player player) {
			super(PieceType.ROOK, player);
			hasMoved = false;
		}

		@Override
		public List<Move> getMoves(ChessBoard chessboard, Position origin) {
			List<Move> moves = new ArrayList<>();
			AbstractPiece movingPiece = chessboard.getPiece(origin);
			for (int y = -1; y <= 1; y++) {
				for (int x = -1; x <= 1; x++) {
					if ( !((y == 0) ^ (x == 0)) ) continue;
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
				visualization = "R";
				break;
			case PLAYER_TWO:
				visualization = "r";
				break;
			}

			return visualization;
		}

			public boolean getHasMoved() {
				return hasMoved;
			}

			public void setHasMoved(boolean hasMoved) {
				this.hasMoved = hasMoved;
			}
}
