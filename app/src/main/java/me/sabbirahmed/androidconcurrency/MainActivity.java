package me.sabbirahmed.androidconcurrency;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ScrollView mScroll;
    private TextView mTextView;
    private ProgressBar mProgressBar;
    private MyTask mTask;
    private boolean mTaskRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScroll = findViewById(R.id.scrollViewLog);
        mTextView = findViewById(R.id.textViewLog);
        mProgressBar = findViewById(R.id.progressBar);


    }

    public void runCode(View view) {

        if (mTaskRun && mTask != null){
            mTask.cancel(true);
            mTaskRun = false;
        } else {
            mTask = new MyTask();
            mTask.execute("sabbir", "Rumana", "shuvo", "ashik");
            mTaskRun = true;
        }


    }

    private void log(String message) {
        mTextView.append(message + "\n");
        scrollTextToEnd();
    }

    public void clearOutput(View view) {
        mTextView.setText("");
        scrollTextToEnd();
    }

    private void scrollTextToEnd() {
        mScroll.post(new Runnable() {
            @Override
            public void run() {
                mScroll.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    private void displayProgressBar(boolean display){
        if (display){
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
          mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    class MyTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            for (String value: strings){
                if (isCancelled()){
                    publishProgress("Cancelled");
                    break;
                }
                Log.i("String Value", value);
                publishProgress(value);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Thread all is done";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            log(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            log(s);
        }

        @Override
        protected void onCancelled() {
            log("Task cancel");
        }

        @Override
        protected void onCancelled(String s) {
            log("cancelled" + s);
        }
    }

}
