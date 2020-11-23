package tp4;

public class Point implements Comparable<Point> {
    // TODO vous pouvez modifier ce que vous voulez, tant que vous ne modifiez pas les tests

    private final Integer x;
    private final Integer y;
    private Integer idx;
    private Integer distance;

    public Point(String xy) {
        String[] xAndY = xy.split(" +");
        this.x = Integer.parseInt(xAndY[0]);
        this.y = Integer.parseInt(xAndY[1]);
    }

    public Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public void setIdx(int idx) { this.idx = idx; }

    public int getIdx() { return this.idx; }

    @Override
    public String toString() {
        return String.format("{X: %d, Y: %d}", x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) {
            return false;
        }

        Point point = (Point)obj;
        return point.x.equals(x) && point.y.equals(y);
    }

    public void manhattanDistance(Point point) {
        this.distance =  Math.abs(this.x - point.x) + Math.abs(this.y - point.y);
    }

    @Override
    public int compareTo(Point point) {
        // TODO ceci vous sera peut etre utile
        if(point.distance < this.distance || (point.distance == this.distance && point.idx < this.idx)) {
            return 1;
        } else {
            return -1;
        }
    }
}
