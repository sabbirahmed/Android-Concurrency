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

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String MESSAGE_KEY = "message_key";
    private ScrollView mScroll;
    private TextView mTextView;
    private ProgressBar mProgressBar;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScroll = findViewById(R.id.scrollViewLog);
        mTextView = findViewById(R.id.textViewLog);
        mProgressBar = findViewById(R.id.progressBar);

        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                String message = bundle.getString(MESSAGE_KEY);
                log(message);
                displayProgressBar(false);
            }
        };

    }

    public void runCode(View view) {
        log("Run code");
        displayProgressBar(true);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "Run: starting thread after 4 seconds");
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString(MESSAGE_KEY, "Thread is complete");
                message.setData(bundle);
                mHandler.sendMessage(message);
            }
        };

//        Handler handler = new Handler();
//        handler.postDelayed(runnable, 3000);

        Thread thread = new Thread(runnable);
        thread.start();

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
