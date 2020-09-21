package tp1;

import java.util.*;

public final class PointOperator {

    // TODO appliquer la translation sur le vecteur d'entree.
    public static void translate(Double[] vector, Double[] translateVector) {
        for(int i = 0; i < vector.length; i++) {
            vector[i] += translateVector[i];
        }
    }

    // TODO appliquer la translation sur le vecteur d'entree.
    public static void rotate(Double[] vector, Double[][] rotationMatrix) {
        Double[] oldVector = vector.clone();
        for (int i = 0; i < rotationMatrix.length; i++) {
            double sum = 0;
            for (int j = 0; j < rotationMatrix[i].length; j++) {
                sum += rotationMatrix[i][j] * oldVector[j];
            }
            vector[i] = sum;
        }
    }

    // TODO appliquer la translation sur le vecteur d'entree.
    public static void divide(Double[] vector, Double divider) {
        for (int i = 0; i < vector.length; i++) {
            vector[i] /= divider;
        }
    }

    // TODO appliquer la translation sur le vecteur d'entree.
    public static void multiply(Double[] vector, Double multiplier) {
        for (int i = 0; i < vector.length; i++) {
            vector[i] *= multiplier;
        }
    }

    // TODO appliquer la translation sur le vecteur d'entree.
    public static void add(Double[] vector, Double adder) {
        for (int i = 0; i < vector.length; i++) {
            vector[i] += adder;
        }
    }

    // TODO retourne une nouvelle coordonnee avec la plus grande valeur en X et la plus grande en Y.
    public static Point2d getMaxCoord(Collection<Point2d> coords) {
        double maxX = 0.0;
        double maxY = 0.0;
        for(Point2d item : coords) {
            if(item.X() > maxX) {
                maxX = item.X();
            }
            if(item.Y() > maxY) {
                maxY = item.Y();
            }
        }
        return new Point2d(maxX, maxY);
    }

    // TODO retourne une nouvelle coordonnee avec la plus petite valeur en X et la plus petite en Y.
    public static Point2d getMinCoord(Collection<Point2d> coords) {
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        for(Point2d item : coords) {
            if(item.X() < minX) {
                minX = item.X();
            }
            if(item.Y() < minY) {
                minY = item.Y();
            }
        }
        return new Point2d(minX, minY);
    }

}
