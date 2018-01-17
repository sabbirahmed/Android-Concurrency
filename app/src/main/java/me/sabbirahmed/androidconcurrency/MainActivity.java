package me.sabbirahmed.androidconcurrency;

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

    ExecutorService mExecutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScroll = findViewById(R.id.scrollViewLog);
        mTextView = findViewById(R.id.textViewLog);
        mProgressBar = findViewById(R.id.progressBar);

        mExecutor = Executors.newFixedThreadPool(5);

    }

    public void runCode(View view) {

        for (int i = 0; i < 10; i++){
            Runnable worker = new BackgroundTask(i);
            mExecutor.execute(worker);
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

}
