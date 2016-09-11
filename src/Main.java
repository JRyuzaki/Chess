/**
 * This class contains the main method and the main game loop..
 * It allows the current player to select a piece, view the possible moves and
 * choose his (valid) move.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import core.ChessBoard;
import core.ChessGame;
import core.moves.Move;
import pieces.AbstractPiece;
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
				int x,y;
				
				do{
					x = readInt();
					y = readInt();
				}while ((x < 0 || x > 7) || (y < 0 || y > 7));
				
				Position fromPosition = new Position(x, y);
				AbstractPiece selectedPiece = chessboard.getPiece(fromPosition);
				
				if(selectedPiece == null){
					System.out.println("No Piece was selected!");
					continue;
				}else if(selectedPiece.getPlayer() != chess.getCurrentTurn()){
					System.out.println("This Piece belongs to your enemy!");
					continue;
				}
				
				List<Move> moves = selectedPiece.getMoves(chessboard, fromPosition);
				//chess.validateMoves(moves);	//TODO: CHECK FOR CHECK
				
				if(moves.isEmpty()){
					System.out.println("The selected Piece can not be moved!");
					continue;
				}else{
					System.out.println(displayMovesAsString(moves));
				}
				
				int moveIndex;
				do{
					System.out.println("What move do you want to do: ");
					System.out.println("Enter a 0 in order to choose a different piece!");
					moveIndex = readInt();
				}while(moveIndex < 0 || moveIndex > moves.size());
				
				if(moveIndex == 0){		
					continue;
				}
				
				Move playerMove = moves.get(moveIndex - 1);
				chess.makeMove(playerMove);
				validTurn = true;	//CHECKME
			}
			System.out.println("Successfull move");
			chess.nextTurn();
		}
		userScanner.close();
	}
	
	private static int readInt() {
		int number;
			while (!userScanner.hasNextInt()) {
				System.out.println("That's not an integer!");
				userScanner.next();
			}
			number = userScanner.nextInt();
		return number;
	}
	
	public static String displayMovesAsString(List<Move> moves){
		String output = "Possible Moves: \n";
		for(Move move : moves){
			output += "\t("+(moves.indexOf(move) + 1)+") " + move.toString() + '\n';
		}
		
		output += "\t(0)Select other Piece\n";
		return output;
	}
}
