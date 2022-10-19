package oving5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CardComparator implements Comparator<Card>{

    private List<Character> suitPriority = new ArrayList<Character>(Arrays.asList('S', 'H', 'D', 'C'));
    private boolean acehighest;
    private char trumf;
  
    
    public CardComparator(boolean acehighest, char trumf) {
        this.acehighest = acehighest;
        this.trumf = trumf;
    }


    @Override
    public int compare(Card o1, Card o2) {
        if (acehighest) {
            
            return o1.getFace() > o2.getFace() ? 1 : -1;
            

        } 
        if (trumf == 'D') {
            List<Character> suitPriority = new ArrayList<Character>(Arrays.asList('S', 'H', 'C', 'D'));
            compare1(o1, o2);

        }
        if (trumf == 'H') {
            List<Character> suitPriority = new ArrayList<Character>(Arrays.asList('S', 'D', 'C', 'H'));
            compare1(o1, o2);
            
        }
        if (trumf == 'S') {
            List<Character> suitPriority = new ArrayList<Character>(Arrays.asList('H', 'D', 'C', 'S'));
            compare1(o1, o2);
        }
        if (trumf == 'C') {
            compare1(o1, o2);
        }
        else {
            compare1(o1, o2);
        }

        // return o1.compareTo(o2); //.getSuit?
    }

    
    public int compare1(Card o1, Card o2) {
        // kløver > ruter > hjerter > spar
        // C > D > H > S
		if (suitPriority.indexOf(o1.getSuit()) > suitPriority.indexOf(o2.getSuit())) {
			return 1;
		}
		else if (suitPriority.indexOf(o1.getSuit()) < suitPriority.indexOf(o2.getSuit())) {
			return -1;
		}
        else return 0;
    }


    public static void main(String[] args) {
        //Collection.sort(card, new CardComparator());
    }
}
