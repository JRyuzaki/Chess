package pieces.impl;

import java.util.ArrayList;
import java.util.List;

import core.ChessBoard;
import core.moves.Capture;
import core.moves.DoubleMove;
import core.moves.EnPessante;
import core.moves.Move;
import core.moves.MoveType;
import core.moves.Upgrade;
import pieces.AbstractPiece;
import pieces.Piece;
import pieces.PieceType;
import pieces.Player;
import util.Position;

public class Pawn extends AbstractPiece{
	private boolean doubleMove = false;
	
	public Pawn(Player player) {
		super(PieceType.PAWN, player);
	}

	@Override
	public List<Move> getMoves(ChessBoard chessboard, Position origin) {
		List<Move> moves = new ArrayList<>();
		AbstractPiece pawn = chessboard.getPiece(origin);
		
		int direction = 1;

		if(pawn.getPlayer() == Player.PLAYER_ONE){
			direction = -1;
		}

		Position forward = new Position(origin.x, origin.y + direction);
		AbstractPiece forwardPiece = chessboard.getPiece(forward);
		if(!ChessBoard.isOutOfBounds(forward) && forwardPiece == null)
			moves.add(new Move(pawn, origin, forward));

		for(int i = -1; i <= 1; i = i + 2){
			Position diagonal = new Position(origin.x + i, origin.y + direction);
			if(ChessBoard.isOutOfBounds(diagonal))
				continue;
			AbstractPiece diagonalPiece = chessboard.getPiece(diagonal);
			Position beside = new Position(origin.x + i, origin.y);
			AbstractPiece besidePiece = chessboard.getPiece(beside);

			if(besidePiece != null && besidePiece.getType() == PieceType.PAWN && besidePiece.getPlayer() != pawn.getPlayer() && diagonalPiece == null){
				Pawn besidePawn = (Pawn)besidePiece;
				if(besidePawn.hasDoubleMove()){
					moves.add(new EnPessante(pawn, origin, diagonal, beside));
				}
			}

			if(diagonalPiece != null && diagonalPiece.getPlayer() != pawn.getPlayer()){
				moves.add(new Capture(pawn, origin, diagonal, diagonalPiece));
			}
		}

		int pawnSpawnY = 1;
		int lastRow = 7;
		if(pawn.getPlayer() == Player.PLAYER_ONE){
			pawnSpawnY = 6;
			lastRow = 0;
		}

		if(origin.y == pawnSpawnY){
			Position doubleForward = new Position(origin.x, origin.y + direction * 2);
			Piece doubleForwardPiece = chessboard.getPiece(doubleForward);
			if(doubleForwardPiece == null){
				moves.add(new DoubleMove(pawn, origin, doubleForward));
			}
		}
		
		for(int i=0;i<moves.size();i++){
			Move move = moves.get(i);
			if(move.getTo().y == lastRow){
				AbstractPiece capturedPiece = null;
				if(move.getType() == MoveType.CAPTURE){
					Capture capture = (Capture) move;
					capturedPiece = capture.getCapturedPiece();
				}
				Upgrade upgrade = new Upgrade(move.getMovedPiece(), move.getFrom(), move.getTo(), null, capturedPiece);
				moves.set(i, upgrade); //replace
			}
		}
		
		return moves;
	}

	public boolean hasDoubleMove() {
		return doubleMove;
	}

	public void setDoubleMove(boolean doubleMove) {
		this.doubleMove = doubleMove;
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
