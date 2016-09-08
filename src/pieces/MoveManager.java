package pieces;

import java.util.ArrayList;
import java.util.List;

import util.Position;

public class MoveManager {
	public static List<Position> getRookMoves(Position origin) {
		return null;
	}

	public static List<Position> getBishopMoves(Position origin) {
		
		return null;
	}
	
	public static List<Position> getKingMoves(Position origin) {
		List <Position> moves = new ArrayList<>();
		
		for(int y = -1; y <= 1; ++y){
			for(int x = -1; x <= 1; ++x){
				Position newPosition = new Position(origin.x + x, origin.y + y);
				if(!newPosition.equals(origin)){
					moves.add(newPosition);
				}
			}
		}
		//TODO: Add Rochade
		return moves;
	}

	public static List<Position> getQueenMoves(Position origin) {
		return null;
	}

	public static List<Position> getKnightMoves(Position origin) {
		List<Position> moves = new ArrayList<>();
		
		for(int a = -1; a <= 1; a += 2){
			for(int b = -1; b <= 1; b += 2){
				Position verticalMove = new Position(origin.x + 2 * a , origin.y + 1 * b);
				Position horizontalMove = new Position(origin.x + 1 * a, origin.y + 2 * b);

				moves.add(verticalMove);
				moves.add(horizontalMove);
			}
		}
		return moves;
	}

	public static List<Position> getPawnMoves(Position origin) {
		List<Position> moves = new ArrayList<>();
		
		return moves;
	}
}
