import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CribbageHand {
	public static final Map<Rank, Integer> CARD_VALUES = setupValues();
	public final List<Card> cards;
	
	public static Map<Rank, Integer> setupValues() {
		return Map.ofEntries(
			Map.entry(Rank.ACE, 1),
			Map.entry(Rank.TWO, 2),
			Map.entry(Rank.THREE, 3),
			Map.entry(Rank.FOUR, 4),
			Map.entry(Rank.FIVE, 5),
			Map.entry(Rank.SIX, 6),
			Map.entry(Rank.SEVEN, 7),
			Map.entry(Rank.EIGHT, 8),
			Map.entry(Rank.NINE, 9),
			Map.entry(Rank.TEN, 10),
			Map.entry(Rank.JACK, 10),
			Map.entry(Rank.QUEEN, 10),
			Map.entry(Rank.KING, 10)
		);
	}
	
	public CribbageHand(Card c1, Card c2, Card c3, Card c4) throws NullPointerException{
		if(c1 == null || c2 == null || c3 == null || c4 == null) {
			throw new NullPointerException();
		}
		cards = List.of(c1, c2, c3, c4);
	}
	
	public Set<Set<Card>> fifteens(Card starter){
		List<Card> list = new ArrayList<>(cards);
		list.add(0, starter);
		
		Set<Set<Card>> sets = powerSet(list);
		Iterator<Set<Card>> itr = sets.iterator();
		
		while(itr.hasNext()) {
			Set<Card> set = itr.next();
			int sum = 0;
			
			for(Card card : set) {
				if(card == null) 
					continue;
				sum += CARD_VALUES.get(card.getRank()); // calc sum of set
			}
			
			if(sum != 15) {
				itr.remove(); // remove sets that != 15
			}
		}
		return sets;
	}
	
	public static Set<Set<Card>> powerSet(List<Card> cards){
		Set<Set<Card>> sets = new HashSet<Set<Card>>();
		
		sets.add(new HashSet<>());
		for(Card card : cards) {
			Set<Set<Card>> newSets = new HashSet<>();
			for(Set<Card> subset : sets) {
				Set<Card> newSubset = new HashSet<>(subset);
				newSubset.add(card);
				newSets.add(newSubset);
			}
			sets.addAll(newSets);
		}
		
		return sets;
	}
}
