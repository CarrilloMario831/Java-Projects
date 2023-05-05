/**
 * Class that implements a binary search tree which implements the MyMap
 * interface.
 * @author Mario Carrillo
 * @version 1 10/28/22
 * @uni mc5132
 */

public class BSTMap<K extends Comparable<K>, V> implements MyMap<K, V> {
    public static final int PREORDER = 1, INORDER = 2, POSTORDER = 3;
    protected Node<K, V> root;
    protected int size;

    /**
     * Creates an empty binary search tree map.
     */
    public BSTMap() { }

    /**
     * Creates a binary search tree map of the given key-value pairs.
     * @param elements an array of key-value pairs
     */
    public BSTMap(Pair<K, V>[] elements) {
        insertElements(elements);
    }

    /**
     * Creates a binary search tree map of the given key-value pairs. If
     * sorted is true, a balanced tree will be created. If sorted is false,
     * the pairs will be inserted in the order they are received.
     * @param elements an array of key-value pairs
     */
    public BSTMap(Pair<K, V>[] elements, boolean sorted) {
        if (!sorted) {
            insertElements(elements);
        } else {
            root = createBST(elements, 0, elements.length - 1);
        }
    }

    /**
     * Recursively constructs a balanced binary search tree by inserting the
     * elements via a divide-snd-conquer approach. The middle element in the
     * array becomes the root. The middle of the left half becomes the root's
     * left child. The middle element of the right half becomes the root's right
     * child. This process continues until low > high, at which point the
     * method returns a null Node.
     * @param pairs an array of <K, V> pairs sorted by key
     * @param low   the low index of the array of elements
     * @param high  the high index of the array of elements
     * @return      the root of the balanced tree of pairs
     */
    protected Node<K, V> createBST(Pair<K, V>[] pairs, int low, int high) {
        Node pointer;
        if (low > high) {
            return null;
        }
        int mid = (low + high) / 2;
        pointer = new Node(pairs[mid].key, pairs[mid].value); // [1, 2, 3]
        size++;
        pointer.left = createBST(pairs, low, mid - 1);
        if(pointer.left != null){
            pointer.left.parent = pointer;
        }
        pointer.right = createBST(pairs, mid + 1, high);
        if(pointer.right != null){
            pointer.right.parent = pointer;
        }

        return pointer;
    }

    /**
     * Inserts the pairs into the tree in the order they appear in the given
     * array.
     * @param pairs the array of <K, V> pairs to insert
     */
    protected void insertElements(Pair<K, V>[] pairs) {
        for (Pair<K, V> pair : pairs) {
            put(pair);
        }
    }

    /**
     * Returns the number of key-value mappings in this map.
     * @return the number of key-value mappings in this map
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if this map contains no key-value mappings.
     * @return true if this map contains no key-value mappings
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns a String of the key-value pairs visited with a preorder
     * traversal. Uses a StringBuilder for efficiency.
     * @return a String of the key-value pairs visited with a preorder
     *         traversal
     */
    public String preorder() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        preorder(root, builder, 0);
        builder.append("]");
//        System.out.println(preorder(root, builder, 0));
        return builder.toString();
    }

    /**
     * Visits the Nodes of the tree in a preorder traversal. Each Node's
     * toString() return value should be appended to the StringBuilder. A ", "
     * must appear between each Node's data in the final String.
     * @param n            the current Node
     * @param builder      the StringBuilder used to build up the output
     * @param nodesVisited the number of nodes visited so far. Useful for
     *                     determining when to append ", ".
     * @return the number of nodes visited after each recursive call
     */
    private int preorder(Node<K, V> n, StringBuilder builder, int nodesVisited) {
        if(n != null) {


            builder.append(n);

            nodesVisited++;

            if (nodesVisited < size) {
                builder.append(", ");
            }


            nodesVisited = preorder(n.left, builder, nodesVisited);
            nodesVisited = preorder(n.right, builder, nodesVisited);
        }
        return nodesVisited;
    }

    /**
     * Returns a String of the key-value pairs visited with an inorder
     * traversal. Uses a StringBuilder for efficiency.
     * @return a String of the key-value pairs visited with an inorder
     *         traversal
     */
    public String inorder() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        inorder(root, builder, 0);
        builder.append("]");
//        System.out.println(inorder(root, builder, 0));
        return builder.toString();
    }

    /**
     * Visits the Nodes of the tree in an inorder traversal. Each Node's
     * toString() return value should be appended to the StringBuilder. A ", "
     * must appear between each Node's data in the final String.
     * @param n            the current Node
     * @param builder      the StringBuilder used to build up the output
     * @param nodesVisited the number of nodes visited so far. Useful for
     *                     determining when to append ", ".
     * @return the number of nodes visited after each recursive call
     */
    private int inorder(Node<K, V> n, StringBuilder builder, int nodesVisited) {
        if(n != null) {

            nodesVisited = inorder(n.left, builder, nodesVisited);

            builder.append(n);

            nodesVisited++;

            if (nodesVisited < size) {
                builder.append(", ");
            }

            nodesVisited = inorder(n.right, builder, nodesVisited);

        }
        return nodesVisited;
    }

    /**
     * Returns a String of the key-value pairs visited with a postorder
     * traversal. Uses a StringBuilder for efficiency.
     * @return a String of the key-value pairs visited with a postorder
     *         traversal
     */
    public String postorder() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        postorder(root, builder, 0);
        builder.append("]");
//        System.out.println(postorder(root, builder, 0));
        return builder.toString();
    }

    /**
     * Visits the Nodes of the tree in a postorder traversal. Each Node's
     * toString() return value should be appended to the StringBuilder. A ", "
     * must appear between each Node's data in the final String.
     * @param n            the current Node
     * @param builder      the StringBuilder used to build up the output
     * @param nodesVisited the number of nodes visited so far. Useful for
     *                     determining when to append ", ".
     * @return the number of nodes visited after each recursive call
     */
    private int postorder(Node<K, V> n, StringBuilder builder, int nodesVisited) {
        if(n != null) {

            nodesVisited = postorder(n.left, builder, nodesVisited);
            nodesVisited = postorder(n.right, builder, nodesVisited);

            builder.append(n);

            nodesVisited++;

            if (nodesVisited < size) {
                builder.append(", ");
            }

        }
        return nodesVisited;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     * @param  key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if this
     *         map contains no mapping for the key
     */
    public V get(K key) {
        Node<K, V> x = iterativeSearch(key);
        return x != null ? x.value : null;
    }

    /**
     * Determines if the supplied key is found in the tree. If so, it returns a
     * reference to the Node containing the key. Otherwise, null is returned.
     * @param key key whose mapping is to be searched from the map
     * @return a reference to the Node containing the specified key
     */
    private Node<K, V> iterativeSearch(K key) {
        Node<K, V> n = root;
            while (n != null) {
                if (key.compareTo(n.key) == 0) {
                    return n;
                } else if (key.compareTo(n.key) < 0) {
                    n = n.left;
                } else if (key.compareTo(n.key) > 0) {
                    n = n.right;
                }
            }
        return null;
    }

    /**
     * Associates the specified value with the specified key in this map. If the
     * map previously contained a mapping for the key, the old value is replaced
     * by the specified value.
     * @param pair  the key-value mapping to insert into the tree
     * @return the previous value associated with key, or null if there was no
     *         mapping for key
     */
    public V put(Pair<K, V> pair) {
        return put(pair.key, pair.value);
    }

    /**
     * Associates the specified value with the specified key in this map. If the
     * map previously contained a mapping for the key, the old value is replaced
     * by the specified value.
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     * @return the previous value associated with key, or null if there was no
     *         mapping for key
     */
    public V put(K key, V value) {
        Node<K,V> save = iterativeSearch(key);
        V old = null;
        Node<K, V> trav = root;
        if(save != null){
            old = save.value;
        }

        if(size == 0){
            root = new Node<K, V>(key, value);
            size++;
        }

        else if(save != null){
            save.value = value;
            return old;
        }
        else {
            Node<K, V> temp = null;
            while(trav != null){
                temp = trav;
                if (key.compareTo(trav.key) < 0) {
                    trav = trav.left;
                } else if (key.compareTo(trav.key) > 0) {
                    trav = trav.right;
                }
            }
            if(key.compareTo(temp.key) < 0){
                temp.left = new Node<K, V>(key, value);
                temp.left.parent = temp;
            }
            else{
                temp.right = new Node<K, V>(key, value);
                temp.right.parent = temp;
            }
            size++;
        }
        return null;
    }

    /**
     * Removes the mapping for a key from this map if it is present.
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with key, or null if there was no
     *         mapping for key
     */
    public V remove(K key) {

        Node<K, V> n = iterativeSearch(key);

        if(n == null){
            return null;
        }

        if(n.left == null){
            if(n.parent == null){
                root = n.right;
            }
            else if(n == n.parent.left){
                n.parent.left = n.right;
            }
            else{
                n.parent.right = n.right;
            }
            if(n.right != null){
                n.right.parent = n.parent;
            }
        }
        else if(n.right == null){
            if(n.parent == null){
                root = n.left   ;
            }
            else if(n == n.parent.left){
                n.parent.left = n.left;
            }
            else{
                n.parent.right = n.left;
            }
            if(n.left != null){
                n.left.parent = n.parent;
            }
        }
        else{
            Node<K, V> min = treeMinimum(n.right);
            if(min.parent != n){
                if(min.parent == null){
                    root = min.right   ;
                }
                else if(min == min.parent.left){
                    min.parent.left = min.right;
                }
                else{
                    min.parent.right = min.right;
                }
                if(min.right != null){
                    min.right.parent = min.parent;
                }
                min.right = n.right;
                min.right.parent = min;
            }
            if(n.parent == null){
                root = min;
            }
            else if(n == n.parent.left){
                n.parent.left = min;
            }
            else{
                n.parent.right = min;
            }
            if(min != null){
                min.parent = n.parent;
            }
            min.left = n.left;
            min.left.parent = min;
        }
        size--;
        return n.value;
    }


    /**
     * Returns a reference to the Node whose key value is the minimum key in the
     * tree.
     * @param x the Node at which to start the traversal
     * @return a reference to the Node whose key value is the minimum key in the
     *         tree
     */
    protected Node<K, V> treeMinimum(Node<K, V> x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    /**
     * Returns a String representation of the tree, where the Nodes are visited
     * with an inorder traversal.
     * @return a String representation of the tree
     */
    public String toString() {
        return inorder();
    }

    /**
     * Returns an ASCII drawing of the tree.
     * @return an ASCII drawing of the tree
     */
    public String toAsciiDrawing() {
        BinarySearchTreePrinter<K, V> printer =
                new BinarySearchTreePrinter<K, V>();
        printer.createAsciiTree(root);
        return printer.toString();
    }

    public void printTraversal(int type) {
        switch (type) {
            case PREORDER:
                System.out.print("Preorder traversal:       ");
                System.out.println(preorder());
                break;
            case INORDER:
                System.out.print("Inorder traversal:        ");
                System.out.println(inorder());
                break;
            case POSTORDER:
                System.out.print("Postorder traversal:      ");
                System.out.println(postorder());
                break;
            default:
                return;
        }
    }

    /**
     * Returns the height of the tree. If the tree is null, the height is -1.
     * @return the height of the tree
     */
    public int height() {
        return height(root) ;
    }

    protected int height(Node<K, V> node) {
        if (node == null) {
            return -1;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * Returns the number of null references in the tree. Uses a recursive
     * helper method to count the null references.
     * @return the number of null references in the tree
     */
    public int nullCount() {
        return nullCount(root);
    }

    private int nullCount(Node<K, V> node) {
        if (node == null) {
            return 1;
        }
        return nullCount(node.left) + nullCount(node.right);
    }

    /**
     * Returns the sum of the levels of each non-null node in the tree starting
     * at the root.
     * For example, the tree
     *   5 <- level 0
     *  / \
     * 2   8 <- level 1
     *      \
     *       10 <- level 2
     * has sum 0 + 2(1) + 2 = 4.
     * @return the sum of the levels of each non-null node in the tree starting
     *         at the root
     */
    public int sumLevels() {
        return sumLevels(root, 0);
    }

    private int sumLevels(Node<K, V> node, int level) {
        if(node == null){
            return 0;
        }
        else {
            return level + sumLevels(node.left, level + 1) + sumLevels(node.right, level + 1);
        }
    }

    /**
     * Returns the sum of the levels of each null node in the tree starting at
     * the root.
     * For example, the tree
     *    5 <- level 0
     *   / \
     *  2   8 <- level 1
     * / \ / \
     * * * * 10 <- level 2
     *        / \
     *        * * <- level 3
     * has sum 3(2) + 2(3) = 12.
     * @return the sum of the levels of each null node in the tree starting at
     *         the root
     */
    public int sumNullLevels() {
        return sumNullLevels(root, 0);
    }

    private int sumNullLevels(Node<K, V> node, int level) {
        if(node == null){
            return level;
        }
        return sumNullLevels(node.left, level + 1) + sumNullLevels(node.right, level + 1);
    }

    public double successfulSearchCost() {
        return size == 0 ? 0 : 1 + (double)sumLevels() / size;
    }

    public double unsuccessfulSearchCost() {
        return (double)sumNullLevels() / nullCount();
    }

    /**
     * Main method to facilitate testing your code.
     * Either a map of <Integer, Integer> or <String, String> will be created.
     * If the first command line argument parses to an int, the map will be of
     * type <Integer, Integer>.
     * @param args the values to insert into the tree
     */
    public static void main(String[] args) {
        boolean usingInts = true;
        if (args.length > 0) {
            try {
                Integer.parseInt(args[0]);
            } catch (NumberFormatException nfe) {
                usingInts = false;
            }
        }

        @SuppressWarnings("rawtypes")
        BSTMap bst;
        if (usingInts) {
            Pair<Integer, Integer>[] pairs = new Pair[args.length];
            for (int i = 0; i < args.length; i++) {
                try {
                    int val = Integer.parseInt(args[i]);
                    pairs[i] = new Pair<>(val, val);
                } catch (NumberFormatException nfe) {
                    System.err.println("Error: Invalid integer '" + args[i]
                            + "' found at index " + i + ".");
                    System.exit(1);
                }
            }
//            bst = new BSTMap<Integer, Integer>(pairs);
            bst = new BSTMap<Integer, Integer>(pairs, true);
        } else {
            Pair<String, String>[] pairs = new Pair[args.length];
            for (int i = 0; i < args.length; i++) {
                pairs[i] = new Pair<>(args[i], args[i]);
            }
            bst = new BSTMap<String, String>(pairs);
        }

        System.out.println(bst.toAsciiDrawing());
        System.out.println();
        System.out.println("Height:                   " + bst.height());
        System.out.println("Total nodes:              " + bst.size());
        System.out.printf("Successful search cost:   %.3f\n",
                          bst.successfulSearchCost());
        System.out.printf("Unsuccessful search cost: %.3f\n",
                          bst.unsuccessfulSearchCost());
        bst.printTraversal(PREORDER);
        bst.printTraversal(INORDER);
        bst.printTraversal(POSTORDER);
    }
}
