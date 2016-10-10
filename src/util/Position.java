package util;

public class Position {
	public int x;
	public int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean equals(Position other) {
		return x == other.x && y == other.y;
	}

	@Override
	public String toString() {
		return "[" + (char) (x + 'A') + "," + (8 - y) + "]";
	}

}
