package me.sabbirahmed.androidconcurrency;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import me.sabbirahmed.androidconcurrency.services.MyIntentService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ScrollView mScroll;
    private TextView mTextView;
    private ProgressBar mProgressBar;

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra(MyIntentService.MESSAGE_KEY);
            log(message);
        }
    };

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
        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(mReceiver, new IntentFilter(MyIntentService.SERVICE_MESSAGE));
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(getApplicationContext())
                .unregisterReceiver(mReceiver);
    }

    public void runCode(View view) {

        log("Running Code");

        MyIntentService.startActionFoo(this, "Value 1", "Value 2");
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
