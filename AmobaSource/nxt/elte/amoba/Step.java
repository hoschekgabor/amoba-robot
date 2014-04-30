package nxt.elte.amoba;

public class Step {
	private int row;
	private int column;
	private PlayerEnum player;
	
	public Step(int row, int column, PlayerEnum player) {
		this.row = row;
		this.column = column;
		this.player = player;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + ((player == null) ? 0 : player.hashCode());
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Step other = (Step) obj;
		if (column != other.column)
			return false;
		if (player != other.player)
			return false;
		if (row != other.row)
			return false;
		return true;
	}
	
	public void setPlayer(PlayerEnum player) {
		this.player = player;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public PlayerEnum getPlayer() {
		return player;
	}
	
}