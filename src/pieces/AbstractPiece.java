package pieces;

import core.moves.Move;

public abstract class AbstractPiece implements Piece{
	private PieceType type;
	private Player player;
	
	public AbstractPiece(PieceType type, Player player){
		this.type = type;
		this.player = player;
	}

	public PieceType getType() {
		return type;
	}

	public void setType(PieceType type) {
		this.type = type;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public boolean isMoveValid(Move move){
		return false; 	//TODO: Implement
	}
	
}
