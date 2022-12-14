package oving7;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CardDeck extends CardContainerImpl {

	// array to hold Card objects, filled in the constructor
	private ArrayList<Card> cards;

	public CardDeck(int suitSize) {
		cards = new ArrayList<Card>();
		for (int i = 0; i < Card.SUITS.length(); i++) {
			for (int face = 1; face <= suitSize; face++) {
				Card card = new Card(Card.SUITS.charAt(i), face);
				cards.add(card);
			}
		}
	}

	@Override
	public String toString() {
		return "[Deck " + cards.toString().substring(1);
	}

	public void deal(CardHand hand, int handSize) {
		for (int i = 0; i < handSize; i++) {
			hand.addCard(cards.remove(cards.size() - 1));
		}
	}

	public int getCardCount() {
		return cards.size();
	}

	public Card getCard(int i) {
		if (i < 0 || i >= getCardCount()) {
			throw new IllegalArgumentException(
					String.format("%s is an illegal card index, when the size of the deck is %s", i, getCardCount()));
		}
		return cards.get(i);
	}

	public void shufflePerfectly() {
		int halfSize = cards.size() / 2;
		for (int i = 0; i < halfSize; i++) {
			Card card = cards.remove(halfSize + i);
			cards.add(i * 2 + 1, card);
		}
	}

	// methods using Predicate<Card>

	public boolean hasCard(Predicate<Card> predicate) {
		// Streams solution:
		// return this.cards.stream().anyMatch(predicate);

		// Manual solution
		for (Card card : cards) {
			if (predicate.test(card)) {
				// Avoid looping through the rest if we have found a match
				return true;
			}
		}

		return false;
	}

	public int getCardCount(Predicate<Card> predicate) {
		// Streams solution:
		// return (int) this.cards.stream().filter(predicate).count();

		// Manual solution
		int count = 0;
		for (Card card : cards) {
			if (predicate.test(card)) {
				count++;
			}
		}

		return count;
	}

	public List<Card> getCards(Predicate<Card> predicate) {
		// Streams solution:
		// return this.cards.stream().filter(predicate).collect(Collectors.toList());

		// Manual solution
		List<Card> matchingCards = new ArrayList<Card>();
		for (Card card : cards) {
			if (predicate.test(card)) {
				matchingCards.add(card);
			}
		}

		return matchingCards;
	}

	@Override
	public Iterator<Card> iterator() {
		return cards.iterator();
	}

}
