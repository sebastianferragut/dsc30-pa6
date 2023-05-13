/*
 * Name: Sebastian Ferragut, David Tsukamoto
 * PID:  A17263077, A17379000
 */

import java.util.*;

/**
 * Binary search tree implementation.
 *
 * @author Sebastian Ferragut, David Tsukamoto
 * @since  {05-10-2023}
 */
public class BSTree<T extends Comparable<? super T>> implements Iterable {

    /* * * * * BST Instance Variables * * * * */

    private int nelems; // number of elements stored
    private BSTNode root; // reference to root node

    /* * * * * BST Node Inner Class * * * * */

    protected class BSTNode {

        T key;
        LinkedList<T> dataList;
        BSTNode left;
        BSTNode right;

        /**
         * A constructor that initializes the BSTNode instance variables.
         *
         * @param left     Left child
         * @param right    Right child
         * @param dataList Linked list of related info
         * @param key      Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, LinkedList<T> dataList, T key) {
            this.left = left;
            this.right = right;
            this.dataList = dataList;
            this.key = key;
        }

        /**
         * A constructor that initializes BSTNode variables. Note: This constructor is
         * used when you want to add a key with no related information yet. In this
         * case, you must create an empty LinkedList for the node.
         *
         * @param left  Left child
         * @param right Right child
         * @param key   Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, T key) {
            this.left = left;
            this.right = right;
            this.dataList = new LinkedList<>();
            this.key = key;
        }

        /**
         * Return the key
         *
         * @return The key
         */
        public T getKey() {
            return this.key;
        }

        /**
         * Return the left child of the node
         *
         * @return The left child of the node
         */
        public BSTNode getLeft() {
            return this.left;
        }

        /**
         * Return the right child of the node
         *
         * @return The right child of the node
         */
        public BSTNode getRight() {
            return this.right;
        }

        /**
         * Return the linked list of the node
         *
         * @return The linked list of the node
         */
        public LinkedList<T> getDataList() {
            return this.dataList;
        }

        /**
         * Setter for left child of the node
         *
         * @param newleft New left child
         */
        public void setLeft(BSTNode newleft) {
            this.left = newleft;
        }

        /**
         * Setter for right child of the node
         *
         * @param newright New right child
         */
        public void setRight(BSTNode newright) {
            this.right = newright;
        }

        /**
         * Setter for the linked list of the node
         *
         * @param newData New linked list
         */
        public void setDataList(LinkedList<T> newData) {
            this.dataList = newData;
        }

        /**
         * Append new data to the end of the existing linked list of the node
         *
         * @param data New data to be appended
         */
        public void addNewInfo(T data) {
            this.dataList.add(data);
        }

        /**
         * Remove 'data' from the linked list of the node and return true. If the linked
         * list does not contain the value 'data', return false.
         *
         * @param data Info to be removed
         * @return True if data was found, false otherwise
         */
        public boolean removeInfo(T data) {
            return this.dataList.remove(data);
        }
    }

    /* * * * * BST Methods * * * * */

    /**
     * 0-arg constructor that initializes root to null and nelems to 0
     */
    public BSTree() {
        this.root = null;
        this.nelems = 0;
    }

    /**
     * Return the root of BSTree. Returns null if the tree is empty
     *
     * @return The root of BSTree, null if the tree is empty
     */
    public BSTNode getRoot() {
        if (getSize() == 0) {
            return null;
        }
        return this.root;
    }

    /**
     * Return the BST size
     *
     * @return The BST size
     */
    public int getSize() {
        return this.nelems;
    }

    /**
     * Insert a key into BST
     * 
     * @param key
     * @return true if insertion is successful and false otherwise
     * @throws NullPointerException if key is null
     */
    public boolean insert(T key) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (this.root == null) {
            this.root = new BSTNode(null, null, key);
            this.nelems += 1;
            return true;
        }
        boolean isInserted = insertHelper(this.root, key);
        if (!isInserted) {
            return isInserted;
        }
        this.nelems += 1;
        return true;
    }

    /**
     * Helper method for insert, recurses until key is added
     *
     * @param toAdd key to be added
     * @param currRoot BSTNode The root of the BST
     * @return boolean true if the key is added, false otherwise
     */
    private boolean insertHelper(BSTNode currRoot, T toAdd) {
        int value = toAdd.compareTo(currRoot.getKey());
        if (value == 0) {
            return false;
        }
        if (value < 0) {
            if (currRoot.getLeft() == null) {
                currRoot.setLeft(new BSTNode(null, null, toAdd));
            } else {
                insertHelper(currRoot.getLeft(), toAdd);
            }
        } else {
            if (currRoot.getRight() == null) {
                currRoot.setRight(new BSTNode(null, null, toAdd));
            } else {
                insertHelper(currRoot.getRight(), toAdd);
            }
        }
        return true;
    }

    /**
     * Return true if the 'key' is found in the tree, false otherwise
     *
     * @param key To be searched
     * @return True if the 'key' is found, false otherwise
     * @throws NullPointerException If key is null
     */
    public boolean findKey(T key) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (this.root.getKey() == null) {
            return false;
        }
        if (this.root.getKey() == key) {
            return true;
        }
        return findKeyHelper(this.root, key);
    }

    /**
     * Helper method for findKey, recurses until key is found
     *
     * @param toFind key to be found
     * @param currRoot BSTNode The root of the BST
     * @return boolean true if the key is found, false otherwise
     */
    private boolean findKeyHelper(BSTNode currRoot, T toFind) {
        if (currRoot == null) {
            return false;
        }
        if (currRoot.getKey() == null) {
            return false;
        }
        if (toFind.compareTo(currRoot.getKey()) == 0) {
            return true;
        }
        if (toFind.compareTo(currRoot.getKey()) < 0) {
            return findKeyHelper(currRoot.getLeft(), toFind);
        } else if (toFind.compareTo(currRoot.getKey()) > 0) {
            return findKeyHelper(currRoot.getRight(), toFind);
        } else {
            return true;
        }
    }


    /**
     * Insert 'data' into the LinkedList of the node whose key is 'key'
     *
     * @param key  Target key
     * @param data To be added to key's LinkedList
     * @throws NullPointerException     If either key or data is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public void insertData(T key, T data) {
        if (key == null || data == null) {
            throw new NullPointerException();
        }
        if (!findKey(key)) {
            throw new IllegalArgumentException();
        } else {
            insertDataHelper(this.root, key).addNewInfo(data);
        }
    }

    /**
     * Helper method for insertData, recurses until node is found
     * and returns node
     *
     * @param toFind key to be found
     * @param currRoot BSTNode The root of the BST
     * @return BSTNode to add data to
     */
    private BSTNode insertDataHelper(BSTNode currRoot, T toFind) {
        //returning currNode
        if (toFind.compareTo(currRoot.getKey()) == 0) {
            return currRoot;
        }
        if (toFind.compareTo(currRoot.getKey()) < 0) {
            return insertDataHelper(currRoot.getLeft(), toFind);
        } else if (toFind.compareTo(currRoot.getKey()) > 0) {
            return insertDataHelper(currRoot.getRight(), toFind);
        }
        return currRoot;
    }


    /**
     * Return the LinkedList of the node with key value 'key'
     *
     * @param key Target key
     * @return LinkedList of the node whose key value is 'key'
     * @throws NullPointerException     If key is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public LinkedList<T> findDataList(T key) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (!findKey(key)) {
            throw new IllegalArgumentException();
        }
        return findDataListHelper(this.root, key);
    }

    /**
     * Helper method for findDataList, recurses until key is found
     * to return dataList of node
     *
     * @param toFind key to be found
     * @param currRoot BSTNode The root of the BST
     * @return boolean true if the key is found, false otherwise
     */
    private LinkedList findDataListHelper(BSTNode currRoot, T toFind) {
        if (toFind.compareTo(currRoot.getKey()) == 0) {
            return currRoot.getDataList();
        }
        if (toFind.compareTo(currRoot.getKey()) < 0) {
            return findDataListHelper(currRoot.getLeft(), toFind);
        } else if (toFind.compareTo(currRoot.getKey()) > 0) {
            return findDataListHelper(currRoot.getRight(), toFind);
        }
        return currRoot.getDataList();
    }

    /**
     * Return the height of the tree
     *
     * @return The height of the tree, -1 if BST is empty
     */
    public int findHeight() {
        if (this.root == null) {
            return -1;
        }
        if (this.root.getLeft() == null && this.root.getRight() == null) {
            return 0;
        }
        return findHeightHelper(this.root);
    }

    /**
     * Helper for the findHeight method
     *
     * @param root Root node
     * @return The height of the tree, -1 if BST is empty
     */
    private int findHeightHelper(BSTNode root) {
        if (root == null) {
            return -1;
        }

        int leftHeight = findHeightHelper(root.getLeft());
        int rightHeight = findHeightHelper(root.getRight());
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /* * * * * BST Iterator * * * * */
    /**
     * Binary search tree iterator implementation.
     *
     * @author Sebastian Ferragut, David Tsukamoto
     * @since  {05-10-2023}
     */
    public class BSTree_Iterator<T> implements Iterator<T> {
        private Stack<BSTNode> iterStack;
        /**
         * A constructor that initializes the Stack with the left path of the root.
         */
        public BSTree_Iterator() {
            this.iterStack = new Stack<BSTNode>();
            BSTNode curr = getRoot();
            curr = getRoot();
            while (curr != null) {
                iterStack.push(curr);
                curr = curr.getLeft();
            }
        }

        /**
         * Returns false if the Stack is empty i.e. no more nodes left to iterate, true otherwise
         *
         * @return boolean false if empty Stack, true otherwise
         */
        public boolean hasNext() {
            return !iterStack.empty();
        }

        /**
         * Returns the next item in the BST.
         *
         * @return T next item in BST
         * @throws NoSuchElementException if there is no next item
         */
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T nextItem = (T) iterStack.pop().getKey();
            return nextItem;
        }

        /**
         * Return string representation of iterStack
         *
         * @return String iterStack ex "[1, 2, 3]
         */
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (getRoot() == null) {
                return "[]";
            }
            Stack<BSTNode> copyStack = (Stack<BSTNode>) iterStack.clone();
            while (!copyStack.isEmpty()) {
                BSTNode curr = copyStack.pop();
                sb.append(curr.key.toString());
                sb.append(", ");
            }
            sb.setLength(sb.length() - 2);
            return "[" + sb.toString() + "]";
        }
    }

    public Iterator<T> iterator() {
        return new BSTree_Iterator();
    }

        /* * * * * Extra Credit Methods * * * * */

    public ArrayList<T> intersection(Iterator<T> iter1, Iterator<T> iter2) {
        /* TODO */
        return null;
    }

    public T levelMax(int level) {
        /* TODO */
        return null;
    }
}

