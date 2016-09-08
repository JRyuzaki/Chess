package core;

import pieces.Player;
import util.Position;

public class ChessGame implements GameLogic{
	private ChessBoard chessboard;
	private Player currentTurn;
	
	public ChessGame(){
		this.chessboard = ChessBoard.getChessboard();
		this.currentTurn = Player.PLAYER_ONE;
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
