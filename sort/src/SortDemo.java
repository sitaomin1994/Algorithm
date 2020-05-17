import java.util.Arrays;
import java.util.Random;

public class SortDemo {

    public static void main(String[] args){


        int[] arr = randomIntArray(10, 1000);
        int[] arr2 = arr.clone();
        Arrays.sort(arr2);
        int[] arr3;
        boolean testResult;

        System.out.println("Generated array:" + Arrays.toString(arr));
        System.out.println("Sorted array:" + Arrays.toString(arr2));

        /* Selection Sort */
        arr3 = arr.clone();
        SelectionSorter.sort(arr3);
        testResult = isEqual(arr2, arr3);
        System.out.println("Result after selection sort: " + Arrays.toString(arr3) + " Correctness: " + testResult);

        /* Insertion Sort */
        arr3 = arr.clone();
        InsertionSorter.sort(arr3);
        testResult = isEqual(arr2, arr3);
        System.out.println("Result after insertion sort: " + Arrays.toString(arr3) + " Correctness: " + testResult);


        /* Shell Sort */
        arr3 = arr.clone();
        ShellSorter.sort(arr3);
        testResult = isEqual(arr2, arr3);
        System.out.println("Result after Shell sort: " + Arrays.toString(arr3) + " Correctness: " + testResult);


        /* Bubble Sort */
        arr3 = arr.clone();
        BubbleSorter.sort(arr3);
        testResult = isEqual(arr2, arr3);
        System.out.println("Result after bubble sort: " + Arrays.toString(arr3) + " Correctness: " + testResult);


        /* Merge Sort */
        arr3 = arr.clone();
        MergeSorter.sort(arr3);
        testResult = isEqual(arr2, arr3);
        System.out.println("Result after merge sort: " + Arrays.toString(arr3) + " Correctness: " + testResult);


        /* Quick Sort */
        arr3 = arr.clone();
        QuickSorter.quickSort(arr3);
        testResult = isEqual(arr2, arr3);
        System.out.println("Result after quick sort: " + Arrays.toString(arr3) + " Correctness: " + testResult);


        /* Heap Sort */
        arr3 = arr.clone();
        HeapSorter.sort(arr3);
        testResult = isEqual(arr2, arr3);
        System.out.println("Result after heap sort: " + Arrays.toString(arr3) + " Correctness: " + testResult);


    }

    public static int[] randomIntArray(int length, int n){

        Random generator = new Random(0);

        int[] a = new int[length];

        for(int i = 0; i < a.length; i++){
            a[i] = generator.nextInt(n);
        }

        return a;
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }

        if (arr1 == null) {
            return true;
        }

        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

}
