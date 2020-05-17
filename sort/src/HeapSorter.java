import java.util.Arrays;
import java.util.PriorityQueue;

/**
 *  This class implement heap sort, details for heap can be found in heap module
 */

public class HeapSorter {

    public static void sort(int[] a){

        // build heap
        Integer[] b = Arrays.stream(a).boxed().toArray( Integer[]::new );
        PriorityQueue<Integer> pq = new PriorityQueue<>(Arrays.asList(b));

        // detail implement of binary heap can be see in heap module
        for(int i = 0; i < a.length; i++){
            a[i] = pq.remove();
        }
    }
}
