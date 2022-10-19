package oving5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CardDeck implements CardContainer, Iterable<Card> {

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

    public void deal(CardHand cardHand, int n) {
        for (int i = 0; i < n; i++) {
            cardHand.addCard(deckOfCards.get(deckOfCards.size() - 1));
            deckOfCards.remove(deckOfCards.size() - 1);
        }
    }

    public Iterator<Card> iterator () {
        return deckOfCards.iterator();
    }

    @Override
    public String toString() {
        return "deckOfCards=" + deckOfCards + "";
    }


    public boolean hasCard(Predicate<Card> predicate) {
        Collection<Card> cards = deckOfCards.stream().filter((element) -> predicate.test(element)).collect(Collectors.toList());
        if (cards.size()>0) return true;
        else return false;
    }

    public int getCardCount(Predicate<Card> predicate) {
        Collection<Card> cards = deckOfCards.stream().filter((element) -> predicate.test(element)).collect(Collectors.toList());
        return cards.size();
    }

    public List<Card> getCards(Predicate<Card> predicate) {
        Collection<Card> cards = deckOfCards.stream().filter((element) -> predicate.test(element)).collect(Collectors.toList());
        List<Card> cards2 = new ArrayList<>(cards);
        return cards2;
    }






    public static void main(String[] args) {
        CardDeck a = new CardDeck(13);
        System.out.println(a);
        a.shufflePerfectly();
        System.out.println(a);
        Predicate<Card> asd = c -> c.getFace() == 12 & c.getSuit() == 'S';
        a.hasCard(asd);
        System.out.println(a.hasCard(asd));
        a.getCardCount(asd);
        a.getCards(asd);
        System.out.println(a.getCardCount(asd));
        System.out.println(a.getCards(asd));
        Predicate<Card> hjerter = c -> c.getSuit() == 'H';
        a.getCardCount(hjerter);
        System.out.println(a.getCardCount(hjerter));
        Predicate<Card> ess = c -> c.getFace() == 1;
        a.getCards(ess);
        System.out.println(a.getCards(ess));
    }






}