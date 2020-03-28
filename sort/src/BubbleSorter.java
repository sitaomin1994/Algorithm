/**
 *  Class to implement bubble sort -
 *  every time one element compare and swap to the end
 *  Optimization: if one iteration no swap it means it is already in order we can just break the loop
 */
public class BubbleSorter {
    /**
     * function to perform sort
     * @param a the array to be sort
     */
    public static void sort(int[] a){

        boolean swap = false;
        for(int length = a.length; length > 1; length--) {
            for (int i = 0; i < length-1; i++) {
                if (a[i] > a[i + 1]) {
                    swap(a, i, i+1);
                    swap = true;
                }
            }
            if(swap == false){
                break;
            }
        }
    }

    /**
     * function to perform swap two elements of an array
     * @param a the array
     * @param i first element position
     * @param j second element position
     */
    public static void swap(int[] a, int i, int j){

        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
