import java.util.List;
import java.util.ArrayList;

public class BinarySearch {

	/**
	 * Search a sorted list of strings using binary search. Return a list of
	 * the indices of the strings checked during the search in the order they
	 * are checked. If the target string is not found, append -1 to the end of
	 * the list. Otherwise, the last element is the index of the target.
	 *
	 * @param strings  the list to be searched
	 * @param target  the string to be searched for
	 * @param fromIdx  the index of the first string in the range of strings to
	 *                 be searched (inclusive)
	 * @param toIdx  the index of the last string in the range of strings to be
	 *               searched (inclusive)
	 * @return a list of the indices of the strings checked during the search.
	 *         If the target is not in the list, the last element is -1.
	 */
	public static List<Integer> binarySearch(List<String> strings,
			String target, int fromIdx, int toIdx) {
		
		List<Integer> list = new ArrayList<>();
		if(target.isEmpty()) {
			list.add(-1); // invalid search target
			return list;
		}
		
		int mid = (fromIdx + toIdx)/2;
		list.add(mid);
		if(strings.get(mid).equals(target)) {
			// element found
		}else if(fromIdx == toIdx) {
			list.add(-1); // element was not found at final element
		}else if(strings.get(mid).compareTo(target) > 0) {
			if(mid > fromIdx) { 
				// more room to search
				list.addAll(binarySearch(strings, target, fromIdx, mid-1));
			}else {
				// no room left, element isn't in the list
				list.add(-1);
			}
		}else {
			if(mid < toIdx) {
				list.addAll(binarySearch(strings, target, mid+1, toIdx));
			}else {
				list.add(-1);
			}
		}
		
		return list;
	}
}
