package pieces;

public class Piece {
	private PieceType type;
	private Player player;
	
	public Piece(PieceType type, Player player){
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
	
	@Override
	public String toString() {
		switch(type){
		case KING:
			return "K";
		case QUEEN:
			return "Q";
		case BISHOP:
			return "B";
		case ROOK:
			return "R";
		case PAWN:
			return "P";
		case KNIGHT:
			return "H";
		default:
			return "";
		}
	}
}
