package com.android.csiapp.Crime.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

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

    public static final String mSceneConductor = "SceneConductor";
    public static final String mAccessInspectors = "AccessInspectors";
    public static final String mExtractionPeople = "ExtractionPeople";

    private static ArrayList<String> mUser = new ArrayList<String>();
    private static HashMap<String,String> mUserHashMap  = new HashMap<String,String>();
    private static HashMap<String,String> mUserParentHashMap = new HashMap<String,String>();
    public static ArrayList<String> mLogin = new ArrayList<String>();
    public static ArrayList<Integer> mUserNodes = new ArrayList<Integer>();

    public UserInfo(Context context){
        this.mContext = context;
    }

    public static void getInitialUser(Context context){
        SharedPreferences prefs = context.getSharedPreferences("InitialDevice", 0);
        String initstatus = prefs.getString("Initial", "0");
        IdentifyProvider identifyProvider = new IdentifyProvider(context);

        if(initstatus.equalsIgnoreCase("1")) {
            mUser = (ArrayList<String>) identifyProvider.queryToGetUser();
            mUserHashMap  = (HashMap<String,String>) identifyProvider.queryToGetHashMap();
            mLogin = (ArrayList<String>) identifyProvider.queryToGetLogin();
            mUserNodes = getTreeNodes(mLogin);
        }
    }

    private static ArrayList<Integer> getTreeNodes(ArrayList<String> mDicitonary){
        //Anita test
        ArrayList<Integer> DEMO_NODES = new ArrayList<Integer>();
        for(int z = 0; z<mDicitonary.size(); z++){
            int level=0;
            DEMO_NODES.add(level);
        }
        return DEMO_NODES;
        //Anita test
    }

    public String getTitle(String rootkey) {
        String title = "";
        switch (rootkey) {
            case mSceneConductor:
                title = mContext.getResources().getString(R.string.scene_conductor);
                break;
            case mAccessInspectors:
                title = mContext.getResources().getString(R.string.access_inspectors);
                break;
            case mExtractionPeople:
                title = mContext.getResources().getString(R.string.extraction_people);
                break;
            default:
                break;
        }
        return title;
    }

    public String getMethod(String rootkey){
        String method = "Multiple";
        return method;
    }

    public static ArrayList<Integer> getNodes(String rootkey) {
        return mUserNodes;
    }

    public static ArrayList<String> getLoginNameList(String rootkey) {
        return mLogin;
    }

    public ArrayList<String> getArray() {
        ArrayList<String> result = new ArrayList<String>();
        result = (mUser.size()!=0)?mUser:new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.sceneConductor)));
        return result;
    }

    public static String getLoginName(String UserName) {
        String result = "";

        if(UserName.equalsIgnoreCase("")) return result;

        String[] item =  UserName.split(",");
        boolean isFirst = true;
        for(int i = 0;i<item.length;i++){
            if(isFirst) {
                isFirst = false;
            }else {
                result = result+",";
            }
            if(mUserHashMap.size()!=0) result = result+valueGetKey(mUserHashMap, item[i].trim());
        }
        return result;
    }

    public static String getUserName(String LoginName) {
        String result = "";

        if(LoginName.equalsIgnoreCase("")) return result;

        String[] item =  LoginName.split(",");
        boolean isFirst = true;
        for(int i = 0;i<item.length;i++){
            if(isFirst) {
                isFirst = false;
            }else {
                result = result+",";
            }
            if(mUserHashMap.size()!=0) result = result+mUserHashMap.get(item[i].trim());
        }
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
