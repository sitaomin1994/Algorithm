import java.util.*;

public class BinaryHeap<AnyType extends Comparable<? super AnyType>>
{

    private static final int DEFAULT_CAPACITY = 10;
    private int currentSize;             // number of elements in heap
    private AnyType[] array;             // The heap array

    //****************************************************************************************
    // constructor for heap
    //****************************************************************************************

    /**
     * Default Constructor - construct by default capacity
     */
    public BinaryHeap(){
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructor of Binary Heap
     * @param capacity the capacity of binary heap
     */
    @SuppressWarnings("unchecked")
    public BinaryHeap(int capacity){
        this.currentSize = 0;
        this.array = (AnyType[]) new Comparable[capacity + 1 ];

    }

    /**
     * Constructs from an array
     * @param items the array of items
     */
    @SuppressWarnings("unchecked")
    public BinaryHeap(AnyType[] items)
    {
        this.currentSize = items.length;
        this.array = (AnyType[]) new Comparable[items.length+1];

        int i = 1;
        for(AnyType item : items)
            this.array[i++ ] = item;

        buildHeap( );
    }

    //****************************************************************************************
    // heap primary methods
    //****************************************************************************************

    /**
     * Establish heap order property from an arbitrary
     * arrangement of items. Runs in linear time.
     */
    private void buildHeap( )
    {
        for(int i = currentSize/2; i>0; i--){
            percolateDown(i);
        }
    }

    /**
     * Insert into the priority queue, maintaining heap order.
     * Duplicates are allowed.
     * @param x the item to insert.
     */
     public void insert(AnyType x){

        if( currentSize == array.length - 1 )
             enlargeArray( array.length * 2 + 1 );

        this.currentSize++;
        this.array[currentSize] = x;

        percolateUp(currentSize);

     }

    /**
     * Find the smallest item in the priority queue.
     * @return the smallest item, or throw an UnderflowException if empty.
     */
     public AnyType findMin(){
         if(isEmpty())
             throw new NoSuchElementException();
         return this.array[1];
     }

    /**
     * Remove the smallest item from the priority queue.
     * @return the smallest item, or throw an UnderflowException if empty.
     */
     public AnyType deleteMin(){

         if(isEmpty())
             throw new NoSuchElementException();

         AnyType minItem = this.array[1];
         this.array[1] = this.array[this.currentSize--];
         percolateDown(1);

         return minItem;
     }

    /**
     * Internal method to percolate down in the heap.
     * @param hole the index at which the percolate begins.
     */
    private void percolateDown(int hole){

        int child;                      // index of child
        int minChildren;                // index of minChildren
        AnyType tmp = array[hole];      // temp for exchange element of hole

        while(hole <= this.currentSize/2){

            child = hole*2;

            // find the position of min children
            if (child != this.currentSize){
                if(this.array[child + 1].compareTo(this.array[child]) < 0){
                    minChildren = child + 1;
                }else{
                    minChildren = child;
                }
            }else{
                minChildren = child;
            }

            // exchange hole temp with min children if necessary
            if(this.array[minChildren].compareTo(tmp) < 0){
                this.array[hole] = this.array[minChildren];
                this.array[minChildren] = tmp;
                hole = minChildren;

            }else{
                break;
            }

        }

    }

    /**
     * Internal method to percolate Up in the heap.
     * @param hole the index at which the percolate begins.
     */
    private void percolateUp(int hole){

        int parent;                     // index of parent
        AnyType tmp = array[hole];      // temp for exchange element of hole

        while(hole/2 >= 1){

            parent = hole/2;

            // exchange hole temp with parent if necessary
            if(this.array[parent].compareTo(tmp) > 0){
                this.array[hole] = this.array[parent];
                this.array[parent] = tmp;
                hole = parent;

            }else{
                break;
            }

        }

    }

    //****************************************************************************************
    //* helpers functions
    //****************************************************************************************

    /**
     * returns index of parent
     */
    private int parent(int index) {
        return index / 2;
    }

    /**
     * returns index of left child of given index
     */
    private int leftChild(int index) {
        return index * 2;
    }

    /**
     * returns index of right child of given index
     */
    private int rightChild(int index) {
        return index * 2 + 1;
    }

    /**
     * returns true if the node at the given index has a parent (is not the root)
     */
    private boolean hasParent(int index) {
        return index > 1;
    }

    /**
     * returns true if the node at the given index has a non-empty left child
     */
    private boolean hasLeftChild(int index) {
        return leftChild(index) <= this.currentSize;
    }

    /**
     * returns true if the node at the given index has a non-empty right child
     */
    private boolean hasRightChild(int index) {
        return rightChild(index) <= this.currentSize;
    }

    /**
     * check whether heap is empty or not
     * @return empty or not
     */
    public boolean isEmpty(){
        return currentSize <= 0;
    }

    /**
     *  make heap logically empty
     */
    public void makeEmpty(){
        this.currentSize = 0;
    }

    /**
     * Returns the number of elements in the heap.
     */
    public int size() {
        return this.currentSize;
    }

    /**
     * enlarge array if current size reaches capacity
     * @param newSize new capacity of array
     */
    @SuppressWarnings("unchecked")
    private void enlargeArray(int newSize)
    {
        AnyType [] old = array;
        array = (AnyType []) new Comparable[newSize];
        System.arraycopy(old, 0, array, 0, old.length);
    }

    /**
     *  Returns a string representation of this queue, such as "[10, 20, 30]";
     *  The elements are not guaranteed to be listed in sorted order.
     */
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        if (!isEmpty()) {
            result.append(this.array[1]);
            for (int i = 2; i <= this.currentSize; i++) {
                result.append(", ").append(this.array[i]);
            }
        }
        return result + "]";
    }

    // Test program
    public static void main(String [] args)
    {

        // generate random data
        int length = 10;
        Random generator = new Random(0);
        int[] elements = new int[length];

        for(int i = 0; i < elements.length; i++){
            elements[i] = generator.nextInt(100);
        }

        //List<Integer> a = new ArrayList<Integer>(Arrays.asList(elements));
        Integer[] newElements = Arrays.stream(elements).boxed().toArray( Integer[]::new );

        // heap
        BinaryHeap<Integer> h = new BinaryHeap<>();
        BinaryHeap<Integer> h2 = new BinaryHeap<>(newElements);

        System.out.println("Heap build from array: " +h2);

        System.out.println("Heap build by insertion: ");

        // Test insertion
        for (int n : elements) {
            System.out.println("Insert element:" + n);
            h.insert(n);
        }

        System.out.println("After Insertion all elements:" + h);

        // Test deleteMin
        while (!h.isEmpty()) {
            System.out.println("Delete Min element:" + h.deleteMin());
            System.out.println("After delete min element:"+ h + ", size "+ h.size());
        }
    }

}
