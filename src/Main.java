/**
 * This class contains the main method and the main game loop.
 * It allows the current player to select a piece, view the possible moves and
 * choose his (valid) move.
 */

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import core.ChessBoard;
import core.ChessGame;
import core.TieType;
import core.moves.Move;
import core.moves.MoveType;
import core.moves.Upgrade;
import pieces.AbstractPiece;
import pieces.impl.Bishop;
import pieces.impl.Knight;
import pieces.impl.Queen;
import pieces.impl.Rook;
import util.Position;

public class Main {
	private static final Logger LOG = LogManager.getLogger(Main.class);
	
	static Scanner userScanner;
	static ChessGame chess;
	
	public static void main(String[] args){
		userScanner = new Scanner(System.in);
		
		chess = new ChessGame();
		LOG.debug("A new Chess game has been created");
		
		boolean gameRunning = true;
		while(gameRunning){
			LOG.debug("The main while loop is now running");
			ChessBoard chessboard = chess.getChessBoard();
			System.out.println(chessboard);
			System.out.println();
			System.out.println("Player "+chess.getCurrentTurn()+" Turn!");
			
			boolean validTurn = false;
			while(!validTurn){

				TieType tie = chess.checkForTie();
				if(tie != null){
					switch (tie) {
					case INSUFFICIENT_MATERIAL:
						System.out.println("Draw");
						System.out.println("You don't have enough material to checkmate your opponent");
						gameRunning = false;
						break;
					case STALEMATE:
						System.out.println("Stalemate");
						gameRunning = false;
						break;
					case THREEFOLD_REPETITION:
						System.out.println("Threefold Repetition:");
						System.out.println("Do you agree to a draw? <y/n>");
						gameRunning = Main.handleYesNoQuestion();
						break;
					case FIFTY_MOVE_RULE:
						System.out.println("Fifty-Move-Rule:");
						System.out.println("Do you agree to a draw? <y/n>");
						gameRunning = Main.handleYesNoQuestion();
						break;
					}
					
					if(!gameRunning){
						break;
					}
				}
				
				if(chess.isCheck()){
					System.out.println("Your King is on Check!");
					if(chess.checkForCheckmate()){
						System.out.println("Checkmate.. you have lost");
						gameRunning = false;
						break;
					}
				}
				
				Position fromPosition = Main.getPositionByChessCoordinates();
				AbstractPiece selectedPiece = chessboard.getPiece(fromPosition);
				
				if(selectedPiece == null){
					System.out.println("No Piece was selected!");
					continue;
				}else if(selectedPiece.getPlayer() != chess.getCurrentTurn()){
					System.out.println("This Piece belongs to your enemy!");
					continue;
				}
				
				List<Move> moves = selectedPiece.getMoves(chessboard, fromPosition);
				moves = chess.validateMoves(moves);
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
				if(playerMove.getType() == MoveType.UPGRADE) handleUpgradeMove(playerMove);
				chess.makeMove(chessboard, playerMove);
				chess.addMoveToHistory(playerMove);
				
				validTurn = true;
			}
			System.out.println("Successfull move");
			chess.nextTurn();
		}
		userScanner.close();
	}

	private static Position getPositionByNumericCoordinates(){
		int x,y;
		do{
			System.out.print("Row of Piece you want to move: ");
			x = readInt();
			System.out.print("Column of Piece you want to move: ");
			y = readInt();
		}while ((x < 0 || x > 7) || (y < 0 || y > 7));
		return new Position(x, y);
	}
	
	private static boolean handleYesNoQuestion(){
		while(!userScanner.hasNext());
		char input = userScanner.next().toLowerCase().charAt(0);
		return input == 'y';
	}

	private static void handleUpgradeMove(Move playerMove) {
		Upgrade upgradeMove = (Upgrade) playerMove;
		String output = "What Piece do you want?\n";
		output += "\t(1) Queen\n";
		output += "\t(2) Knight\n";
		output += "\t(3) Rook\n";
		output += "\t(4) Bishop\n";
		System.out.println(output);
		
		int choice;
		do{
			choice = readInt();
		}while(choice < 1 || choice > 4);
		
		AbstractPiece newPiece = null;
		switch(choice){
		case 1:
			newPiece = new Queen(chess.getCurrentTurn());
			break;
		case 2:
			newPiece = new Knight(chess.getCurrentTurn());
			break;
		case 3:
			newPiece = new Rook(chess.getCurrentTurn());
			break;
		case 4:
			newPiece = new Bishop(chess.getCurrentTurn());
			break;
		}
		
		upgradeMove.setNewPiece(newPiece);
		LOG.debug("A piece has been promoted!");
	}
	
	private static int readInt() {
		int number;
			while (!userScanner.hasNextInt()) {
				System.out.println("That's not an integer!");
				userScanner.next();
			}
			number = userScanner.nextInt();
			LOG.debug("Currently a number is being input");
		return number;
	}
	
	private static Position getPositionByChessCoordinates() {
		Position position = null;
		boolean validCoordinate = false;
		while(!validCoordinate){
		String chessCoordinateString;
			while (!userScanner.hasNext()) {
				System.out.println("That's not a valid ChessBoard-Coordinate!");
				userScanner.next();
			}
			chessCoordinateString = userScanner.next();
			if(chessCoordinateString.length() > 2){
				System.out.println("That's not a valid ChessBoard-Coordinate!");
				continue;
			}
			
			chessCoordinateString = chessCoordinateString.toUpperCase();
			
			char xCoordinate = chessCoordinateString.charAt(0);
			char yCoordinate = chessCoordinateString.charAt(1);
			
			if(xCoordinate < 'A' || xCoordinate > 'H' || yCoordinate < '1' || yCoordinate > '8'){
				System.out.println("That's not a valid ChessBoard-Coordinate!");
				continue;
			}
			
			validCoordinate = true;
			int x = xCoordinate - 65;
			int y = 56 - yCoordinate;
			position = new Position(x, y);
		}
		return position;
	}
	
	public static String displayMovesAsString(List<Move> moves){
		String output = "Possible Moves: \n";
		for(Move move : moves){
			output += "\t("+(moves.indexOf(move) + 1)+") " + move.toString() + '\n';
		}
		
		output += "\t(0) Select other Piece\n";
		LOG.debug("Moves are displayed");
		return output;
	}
}
