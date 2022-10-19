package stateandbehavior;


public class Digit {
    int tallsystem;
    int siffer;
    String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Digit(int tallsystem) {
        if (tallsystem < 37) {
            this.tallsystem = tallsystem;
        }
    }

    public int getValue() {
        return siffer;
    }
    public int getBase() {
        return tallsystem;
    }
    public boolean increment() {
        siffer +=1;
        if (siffer == tallsystem) {
            siffer = 0;
            return true;
        }
        else {
            return false;
        }
    }


    @Override
    public String toString() {
        if (siffer < 10) {
            return String.valueOf(siffer);
        } else {
            char ch = s.charAt(siffer-10);
            return String.valueOf(ch);
        }
        
    }

    public static void main(String[] args) {
        Digit ab = new Digit(16);
        System.out.println(ab);
        ab.increment();
        ab.increment();
        ab.increment();
        ab.increment();
        ab.increment();
        System.out.println(ab);
        ab.increment();
        ab.increment();
        ab.increment();
        ab.increment();
        ab.increment();
        ab.increment();
        ab.increment();
        ab.increment();
        ab.increment();
        ab.increment();
        System.out.println(ab);
        ab.increment();
        System.out.println(ab);

    }

}
