package KthSmallestElement;

import java.util.*;

public class KthSmallestElement {
    /**
     * Explication de votre complexité temporelle
     *
     * Dans un premier temps, on ajoute tous les éléments de la première colone dans un ArrayList (O(n))
     * Ensuite, on construit notre heap (PriorityQueue) à partir de la liste de Points déjà créée,
     * ce qui revient a appeler la fonction buildHeap() qui est de complexité O(n).
     *
     * Ensuite, on fait une boucle for, qui va être éxécutée k fois. Dans celle-ci on fait un poll()
     * et possiblement un offer(), qui sont deux méthodes de complexité O(log(n)).
     *
     * Ainsi, on obtient bien une complexité temporelle de O(n) + O(n) + O(k * (log(n) + log(n))) -> O(k*log(n)).
     *
     * Explication de votre complexité spatiale
     *
     * On créé un heap de complexité spatiale O(n), celui-ci ne dépassera jamais une taille de n car pour pouvoir ajouter
     * un élément dedans, il faut dans un premier temps faire un poll().
     *
     */
    /** TODO Worst case
     *      Time complexity : O ( k log max(m,n) )
     *      Space complexity : O ( log max(m,n) )
     *
     * Returns the `k`th smallest element in `matrix`
     * @param matrix 2D table of shape M x N respecting the following rules
     *               matrix[i][j] <= matrix[i+1][j]
     *               matrix[i][j] <= matrix[i][j + 1]
     * @param k Index of the smallest element we want
     * @return `K`th smallest element in `matrix`, null if non-existent
     */
    static public <T extends Comparable<T>> T findKthSmallestElement(final T[][] matrix, final int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0 || k < 0)
            return null;

        if(matrix.length == 1 && matrix[0].length == 1 && k == 0)
            return matrix[0][0];

        // O(n)
        ArrayList<Node<T>> nodeList = new ArrayList<>(matrix.length);
        for(int i = 0; i < matrix.length; i++) {
            nodeList.add(new Node<>(matrix[i][0], i, 0));
        }

        // O(n) car on va utiliser le buildHeap()
        PriorityQueue<Node<T>> heap = new PriorityQueue<>(nodeList);

        // O(k*log(n))
        for(int i = k; !heap.isEmpty(); i--) {
            Node<T> node = heap.poll();
            if (i == 0) return node.value;

            int col = node.col;
            int row = node.row;
            if (row < matrix[col].length - 1)
                heap.offer(new Node<>(matrix[col][row + 1], col, row + 1));
        }

        return null;
    }

    /**
     * Data structure that contains the value and the position of an element
     * @param <T> the type of the element in the matrix
     */
    static class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
        T value;
        int row, col;
        public Node(T val, int x, int y) {
            value = val;
            col = x;
            row = y;
        }
        @Override
        public int compareTo(Node<T> o) {
            return value.compareTo(o.value);
        }
    }
}