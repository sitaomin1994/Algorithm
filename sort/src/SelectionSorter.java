/**
 *  Selection sort class perform selection sort:
 *  Every time select smallest number and swap it with the first element of the array
 */

public class SelectionSorter {

    /**
     * Sort array using selection sort
     * @param a the array to sort
     */
    public static void sort(int[] a){

        int minPosition;

        for(int i = 0; i < a.length; i++){

            minPosition = i;

            for(int j = i; j < a.length; j++){

                if(a[j] < a[minPosition]){
                    minPosition = j;
                }
            }

            swap(a, i, minPosition);

        }
    }

    /**
     * swap two element of an array
     * @param a an array
     * @param i postion of first element
     * @param j position of second element
     */
    public static void swap(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
