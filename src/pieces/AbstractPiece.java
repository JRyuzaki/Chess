package pieces;

import java.util.ArrayList;
import java.util.List;

import core.ChessBoard;
import core.moves.Capture;
import core.moves.Move;
import util.Position;

public abstract class AbstractPiece implements Piece{
	private PieceType type;
	private Player player;
	
	public AbstractPiece(PieceType type, Player player){
		this.type = type;
		this.player = player;
	}

	public PieceType getType() {
		return type;
	}

	public void setType(PieceType type) {
		this.type = type;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public boolean isMoveValid(Move move){
		return false; 	//TODO: Implement
	}
	
	public static List<Move> getStraightMoves(ChessBoard chessboard, Position origin){
		List<Move> moves = new ArrayList<Move>();
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
	
	public static List<Move> getDiagonalMoves(ChessBoard chessboard, Position origin){
		List<Move> moves = new ArrayList<Move>();
		AbstractPiece movingPiece = chessboard.getPiece(origin);

		// upleft moves
		for (int i = 1; i < 8; i++) {
			Position current = new Position(origin.x - i, origin.y - i);
			if (ChessBoard.isOutOfBounds(current)) break;
			AbstractPiece piece = chessboard.getPiece(current);
			if (piece == null) {
				moves.add(new Move(movingPiece, origin, current));
			}else{
				if (piece.getPlayer() != movingPiece.getPlayer()) moves.add(new Capture(movingPiece, origin, current, piece));
				break;
			}
		}
		// downright moves
		for (int i = 1; i < 8; i++) {
			Position current = new Position(origin.x + i, origin.y + i);
			if (ChessBoard.isOutOfBounds(current)) break;
			AbstractPiece piece = chessboard.getPiece(current);
			if (piece == null) {
				moves.add(new Move(movingPiece, origin, current));
			}else{
				if (piece.getPlayer() != movingPiece.getPlayer()) moves.add(new Capture(movingPiece, origin, current, piece));
				break;
			}
		}
		// upright moves
		for (int i = 1; i < 8; i++) {
			Position current = new Position(origin.x + i, origin.y - i);
			if (ChessBoard.isOutOfBounds(current)) break;
			AbstractPiece piece = chessboard.getPiece(current);
			if (piece == null) {
				moves.add(new Move(movingPiece, origin, current));
			}else{
				if (piece.getPlayer() != movingPiece.getPlayer()) moves.add(new Capture(movingPiece, origin, current, piece));
				break;
			}
		}
		// downleft moves
		for (int i = 1; i < 8; i++) {
			Position current = new Position(origin.x - i, origin.y + i);
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
}
