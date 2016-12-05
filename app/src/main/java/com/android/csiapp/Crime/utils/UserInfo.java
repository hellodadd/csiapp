package com.android.csiapp.Crime.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.csiapp.Databases.DictionaryProvider;
import com.android.csiapp.Databases.IdentifyProvider;
import com.android.csiapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by user on 2016/11/30.
 */
public class UserInfo {
    private Context mContext;
    private static ArrayList<String> mUser = new ArrayList<String>();
    private static HashMap<String,String> mUserHashMap  = new HashMap<String,String>();

    public UserInfo(Context context){
        this.mContext = context;
    }

    public static void getInitialUser(Context context){
        SharedPreferences prefs = context.getSharedPreferences("InitialDevice", 0);
        String initstatus = prefs.getString("Initial", "0");
        IdentifyProvider identifyProvider = new IdentifyProvider(context);

        if(initstatus.equalsIgnoreCase("1")) {
            mUser = (ArrayList<String>) identifyProvider.queryToGetList();
            mUserHashMap  = (HashMap<String,String>) identifyProvider.queryToGetHashMap();
        }
    }

    public ArrayList<String> getArray() {
        ArrayList<String> result = new ArrayList<String>();
        result = (mUser.size()!=0)?mUser:new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.sceneConductor)));
        return result;
    }

    public static String getUserName(String LoginName) {
        String result = "";
        if(mUserHashMap.size()!=0) result = mUserHashMap.get(LoginName);
        return result;
    }

    public static String getLoginName(String UserName) {
        String result = "";
        if(mUserHashMap.size()!=0) result = (String) valueGetKey(mUserHashMap, UserName);
        return result;
    }

    private static String valueGetKey(Map map, String value) {
        Set set = map.entrySet();
        ArrayList arr = new ArrayList<>();
        Iterator it = set.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            if(entry.getValue().equals(value)) {
                String s = (String) entry.getKey();
                arr.add(s);
            }
        }
        if(arr.size()!=0) return (String) arr.get(0);
        else return "";
    }
}
