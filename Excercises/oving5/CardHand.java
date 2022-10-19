package oving5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CardHand implements CardContainer, Iterable<Card> {
    private List<Card> cardHand = new ArrayList<Card>();
    


    public CardHand() {
    }

    public CardHand(List<Card> cardHand) {
        this.cardHand = cardHand;
    }

    public int getCardCount() {
        return cardHand.size();
    }
    public Card getCard(int n) {
        return cardHand.get(n);
    }
    public void addCard(Card card) {
        cardHand.add(card);
    }
    public Card play(int n) {
        Card playcard = cardHand.get(n);
        cardHand.remove(n);
        return playcard;
    }

    
    public Iterator<Card> iterator() {
        return cardHand.iterator();
    }

    @Override
    public String toString() {
        return "CardHand=" + cardHand;
    }


    public static void main(String[] args) {

    }
    

}