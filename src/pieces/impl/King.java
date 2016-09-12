package pieces.impl;

import java.util.ArrayList;
import java.util.List;

import core.ChessBoard;
import core.moves.Capture;
import core.moves.Move;
import core.moves.Rochade;
import pieces.AbstractPiece;
import pieces.PieceType;
import pieces.Player;
import util.Position;

public class King extends AbstractPiece{
	private boolean hasMoved;
	
	public King(Player player) {
		super(PieceType.KING, player);
		hasMoved = false;
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
		
		//Rochade moves if available 
		//TODO: check if king is in check before all this
		int firstRow = 0;
		if(king.getPlayer() == Player.PLAYER_ONE){
			firstRow = 7;
		}
		King k = (King)king;
		if(origin.y == firstRow && origin.x == 4 && !k.getHasMoved() ) { //king is at starting position and hasnt moved
			AbstractPiece leftRook = chessboard.getPiece(new Position(0, origin.y));
			if(leftRook != null && leftRook.getType() == PieceType.ROOK){ //there is a rook there
				Rook rook = (Rook) leftRook;
				if(!rook.getHasMoved()) { //which hasnt moved
					AbstractPiece[] betweenPieces = new AbstractPiece[3];
					betweenPieces[0] = chessboard.getPiece(new Position(1, origin.y));
					betweenPieces[1] = chessboard.getPiece(new Position(2, origin.y));
					betweenPieces[2] = chessboard.getPiece(new Position(3, origin.y));
					if(betweenPieces[0]==null && betweenPieces[1]==null && betweenPieces[2]==null) { //and there is nothing between them
						moves.add(new Rochade(king, origin, new Position(2, origin.y), leftRook, new Position(0, origin.y), new Position(3, origin.y)));
					}
				}
			}
			AbstractPiece rightRook = chessboard.getPiece(new Position(7, origin.y));
			if(rightRook != null && rightRook.getType() == PieceType.ROOK){
				Rook rook = (Rook) rightRook;
				if(!rook.getHasMoved()) {
					AbstractPiece[] betweenPieces = new AbstractPiece[2];
					betweenPieces[0] = chessboard.getPiece(new Position(5, origin.y));
					betweenPieces[1] = chessboard.getPiece(new Position(6, origin.y));
					if(betweenPieces[0]==null && betweenPieces[1]==null ) {
						moves.add(new Rochade(king, origin, new Position(6, origin.y), rightRook, new Position(7, origin.y), new Position(5, origin.y)));
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
			break;
		case PLAYER_TWO:
			visualization = "k";
			break;
		}
		
		return visualization;
	}

	public boolean getHasMoved() {
		return hasMoved;
	}

	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}
}
