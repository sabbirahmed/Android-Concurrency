package me.sabbirahmed.androidconcurrency.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "me.sabbirahmed.androidconcurrency.action.FOO";
    private static final String ACTION_BAZ = "me.sabbirahmed.androidconcurrency.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "me.sabbirahmed.androidconcurrency.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "me.sabbirahmed.androidconcurrency.extra.PARAM2";
    public static final String CODE_RUNNER = "CodeRunner";

    public MyIntentService() {
        super("MyIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        Log.i(CODE_RUNNER, "handleActionFoo: Start service");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.i(CODE_RUNNER, "handleActionFoo: End service");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(CODE_RUNNER, "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(CODE_RUNNER, "onDestroy");
    }
}
