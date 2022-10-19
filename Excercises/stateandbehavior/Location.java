package stateandbehavior;

public class Location {
    double x;
    double y;

    public void up() {
        y -= 1;
    }
    public void down() {
        y += 1;
    }
    public void left() {
        x -= 1;
    }
    public void right() {
        x += 1;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public Location() {

    }
    @Override
    public String toString() {
        
        return "x = " + x + ", y = " + y;
    }

    public static void main(String[] args) {
        Location asd1 = new Location();
        System.out.println(asd1);
        asd1.down();
        asd1.down();
        asd1.right();
        asd1.getX();
        asd1.getY();
        System.out.println(asd1);
        
    }
}
