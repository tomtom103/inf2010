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
        BinaryNode<ValueType> parent = current;
        if(root == null) {
            root = new BinaryNode<>(value, null);
            root.height = 0;
        } else {
            while(!value.equals(current.value)) {
                ///////////////////////////////
                //int cptLeft = root.height;///
                //int cptRight = root.height;//
                ///////////////////////////////
                //parent = current.parent != null? current.parent:current;
                //parent.height = Math.max(height(parent.left), height(parent.right)) + 1;

                current.height = Math.max(height(current.left), height(current.right)) + 1;
                if (value.compareTo(current.value) < 0) {
                    //parent = current;
                    //parent.height = Math.max(height(parent.left), height(parent.right)) + 1;
                    //parent.height = cptLeft--;
                    /////////////////////////////

                    if(current.left == null){
                        current.left = new BinaryNode<>(value, current);
                        current.left.height = 0;
                        current.height = Math.max(height(current.left), height(current.right)) + 1;
                        balance(current);
                        break;
                    }
                    //parent.height = Math.max(height(parent.left), height(parent.right)) + 1;
                   // current.height = Math.max(height(current.left), height(current.right)) + 1;
                    current = current.left;
                    //current.height = Math.max(height(current.left), height(current.right)) + 1;
                } else {
                    //parent = current;
                    //parent.height = Math.max(height(parent.left), height(parent.right)) + 1;
                    //parent.height = cptRight--;
                    if(current.right == null){
                        current.right = new BinaryNode<>(value, current);
                        current.right.height = 0;
                        current.height = Math.max(height(current.left), height(current.right)) + 1;
                        balance(current);
                        break;

                    }
                    //current.height = Math.max(height(current.left), height(current.right)) + 1;
                    current = current.right;
                    //current.height = Math.max(height(current.left), height(current.right)) + 1;
                }
            }
            /*
            if(value.compareTo(parent.value) < 0) {
                parent.left = new BinaryNode<ValueType>(value, parent);
                parent.left.height = 0;
                //parent.height = Math.max(height(parent.left), height(parent.right)) + 1;
               // if(parent.parent != null)
                //    parent.parent.height++;
                //oli test
                //root.height++;
                //////////////
                this.balance(parent.left);

            } else if (value.compareTo(parent.value) > 0) {
                parent.right = new BinaryNode<ValueType>(value, parent);
                parent.right.height = 0;
                //parent.height = Math.max(height(parent.left), height(parent.right)) + 1;
                //if(parent.parent != null)
                 //   parent.parent.height++;
                //oli test
                //root.height++;
                ///////////
                this.balance(parent.right);
            } else {
                return;
            }*/
        }
    }

    public int updateHauteur(BinaryNode<ValueType> current){
        return Math.max(height(current.left), height(current.right)) + 1;
    }

    public BinaryNode<ValueType> find(ValueType value) {
        BinaryNode<ValueType> current = root;
        while(current != null && !value.equals(current.value)) {
            if(value.compareTo(current.value) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return current;
    }

    /** TODO Worst case : O ( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *
     * Removes value from the tree and keeps it as a balanced AVL Tree
     * Should call balance only if removal succeeds
     *
     * @param value value to remove from the tree
     */
    public void remove(ValueType value){
        if(value == null) {
            return;
        }
        BinaryNode<ValueType> current = find(value);
        if(current != null) {
            // Case 1: Leaf node
            if(current.left == null && current.right == null) {
                current = null;
            }
            // Case 2: Node only has 1 child
            else if(current.left == null) {
                current.right = null;
            } else if (current.right == null) {
                current.left = null;
            }
            // Case 3: node both children
            else {

            }
        }
    }

    /** TODO Worst case : O ( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *
     * Verifies if the tree contains value
     * @param value value to verify
     * @return if value already exists in the tree
     */
    public boolean contains(ValueType value) {
        BinaryNode<ValueType> current = find(value);
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
        return root != null? root.height: -1;
    }

    private int height(BinaryNode<ValueType> node) {
        return node == null ? -1 : node.height;
    }

    /** TODO Worst case : O( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *
     * Returns the node which has the minimal value contained in our root tree
     * @return Node which has the minimal value contained in our root tree
     */
    public ValueType findMin() {
        BinaryNode<ValueType> current = root;
        if(current == null) {
            return null;
        }
        while(current.left != null) {
            current = current.left;
        }
        return current.value;
    }


    /** TODO Worst case : O( n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     * Returns all values contained in the root tree in ascending order
     * @return Values contained in the root tree in ascending order
     */
    public List<ValueType> infixOrder() {
        LinkedList<ValueType> liste = new LinkedList<>();
        Stack<BinaryNode<ValueType>> queue = new Stack<>();
        BinaryNode<ValueType> current = root;
        while(!queue.empty() || current != null){
            if(current != null){
                queue.push(current);
                current = current.left;
            }
            else{
                current = queue.pop();
                liste.add(current.value);
                current = current.right;
            }
        }
        return liste;
    }

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
        BinaryNode<ValueType> nodeParent;
        //if(node.parent.parent != null){
        //    nodeParent = node.parent.parent;
        //}
        while(node != null){
            node.height = updateHauteur(node);
            if(height(node.left) >= 2 + height(node.right)){
                if(height(node.left.left) >= height(node.left.right)){
                    rotateRight(node);
                }
                else{
                    rotateLeft(node.left);
                    rotateRight(node);
                }
            }
            else if(height(node.right) >= 2 + height(node.left)){
                if(height(node.right.right) >= height(node.right.left)){
                    rotateLeft(node);
                }
                else{
                    rotateRight(node.right);
                    rotateLeft(node);
                }
            }
            node = node.parent;
        }
        /*if(node.parent != null){
            nodeParent = node.parent;
        }
        else{
            nodeParent = node;
        }
        //nodeParent = node.parent;
        int heightLeft = height(nodeParent.left);
        int heightRight = height(nodeParent.right);
        int balanceFactor =  heightLeft - heightRight;
        if(balanceFactor > 1) {
            if(nodeParent.left != null && nodeParent.left.value.compareTo(nodeParent.value) > 0){
                //rotateLeft(nodeParent);
                rotateRight(nodeParent);
            }
            else{
                //rotateRight(nodeParent.right);
                //rotateRight(nodeParent);
                rotateLeft(nodeParent);
            }
        }
        else if(balanceFactor < -1){
            if(nodeParent.left != null && nodeParent.left.right != null && nodeParent.left.left != null && height(nodeParent.left.left) > height(nodeParent.left.right)){
                rotateRight(nodeParent);
            }
            else{
                rotateLeft(nodeParent);
               // rotateLeft(nodeParent.left);
                //rotateRight(nodeParent);
            }
        }*/
    }

    /** TODO Worst case : O ( 1 )
     *
     * Single rotation to the left child, AVR Algorithm
     * @param node1 Node to become child of its left child
     */
    private void rotateLeft(BinaryNode<ValueType> node1){
        BinaryNode<ValueType> nodeTmp = node1.right;
        nodeTmp.parent = node1.parent;
        node1.parent = nodeTmp;
        BinaryNode<ValueType> nodeTmp2 = nodeTmp.left;

        nodeTmp.left = node1;
        node1.right = nodeTmp2;

        //nodeTmp.parent = node1.parent;
      //  nodeTmp.left = node1;
       // node1.parent = nodeTmp;
       // BinaryNode<ValueType> nodeTmp = node1.right;
       // nodeTmp.parent = node1.parent;
       // node1.parent = nodeTmp;
       // node1.right = nodeTmp.left;

       // nodeTmp.left = node1;



        //node1.height = node1.parent.height -1;

        node1.height = Math.max(height(node1.left), height(node1.right)) + 1;

        nodeTmp.height = Math.max(height(nodeTmp.left), height(nodeTmp.right)) + 1;
        if(nodeTmp.parent == null)
            root = nodeTmp;
    }

    /** TODO Worst case : O ( 1 )
     *
     * Single rotation to the right, AVR Algorithm
     * @param node1 Node to become child of its right child
     */
    private void rotateRight(BinaryNode<ValueType> node1){
        /*BinaryNode<ValueType> nodeTmp = node1.left;
        //<ValueType> nodeTmpRight = node1.right;
        node1.left = nodeTmp.right;

        nodeTmp.right = node1;
        node1.parent = nodeTmp;
        node1.height = node1.parent.height -1;*/
        BinaryNode<ValueType> nodeTmp = node1.left;
        nodeTmp.parent = node1.parent;
        node1.parent = nodeTmp;
        BinaryNode<ValueType> nodeTmp2 = nodeTmp.right;

        nodeTmp.right = node1;
        node1.left = nodeTmp2;

        node1.height = Math.max(height(node1.left), height(node1.right)) + 1;
        nodeTmp.height = Math.max(height(nodeTmp.left), height(nodeTmp.right)) + 1;
        if(nodeTmp.parent == null)
            root = nodeTmp;

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
