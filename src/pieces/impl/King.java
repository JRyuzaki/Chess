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

public class King extends AbstractPiece{
	public King(Player player) {
		super(PieceType.KING, player);
	}

	@Override
	public List<Move> getMoves(ChessBoard chessboard, Position origin) {
		AbstractPiece king = chessboard.getPiece(origin);
		
		List<Move> moves = new ArrayList<>();
		
		for(int y = -1; y <= 1; ++y){
			for(int x = -1; x <= 1; ++x){
				Position newPosition = new Position(origin.x + x, origin.y + y);
				if(!ChessBoard.isOutOfBounds(newPosition) && !origin.equals(newPosition)){
					AbstractPiece piece = chessboard.getPiece(newPosition);
					if(piece == null){
						moves.add(new Move(king, origin, newPosition));
					}else{
						if(piece.getPlayer() != king.getPlayer())
							moves.add(new Capture(king, origin, newPosition, piece));
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
			visualization = "K";
		case PLAYER_TWO:
			visualization = "k";
		}
		
		return visualization;
	}
}
