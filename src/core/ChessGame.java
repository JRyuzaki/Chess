package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import core.moves.Capture;
import core.moves.Move;
import pieces.Piece;
import pieces.PieceType;
import pieces.Player;
import util.Position;

public class ChessGame implements ChessLogic {
	private ChessBoard chessboard;
	private Player currentTurn;
	private Stack<Move> moveHistory;

	public ChessGame() {
		this.chessboard = new ChessBoard();
		this.currentTurn = Player.PLAYER_ONE;
		this.moveHistory = new Stack<>();
	}

	// TODO: REFRACTOR
	public void printMoves(List<Position> moves) {
		for (Position move : moves) {
			System.out.print(move + " ");
		}
		System.out.println();
	}

	public void setChessBoard(ChessBoard chessboard) {
		this.chessboard = chessboard;
	}

	public ChessBoard getChessBoard() {
		return this.chessboard;
	}

	public Player getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(Player currentTurn) {
		this.currentTurn = currentTurn;
	}

	@Override
	public void nextTurn() {
		if (this.currentTurn == Player.PLAYER_ONE) {
			this.currentTurn = Player.PLAYER_TWO;
		} else {
			this.currentTurn = Player.PLAYER_ONE;
		}
	}

	@Override
	public boolean checkForCheckmate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkForTie() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void makeMove(Move move) {
		// TODO Auto-generated method stub

	}

	public List<Move> getPawnMoves(Position origin) {
		return null;
	}

	public List<Move> getKnightMoves(Position origin) {
		Piece knight = this.chessboard.getPiece(origin);
		
		List<Move> moves = new ArrayList<>();
		
		for(int i = -1; i <= 1; i = i + 2){
			for(int b = -1; b <= 1; b = b + 2){
				Position horizontalJump = new Position(origin.x + i * 2, origin.y + b);
				
				if(!ChessBoard.isOutOfBounds(horizontalJump)){
					Piece horizontalJumpPiece = this.chessboard.getPiece(horizontalJump);
					if(horizontalJumpPiece == null){
						moves.add(new Move(knight, origin, horizontalJump));
					}else{
						if (horizontalJumpPiece.getPlayer() != currentTurn) moves.add(new Capture(knight, origin, horizontalJump, horizontalJumpPiece));
					}
				}
				
				Position verticalJump = new Position(origin.x + b, origin.y + i * 2);
				if(!ChessBoard.isOutOfBounds(verticalJump)){
					Piece verticalJumpPiece = this.chessboard.getPiece(verticalJump);
					if(verticalJumpPiece == null){
						moves.add(new Move(knight, origin, verticalJump));
					}else{
						if (verticalJumpPiece.getPlayer() != currentTurn) moves.add(new Capture(knight, origin, verticalJump, verticalJumpPiece));
					}
				}
			}
		}
		return moves;
	}

	public List<Move> getKingMoves(Position origin) {
		Piece king = this.chessboard.getPiece(origin);
		
		List<Move> moves = new ArrayList<>();
		
		for(int y = -1; y <= 1; ++y){
			for(int x = -1; x <= 1; ++x){
				Position newPosition = new Position(origin.x + x, origin.y + y);
				if(!ChessBoard.isOutOfBounds(newPosition) && !origin.equals(newPosition)){
					Piece piece = this.chessboard.getPiece(newPosition);
					if(piece == null){
						moves.add(new Move(king, origin, newPosition));
					}else{
						if(piece.getPlayer() != this.currentTurn)
							moves.add(new Capture(king, origin, newPosition, piece));
					}
				}
			}
		}
		
		return moves;
	}
	
	public List<Move> getQueenMoves(Position origin) {
		List<Move> moves = new ArrayList<Move>();
		moves.addAll(getBishopMoves(origin));
		moves.addAll(getRookMoves(origin));
		return moves;
	}

	public List<Move> getBishopMoves(Position origin) {
		List<Move> moves = new ArrayList<Move>();
		Piece bishop = chessboard.getPiece(origin);

		// upleft moves
		for (int i = 1; i < 8; i++) {
			Position current = new Position(origin.x - i, origin.y - i);
			if (ChessBoard.isOutOfBounds(current)) break;
			Piece piece = chessboard.getPiece(current);
			if (piece == null) {
				moves.add(new Move(bishop, origin, current));
			}else{
				if (piece.getPlayer() != currentTurn) moves.add(new Capture(bishop, origin, current, piece));
				break;
			}
		}
		// downright moves
		for (int i = 1; i < 8; i++) {
			Position current = new Position(origin.x + i, origin.y + i);
			if (ChessBoard.isOutOfBounds(current)) break;
			Piece piece = chessboard.getPiece(current);
			if (piece == null) {
				moves.add(new Move(bishop, origin, current));
			}else{
				if (piece.getPlayer() != currentTurn) moves.add(new Capture(bishop, origin, current, piece));
				break;
			}
		}
		// upright moves
		for (int i = 1; i < 8; i++) {
			Position current = new Position(origin.x + i, origin.y - i);
			if (ChessBoard.isOutOfBounds(current)) break;
			Piece piece = chessboard.getPiece(current);
			if (piece == null) {
				moves.add(new Move(bishop, origin, current));
			}else{
				if (piece.getPlayer() != currentTurn) moves.add(new Capture(bishop, origin, current, piece));
				break;
			}
		}
		// downleft moves
		for (int i = 1; i < 8; i++) {
			Position current = new Position(origin.x - i, origin.y + i);
			if (ChessBoard.isOutOfBounds(current)) break;
			Piece piece = chessboard.getPiece(current);
			if (piece == null) {
				moves.add(new Move(bishop, origin, current));
			}else{
				if (piece.getPlayer() != currentTurn) moves.add(new Capture(bishop, origin, current, piece));
				break;
			}
		}

		return moves;
	}

	public List<Move> getRookMoves(Position origin) {
		List<Move> moves = new ArrayList<Move>();
		Piece rook = chessboard.getPiece(origin);

		// right moves
		for (int i = 1; i < 8; i++) {
			Position current = new Position(origin.x + i, origin.y);
			if (ChessBoard.isOutOfBounds(current)) break;
			Piece piece = chessboard.getPiece(current);
			
			if (piece == null) {
				moves.add(new Move(rook, origin, current));
			}else{
				if (piece.getPlayer() != currentTurn) moves.add(new Capture(rook, origin, current, piece));
				break;
			}
		}
		// left moves
		for (int i = 1; i < 8; i++) {
			Position current = new Position(origin.x - i, origin.y);
			if (ChessBoard.isOutOfBounds(current)) break;
			Piece piece = chessboard.getPiece(current);
			if (piece == null) {
				moves.add(new Move(rook, origin, current));
			}else{
				if (piece.getPlayer() != currentTurn) moves.add(new Capture(rook, origin, current, piece));
				break;
			}
		}
		// up moves
		for (int i = 1; i < 8; i++) {
			Position current = new Position(origin.x, origin.y - i);
			if (ChessBoard.isOutOfBounds(current)) break;
			Piece piece = chessboard.getPiece(current);
			if (piece == null) {
				moves.add(new Move(rook, origin, current));
			}else{
				if (piece.getPlayer() != currentTurn) moves.add(new Capture(rook, origin, current, piece));
				break;
			}
		}
		// down moves
		for (int i = 1; i < 8; i++) {
			Position current = new Position(origin.x, origin.y + i);
			if (ChessBoard.isOutOfBounds(current)) break;
			Piece piece = chessboard.getPiece(current);
			if (piece == null) {
				moves.add(new Move(rook, origin, current));
			}else{
				if (piece.getPlayer() != currentTurn) moves.add(new Capture(rook, origin, current, piece));
				break;
			}
		}

		return moves;
	}

}
