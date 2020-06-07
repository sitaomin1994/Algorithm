
// BinarySearchTree class
//
// ******************OPERATIONS*********************
// void insert( x )       --> Insert x  - using internal method insert(x, t)
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present - using internal methods contains(x, t)
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// int height( )          --> Return height of the tree - using internal methods height(t)
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ********************************************************

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {

    /** The tree root. */
    private BinaryNode<AnyType> root;

    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
        // Properties
        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child

        // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }
    }

    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     *  insert an element in the binary search tree
     */
    public void insert(AnyType x){
        root = insert(x, root);
    }

    /**
     * Internal method to insert an element into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t){

        if(t == null){
            return new BinaryNode<>(x, null, null);
        }

        int compareResult = x.compareTo(t.element);

        if(compareResult < 0){
            t.left = insert(x, t.left);
        }else if(compareResult > 0){
            t.right = insert(x, t.right);
        }

        // duplicate element do nothing
        return t;
    }

    /**
     *  Remove an element in the binary search tree
     */
    public void remove(AnyType x){
        remove(x, root);
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t){
        if(t == null){
            return t;
        }
        // recursively to find element ot be deleted
        int compareResult = x.compareTo(t.element);
        if(compareResult < 0){
            t.left = remove(x, t.left);
        }else if(compareResult > 0){
            t.right = remove(x, t.right);
        }else{
            // found the element to remove, do something
            if(t.left != null && t.right != null){
                // Two children - replace node with smallest in right subtree
                //left most element in the right subtree
                BinaryNode<AnyType> node = t.right;
                while(node.left != null){
                    node = node.left;
                }
                //replace element
                t.element = node.element;
                //remove element in right subtree
                t.right = remove(node.element, t.right);
            }else{
                //one child or no children
                t = (t.left == null)? t.right: t.left;
            }
        }

        return t;
    }

    /**
     *  check if the tree contains a node
     */
    public boolean contains(AnyType x){
        return contains(x, root);
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains(AnyType x, BinaryNode<AnyType> t){

        if(t == null){
            return false;
        }

        int compareResult = x.compareTo(t.element);
        if(compareResult < 0){
            return contains(x, t.left);
        }else if(compareResult > 0){
            return contains(x, t.right);
        }else{
            return true;
        }
    }

    /**
     *  Find largest element of the tree
     */
    public AnyType findMax(){
        if(isEmpty()){
            throw new RuntimeException("There is no elements in the tree!");
        }

        BinaryNode<AnyType> node = root;
        //right most element in the tree
        while(node.right != null){
            node = node.right;
        }

        return node.element;
    }

    /**
     *  Find smallest element of the tree
     */
    public AnyType findMin(){
        if(isEmpty()){
            throw new RuntimeException("There is no elements in the tree!");
        }

        BinaryNode<AnyType> node = root;
        //left most element in the tree
        while(node.left != null){
            node = node.left;
        }

        return node.element;
    }

    /**
     *  compute height of a subtree
     */
    public int height(){
        return height(root);
    }

    private int height(BinaryNode<AnyType> t){
        //base case
        if(t == null){
            return -1;
        }

        int left = height(t.left);
        int right = height(t.right);
        //height is 1 + max height of left and right subtree
        return 1 + Math.max(left, right);
    }

    /**
     *  make tree empty
     */
    public void makeEmpty(){
        root = null;
    }

    /**
     *  check if tree is empty or not
     */
    public boolean isEmpty(){
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree(root);
    }

    /**
     * Internal method to print a subtree in sorted order - inorder traversal.
     * @param t the node that roots the subtree.
     */
    private void printTree(BinaryNode<AnyType> t)
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.print( t.element + " ");
            printTree( t.right );
        }
    }

    // Test program
    public static void main( String [ ] args )
    {
        BinarySearchTree<Integer> t = new BinarySearchTree<>( );

        Random generator = new Random(0);
        int NUMS = 20;
        int RANGE = 100;
        ArrayList<Integer> treeElements = new ArrayList<>();

        System.out.println( "=====================================================================" );
        System.out.println( "Generate Tree by Insert: " );

        for( int i = 0; i < NUMS; i++){
            int element = generator.nextInt(RANGE);
            treeElements.add(element);
            t.insert(element);
        }

        t.printTree();
        System.out.println();

        System.out.println( "=====================================================================" );
        System.out.println( "Testing remove: " );
        int REMOVE_TIMES = 5;
        Random rand = new Random(0);
        for(int i = 0; i < REMOVE_TIMES; i++){
            int randomElement = treeElements.get(rand.nextInt(treeElements.size()));
            treeElements.removeAll(Collections.singleton(randomElement));
            System.out.println("After removing " + randomElement + " : ");
            t.remove(randomElement);
            t.printTree();
            System.out.println();
        }

        System.out.println( "=====================================================================" );
        System.out.println( "Testing contains: " );
        int TEST_TIMES = 3;
        t.printTree();
        System.out.println();

        for(int i = 0; i < TEST_TIMES; i++){
            int containElement = treeElements.get(rand.nextInt(treeElements.size()));
            System.out.println("Contains " + containElement + " ? " + t.contains(containElement));
            int randomElement = rand.nextInt(RANGE);
            System.out.println("Contains " + randomElement + " ? " + t.contains(randomElement));
        }

        System.out.println( "=====================================================================" );
        System.out.println( "Testing findMin, findMax, height: " );
        t.printTree();
        System.out.println();
        System.out.println("findMax: " + t.findMax());
        System.out.println("findMin: " + t.findMin());
        System.out.println("height: " + t.height());

    }
}
