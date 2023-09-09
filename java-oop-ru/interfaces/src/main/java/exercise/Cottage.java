package exercise;

import static java.lang.String.format;

public class Cottage implements Home {

    public double area;
    public int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public int compareTo(Home another) {
        return (int) (this.getArea() - another.getArea());
    }

    @Override
    public String toString() {
        return format("%d этажный коттедж площадью %.1f метров", floorCount, area);
    }
}
