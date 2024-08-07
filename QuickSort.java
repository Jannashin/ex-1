import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    public static void main(String[] args) {
        Random rand = new Random();
        int[] array = {3, 6, 8, 10, 1, 2, 1};

        quicksort(array, 0, array.length - 1);
        System.out.println("Sorted array: " + Arrays.toString(array));
        
        int[] sizes = {1000, 5000, 10000, 50000, 100000, 500000, 1000000};
        for (int size : sizes) {
            int[] randomArray = new int[size];
            for (int i = 0; i < size; i++) {
                randomArray[i] = rand.nextInt(size);
            }
            
            long startTime = System.nanoTime();
            quicksort(randomArray, 0, randomArray.length - 1);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000;
            
            System.out.println("Array size: " + size + " | Time taken: " + duration + " ms");
        }
    }

    public static void quicksort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quicksort(array, low, pivotIndex - 1);
            quicksort(array, pivotIndex + 1, high);
        }
    }

    public static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
