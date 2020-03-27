import java.util.Arrays;
/**
 *  Class to perform merge sort:
 *  1. Recursively split array to 2 halves
 *  2. Merge two sorted part
 */
public class MergeSorter {
    /**
     * sort function for merge sort - recursively merge sort
     * @param a the array to be sort
     */
    public static void sort(int[] a){

        if(a.length <= 1){
            return;
        }

        int[] first = Arrays.copyOfRange(a, 0, a.length/2);
        int[] second = Arrays.copyOfRange(a, a.length/2, a.length);

        sort(first);          // sort first
        sort(second);         // sort second
        merge(a, first, second);  // merge first and second to a

    }

    /**
     *  Function to perform merge two sorted arrays
     * @param result orignal array
     * @param a array a
     * @param b array b
     * @return result array
     */
    public static void merge(int[] result, int[] a, int[] b){

        int indexA = 0;  // index pointer for transversing array a
        int indexB = 0;  // index pointer for transversing array b
        int index = 0;   // index pointer for result

        while(indexA < a.length && indexB < b.length){

            if(a[indexA] < b[indexB]){
                result[index] = a[indexA];
                indexA++;
            }else{
                result[index] = b[indexB];
                indexB++;
            }
            index++;
        }

        // if array A left elements then copy them to result
        while(indexA < a.length){
            result[index] = a[indexA];
            indexA++;
            index++;
        }

        // if array B left elements then copy them to result
        while(indexB < b.length){
            result[index] = b[indexB];
            indexB++;
            index++;
        }

    }
}
