package model;

import view.Main;

import java.util.concurrent.locks.Lock;

public class FunctionB implements Runnable {
    public static final int FUNCTION_ID = 1;
    private int n;
    private int k;
    private Lock lock;
    private Main frame;
    private int sleepTime;
    private Sort sortArray;

    public FunctionB(int n, int k, Lock lock, Main frame) {
        this.n = n;
        this.k = k;
        this.lock = lock;
        this.frame = frame;
        sleepTime = 1000;
    }
    @Override
    public void run() {
        for (int currentSize = 2; currentSize < n; currentSize++) {
            int time = 0;
            for (int currentArrayCount = 1; currentArrayCount < k; currentArrayCount++)
            {
                sortArray = new Sort(currentSize);
                time += sortArray.getSortingTime();
            }
            double averageTime = (double) time / k;
            lock.lock();
            try {
                Point point = new Point((double) currentSize , averageTime);
                frame.getGraphic().addValue(FUNCTION_ID, point);
                frame.getMainPointsTable().addNewPoint(point);
                frame.getGraphic().repaint();
            } finally {
                lock.unlock();
            }
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                currentSize = n;
            }
        }
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
