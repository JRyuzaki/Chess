import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import core.ChessBoard;
import core.ChessGame;
import pieces.Piece;
import util.Position;

public class Main {
	
	static Scanner userScanner;
	
	public static void main(String[] args){
		ChessGame chess = new ChessGame();
		
		userScanner = new Scanner(System.in);
		
		boolean gameRunning = true;
		while(gameRunning){
			ChessBoard chessboard = chess.getChessBoard();
			System.out.println(chessboard);
			System.out.println();
			System.out.println("Player "+chess.getCurrentTurn()+" Turn!");
			
			boolean validTurn = false;
			while(!validTurn){
				System.out.println("Please Enter the Coordinates of the Piece you want to move: ");
				int x = readInt();
				int y = readInt();
				Position fromPosition = new Position(x, y);
				Piece selectedPiece = chessboard.getPiece(fromPosition);
				
				if(selectedPiece == null){
					System.out.println("No Piece was selected!");
					continue;
				}
				List<Position> moves = chess.getMoves(fromPosition);
				chess.validateMoves(moves);
				
				chess.printMoves(moves);
				
				System.out.println("Where to go: ");
				int newX = readInt();
				int newY = readInt();
				Position toPosition = new Position(x,y);
				if(chess.isMoveValid(moves, toPosition)){
					chess.makeMove(fromPosition, toPosition);
				}
				
			}
			chess.nextTurn();
		}
		userScanner.close();
	}
	
	private static int readInt() {
		int number;
		do {
			while (!userScanner.hasNextInt()) {
				System.out.println("That's not an integer!");
				userScanner.next();
			}
			number = userScanner.nextInt();
		} while (number < 0 || number > 7);
		return number;
	}
}
