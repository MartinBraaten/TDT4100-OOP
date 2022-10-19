package oving3;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {

    private List<Card> deckOfCards = new ArrayList<Card>();

    public CardDeck(int n) {
        if (n<0 || n>13) {
            throw new IllegalArgumentException("n cannot be a negative value or larger than 13.");
        } else {
            for (int i = 1; i < n+1; i++) {
                deckOfCards.add(new Card('S',i));
            }
            for (int i = 1; i < n+1; i++) {
                deckOfCards.add(new Card('H',i));
                //System.out.println(deckOfCards);
            }
            for (int i = 1; i < n+1; i++) {
                deckOfCards.add(new Card('D',i));
            }
            for (int i = 1; i < n+1; i++) {
                deckOfCards.add(new Card('C',i));
            }
        }

    }
    

    public int getCardCount() {
        return deckOfCards.size();
    }
    public Card getCard(int n) {
        return deckOfCards.get(n);
    }
    public void shufflePerfectly() {
        int half = deckOfCards.size()/2;
        List<Card> topHalf = deckOfCards.subList(deckOfCards.size()/2, deckOfCards.size());
        List<Card> bottomHalf = deckOfCards.subList(0, deckOfCards.size()/2);
        //System.out.println("tophalf = " +topHalf);
        //System.out.println("bottomhalf = "+bottomHalf);
        List<Card> tempList = new ArrayList<>(deckOfCards);

        for (int i = 0; i < half; i++) {
            tempList.set((i*2),bottomHalf.get(i));
            tempList.set((i*2+1),topHalf.get(i));
        }
        deckOfCards = tempList;
    }



    @Override
    public String toString() {
        return "deckOfCards=" + deckOfCards + "";
    }


    public static void main(String[] args) {
        CardDeck a = new CardDeck(3);
        System.out.println(a);
        a.shufflePerfectly();
        System.out.println(a);
    }
}
