/**
 * This class implement the 3 way partition algorithm for quick sort
 * It divide an array to three part [ < v | - v| > v ]
 * It is also known as Dutch Flags problem see https://leetcode.com/problems/sort-colors/
 *
 * The basic idea is use 3 pointers, begin, curr, ends and swap them [begin | curr | end]
 * one pass if curr < end
 * a[curr] < pivot - swap curr and begin, curr++, begin++
 * a[curr] = pivot - curr++
 * a[curr] > pivot - swap curr and end, end--
 * so every time, larger item will be swap to the end, smaller item will be swap to the begining
 * Also, every time when curr++, begin either++ or stay so [begin - curr] contains item = pivot
 */


public class ThreeWayPartition {

    public static int[] partition(int[] arr, int left, int right, int pivot) {
        int begin = left;
        int end = right;
        int curr = left;

        while (curr <= end) {
            if (arr[curr] < pivot) {
                swap(arr, begin++, curr++);
            } else if (arr[curr] > pivot) {
                swap(arr, end--, curr);
            } else {
                curr++;
            }
        }
        return new int[] { begin, end };
    }

    // for test
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // for test
    public static int[] generateArray() {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 3);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] test = generateArray();

        printArray(test);
        int[] res = partition(test, 0, test.length - 1, 1);
        printArray(test);
        System.out.println(res[0]);
        System.out.println(res[1]);

    }



}
