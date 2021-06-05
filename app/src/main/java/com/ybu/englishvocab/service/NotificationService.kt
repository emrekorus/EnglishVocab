package com.ybu.englishvocab.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.widget.Toast


class NotificationService : Service() {

    val TAG = "NotificationService"

    override fun onBind(intent: Intent): IBinder? {
        return null
    }


    override fun onCreate() {
        Log.d(TAG, "onCreate: Started.");
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: Started.");
        Thread {
            var a = 0;
            while (true) {
                Log.d(TAG, "onStartCommand: Started." + a.toString());
                Thread.sleep(5000)
                a++;
            }

        }.start()


        /*
        val handler = Handler(Looper.getMainLooper())
        handler.post(Runnable {
            while (true){
                Toast.makeText(
                    this,
                    "My Awesome service toast...",
                    Toast.LENGTH_SHORT
                ).show()
                Thread.sleep(5000)
            }
        })*/
        return START_STICKY
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: Started.");
        super.onDestroy()
    }

    /*
    //OLD NOTIFICATION
    val TAG = "NotificationService"
    private val CHANNEL_ID = "channel_id_english_vocab_app"
    private val notificationId = 101

    override fun onBind(intent: Intent): IBinder? {
        return null
    }


    override fun onTaskRemoved(rootIntent: Intent) {
        val restartServiceIntent = Intent(applicationContext, this.javaClass)
        restartServiceIntent.setPackage(packageName)
        startService(restartServiceIntent)
        super.onTaskRemoved(rootIntent)
    }

    override fun onCreate() {
        Log.d(TAG, "onCreate: Started.");
        createNotificationChannel()
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: Started.");
        sendNotification()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(TAG, "onStartCommand: Started.");
        super.onDestroy()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notification Title"
            val descriptionText = "Notification Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager =
                baseContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification() {
        val builder = NotificationCompat.Builder(baseContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Welcome")
            .setContentText("Welcome to EnglishVocab Application...")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(baseContext)) {
            notify(notificationId, builder.build())
        }
    }*/
}