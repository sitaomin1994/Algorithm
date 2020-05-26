import java.util.Arrays;
import java.util.Random;

/**
 *  This class implement the algorithm of quick select
 *  Search for kth smallest or largest elememtn in an unordered array
 *  Time complexity: O(n^2) , average: O(n)
 *  Step:
 *  1. pick a pivot and partition array [ <=v | > v]
 *  2. check
 *      - if position of v = k, then return v
 *      - if position of v < k, recursive do it for array[k+1, v]
 *      - if position of v > k, recirsive do it for array[0, k -1]
 */


public class QuickSelect {

    public static int quickSelect(int[] arr, int left, int right, int k){

        // swap pivot to the end of array
        int pivotPosition = left + (int) (Math.random() * (right - left + 1));
        swap(arr, pivotPosition, right);

        //partition the array
        pivotPosition = partition(arr, left, right);

        //check pivotPosition with k

        if(pivotPosition == k){
            return arr[pivotPosition];
        }else if(pivotPosition < k){
            return quickSelect(arr, pivotPosition+1, right, k);
        }else{
            return quickSelect(arr, left, pivotPosition-1, k);
        }
    }

    public static int partition(int[] arr, int left, int right) {

        int boundary = left;        // stores the first element of [|>v]
        int current = left;         // pointer for tranverse the array
        int end = right -1;         // end of array
        int pivot = arr[right];     // pivot element

        while(current <= end){
            if(arr[current] <= pivot){
                swap(arr, current, boundary);
                boundary++;
                current++;
            }else{
                current++;
            }
        }

        swap(arr, boundary, right);  // swap pivot to its position

        return boundary;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args)
    {
        int length = 20;
        int range = 500;
        Random generator = new Random();

        int[] arr = new int[length];
        for(int i = 0; i < arr.length; i++){
            arr[i] = generator.nextInt(range);
        }

        int[] arr2 = arr.clone();
        Arrays.sort(arr2);

        int kPosition = generator.nextInt(arr.length);

        int result = quickSelect(arr, 0, arr.length-1, kPosition-1);

        System.out.println(Arrays.toString(arr2));
        System.out.println(kPosition + "th smallest element:" + result);
    }
}
