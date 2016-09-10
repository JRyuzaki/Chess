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

public class Rook extends AbstractPiece{

	public Rook(Player player) {
		super(PieceType.ROOK, player);
	}

	@Override
	public List<Move> getMoves(ChessBoard chessboard, Position origin) {
		List<Move> moves = new ArrayList<>();
		AbstractPiece movingPiece = chessboard.getPiece(origin);

		// right moves
		for (int i = 1; i < 8; i++) {
			Position current = new Position(origin.x + i, origin.y);
			if (ChessBoard.isOutOfBounds(current)) break;
			AbstractPiece piece = chessboard.getPiece(current);
			
			if (piece == null) {
				moves.add(new Move(movingPiece, origin, current));
			}else{
				if (piece.getPlayer() != movingPiece.getPlayer()) moves.add(new Capture(movingPiece, origin, current, piece));
				break;
			}
		}
		// left moves
		for (int i = 1; i < 8; i++) {
			Position current = new Position(origin.x - i, origin.y);
			if (ChessBoard.isOutOfBounds(current)) break;
			AbstractPiece piece = chessboard.getPiece(current);
			if (piece == null) {
				moves.add(new Move(movingPiece, origin, current));
			}else{
				if (piece.getPlayer() != movingPiece.getPlayer()) moves.add(new Capture(movingPiece, origin, current, piece));
				break;
			}
		}
		// up moves
		for (int i = 1; i < 8; i++) {
			Position current = new Position(origin.x, origin.y - i);
			if (ChessBoard.isOutOfBounds(current)) break;
			AbstractPiece piece = chessboard.getPiece(current);
			if (piece == null) {
				moves.add(new Move(movingPiece, origin, current));
			}else{
				if (piece.getPlayer() != movingPiece.getPlayer()) moves.add(new Capture(movingPiece, origin, current, piece));
				break;
			}
		}
		// down moves
		for (int i = 1; i < 8; i++) {
			Position current = new Position(origin.x, origin.y + i);
			if (ChessBoard.isOutOfBounds(current)) break;
			AbstractPiece piece = chessboard.getPiece(current);
			if (piece == null) {
				moves.add(new Move(movingPiece, origin, current));
			}else{
				if (piece.getPlayer() != movingPiece.getPlayer()) moves.add(new Capture(movingPiece, origin, current, piece));
				break;
			}
		}

		return moves;
	}

	@Override
	public String toString() {
		String visualization = "~";
		
		switch(this.getPlayer()){
		case PLAYER_ONE:
			visualization = "R";
			break;
		case PLAYER_TWO:
			visualization = "r";
			break;
		}
		
		return visualization;
	}
}
