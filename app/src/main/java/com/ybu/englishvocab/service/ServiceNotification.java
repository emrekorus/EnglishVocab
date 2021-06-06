package com.ybu.englishvocab.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.ybu.englishvocab.R;
import com.ybu.englishvocab.models.Vocab;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ServiceNotification extends Service {
    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    private final static String default_notification_channel_id = "default";
    Timer timer;
    TimerTask timerTask;
    String TAG = "ServiceNotification";
    int Your_X_SECS = 500;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        super.onStartCommand(intent, flags, startId);
        startTimer();
        return START_STICKY;
    }


    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        /*stopTimerTask() ;
        super .onDestroy() ;*/
    }

    //we are going to use a handler to be able to run in our TimerTask
    final Handler handler = new Handler();

    public void startTimer() {
        timer = new Timer();
        initializeTimerTask();
        timer.schedule(timerTask, 120000, Your_X_SECS * 1000); //
    }

    public void stopTimerTask() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        createNotification();
                    }
                });
            }
        };
    }

    private void createNotification() {

        Vocab vocab = selectRandomVocab();
        Notification.MediaStyle style = new Notification.MediaStyle();

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), default_notification_channel_id);
        mBuilder.setContentTitle(vocab.getWord() + " - " + vocab.getType());
        mBuilder.setContentText(vocab.getDescription());
        mBuilder.setTicker(vocab.getDescription());
        mBuilder.setSmallIcon(R.drawable.ic_notifications_black_24dp);
        mBuilder.setAutoCancel(true);
        mBuilder.addAction(R.drawable.ic_save, "Memorized", makePendingIntent("memorized", vocab.getId()));
        mBuilder.addAction(R.drawable.ic_time, "Remind Later", makePendingIntent("remind_later", vocab.getId()));
        // mBuilder.setContentIntent(resultPendingIntent);
        style.setShowActionsInCompactView(0);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
            assert mNotificationManager != null;
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
        assert mNotificationManager != null;
        mNotificationManager.notify((int) System.currentTimeMillis(), mBuilder.build());
    }

    public PendingIntent makePendingIntent(String name, Integer id) {
/*
        Intent resultIntent = new Intent(this, MyReceiver.class);
        resultIntent.setAction("NOTIFICATION");
        resultIntent.putExtra("action",name);
        // Create the TaskStackBuilder and add the intent, which inflates the back stack
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(resultIntent);
        // Get the PendingIntent containing the entire back stack
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
*/
        Log.d(TAG, "vocab_id: " + id);
        Intent intent = new Intent(getApplicationContext(), MyReceiver.class);
        intent.putExtra("action", name);
        intent.putExtra("id", id);
        //intent.setAction("NOTIFICATION");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_CANCEL_CURRENT);
        return pendingIntent;
    }

    /*
     * returns random vocab from user own vocab list
     */
    private Vocab selectRandomVocab() {
        DatabaseHelper db = new DatabaseHelper(this);
        ArrayList<Integer> myVocabList_ids = db.getMyList();
        Log.d(TAG, "myVocabList: " + myVocabList_ids.toString());
        ArrayList<Vocab> myVocabList = new ArrayList<Vocab>();

        for (int i = 0; i < myVocabList_ids.size(); i++) {
            myVocabList.add(db.getVocab(Long.valueOf(myVocabList_ids.get(i))));
        }
        int index = (int) (Math.random() * myVocabList.size());
        return myVocabList.get(index);
    }

}