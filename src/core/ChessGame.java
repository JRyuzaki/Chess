package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import core.moves.Move;
import pieces.Piece;
import pieces.Player;
import util.Position;

public class ChessGame implements ChessLogic{
	private ChessBoard chessboard;
	private Player currentTurn;
	private Stack<Move> moveHistory;
	
	public ChessGame(){
		this.chessboard = new ChessBoard();
		this.currentTurn = Player.PLAYER_ONE;
		this.moveHistory = new Stack<>();
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
	
	public List<Move> getRookkMoves(Position origin) {
		return null;
	}
	
	public List<Move> getKnightMoves(Position origin) {
		return null;
	}
	
	public List<Move> getBishopMoves(Position origin) {
		return null;
	}
	
	public List<Move> getQueenMoves(Position origin) {
		return null;
	}
	
	public List<Move> getKingMoves(Position origin) {
		return null;
	}
	
	public List<Move> getRookMoves(Position origin) {
		return null;
	}
}
