package model;

import java.util.Random;

public class Sort {

    private int currentSize;
    private long time;
    private int[] array;


    Sort(int currentSize)
    {
        this.currentSize = currentSize;
        this.time = sortTime(generateArray());
    }

    public long getSortingTime()
    {
        return time;
    }

    private int[] generateArray() {
        array = new int[currentSize];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(currentSize);
        }
        return array;
    }

    private int[] countingSort(int[] array) {
        int max = 0;

        for (int num : array) {
            if (num > max){
                max = num;
            }
        }
        int numCounts[] = new int[max + 1];
        for (int num : array){
            numCounts[num]++;
        }
        int[] sortedArray = new int [array.length];
        int currentSortedIndex = 0;
        for (int n = 0; n < numCounts.length; n++){
            int count = numCounts[n];
            for (int k = 0; k < count; k++){
                sortedArray[currentSortedIndex] = n;
                currentSortedIndex++;
            }
        }
        return  sortedArray;
    }

    public long sortTime(int[] arrayToSort) {
        long startTime = System.nanoTime() / 100;
        countingSort(arrayToSort);
        long endTime = System.nanoTime() / 100;
        long result = endTime - startTime;

        return result;
    }
}
