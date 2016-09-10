package pieces.impl;

import java.util.ArrayList;
import java.util.List;

import core.ChessBoard;
import core.moves.Capture;
import core.moves.Move;
import pieces.AbstractPiece;
import pieces.PieceType;
import pieces.Player;
import util.Position;

public class Pawn extends AbstractPiece{

	public Pawn(Player player) {
		super(PieceType.PAWN, player);
	}

	//TODO passante type capture
	@Override
	public List<Move> getMoves(ChessBoard chessboard, Position origin) {
		List<Move> moves = new ArrayList<>();
		AbstractPiece pawn = chessboard.getPiece(origin);
		
		if(pawn.getPlayer() == Player.PLAYER_ONE){
			
			Position up = new Position(origin.x, origin.y -1);
			if(!ChessBoard.isOutOfBounds(up)){
				AbstractPiece uppiece = chessboard.getPiece(up);
				if(uppiece == null) 
					moves.add(new Move(pawn, origin, up));
			}
			
			Position upleft = new Position(origin.x - 1, origin.y - 1);
			if(!ChessBoard.isOutOfBounds(upleft)){
				Position left = new Position(origin.x - 1, origin.y);
				AbstractPiece leftpiece = chessboard.getPiece(left);
				AbstractPiece upleftpiece = chessboard.getPiece(upleft);
				if(leftpiece == null && upleftpiece != null && upleftpiece.getPlayer() != pawn.getPlayer()){
					moves.add(new Capture(pawn, origin, upleft, upleftpiece));
				}else if(leftpiece != null && leftpiece.getPlayer() != pawn.getPlayer() && upleftpiece == null){
					moves.add(new Capture(pawn, origin, upleft, leftpiece)); //en passant
				}
			}
			
			Position upright = new Position(origin.x + 1, origin.y - 1);
			if(!ChessBoard.isOutOfBounds(upright)){
				Position right = new Position(origin.x + 1, origin.y);
				AbstractPiece rightpiece = chessboard.getPiece(right);
				AbstractPiece uprightpiece = chessboard.getPiece(upright);
				if(rightpiece == null && uprightpiece != null && uprightpiece.getPlayer() != pawn.getPlayer()){
					moves.add(new Capture(pawn, origin, upleft, uprightpiece));
				}else if(rightpiece != null && rightpiece.getPlayer() != pawn.getPlayer() && uprightpiece == null){
					moves.add(new Capture(pawn, origin, upleft, rightpiece)); //en passant
				}
			}

			if(origin.y == 6 ){
				Position upup = new Position(origin.x, origin.y - 2);
				AbstractPiece upuppiece = chessboard.getPiece(upup);
				if (upuppiece == null) 
					moves.add(new Move(pawn, origin, upup));
			}
			
		}else{
			
			Position down = new Position(origin.x, origin.y +1);
			if(!ChessBoard.isOutOfBounds(down)){
				AbstractPiece downpiece = chessboard.getPiece(down);
				if(downpiece == null) 
					moves.add(new Move(pawn, origin, down));
			}
			
			Position downleft = new Position(origin.x - 1, origin.y + 1);
			if(!ChessBoard.isOutOfBounds(downleft)){
				Position left = new Position(origin.x - 1, origin.y);
				AbstractPiece leftpiece = chessboard.getPiece(left);
				AbstractPiece downleftpiece = chessboard.getPiece(downleft);
				if(leftpiece == null && downleftpiece != null && downleftpiece.getPlayer() != pawn.getPlayer()){
					moves.add(new Capture(pawn, origin, downleft, downleftpiece));
				}else if(leftpiece != null && leftpiece.getPlayer() != pawn.getPlayer() && downleftpiece == null){
					moves.add(new Capture(pawn, origin, downleft, leftpiece)); //en passant
				}
			}
			
			Position downright = new Position(origin.x + 1, origin.y + 1);
			if(!ChessBoard.isOutOfBounds(downright)){
				Position right = new Position(origin.x + 1, origin.y);
				AbstractPiece rightpiece = chessboard.getPiece(right);
				AbstractPiece downrightpiece = chessboard.getPiece(downleft);
				if(rightpiece == null && downrightpiece != null && downrightpiece.getPlayer() != pawn.getPlayer()){
					moves.add(new Capture(pawn, origin, downleft, downrightpiece));
				}else if(rightpiece != null && rightpiece.getPlayer() != pawn.getPlayer() && downrightpiece == null){
					moves.add(new Capture(pawn, origin, downleft, rightpiece)); //en passant
				}
			}

			if(origin.y == 6 ){
				Position downdown = new Position(origin.x, origin.y + 2);
				AbstractPiece downdownpiece = chessboard.getPiece(downdown);
				if (downdownpiece == null) 
					moves.add(new Move(pawn, origin, downdown));
			}
		}
		
		return moves;
	}

	@Override
	public String toString() {
		String visualization = "~";
		
		switch(this.getPlayer()){
		case PLAYER_ONE:
			visualization = "P";
			break;
		case PLAYER_TWO:
			visualization = "p";
			break;
		}
		
		return visualization;
	}
}
