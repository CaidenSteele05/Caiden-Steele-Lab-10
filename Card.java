import java.util.Objects;

public class Card implements Comparable<Card> {
	
	private Rank rank;
	private Suit suit;
	
	public Card(Rank rank, Suit suit) throws NullPointerException{
		if(rank == null || suit == null) {
			throw new NullPointerException();
		}
		this.rank = rank;
		this.suit = suit;
	}

	@Override
	public int compareTo(Card other) {
		int suitC = this.suit.compareTo(other.suit);
		int rankC = this.rank.compareTo(other.rank);
		
		if(suitC == 0 && rankC == 0) {
			return 0;
		}else if(suitC != 0) {
			return suitC;
		}
		return rankC;
	}
	
	public boolean equals(Object obj) {
		if(obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		return this.compareTo((Card) obj) == 0;
	}
	
	public Rank getRank() {
		return rank;
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	public int hashCode() {
		return Objects.hash(rank, suit);
	}
	
	public String toString() {
		return rank.toString() + suit.toString();
	}
}
