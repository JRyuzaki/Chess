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

public class Knight extends AbstractPiece{

	public Knight(Player player) {
		super(PieceType.KNIGHT, player);
	}

	@Override
	public List<Move> getMoves(ChessBoard chessboard, Position origin) {
		AbstractPiece knight = chessboard.getPiece(origin);
		
		List<Move> moves = new ArrayList<>();
		
		for(int i = -1; i <= 1; i = i + 2){
			for(int b = -1; b <= 1; b = b + 2){
				Position horizontalJump = new Position(origin.x + i * 2, origin.y + b);
				
				if(!ChessBoard.isOutOfBounds(horizontalJump)){
					AbstractPiece horizontalJumpPiece = chessboard.getPiece(horizontalJump);
					if(horizontalJumpPiece == null){
						moves.add(new Move(knight, origin, horizontalJump));
					}else{
						if (horizontalJumpPiece.getPlayer() != knight.getPlayer()) moves.add(new Capture(knight, origin, horizontalJump, horizontalJumpPiece));
					}
				}
				
				Position verticalJump = new Position(origin.x + b, origin.y + i * 2);
				if(!ChessBoard.isOutOfBounds(verticalJump)){
					AbstractPiece verticalJumpPiece = chessboard.getPiece(verticalJump);
					if(verticalJumpPiece == null){
						moves.add(new Move(knight, origin, verticalJump));
					}else{
						if (verticalJumpPiece.getPlayer() != knight.getPlayer()) moves.add(new Capture(knight, origin, verticalJump, verticalJumpPiece));
					}
				}
			}
		}
		return moves;
	}
	
	@Override
	public String toString() {
		String visualization = "~";
		
		switch(this.getPlayer()){
		case PLAYER_ONE:
			visualization = "N";
			break;
		case PLAYER_TWO:
			visualization = "n";
			break;
		}
		
		return visualization;
	}
}
