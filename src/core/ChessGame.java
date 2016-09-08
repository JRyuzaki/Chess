package core;

import java.util.ArrayList;
import java.util.List;

import pieces.MoveManager;
import pieces.Piece;
import pieces.PieceType;
import pieces.Player;
import util.Position;

public class ChessGame implements GameLogic{
	private ChessBoard chessboard;
	private Player currentTurn;
	
	public ChessGame(){
		this.chessboard = ChessBoard.getChessboard();
		this.currentTurn = Player.PLAYER_ONE;
	}
	
	public List<Position> getMoves(Position selected) {
		if (ChessBoard.isOutOfBounds(selected))
			throw new IllegalArgumentException("Invalid click x,y: " + selected.toString());
		Piece piece = chessboard.getPiece(selected); // get the piece on that location
		if (piece == null)
			return null; // user clicked empty square
		if (piece.getPlayer() != currentTurn)
			return null; // user clicked on enemy piece
		
		List<Position> moves = new ArrayList<Position>();
		PieceType type = piece.getType();
		switch (type) {
		case KING:
			moves = MoveManager.getKingMoves(selected);
			break;
		case QUEEN:
			moves = MoveManager.getQueenMoves(selected);
			break;
		case BISHOP:
			moves = MoveManager.getBishopMoves(selected);
			break;
		case ROOK:
			moves = MoveManager.getRookMoves(selected);
			break;
		case PAWN:
			moves = MoveManager.getPawnMoves(selected);
			break;
		case KNIGHT:
			moves = MoveManager.getKnightMoves(selected);
			break;
		}
		return moves;
	}
	
	// finding unvalids and them removing them all together, to avoid remove()
	// inside the iteration
	public void validateMoves(List<Position> moves) {
		ArrayList<Position> invalids = new ArrayList<Position>();
		for (Position move : moves) {
			// out of map is not a valid move
			if (ChessBoard.isOutOfBounds(move)) {
				invalids.add(move);
				continue;
			}
			Piece piece = chessboard.getPiece(move);
			if (piece != null) {
				if (piece.getPlayer() == currentTurn) {
					invalids.add(move);
					continue;
				}
			}
			//TODO remove moves that cause checkmate
		}
		moves.removeAll(invalids);
	}
	
	public boolean isMoveValid(List<Position> moves, Position pos) {
		if (ChessBoard.isOutOfBounds(pos))
			throw new IllegalArgumentException("Invalid click x,y: " + pos.toString());
		
		boolean valid = false;
		for(Position move : moves){
			if(move.equals(pos)){
				valid = true;
				break;
			}
		}
		return valid;
	}
	
	public void printMoves(List<Position> moves) {
		for (Position move : moves) {
			System.out.print(move + " ");
		}
		System.out.println();
	}
	
	public void setChessBoard(ChessBoard chessboard){
		this.chessboard = chessboard;
	}
	
	public ChessBoard getChessBoard(){
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
		if(this.currentTurn == Player.PLAYER_ONE){
			this.currentTurn = Player.PLAYER_TWO;
		}else{
			this.currentTurn = Player.PLAYER_ONE;
		}
	}

	@Override
	public void makeMove(final Position from, final Position to) {
		Piece piece = chessboard.getPiece(from);
		chessboard.setPiece(from, null);
		chessboard.setPiece(to, piece);
	}
	
}
