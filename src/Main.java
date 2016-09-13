/**
 * This class contains the main method and the main game loop..
 * It allows the current player to select a piece, view the possible moves and
 * choose his (valid) move.
 */

import java.util.List;
import java.util.Scanner;

import core.*;
import core.moves.*;
import pieces.*;
import pieces.impl.*;
import util.Position;

public class Main {
	
	static Scanner userScanner;
	static ChessGame chess;
	
	public static void main(String[] args){
		userScanner = new Scanner(System.in);
		
		chess = new ChessGame();
		
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

				Player enemy = (chess.getCurrentTurn() == Player.PLAYER_ONE)?Player.PLAYER_TWO:Player.PLAYER_ONE;
				if(ChessGame.isFieldThreaten(chessboard, chessboard.getPositionOfPiece(chess.getKingForPlayer(enemy)), chess.getCurrentTurn())){
					System.out.println("CHECK");	//TODO: More appealing visual?
				}
				validTurn = true;
			}
			System.out.println("Successfull move");
			chess.nextTurn();
		}
		userScanner.close();
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
		
		output += "\t(0) Select other Piece\n";
		return output;
	}
}
