package com.android.csiapp.Crime.utils;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.android.csiapp.Databases.CrimeItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016/12/7.
 */
public class CellCollection{
    private Context context = null;
    private String ACTION_RECEIVE_RESULT = "com.kuaikan.send_result";
    private List<String> result = new ArrayList<String>();

    public CellCollection(Context context) {
        Log.d("Anita", "CellCollection");
        this.context = context;
        startCollection();
    }

    private void startCollection(){
        Log.d("Anita", "startCollection");
        Intent it=new Intent();
        it.setAction("com.kuaikan.one_key");
        it.setComponent(new ComponentName("com.kuaikan.app.scenecollection",
                "com.kuaikan.app.scenecollection.OneKeyService"));
        it.putExtra("request_type", 0);

        context.startService(it);

        IntentFilter filter = new IntentFilter();
        filter.addAction("com.kuaikan.send_result");
        context.registerReceiver(mReceiver,filter);
    }

    private void stopCollection(){
        Log.d("Anita", "stopCollection");
        Intent it=new Intent();
        it.setAction("com.kuaikan.one_key");
        it.setComponent(new ComponentName("com.kuaikan.app.scenecollection",
                "com.kuaikan.app.scenecollection.OneKeyService"));
        it.putExtra("request_type", 0);
        context.stopService(it);
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(ACTION_RECEIVE_RESULT.equals(action)){
                result= intent.getStringArrayListExtra("result");
                stopCollection();
            }
        }
    };

    public List<String> getInfo(){
        return result;
    }
}
