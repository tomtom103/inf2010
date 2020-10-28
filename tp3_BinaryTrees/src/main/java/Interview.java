import java.util.Arrays;

public class Interview<ValueType extends Comparable<? super ValueType> > {
    // 2D array of any shape which contains elements sorted from left to right, top to bottom
    private final ValueType[][] matrix;

    public Interview(ValueType[][] matrix) {
        this.matrix = matrix.clone();
    }

    /** TODO Worst case : O ( max(log n, log m) )
     *
     * Verifies if the value is contained within the 2D array
     * @param value value to verify
     * @return if value is in matrix
     */
    public boolean contains(ValueType value) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        return search(matrix, value, 0, matrix.length - 1);
    }

    private boolean search(ValueType[][] matrix, ValueType value, int startRow, int endRow ) {
        if(startRow < 0 || startRow > endRow) return false;

        int last = matrix[startRow].length - 1;
        int mid = (endRow - startRow) / 2 + startRow;

        if(value.compareTo(matrix[mid][0]) < 0) {
            return search(matrix, value, startRow, mid - 1);
        }
        else if (value.compareTo(matrix[mid][last]) > 0) {
            return search(matrix, value, mid + 1, endRow);
        }
        else {
            return Arrays.binarySearch(matrix[mid], value) >= 0;
        }
    }
}
