import java.util.Arrays;

/**
 *  Insertion Sort - begin from [0 .. 0]
 *  Every time insert next element to the proper position of previous sorted sub array
 *
 *  Example:
 *  [1, 3, 4 ] 2 ] -> [1, _, 3, 4] 2 -> [1, 2, 3, 4]
 */
public class InsertionSorter {

    /**
     * sort for insertion sort
     * @param a the array to sort
     */
    public static void sort(int[] a){

        for(int i = 1; i< a.length; i++){
            int next = a[i];

            // Move all larger element up
            int j = i;
            while(j > 0 && a[j-1]> next){
                a[j] = a[j-1];
                j--;
            }
            a[j] = next;
        }
    }
}
