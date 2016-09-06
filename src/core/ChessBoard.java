package core;
import pieces.*;
import pieces.impl.*;
import util.Position;

public class ChessBoard {
	private AbstractPiece[][] board; // [ x ] [ y ] 0,0 top left
	private static ChessBoard chessboardSingleton;
	
	private ChessBoard() {
		board = new AbstractPiece[8][8];

		for (int i = 0; i < 8; i++){
			board[i][6] = new Pawn(Player.PLAYER_ONE);
		}
		
		board[0][7] = new Rook(Player.PLAYER_ONE);
		board[1][7] = new Knight(Player.PLAYER_ONE);
		board[2][7] = new Bishop(Player.PLAYER_ONE);
		board[3][7] = new Queen(Player.PLAYER_ONE);
		board[4][7] = new King(Player.PLAYER_ONE);
		board[5][7] = new Bishop(Player.PLAYER_ONE);
		board[6][7] = new Knight(Player.PLAYER_ONE);
		board[7][7] = new Rook(Player.PLAYER_ONE);

		for (int i = 0; i < 8; i++){
			board[i][1] = new Pawn(Player.PLAYER_TWO);
		}
		
		board[0][0] = new Rook(Player.PLAYER_TWO);
		board[1][0] = new Knight(Player.PLAYER_TWO);
		board[2][0] = new Bishop(Player.PLAYER_TWO);
		board[3][0] = new Queen(Player.PLAYER_TWO);
		board[4][0] = new King(Player.PLAYER_TWO);
		board[5][0] = new Bishop(Player.PLAYER_TWO);
		board[6][0] = new Knight(Player.PLAYER_TWO);
		board[7][0] = new Rook(Player.PLAYER_TWO);
	}

	/**
	 * Static Factory Method for the singleton
	 * @return ChessBoard
	 */
	public static ChessBoard getChessboard(){
		if(ChessBoard.chessboardSingleton == null)
			ChessBoard.chessboardSingleton = new ChessBoard();
		
		return ChessBoard.chessboardSingleton;
	}
	
	
	public void makeMove(final Position from, final Position to) {
		if (isOutOfBounds(from))
			throw new ArrayIndexOutOfBoundsException("Invalid From-Position! x,y: " + from.toString());

		if (isOutOfBounds(to))
			throw new ArrayIndexOutOfBoundsException("Invalid To-Position! x,y: " + to.toString());

		/*AbstractPiece target = getPiece(to);
		if(target != null){
			if (target.getPlayer() == Player.PLAYER_ONE)		return;
			if(target.getPlayer() == Player.PLAYER_TWO){
				//TODO update, an enemy piece has been eaten
			}
		}*/
		
		//move it there
		setPiece(from, null); 
		AbstractPiece piece = getPiece(from);
		setPiece(to, piece);
	}

	public AbstractPiece getPiece(Position pos) {
		if(isOutOfBounds(pos))
			throw new IllegalArgumentException("Invalid Position");
		
		return board[pos.x][pos.y];
	}

	public void setPiece(Position pos, AbstractPiece piece) {
		if(isOutOfBounds(pos)){
			throw new IllegalArgumentException("Position is invalid!");
		}
		
		board[pos.x][pos.y] = piece;
	}
	
	public static boolean isOutOfBounds(Position position) {
		return (position.x >= 8 || position.x < 0 || position.y >= 8 || position.y < 0);
	}


}
