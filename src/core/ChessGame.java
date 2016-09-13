package core;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

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
	public static final Logger LOG = LogManager.getLogger(ChessBoard.class);
	private ChessBoard chessboard;
	private Player currentTurn;
	private Stack<Move> moveHistory;

	private Map<Player, King> kings;
	
	public ChessGame() {
		this.chessboard = new ChessBoard();
		this.currentTurn = Player.PLAYER_ONE;
		this.moveHistory = new Stack<>();
		this.kings = new EnumMap<>(Player.class);
		this.kings.put(Player.PLAYER_ONE, (King)this.chessboard.getPiece(new Position(4, 7)));
		this.kings.put(Player.PLAYER_TWO, (King)this.chessboard.getPiece(new Position(4, 0)));	
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
			LOG.debug("Current turn switched from Player One to Player Two");
		} else {
			this.currentTurn = Player.PLAYER_ONE;
			LOG.debug("It is now Player One's turn.");
		}
	}

	@Override
	public boolean checkForCheckmate() {
		LOG.debug("Currently the game is checking for a checkmate situation");
		King myKing = getKingForPlayer(currentTurn);
		Position myKingPosition = chessboard.getPositionOfPiece(myKing);
		Player enemy = (currentTurn == Player.PLAYER_ONE)?Player.PLAYER_TWO:Player.PLAYER_ONE;
		return ChessGame.isFieldThreaten(chessboard, myKingPosition, enemy );
	}

	@Override
	public boolean checkForTie() {
		// TODO Auto-generated method stub
		LOG.debug("Currently the game is checking for a tie situation.");
		return false;
	}

	@Override
	public void makeMove(ChessBoard chessboard, Move move) {	
		
		Position from = move.getFrom();
		Position to = move.getTo();
		AbstractPiece movedPiece = move.getMovedPiece();
		
		chessboard.setPiece(from, null);
		chessboard.setPiece(to, movedPiece);
		
		//for rochade(castling)
		if(movedPiece.getType() == PieceType.KING) {
			King king = (King) movedPiece;
			king.setHasMoved(true);
			LOG.debug("The game is checking whether or not the KING has already moved (important for castling");
		}else if(movedPiece.getType() == PieceType.ROOK) {
			Rook rook = (Rook) movedPiece;
			rook.setHasMoved(true);
			LOG.debug("The game is checking whether or not the ROOK has already moved (important for castling");
		}
		
		if(move.getType() == MoveType.ROCHADE){
			Rochade rochade = (Rochade)move;
			chessboard.setPiece(rochade.getRookOrigin(), null);
			chessboard.setPiece(rochade.getRookPosition(), rochade.getMovedRook());
			LOG.debug("A CASTLING move has been performed");
		}
		
		if(move.getType() == MoveType.UPGRADE){
			Upgrade upgrade = (Upgrade)move;
			chessboard.setPiece(upgrade.getTo(), upgrade.getNewPiece());
			LOG.debug("A PROMOTION move has been performed");
		}else if(move.getType() == MoveType.DOUBLE_MOVE){
			Pawn pawn = (Pawn)move.getMovedPiece();
			pawn.setDoubleMove(true);
			LOG.debug("A DOUBLEMOVE move has been done");
		}
		
		if(move.getType() == MoveType.EN_PASSANTE){
			EnPessante enpessante = (EnPessante)move;
			this.chessboard.setPiece(enpessante.getEnemyPawnPosition(), null);
			LOG.debug("A EN PASSANT move has been performed");
		}else{
			Move lastMove = this.moveHistory.isEmpty()?null:this.moveHistory.peek();
			if(lastMove != null && lastMove.getType() == MoveType.DOUBLE_MOVE){
				Pawn lastMovePawn = (Pawn)lastMove.getMovedPiece();
				lastMovePawn.setDoubleMove(false);
				LOG.debug("A PAWN's DOUBLEMOVE flag got reset! (EN PASSANT)");
			}
		}
		
		//this.moveHistory.push(move);
	}
	
	public void addMoveToHistory(Move move){
		this.moveHistory.push(move);
	}
	
	public void undoMove(){
		if(!moveHistory.isEmpty()) return;
		
		Move move = moveHistory.pop();
		
		// move the piece back no matter what kind of move it was first
		Position from = move.getFrom();
		Position to = move.getTo();
		AbstractPiece movedPiece = move.getMovedPiece();
		chessboard.setPiece(from, movedPiece);
		chessboard.setPiece(to, null);
		
		if(move.getType() == MoveType.CAPTURE){
			Capture capture = (Capture)move;
			AbstractPiece captured = capture.getCapturedPiece();
			chessboard.setPiece(to, captured);
			LOG.debug("A CAPTURE move has been UNDONE");
			
		}else if(move.getType() == MoveType.DOUBLE_MOVE){
			Pawn pawn = (Pawn)move.getMovedPiece();
			pawn.setDoubleMove(false);
			LOG.debug("A DOUBLEMOVE has been UNDONE");
			
		}else if(move.getType() == MoveType.EN_PASSANTE){
			EnPessante enpessante = (EnPessante)move;
			Pawn movingPawn = (Pawn) enpessante.getMovedPiece();
			
			Player enemy = Player.PLAYER_ONE;
			if(movingPawn.getPlayer() == Player.PLAYER_ONE){
				enemy = Player.PLAYER_TWO;
			}
			
			Pawn enemyPawn = new Pawn(enemy);
			enemyPawn.setDoubleMove(true);
			chessboard.setPiece(enpessante.getEnemyPawnPosition(), enemyPawn);
			LOG.debug("A EN PASSANT move has been UNDONE");
			
		}else if (move.getType() == MoveType.UPGRADE){
			Upgrade upgrade = (Upgrade)move;
			AbstractPiece captured = upgrade.getUpgradedPiece();

			chessboard.setPiece(from, upgrade.getMovedPiece());
			chessboard.setPiece(to, captured);
			LOG.debug("A UPGRADE move has been UNDONE");
			
			//King has already been moved back above - so only the rook has to be moved back!
		}else if (move.getType() == MoveType.ROCHADE){
			Rochade rochade = (Rochade)move;
			chessboard.setPiece(rochade.getRookOrigin(), rochade.getMovedRook());
			chessboard.setPiece(rochade.getRookPosition(), null);
			LOG.debug("A ROCHADE move has been UNDONE");
		}
	}
	
	@Override
	public List<Move> validateMoves(List<Move> moves) {
		Player enemy = (this.currentTurn == Player.PLAYER_ONE)?Player.PLAYER_TWO:Player.PLAYER_ONE;
		
		List<Move> invalidMoves = new ArrayList<>();
		
		for(Move move : moves){
			ChessBoard afterMove = new ChessBoard(this.chessboard);
			this.makeMove(afterMove, move);
			Position kingPosition = afterMove.getPositionOfPiece(this.kings.get(this.currentTurn));
			
			if(ChessGame.isFieldThreaten(afterMove, kingPosition, enemy)){
				invalidMoves.add(move);
			}
		}
		moves.removeAll(invalidMoves);
		return moves;
	}
	
	public static boolean isFieldThreaten(ChessBoard chessboard, Position field, Player enemy){
		List<AbstractPiece> enemyPieces = chessboard.getPiecesOfPlayer(enemy);
		for(AbstractPiece piece : enemyPieces){
			List<Move> enemyMoves = piece.getMoves(chessboard, chessboard.getPositionOfPiece(piece));
			for(Move enemyMove : enemyMoves){
				if(enemyMove.getTo().equals(field)){
					return true;
				}
			}
		}
		return false;
	}
	
	public King getKingForPlayer(Player player){
		return this.kings.get(player);
	}
}
