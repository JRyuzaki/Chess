package core;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;

import pieces.AbstractPiece;
import pieces.Player;
import pieces.impl.Bishop;
import pieces.impl.King;
import pieces.impl.Knight;
import pieces.impl.Pawn;
import pieces.impl.Queen;
import pieces.impl.Rook;
import util.Position;

public class ChessBoard {
	private static final Logger LOG = LogManager.getLogger(ChessBoard.class);
	
	private AbstractPiece[][] board; // [ x ] [ y ] 0,0 top left
	
	public ChessBoard() {
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
		
		LOG.debug("ChessBoard initialized...");
	}
	
	//Shallow Copy only!
	public ChessBoard(ChessBoard chessboard){
		this.board = new AbstractPiece[8][8];
		for(int y = 0; y < 8; ++y){
			for(int x = 0; x < 8; ++x){
				this.board[x][y] = chessboard.getPiece(new Position(x, y));
			}
		}
	}

	public AbstractPiece getPiece(Position pos) {
		return board[pos.x][pos.y];
	}

	public void setPiece(Position pos, AbstractPiece piece) {
		board[pos.x][pos.y] = piece;
	}
	
	public static boolean isOutOfBounds(Position position) {
		return (position.x >= 8 || position.x < 0 || position.y >= 8 || position.y < 0);
	}

	public Position getPositionOfPiece(AbstractPiece piece){
		for(int y = 0; y < 8; ++y){
			for(int x = 0; x < 8; ++x){
				if(this.board[x][y] == piece){
					return new Position(x, y);
				}
			}
		}
		return null;
	}
	
	public List<AbstractPiece> getPiecesOfPlayer(Player player){
		ArrayList<AbstractPiece> pieces = new ArrayList<>();
		for(int y = 0; y < 8; ++y){
			for(int x = 0; x < 8; ++x){
				if(this.board[y][x] != null && this.board[y][x].getPlayer() == player)
					pieces.add(this.board[y][x]);
			}
		}
		return pieces;
	}
	
	@Override
	public String toString() {
		String output="__________________\n";
		
		for(int y = 0; y < 8; ++y){
			output = output + "|";
			for(int x = 0; x < 8; ++x){
				AbstractPiece piece = this.getPiece(new Position(x, y));
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
