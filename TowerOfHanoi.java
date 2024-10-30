import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TowerOfHanoi {
	private static Map<Peg, Deque<Integer>> diskStacks;
	
	public TowerOfHanoi(int numDisks, Peg start) throws IllegalArgumentException, NullPointerException{
		if(numDisks <= 0)
			throw new IllegalArgumentException();
		if(start == null)
			throw new NullPointerException();
		
		initializeDisks(numDisks, start);
	}
	
	private void initializeDisks(int numDisks, Peg start) {
        diskStacks = new HashMap<>();
        diskStacks.put(Peg.MIDDLE, new ArrayDeque<>());
        diskStacks.put(Peg.LEFT, new ArrayDeque<>());
        diskStacks.put(Peg.RIGHT, new ArrayDeque<>());

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = numDisks; i > 0; i--) {
            stack.push(i);
        }
        diskStacks.put(start, stack);
    }
	
	public Deque<Integer> getDiskStack(Peg peg) {
		Deque<Integer> cloneStack = new ArrayDeque<>();
		cloneStack.addAll(diskStacks.get(peg));
		return cloneStack;
	}
	
	public void moveDisk(Move move) throws NullPointerException, IllegalArgumentException{
		if(move == null) 
			throw new NullPointerException();
		
		Deque<Integer> stackFrom = diskStacks.get(move.from);
		Deque<Integer> stackTo = diskStacks.get(move.to);
		
		int fromFirst = stackFrom.isEmpty() ? 999 : stackFrom.getFirst();
		int toFirst = stackTo.isEmpty() ? 999 : stackTo.getFirst();
		
		if(stackFrom.isEmpty() || fromFirst > toFirst)
			throw new IllegalArgumentException();
		
		stackTo.addFirst(stackFrom.removeFirst());
	}
	
	public static List<Move> solve(int numDisks, Peg start, Peg end) throws IllegalArgumentException, NullPointerException{
		List<Move> moves = new ArrayList<Move>();

		if(numDisks < 0)
			throw new IllegalArgumentException();
		if(start == null || end == null)
			throw new NullPointerException();
		if(numDisks == 0 || start.equals(end)) {
			return new ArrayList<Move>();
		}
		
		Peg other = Peg.other(start, end);
		if(numDisks == 1) {
			moves.add(Move.move(start, end));
		}else if(numDisks >= 2) {
			moves.addAll(solve(numDisks - 1, start, other));
			moves.add(Move.move(start, end));
			moves.addAll(solve(numDisks - 1, other, end));
		}

		return moves;
	}
	
	private String reverseStack(Peg peg) {
		Object[] moves = diskStacks.get(peg).toArray();
		String str = "[";
		for(int i = moves.length-1; i >= 0; i--) {
			str += moves[i] + (i == 0 ? "" : ", ") ;
		}
	    return str + "]";
	}

	public String toString() {
		String gameState = "" +
				"  LEFT: " + reverseStack(Peg.LEFT) + System.lineSeparator() +
				"MIDDLE: " + reverseStack(Peg.MIDDLE) + System.lineSeparator() +
				" RIGHT: " + reverseStack(Peg.RIGHT);
		return gameState;
	}
}
