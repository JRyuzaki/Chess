package core;
import pieces.*;
import util.Position;

public class ChessBoard {
	private Piece[][] board; // [ x ] [ y ] 0,0 top left
	private static ChessBoard chessboardSingleton;
	
	private ChessBoard() {
		board = new Piece[8][8];

		for (int i = 0; i < 8; i++){
			board[i][6] = new Piece(PieceType.PAWN, Player.PLAYER_ONE);
		}
		
		board[0][7] = new Piece(PieceType.ROOK, Player.PLAYER_ONE);
		board[1][7] = new Piece(PieceType.KNIGHT, Player.PLAYER_ONE);
		board[2][7] = new Piece(PieceType.BISHOP, Player.PLAYER_ONE);
		board[3][7] = new Piece(PieceType.KING, Player.PLAYER_ONE);
		board[4][7] = new Piece(PieceType.QUEEN, Player.PLAYER_ONE);
		board[5][7] = new Piece(PieceType.BISHOP, Player.PLAYER_ONE);
		board[6][7] = new Piece(PieceType.KNIGHT, Player.PLAYER_ONE);
		board[7][7] = new Piece(PieceType.ROOK, Player.PLAYER_ONE);

		for (int i = 0; i < 8; i++){
			board[i][6] = new Piece(PieceType.PAWN, Player.PLAYER_TWO);
		}
		
		board[0][7] = new Piece(PieceType.ROOK, Player.PLAYER_TWO);
		board[1][7] = new Piece(PieceType.KNIGHT, Player.PLAYER_TWO);
		board[2][7] = new Piece(PieceType.BISHOP, Player.PLAYER_TWO);
		board[3][7] = new Piece(PieceType.KING, Player.PLAYER_TWO);
		board[4][7] = new Piece(PieceType.QUEEN, Player.PLAYER_TWO);
		board[5][7] = new Piece(PieceType.BISHOP, Player.PLAYER_TWO);
		board[6][7] = new Piece(PieceType.KNIGHT, Player.PLAYER_TWO);
		board[7][7] = new Piece(PieceType.ROOK, Player.PLAYER_TWO);
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

	public Piece getPiece(Position pos) {
		if(isOutOfBounds(pos))
			throw new IllegalArgumentException("Invalid Position");
		
		return board[pos.x][pos.y];
	}

	public void setPiece(Position pos, Piece piece) {
		if(isOutOfBounds(pos)){
			throw new IllegalArgumentException("Position is invalid!");
		}
		
		board[pos.x][pos.y] = piece;
	}
	
	public static boolean isOutOfBounds(Position position) {
		return (position.x >= 8 || position.x < 0 || position.y >= 8 || position.y < 0);
	}

	@Override
	public String toString() {
		String output="__________________\n";
		
		for(int y = 0; y < 8; ++y){
			output = output + "|";
			for(int x = 0; x < 8; ++x){
				Piece piece = this.getPiece(new Position(x, y));
				if(piece != null){
					output = output + piece + " ";
				}else{
					output = output + "  ";
				}
			}
			output = output + "|\n";
		}
		
		output = output + "__________________\n";
		return output;
	}
}
