
public enum Move {
	LEFT_TO_MIDDLE(Peg.LEFT, Peg.MIDDLE),
	LEFT_TO_RIGHT(Peg.LEFT, Peg.RIGHT),
	MIDDLE_TO_LEFT(Peg.MIDDLE, Peg.LEFT),
	MIDDLE_TO_RIGHT(Peg.MIDDLE, Peg.RIGHT),
	RIGHT_TO_LEFT(Peg.RIGHT, Peg.LEFT),
	RIGHT_TO_MIDDLE(Peg.RIGHT, Peg.MIDDLE);
	
	public final Peg from;
	public final Peg to;

	private Move(Peg from, Peg to) {
		this.from = from;
		this.to = to;
	}
	
	public static Move move(Peg from, Peg to) throws IllegalArgumentException, NullPointerException{
		if(from.equals(to))
			throw new IllegalArgumentException();
		if(from == null || to == null)
			throw new NullPointerException();
		
		if(from.equals(Peg.LEFT)) {
			if(to.equals(Peg.MIDDLE))
				return Move.LEFT_TO_MIDDLE;
			return Move.LEFT_TO_RIGHT;
		}else if(from.equals(Peg.RIGHT)) {
			if(to.equals(Peg.MIDDLE))
				return Move.RIGHT_TO_MIDDLE;
			return Move.RIGHT_TO_LEFT;
		}else {
			if(to.equals(Peg.LEFT))
				return Move.MIDDLE_TO_LEFT;
			return Move.MIDDLE_TO_RIGHT;
		}
	}
	
}
