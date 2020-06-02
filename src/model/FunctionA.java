package model;

import view.Main;
import java.util.concurrent.locks.Lock;

public class FunctionA implements Runnable {

    public double beginI;
    public double endI;
    private Lock lock;
    private Point secondPoint;
    public static final int FUNCTION_ID = 0;
    private double step;
    private Main frame;
    private int sleepTime;

    public FunctionA(Lock lock, Main frame) {
        this.lock = lock;
        beginI = 0;
        this.endI = 100;
        this.step = 1;
        this.frame = frame;
        sleepTime = 1000;
    }
    public double getY(double x) {
        return 5*x-3;
    }

    @Override
    public void run() {
        double beginX = beginI;
        double endX = endI;
        double tempFx ;
        for (double x = beginX; x <= endX; x += step) {
            tempFx = getY(x);
            tempFx = Math.round(tempFx );
            x = Math.round(x);
            lock.lock();
            try {
                secondPoint = new Point(x, tempFx);
                frame.getGraphic().addValue(FUNCTION_ID, secondPoint);
                frame.getGraphic().repaint();
            } finally {
                lock.unlock();
            }
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
