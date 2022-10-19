package oving5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class CardContainerIterator implements Iterator<Card> {

	private int position = -1;
	private List<Card> cardHand = new ArrayList<Card>();

    
    public CardContainerIterator(CardContainer container) {
        for (int i = 0; i < container.getCardCount(); i++) {
			    cardHand.add(container.getCard(i));
		}
    }
    
    @Override
    public boolean hasNext() {
        return position + 1 < cardHand.size() ? true : false;
    }
    @Override
    public Card next() {
        position ++;
		return position < cardHand.size() ? cardHand.get(position) : null;
    }

    public static void main(String[] args) {
		CardContainerIterator deckOfCards = new CardContainerIterator(new CardDeck(13));
		while (deckOfCards.hasNext()) {
			System.out.println(deckOfCards.next());
		}
	}
}
