package KthSmallestElement;

import java.util.*;

public class KthSmallestElement {
    /**
     * Explication de votre complexité temporelle
     *
     *
     * Explication de votre complexité spatiale
     *
     * On initialise un priorityQueue qui aura en tout temps au maximum n élements
     * étant donné qu'on ajoute dans un premier temps les premières valeurs de chaque ligne
     * Ensuite, pour trouver le k-ième élément, on fait un poll, ce qui enleve l'element en tête,
     * puis on en rajoute un nouveau, ce qui fait qu'on a jamais plus de n éléments dans le Heap.
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

        PriorityQueue<Node<T>> heap = new PriorityQueue<>(
                (a, b) -> Integer.compare(a.value.compareTo(b.value), 0));

        for (int i = 0; i < matrix.length; i++) { // Partie 1
            if (matrix[i].length > 0)
                heap.offer(new Node<>(matrix[i][0], i, 0));
        }

        for(int i = k; !heap.isEmpty(); i--) { // Partie 2
            Node<T> node = heap.poll();
            if (i == 0) return node.value;

            int col = node.col;
            int row = node.row;
            if (row < matrix[col].length - 1)
                heap.offer(new Node<>(matrix[col][row + 1], col, row + 1));
        }

        return null;
    }

    static class Node<T extends Comparable<? super T>> {
        T value;
        int row, col;
        public Node(T val, int x, int y) {
            value = val;
            col = x;
            row = y;
        }
    }
}