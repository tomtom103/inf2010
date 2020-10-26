import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class AvlTree<ValueType extends Comparable<? super ValueType> > {

    // Only node which has its parent to null
    private BinaryNode<ValueType> root;

    public AvlTree() { }

    /** TODO Worst case : O ( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *
     * Adds value to the tree and keeps it as a balanced AVL Tree
     * Should call balance only if insertion succeeds
     * AVL Trees do not contain duplicates
     *
     * @param value value to add to the tree
     */
    public void add(ValueType value) {
        BinaryNode<ValueType> current = root;
        //root.height = root.height == (null)? 0: root.height;
        BinaryNode<ValueType> parent = current;
        if(root == null) {
            root = new BinaryNode<>(value, null);
        } else {
            while(current != null && !value.equals(current.value)) {
                if (value.compareTo(current.value) < 0) {

                    parent = current;
                    current = current.left;

                } else {
                    parent = current;
                    current = current.right;

                }
                if(current != null && (parent.height - current.height) != 1){
                    current.height = parent.height -1;
                }
            }
            if(value.compareTo(parent.value) < 0) {
                parent.left = new BinaryNode<ValueType>(value, parent);
                //parent.left.height = parent.height + 1;

                root.height += 1;
                this.balance(parent.left);
            } else if (value.compareTo(parent.value) > 0) {
                parent.right = new BinaryNode<ValueType>(value, parent);
                //parent.right.height = parent.height + 1;

                root.height+=1;
                this.balance(parent.right);
            } else {
                return;
            }
        }

    }

    /** TODO Worst case : O ( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *
     * Removes value from the tree and keeps it as a balanced AVL Tree
     * Should call balance only if removal succeeds
     *
     * @param value value to remove from the tree
     */
    public void remove(ValueType value){

    }

    /** TODO Worst case : O ( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *
     * Verifies if the tree contains value
     * @param value value to verify
     * @return if value already exists in the tree
     */
    public boolean contains(ValueType value) {
        BinaryNode<ValueType> current = root;
        while(current != null && !value.equals(current.value)) {
            if(value.compareTo(current.value) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        if(current == null) {
            return false;
        }
        return true;
    }

    /** TODO Worst case : O( 1 )
     * Returns the max level contained in our root tree
     * @return Max level contained in our root tree
     */
    public int getHeight() {
        return root.height;

    }

    /** TODO Worst case : O( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *
     * Returns the node which has the minimal value contained in our root tree
     * @return Node which has the minimal value contained in our root tree
     */
    public ValueType findMin() {
        BinaryNode<ValueType> current = root;
        if(current == null){
            return null;
        }
        BinaryNode<ValueType> parent = current;
        while(parent != null && parent.left != null){
            parent = parent.left;
        }
        return parent.value;
    }


    /** TODO Worst case : O( n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     * Returns all values contained in the root tree in ascending order
     * @return Values contained in the root tree in ascending order
     */
    public List<ValueType> infixOrder() { return new LinkedList<>(); }

    /** TODO Worst case : O( n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *
     * Returns all values contained in the root tree in level order from top to bottom
     * @return Values contained in the root tree in level order from top to bottom
     */
    public List<ValueType> levelOrder(){
        LinkedList<ValueType> liste = new LinkedList<>();
        if (root == null || (root.left == null && root.right == null))
            return liste;

        LinkedList<BinaryNode<ValueType>> stack = new LinkedList<>();
        BinaryNode<ValueType> current = root;
        stack.add(current);
        while(!stack.isEmpty()){
            current = stack.poll();
            liste.add(current.value);
            if(current.left != null){
                stack.offer(current.left);
            }
            if(current.right != null){
                stack.offer(current.right);
            }
        }
        return liste;
    }



    /** TODO Worst case : O( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *
     * Balances the whole tree
     * @param node Node to balance all the way to root
     */
    private void balance(BinaryNode<ValueType> node) {

    }

    /** TODO Worst case : O ( 1 )
     *
     * Single rotation to the left child, AVR Algorithm
     * @param node1 Node to become child of its left child
     */
    private void rotateLeft(BinaryNode<ValueType> node1){

    }

    /** TODO Worst case : O ( 1 )
     *
     * Single rotation to the right, AVR Algorithm
     * @param node1 Node to become child of its right child
     */
    private void rotateRight(BinaryNode<ValueType> node1){

    }

    static private class BinaryNode<ValueType> {
        ValueType value;

        BinaryNode<ValueType> parent; // Pointer to the node containing this node

        BinaryNode<ValueType> left = null; // Pointer to the node on the left which should contain a value below this.value
        BinaryNode<ValueType> right = null; // Pointer to the node on the right which should contain a value above this.value

        int height = 0;

        BinaryNode(ValueType value, BinaryNode<ValueType> parent)
        {
            this.value = value;
            this.parent = parent;
        }
    }

}

