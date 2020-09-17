package tp1;

import java.util.*;
import java.util.stream.Collectors;

public class BaseShape implements Cloneable {
    private Collection<Point2d> coords;

    // TODO Initialiser la liste de points.
    public BaseShape() {
       this.coords = new ArrayList<>();
    }

    // TODO prendre une liste de points et creer une nouvelle forme.
    public BaseShape(Collection<Point2d> coords) {
        this.coords = new ArrayList<>(coords);
    }

    // TODO ajouter ou retirer des coordonnees a la liste de points.
    public BaseShape add(Point2d coord) {
        this.coords.add(coord);
        return this;
    }
    public BaseShape add(BaseShape shape) {
        coords.addAll(shape.getCoords());
        return this;
    }
    public BaseShape addAll(Collection<Point2d> coords) {
        this.coords.addAll(coords);
        return null;
    }
    public BaseShape remove(Point2d coord) {
        this.coords.remove(coord);
        return this;
    }
    public BaseShape remove(BaseShape shape) {
        this.coords.removeAll(shape.getCoords());
        return this;
    }
    public BaseShape removeAll(Collection<Point2d> coords) {
        this.coords.removeAll(coords);
        return this;
    }

    // TODO retourner les coordonnees de la liste.
    public Collection<Point2d> getCoords() {
        return this.coords;
    }

    // TODO retourner une nouvelle liste ou tous les points sont des copy
    public Collection<Point2d> getCoordsDeepCopy() {
        ArrayList<Point2d> clone = new ArrayList<Point2d>(this.coords.size());
        for (Point2d item : this.coords) clone.add(item.clone());
        return clone;
    }

    // TODO appliquer la translation sur la forme.
    public BaseShape translate(Point2d point) {
        for(Point2d item : this.coords) {
            PointOperator.translate(item.vector, point.vector);
        }
        return this;
    }

    // TODO appliquer la rotation sur la forme.
    public BaseShape rotate(Double angle) {
        Double[][] rotationMatrix = new Double[][]
                {
                        {Math.cos(angle), -Math.sin(angle)},
                        {Math.sin(angle), Math.cos(angle)}
                };
        for(Point2d item : this.coords) {
            PointOperator.rotate(item.vector, rotationMatrix);
        }
        return this;
    }

    // TODO donner la plus grande valeur en X
    public Double getMaxX() {
        Point2d maxPoint = PointOperator.getMaxCoord(this.coords);
        return maxPoint.X();
    }

    // TODO donner la plus grande valeur en Y
    public Double getMaxY() {
        Point2d maxPoint = PointOperator.getMaxCoord(this.coords);
        return maxPoint.Y();
    }

    // TODO donner les plus grandes valeurs en X et Y
    public Point2d getMaxCoord() {
        Point2d maxPoint = PointOperator.getMaxCoord(this.coords);
        return maxPoint;
    }

    // TODO donner la plus petite valeur en X
    public Double getMinX() {
        Point2d minPoint = PointOperator.getMinCoord(this.coords);
        return minPoint.X();
    }
    // TODO donner la plus petite valeur en Y
    public Double getMinY() {
        Point2d minPoint = PointOperator.getMinCoord(this.coords);
        return minPoint.Y();
    }

    // TODO donner les plus petites valeurs en X et Y
    public Point2d getMinCoord() {
        Point2d minPoint = PointOperator.getMinCoord(this.coords);
        return minPoint;
    }

    // TODO retourner une nouvelle forme.
    public BaseShape clone() {
        return new BaseShape();
    }
}
