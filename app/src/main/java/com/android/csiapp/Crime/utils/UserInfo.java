package com.android.csiapp.Crime.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.csiapp.Databases.DictionaryProvider;
import com.android.csiapp.Databases.IdentifyProvider;
import com.android.csiapp.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by user on 2016/11/30.
 */
public class UserInfo {
    private Context mContext;
    private static ArrayList<String> mUser = new ArrayList<String>();

    public UserInfo(Context context){
        this.mContext = context;
    }

    public static void getInitialUser(Context context){
        SharedPreferences prefs = context.getSharedPreferences("InitialDevice", 0);
        String initstatus = prefs.getString("Initial", "0");
        IdentifyProvider identifyProvider = new IdentifyProvider(context);

        if(initstatus.equalsIgnoreCase("1")) {
            mUser = (ArrayList<String>) identifyProvider.queryToGetList();
        }
    }

    public ArrayList<String> getArray() {
        ArrayList<String> result = new ArrayList<String>();
        result = (mUser.size()!=0)?mUser:new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.sceneConductor)));
        return result;
    }
}
