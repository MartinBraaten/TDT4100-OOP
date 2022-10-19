package oving5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Card implements Comparable<Card> {
    private char suit;
    private int face;
    private List<Character> suitPriority = new ArrayList<Character>(Arrays.asList('S', 'H', 'D', 'C'));


    public Card(char suit, int face) {
        if (suit == 'S' || suit == 'H' || suit == 'D' || suit == 'C') {
            this.suit = suit;
        } else {
            throw new IllegalArgumentException("Wrong suit.");
        }
        if (1 <= face && face <=13){
            this.face = face;
        } else {
            throw new IllegalArgumentException("Wrong face.");
        }
    }

    public char getSuit() {
        return suit;
    }
    public int getFace() {
        return face;
    }

    @Override
    public String toString() {
        return ""+suit + face+"";
    }

/*  spar kommer etter hjerter
    hjerter kommer etter ruter
    ruter kommer etter kløver
    Ved lik kortfarge skal verdien brukes i stigende rekkefølge, altså 1 (ess) kommer før 2 */

    @Override
    public int compareTo(Card o) {
        // kløver > ruter > hjerter > spar
        // C > D > H > S
		if (suitPriority.indexOf(suit) > suitPriority.indexOf(o.suit)) {
			return 1;
		}
		else if (suitPriority.indexOf(suit) < suitPriority.indexOf(o.suit)) {
			return -1;
		}
		else {
			return face > o.face ? 1 : -1;
		}
    }
}
