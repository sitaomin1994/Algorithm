/**
 * This class implemented shell sort which is a specific type of insertion sort
 */

public class ShellSorter {

    public static void sort(int[] a){

        int N = a.length;
        int h = 1;
        while(h < N/3) h = 3*h+1;  //get h sequence 1 4 13 40 121 ....

        while(h>=1){

            /*modified insertion sort*/
            for(int i = h; i< a.length ; i++){

                int next = a[i];
                int j = i;
                // move all larger item up
                while((j-h) >= 0 && a[j-h] > next){
                      a[j] = a[j-h];
                      j = j - h;
                }
                a[j] = next;
            }

            h = h/3;
        }

    }
}
