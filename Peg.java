
public enum Peg {
	LEFT,
	MIDDLE,
	RIGHT;
	
	public static Peg other(Peg p1, Peg p2) throws IllegalArgumentException, NullPointerException{
		if(p1.equals(p2))
			throw new IllegalArgumentException();
		if(p1 == null || p2 == null)
			throw new NullPointerException();
		
		return LEFT.equals(p1) || LEFT.equals(p2) ? (MIDDLE.equals(p1) || MIDDLE.equals(p2) ? RIGHT : MIDDLE) : LEFT;
	}
}
