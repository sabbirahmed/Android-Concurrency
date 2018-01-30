package me.sabbirahmed.androidconcurrency;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "CodeRunner";
    private ScrollView mScroll;
    private TextView mTextView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScroll = findViewById(R.id.scrollViewLog);
        mTextView = findViewById(R.id.textViewLog);
        mProgressBar = findViewById(R.id.progressBar);


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void runCode(View view) {


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
