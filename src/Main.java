import java.util.Scanner;

import core.ChessBoard;
import core.ChessGame;
import pieces.Piece;
import util.Position;

public class Main {
	public static void main(String[] args){
		ChessGame chess = new ChessGame();
		
		Scanner userScanner = new Scanner(System.in);
		
		boolean gameRunning = true;
		while(gameRunning){
			ChessBoard chessboard = chess.getChessBoard();
			System.out.println(chessboard);
			System.out.println();
			System.out.println("Player "+chess.getCurrentTurn()+" Turn!");
			
			boolean validTurn = false;
			while(!validTurn){
				System.out.println("Please Enter the Coordinates of the Piece you want to move: ");
				int x,y;
				try{
					x = userScanner.nextInt();
					y = userScanner.nextInt();
					//TODO: Check if entered coordinates are out of bounds
				}catch(NumberFormatException e){
					System.out.println("Entered Coordinates are not valid");
					continue;
				}
				
				Position fromPosition = new Position(x, y);
				Piece selectedPiece = chessboard.getPiece(fromPosition);
				
				if(selectedPiece == null){
					System.out.println("No Piece was selected!");
					continue;
				}
				
				int newX, newY;
				try{
					newX = userScanner.nextInt();
					newY = userScanner.nextInt();
					//TODO: Check if entered coordinates are out of bounds
				}catch(NumberFormatException e){
					System.out.println("Entered Target-Coordinates are not valid!");
					continue;
				}
				
				//TODO: Check if entered move is valid
				
				//TODO: Update Chessboard
			}
			chess.nextTurn();
		}
		userScanner.close();
	}
}
