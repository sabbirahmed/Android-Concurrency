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

    private MyService mService;

    private final ServiceConnection mServiceCon = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyService.ServiceBinder serviceBinder = (MyService.ServiceBinder) iBinder;
            mService = serviceBinder.getService();

            Log.i(TAG, "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            if (mService != null){
                mService = null;
            }

            Log.i(TAG, "onServiceDisconnected");
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
        Intent serviceIntent = new Intent(this, MyService.class);
        bindService(serviceIntent, mServiceCon, Context.BIND_AUTO_CREATE);
        Log.i(TAG, "onStart: Service bind");
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(mServiceCon);
        Log.i(TAG, "onStop: Service stop");
    }

    public void runCode(View view) {

        log(mService.getValue());

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
