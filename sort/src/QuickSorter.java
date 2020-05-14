import java.util.Arrays;

/**
 *  This class is the implementation of Quick sort
 *  steps recursively sort sub array
 *  - randomly choose an pivot v and swap to first position of array
 *  - three-way partition the array to [< v| = v | >v]
 *  - recursively sort [<v|] [|>v] part
 *
 *  It is a reverse process of merge sort, we partition first then sort subarray, for merge sort
 *  we split subarray first and then merge them
 */
public class QuickSorter {

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * Quick sort algorithm [left, ......, right]
     * @param arr - array to be sort
     * @param left - left boundary of array
     * @param right - right boundary of array
     */

    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            // randomly swap an item to the end
            swap(arr, left + (int) (Math.random() * (right - left + 1)), right);
            // three-way partition
            int[] p = partition(arr, left, right);
            //recursively sort sub-array
            quickSort(arr, left, p[0] - 1);
            quickSort(arr, p[1] + 1, right);
        }
    }

    public static int[] partition(int[] arr, int left, int right) {

        int begin = left;
        int end = right-1;  // pivot = arr[right] so end is position right-1
        int curr = left;

        while (curr <= end) {
            if (arr[curr] < arr[right]) {
                swap(arr, begin++, curr++);
            } else if (arr[curr] > arr[right]) {
                swap(arr, end--, curr);
            } else {
                curr++;
            }
        }
        // swap the first item > pivot with pivot
        swap(arr, end+1, right);
        // return boundary
        return new int[] { begin, end+1};
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
