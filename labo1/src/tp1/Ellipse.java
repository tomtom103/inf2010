package tp1;

import java.util.Collection;

public class Ellipse extends BaseShape {

    // TODO creer une ellipse avec une largeur et une longueur.
    public Ellipse(Double widthRadius, Double heightRadius) {
        super();

        for(Double i = 0.0; i < widthRadius / 2; i++) {
            Double maxJ = Math.sqrt((1-(Math.pow(i, 2)/Math.pow(widthRadius/2, 2)))*Math.pow(heightRadius/2, 2));
            for(Double j = 0.0; j < maxJ; j++) {
                super.add(new Point2d(new Double[]{i,j}));
                super.add(new Point2d(new Double[]{i,-j}));
                super.add(new Point2d(new Double[]{-i,j}));
                super.add(new Point2d(new Double[]{-i,-j}));
            }
        }
    }

    // TODO creer une ellipse avec les dimensions contenu dans un Point.
    public Ellipse(Point2d dimensions) {
        super();
        Double widthRadius = dimensions.X();
        Double heightRadius = dimensions.Y();
        for(Double i = 0.0; i < widthRadius / 2; i++) {
            Double maxJ = Math.sqrt((1-(Math.pow(i, 2)/Math.pow(widthRadius/2, 2)))*Math.pow(heightRadius/2, 2));
            for(Double j = 0.0; j < maxJ; j++) {
                super.add(new Point2d(new Double[]{i,j}));
                super.add(new Point2d(new Double[]{i,-j}));
                super.add(new Point2d(new Double[]{-i,j}));
                super.add(new Point2d(new Double[]{-i,-j}));
            }
        }
    }

    // TODO initialiser le parent.
    private Ellipse(Collection<Point2d> coords) {
        super(coords);
    }

    // TODO appliquer la translation sur la forme.
    @Override
    public Ellipse translate(Point2d point) {
        for(Point2d item : super.getCoords()) {
            item.translate(point.vector);
        }
        return this;
    }

    // TODO appliquer la rotation sur la forme.
    @Override
    public Ellipse rotate(Double angle) {
        for(Point2d item : super.getCoords()) {
            item.rotate(angle);
        }
        return this;
    }

    // TODO retourner une nouvelle forme.
    @Override
    public Ellipse clone() {
        return new Ellipse(super.getCoordsDeepCopy());
    }
}
