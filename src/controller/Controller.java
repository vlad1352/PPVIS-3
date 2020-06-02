package controller;

import model.FunctionA;
import model.FunctionB;
import view.Main;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class Controller {

    public Main frame;
    private FunctionA functionA;
    private FunctionB functionB;
    private Lock lock;
    private List<Thread> threads;

    public Controller(Main frame, Lock lock) {
        this.frame = frame;
        this.lock = lock;
        this.threads = new ArrayList<>();
        this.functionA = new FunctionA(lock, frame);
        this.functionB = new FunctionB(1, 2, lock, frame);
    }

    public void startFunctionAThread() {
        this.functionA = new FunctionA(lock, frame);
        Thread functionAThread = new Thread(functionA);
        threads.add(functionAThread);
        functionAThread.start();
    }

    public void startFunctionBThread(int n, int k) {
        this.functionB = new FunctionB(n, k, lock, frame);
        Thread functionBThread = new Thread(functionB);
        threads.add(functionBThread);
        functionBThread.start();
    }

    public void stopThreads() {
        for (Thread thread : threads) {
            thread.interrupt();

        }
        threads.clear();
    }

}
