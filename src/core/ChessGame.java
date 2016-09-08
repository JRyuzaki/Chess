package core;

import java.util.ArrayList;

import pieces.Piece;
import pieces.Player;
import util.Position;

public class ChessGame implements GameLogic{
	private ChessBoard chessboard;
	private Player currentTurn;
	
	public ChessGame(){
		this.chessboard = ChessBoard.getChessboard();
		this.currentTurn = Player.PLAYER_ONE;
	}
	
	// finding unvalids and them removing them all together, to avoid remove()
	// inside the iteration
	public void validateMoves(ArrayList<Position> moves) {
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
		}
		moves.removeAll(invalids);
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
	public void makeMove(Position from, Position to) {
		
	}
}
