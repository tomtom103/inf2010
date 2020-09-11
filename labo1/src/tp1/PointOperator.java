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
        for (int i = 0; i < rotationMatrix.length; i++) {
            double sum = 0;
            for (int j = 0; j < rotationMatrix[0].length; j++) {
                sum += rotationMatrix[i][j] * vector[j];
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
        Point2d max = Collections.max(coords, (o1, o2) -> {
            int result = Double.compare(o1.X(), o2.X());
            if( result == 0) {
                // X1 et X2 sont egaux, on verifie Y
                result = Double.compare(o1.Y(), o2.Y());
            }
            return result;
        });
        return max;
    }

    // TODO retourne une nouvelle coordonnee avec la plus petite valeur en X et la plus petite en Y.
    public static Point2d getMinCoord(Collection<Point2d> coords) {
        Point2d min = Collections.min(coords, new Comparator<Point2d>() {
            public int compare(Point2d o1, Point2d o2) {
                int result = Double.compare(o1.X(), o2.X());
                if( result == 0) {
                    // X1 et X2 sont egaux, on verifie Y
                    result = Double.compare(o1.Y(), o2.Y());
                }
                return result;
            }
        });
        return min;
    }

}
