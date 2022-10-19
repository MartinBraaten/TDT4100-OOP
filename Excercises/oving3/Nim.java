package oving3;

public class Nim {
    private int pile1;
    private int pile2;
    private int pile3;

    public void removePieces(int number, int targetPile) {
        if (isValidMove(number, targetPile) == true) {
            getPile(targetPile) -= number;
        } else {
            throw new IllegalArgumentException("Cannot remove pieces.");
        }
        //targetPile -= number;
    }

    public boolean isValidMove(int number, int targetPile) {
        if (isGameOver() == true) {
            return false;
        } else if (targetPile != 0 || targetPile != 1 || targetPile != 2){
            //throw new IllegalArgumentException("Not valid targetpile. Must be 0, 1 or 2.");
            return false;
        } else if (number < 1){
            //throw new IllegalArgumentException("Not valid number. Must be >=1.");
            return false;
        } else if (getPile(targetPile) - number <= 0) {
            throw new IllegalStateException("Game over.");
        } else {
            throw new IllegalArgumentException("??");
        }
        
        
    }

    public boolean isGameOver() {
        if (pile1 <= 0 || pile2 <= 0 || pile3 <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public int getPile(int targetPile) {
        if (targetPile == 0) {
            return pile1;
        } else if (targetPile == 1) {
            return pile2;
        } else {
            return pile3;
        }
    }

    public Nim(int pileSize) {
        this.pile1 = pileSize;
        this.pile2 = pileSize;
        this.pile3 = pileSize;
    }
    public Nim() {
        this.pile1 = 10;
        this.pile2 = 10;
        this.pile3 = 10;
    }


    @Override
    public String toString() {

        return "pile 1 = " + pile1 +", pile 2 = " + pile2 +", pile 3 = " + pile3;
    }

    public static void main(String[] args) {
        Nim a = new Nim();
        System.out.println(a);
    }
    



}
