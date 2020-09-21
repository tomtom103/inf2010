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
        return this;
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
        return new ArrayList<>(this.coords);
    }

    // TODO retourner une nouvelle liste ou tous les points sont des copy
    public Collection<Point2d> getCoordsDeepCopy() {
        ArrayList<Point2d> clone = new ArrayList<>();
        for (Point2d item : this.coords) clone.add(item.clone());
        return clone;
    }

    // TODO appliquer la translation sur la forme.
    public BaseShape translate(Point2d point) {
        for(Point2d item : this.coords) {
            item.translate(point);
        }
        return this;
    }

    // TODO appliquer la rotation sur la forme.
    public BaseShape rotate(Double angle) {
        for(Point2d item : this.coords) {
            item.rotate(angle);
        }
        return this;
    }

    // TODO donner la plus grande valeur en X
    public Double getMaxX() {
        return PointOperator.getMaxCoord(this.coords).X();
    }

    // TODO donner la plus grande valeur en Y
    public Double getMaxY() {
        return PointOperator.getMaxCoord(this.coords).Y();
    }

    // TODO donner les plus grandes valeurs en X et Y
    public Point2d getMaxCoord() {
        return PointOperator.getMaxCoord(this.coords);
    }

    // TODO donner la plus petite valeur en X
    public Double getMinX() {
        return PointOperator.getMinCoord(this.coords).X();
    }
    // TODO donner la plus petite valeur en Y
    public Double getMinY() {
        return PointOperator.getMinCoord(this.coords).Y();
    }

    // TODO donner les plus petites valeurs en X et Y
    public Point2d getMinCoord() {
        return PointOperator.getMinCoord(this.coords);
    }

    // TODO retourner une nouvelle forme.
    public BaseShape clone() {
        return new BaseShape(this.coords);
    }
}
