package oving3;

public class Card {
    private char suit;
    private int face;

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


    
}
