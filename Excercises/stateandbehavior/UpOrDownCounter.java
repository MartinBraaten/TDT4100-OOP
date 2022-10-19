package stateandbehavior;

public class UpOrDownCounter {
    int start;
    int end;
    int counter;
    int increment;

    public UpOrDownCounter(int start, int end) {
        counter = start;
        this.start = start;
        this.end = end;

        if (start>end) {
            increment = -1;
        } else if(start==end) {
            throw new IllegalArgumentException("Start and end are equal");
        } else {
            increment = 1;
        }
    }

    public int getCounter() {
        return counter;
    }

    public boolean count() {
        if (counter == end) {
            return false;
        } else {
            counter += increment;
            return true;
        } 
    }

    @Override
    public String toString() {
        return String.valueOf(counter);
    }

    public static void main(String[] args) {
        UpOrDownCounter qwe = new UpOrDownCounter(0, 5);
        qwe.count();
        qwe.count();
        qwe.count();
        System.out.println(qwe);
        qwe.count();
        qwe.count();
        qwe.count();
        System.out.println(qwe);
        UpOrDownCounter qwe1 = new UpOrDownCounter(0, 0);
    }


}
