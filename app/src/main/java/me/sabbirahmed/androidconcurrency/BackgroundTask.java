package me.sabbirahmed.androidconcurrency;

import android.util.Log;

/**
 * Created by oceanize on 1/16/18.
 */

public class BackgroundTask implements Runnable {

    public static final String TAG = "CodRunner";
    private int threadNumber;

    public BackgroundTask(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {

        Log.e(TAG, Thread.currentThread().getName() +
                " start, thread number = " + threadNumber);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.e(TAG, Thread.currentThread().getName() +
                " end, thread number = " + threadNumber);
    }

}
