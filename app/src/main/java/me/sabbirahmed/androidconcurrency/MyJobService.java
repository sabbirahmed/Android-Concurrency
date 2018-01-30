package me.sabbirahmed.androidconcurrency;

import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class MyJobService extends JobService {

    public static final String TAG = "CodeRunner";

    public MyJobService() {
    }

    @Override
    public boolean onStartJob(final JobParameters jobParameters) {
        Log.i(TAG, "onStartJob");

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Log.i(TAG, "Job complete");
                LocalBroadcastManager.getInstance(getApplicationContext())
                        .sendBroadcast(new Intent("Service"));
                jobFinished(jobParameters, false);
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.i(TAG, "onStopJob");
        return false;
    }

}
