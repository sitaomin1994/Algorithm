import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 *  Implements a priority queue of integers using a min-heap represented as an array.
 */

public class BinaryHeapInt {

    private static final int DEFAULT_CAPACITY = 10;
    private int currentSize;             // number of elements in heap
    private int[] array;             // The heap array

    //****************************************************************************************
    // constructor for heap
    //****************************************************************************************

    /**
     * Default Constructor - construct by default capacity
     */
    public BinaryHeapInt(){
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructor of Binary Heap
     * @param capacity the capacity of binary heap
     */
    public BinaryHeapInt(int capacity){
        this.currentSize = 0;
        this.array = new int[capacity + 1 ];

    }

    /**
     * Constructs from an array
     * @param items the array of items
     */
    public BinaryHeapInt(int[] items)
    {
        this.currentSize = items.length;
        this.array = new int[items.length+1];

        int i = 1;
        for(int item : items)
            this.array[i++ ] = item;

        buildHeap();
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
    public void insert(int x){

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
    public int findMin(){
        if(isEmpty())
            throw new NoSuchElementException();
        return this.array[1];
    }

    /**
     * Remove the smallest item from the priority queue.
     * @return the smallest item, or throw an UnderflowException if empty.
     */
    public int deleteMin(){

        if(isEmpty())
            throw new NoSuchElementException();

        int minItem = this.array[1];
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
        int tmp = array[hole];      // temp for exchange element of hole

        while(hole <= this.currentSize/2){

            child = hole*2;

            // find the position of min children
            if (child != this.currentSize){
                if(this.array[child + 1] < this.array[child]){
                    minChildren = child + 1;
                }else{
                    minChildren = child;
                }
            }else{
                minChildren = child;
            }

            // exchange hole temp with min children if necessary
            if(this.array[minChildren] < tmp){
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
        int tmp = array[hole];      // temp for exchange element of hole

        while(hole/2 >= 1){

            parent = hole/2;

            // exchange hole temp with parent if necessary
            if(this.array[parent] > tmp){
                this.array[hole] = this.array[parent];
                this.array[parent] = tmp;
                hole = parent;

            }else{
                break;
            }

        }

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
    private void enlargeArray(int newSize)
    {
        int [] old = array;
        array = new int[newSize];
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
}
