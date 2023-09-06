package exercise;

public class Segment {
    private Point pointBegin;
    private Point pointEnd;

    public Segment(Point point1, Point point2) {
        this.pointBegin = point1;
        this.pointEnd = point2;
    }

    public Point getBeginPoint() {
        return pointBegin;
    }

    public Point getEndPoint() {
        return pointEnd;
    }

    public Point getMidPoint() {
        int x = (pointBegin.getX() + pointEnd.getX()) / 2;
        int y = (pointBegin.getY() + pointEnd.getY()) / 2;
        return new Point(x, y);
    }
}