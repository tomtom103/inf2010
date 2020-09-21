package tp1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Rectangle extends BaseShape {

    // TODO creer un rectangle avec une largeur et une longueur.
    public Rectangle(Double width, Double height) {
        super();
        // Plus la precision est grande et plus le programme va dessiner de points
        for(Double i = 0.0; i < (width / 2); i++) {
            for(Double j = 0.0; j < (height / 2); j++) {
                super.add(new Point2d(new Double[]{i, j}));
                super.add(new Point2d(new Double[]{-i, j}));
                super.add(new Point2d(new Double[]{i, -j}));
                super.add(new Point2d(new Double[]{-i, -j}));
            }
        }

    }

    // TODO creer un rectangle avec les dimensions contenu dans un Point.
    public Rectangle(Point2d dimensions) {
        super();
        Double width = dimensions.X();
        Double height = dimensions.Y();

        for(Double i = 0.0; i < (width / 2); i++) {
            for(Double j = 0.0; j < (height / 2); j++) {
                super.add(new Point2d(new Double[]{i, j}));
                super.add(new Point2d(new Double[]{-i, j}));
                super.add(new Point2d(new Double[]{i, -j}));
                super.add(new Point2d(new Double[]{-i, -j}));
            }
        }
    }

    // TODO initialiser le parent.
    private Rectangle(Collection<Point2d> coords) {
        super(coords);
    }

    // TODO appliquer la translation sur la forme.
    @Override
    public Rectangle translate(Point2d point) {
        for(Point2d item : super.getCoords()) {
            item.translate(point.vector);
        }
        return this;
    }

    // TODO appliquer la rotation sur la forme.
    @Override
    public Rectangle rotate(Double angle) {
        for(Point2d item : super.getCoords()) {
            item.rotate(angle);
        }
        return this;
    }

    // TODO retourner une nouvelle forme.
    @Override
    public Rectangle clone() {
        return new Rectangle(super.getCoordsDeepCopy());
    }
}
