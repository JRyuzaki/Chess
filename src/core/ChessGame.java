package core;

import java.util.Stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import core.moves.Capture;
import core.moves.EnPessante;
import core.moves.Move;
import core.moves.MoveType;
import core.moves.Rochade;
import core.moves.Upgrade;
import pieces.AbstractPiece;
import pieces.PieceType;
import pieces.Player;
import pieces.impl.King;
import pieces.impl.Pawn;
import pieces.impl.Rook;
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
		
		Position from = move.getFrom();
		Position to = move.getTo();
		AbstractPiece movedPiece = move.getMovedPiece();
		
		this.chessboard.setPiece(from, null);
		this.chessboard.setPiece(to, movedPiece);
		
		//for rochade(castling)
		if(movedPiece.getType() == PieceType.KING) {
			King king = (King) movedPiece;
			king.setHasMoved(true);
		}else if(movedPiece.getType() == PieceType.ROOK) {
			Rook rook = (Rook) movedPiece;
			rook.setHasMoved(true);
		}
		
		if(move.getType() == MoveType.ROCHADE){
			Rochade rochade = (Rochade)move;
			chessboard.setPiece(rochade.getRookOrigin(), null);
			chessboard.setPiece(rochade.getRookPosition(), rochade.getMovedRook());
		}
		
		if(move.getType() == MoveType.UPGRADE){
			Upgrade upgrade = (Upgrade)move;
			chessboard.setPiece(upgrade.getTo(), upgrade.getNewPiece());
		}else if(move.getType() == MoveType.DOUBLE_MOVE){
			Pawn pawn = (Pawn)move.getMovedPiece();
			pawn.setDoubleMove(true);
		}
		
		if(move.getType() == MoveType.EN_PESSANTE){
			EnPessante enpessante = (EnPessante)move;
			this.chessboard.setPiece(enpessante.getEnemyPawnPosition(), null);
		}else{
			Move lastMove = this.moveHistory.isEmpty()?null:this.moveHistory.peek();
			if(lastMove != null && lastMove.getType() == MoveType.DOUBLE_MOVE){
				Pawn lastMovePawn = (Pawn)lastMove.getMovedPiece();
				lastMovePawn.setDoubleMove(false);
			}
		}
		
		this.moveHistory.push(move);
	}
	
	public void undoMove(){
		if(!moveHistory.isEmpty()) return;
		
		Move move = moveHistory.pop();
		
		//move it back
		Position from = move.getFrom();
		Position to = move.getTo();
		AbstractPiece movedPiece = move.getMovedPiece();
		chessboard.setPiece(from, movedPiece);
		chessboard.setPiece(to, null);
		
		if(move.getType() == MoveType.CAPTURE){
			Capture capture = (Capture)move;
			AbstractPiece captured = capture.getCapturedPiece();
			chessboard.setPiece(to, captured);
			
		}else if(move.getType() == MoveType.DOUBLE_MOVE){
			Pawn pawn = (Pawn)move.getMovedPiece();
			pawn.setDoubleMove(false);
			
		}else if(move.getType() == MoveType.EN_PESSANTE){
			EnPessante enpessante = (EnPessante)move;
			Pawn movingPawn = (Pawn) enpessante.getMovedPiece();
			
			Player enemy = Player.PLAYER_ONE;
			if(movingPawn.getPlayer() == Player.PLAYER_ONE){
				enemy = Player.PLAYER_TWO;
			}
			
			Pawn enemyPawn = new Pawn(enemy);
			enemyPawn.setDoubleMove(true);
			chessboard.setPiece(enpessante.getEnemyPawnPosition(), enemyPawn );
			
		}else if (move.getType() == MoveType.UPGRADE){
			Upgrade upgrade = (Upgrade)move;
			AbstractPiece captured = upgrade.getUpgradedPiece();

			chessboard.setPiece(from, upgrade.getMovedPiece());
			chessboard.setPiece(to, captured);
		}else if (move.getType() == MoveType.ROCHADE){
			//move rook back also
			Rochade rochade = (Rochade)move;
			chessboard.setPiece(rochade.getRookOrigin(), rochade.getMovedRook());
			chessboard.setPiece(rochade.getRookPosition(), null);
		}
	}
}
