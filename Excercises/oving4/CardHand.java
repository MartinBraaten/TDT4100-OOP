package oving4;

import java.util.ArrayList;
import java.util.List;

public class CardHand {
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

    @Override
    public String toString() {
        return "CardHand=" + cardHand;
    }


    public static void main(String[] args) {

    }
    

}
