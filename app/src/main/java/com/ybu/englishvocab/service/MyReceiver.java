package com.ybu.englishvocab.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ybu.englishvocab.models.Vocab;

import java.util.ArrayList;

public class MyReceiver extends BroadcastReceiver {
    private static String TAG = "MyReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getStringExtra("action");
        int id =  intent.getIntExtra("id",-1);
        Log.d(TAG, "action " + action + " vocab: "+ id);

        if(action.equalsIgnoreCase("memorized")){
            DatabaseHelper db = new DatabaseHelper(context);
            Boolean result = db.deleteWordFromMyList(id);
            Log.d(TAG, "result " + result + " vocab: "+ id);
            db.close();
        }
        else if(action.equalsIgnoreCase("remind_later")){

        }


    }
}
