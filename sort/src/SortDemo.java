import java.util.Arrays;
import java.util.Random;

public class SortDemo {

    public static void main(String[] args){


        int[] a = randomIntArray(20, 1000);
        int[] b;

        System.out.println("Generated array:");
        System.out.println(Arrays.toString(a));

        /* Selection Sort */
        b = a.clone();
        SelectionSorter.sort(b);
        System.out.println("Result After selection sort:");
        System.out.println(Arrays.toString(b));

        /* Insertion Sort */
        b = a.clone();

        System.out.println("Result After insertion sort:");
        System.out.println(Arrays.toString(b));

        /* Bubble Sort */
        b = a.clone();

        System.out.println("Result After bubble sort:");
        System.out.println(Arrays.toString(b));

        /* Merge Sort */
        b = a.clone();

        System.out.println("Result After merge sort:");
        System.out.println(Arrays.toString(b));

        /* Quick Sort */
        b = a.clone();

        System.out.println("Result After quick sort:");
        System.out.println(Arrays.toString(b));

        /* Heap Sort */
        b = a.clone();

        System.out.println("Result After heap sort:");
        System.out.println(Arrays.toString(b));


    }

    public static int[] randomIntArray(int length, int n){

        Random generator = new Random(0);

        int[] a = new int[length];

        for(int i = 0; i < a.length; i++){
            a[i] = generator.nextInt(n);
        }

        return a;
    }

}
