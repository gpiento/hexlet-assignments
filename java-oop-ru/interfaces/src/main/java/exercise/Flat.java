package exercise;

import static java.lang.String.format;

public class Flat implements Home {

    public double area;
    public double balconyArea;
    public int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    @Override
    public double getArea() {
        return area + balconyArea;
    }

    @Override
    public int compareTo(Home another) {
        return (int) (this.getArea() - another.getArea());
    }

    @Override
    public String toString() {
        return format("Квартира площадью %.1f метров на %d этаже", this.getArea(), floor);
    }
}
