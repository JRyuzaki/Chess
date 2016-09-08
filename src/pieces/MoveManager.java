package pieces;

import java.util.ArrayList;
import java.util.List;

import core.ChessBoard;
import util.Position;

public class MoveManager {
	public static List<Position> getRookMoves(Position origin) {
		return null;
	}

	public static List<Position> getBishopMoves(Position origin) {
		return null;
	}
	
	public static List<Position> getKingMoves(Position origin) {
		return null;
	}

	public static List<Position> getQueenMoves(Position origin) {
		return null;
	}

	public static List<Position> getKnightMoves(Position origin) {
		Piece knight = ChessBoard.getChessboard().getPiece(origin);
		List<Position> moves = new ArrayList<Position>();
		
		for(int a = -1; a <= 1; ++a){
			for(int b = -1; b <= 1; ++b){
				Position verticalMove = new Position(origin.x + 2 * a , origin.y + 1 * b);
				
				Piece verticalMovePiece = ChessBoard.getChessboard().getPiece(verticalMove);
				if(verticalMovePiece.getPlayer() != knight.getPlayer()){
					moves.add(verticalMove);
				}
				
				Position horizontalMove = new Position(origin.x + 1 * a, origin.y + 2 * b);
				Piece horizontalMovePiece = ChessBoard.getChessboard().getPiece(horizontalMove);
				if(horizontalMovePiece.getPlayer() != knight.getPlayer()){
					moves.add(horizontalMove);
				}
			}
		}
		return moves;
	}

	public static List<Position> getPawnMoves(Position origin) {
		return null;
	}
}
