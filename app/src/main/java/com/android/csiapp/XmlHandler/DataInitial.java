package com.android.csiapp.XmlHandler;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.SystemProperties;
import android.util.Log;

import com.android.csiapp.Databases.CrimeItem;
import com.android.csiapp.Databases.CrimeProvider;
import com.android.csiapp.Databases.IdentifyProvider;
import com.android.csiapp.XmlHandler.Dictionary;
import com.android.csiapp.XmlHandler.User;
import com.android.csiapp.XmlHandler.XmlHandler;
import com.amap.api.maps.MapsInitializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2016/11/1.
 */
public class DataInitial {
    private Context mContext = null;

    //Page 1
    private HashMap<String,String> vParentData = new HashMap<>();
    private HashMap<String, HashMap<String,List<String>>> mCasetypeData = new HashMap<>();
    private HashMap<String,String> mAreaParentData = new HashMap<>();
    private HashMap<String, HashMap<String,List<String>>> mAreaData = new HashMap<>();

    public DataInitial(Context context){
        this.mContext = context;
    }

    //Command 1
    public void createDeviceMsgXml() {
        XmlHandler xmlhandler = new XmlHandler();
        String deviceid = (SystemProperties.get("ro.serialno"));
        String initstatus = "0";
        String swversion = "";
        String mapversion= MapsInitializer.getVersion();

        SharedPreferences prefs = mContext.getSharedPreferences("InitialDevice", 0);
        initstatus = prefs.getString("Initial", "0");

        try {
            PackageManager manager = mContext.getPackageManager();
            PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), 0);
            swversion = info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("Anita","initstatus = "+initstatus+", mapversion = "+mapversion);
        xmlhandler.createDeviceMsg(deviceid, initstatus, swversion, mapversion);
    }

    //Command 2
    public boolean InitialDevice(){
        XmlHandler xmlhandler = new XmlHandler();
        Object[] object = xmlhandler.getInitialDeviceCmd();

        if(object == null && object.length!=2) return false;

        List<Dictionary> dictionarys = (List<Dictionary>) object[0];
        List<User> users = (List<User>) object[1];

        //Page 1
        List<Dictionary> mCasetype = new ArrayList<>();
        List<Dictionary> mArea = new ArrayList<>();
        List<Dictionary> mSceneCondition = new ArrayList<>();
        List<Dictionary> mWeatherCondition = new ArrayList<>();
        List<Dictionary> mWindDirection = new ArrayList<>();
        List<Dictionary> mIlluminationCondition = new ArrayList<>();

        //Page 2
        List<Dictionary> mSex = new ArrayList<>();
        List<Dictionary> mTool_category = new ArrayList<>();
        List<Dictionary> mTool_source = new ArrayList<>();

        //Page 5
        List<Dictionary> mHandEvidence = new ArrayList<>();
        List<Dictionary> mHandGetMethod = new ArrayList<>();
        List<Dictionary> mFootEvidence = new ArrayList<>();
        List<Dictionary> mFootGetMethod = new ArrayList<>();
        List<Dictionary> mToolEvidence = new ArrayList<>();
        List<Dictionary> mToolInfer = new ArrayList<>();
        List<Dictionary> mToolGetMethod= new ArrayList<>();

        //Page 7
        List<Dictionary> mCrimePeopleNumber = new ArrayList<>();
        List<Dictionary> mCrimeMeans = new ArrayList<>();
        List<Dictionary> mCrimeCharacter = new ArrayList<>();
        List<Dictionary> mCrimeEntranceExport = new ArrayList<>();
        List<Dictionary> mCrimeTiming = new ArrayList<>();
        List<Dictionary> mSelectObject = new ArrayList<>();
        List<Dictionary> mCrimeFeature= new ArrayList<>();
        List<Dictionary> mIntrusiveMethod = new ArrayList<>();
        List<Dictionary> mSelectLocation = new ArrayList<>();
        List<Dictionary> mCrimePurpose= new ArrayList<>();

        int count = 0;
        //Prase Dictionary
        for(int i=0;i<dictionarys.size();i++){
            String rootKey = dictionarys.get(i).getRootKey();
            switch (rootKey){
                case "AJXBDM":
                    mCasetype.add(dictionarys.get(i));
                    break;
                case "GXSDM":
                    mArea.add(dictionarys.get(i));
                    break;
                case "XCTJDM":
                    mSceneCondition.add(dictionarys.get(i));
                    break;
                case "XCKYTQQKDM":
                    mWeatherCondition.add(dictionarys.get(i));
                    break;
                case "XCFXDM":
                    mWindDirection.add(dictionarys.get(i));
                    break;
                case "XCKYGZTJDM":
                    mIlluminationCondition.add(dictionarys.get(i));
                    break;
                case "XBDM":
                    mSex.add(dictionarys.get(i));
                    break;
                case "ZAGJLMDM":
                    mTool_category.add(dictionarys.get(i));
                    break;
                case "ZAGJLYDM":
                    mTool_source.add(dictionarys.get(i));
                    break;
                case "SYHJLXDM":
                    mHandEvidence.add(dictionarys.get(i));
                    break;
                case "ZWTQFFDM":
                    mHandGetMethod.add(dictionarys.get(i));
                    break;
                case "ZJHJLXDM":
                    mFootEvidence.add(dictionarys.get(i));
                    break;
                case "ZJTQFFDM":
                    mFootGetMethod.add(dictionarys.get(i));
                    break;
                case "GJHJZLDM":
                    mToolEvidence.add(dictionarys.get(i));
                    break;
                case "GJTDZLDM":
                    mToolInfer.add(dictionarys.get(i));
                    break;
                case "GJHJTQFFDM":
                    mToolGetMethod.add(dictionarys.get(i));
                    break;
                case "ZARSLDM":
                    mCrimePeopleNumber.add(dictionarys.get(i));
                    break;
                case "ZASDFLDM":
                    mCrimeMeans.add(dictionarys.get(i));
                    break;
                case "AJXZDM":
                    mCrimeCharacter.add(dictionarys.get(i));
                    break;
                case "ZACRKDM":
                    mCrimeEntranceExport.add(dictionarys.get(i));
                    break;
                case "ZASJDFLDM":
                    mCrimeTiming.add(dictionarys.get(i));
                    break;
                case "XZDXFLDM":
                    mSelectObject.add(dictionarys.get(i));
                    break;
                case "ZATDFLDM":
                    mCrimeFeature.add(dictionarys.get(i));
                    break;
                case "QRFSFLDM":
                    mIntrusiveMethod.add(dictionarys.get(i));
                    break;
                case "XZCSFLDM":
                    mSelectLocation.add(dictionarys.get(i));
                    break;
                case "ZADJMDDM":
                    mCrimePurpose.add(dictionarys.get(i));
                    break;
                default:
                    Log.d("Anita","cannot find the root key" + rootKey);
                    count++;
                    break;
            }
        }
        //Todo:need to create provider to save initial data

        //Prase User
        IdentifyProvider mIdentify = new IdentifyProvider(mContext);
        for(int j=0;j<users.size();j++){
            mIdentify.insert(users.get(j));
        }

        Log.d("Anita", "Casetype count = "+mCasetype.size()+"\n" +"Area count = "+mArea.size()+"\n"
                +"SceneCondition count = "+mSceneCondition.size()+"\n" +"WeatherCondition count = "+mWeatherCondition.size()+"\n"
                +"WindDirection count = "+mWindDirection.size()+"\n" +"IlluminationCondition count = "+mIlluminationCondition.size()+"\n"
                +"Sex count = "+mSex.size()+"\n" +"mTool_category count = "+mTool_category.size()+"\n"
                +"mTool_source count = "+mTool_source.size()+"\n" +"mHandEvidence count = "+mHandEvidence.size()+"\n"
                +"mHandGetMethod count = "+mHandGetMethod.size()+"\n" +"mFootEvidence count = "+mFootEvidence.size()+"\n"
                +"mFootGetMethod count = "+mFootGetMethod.size()+"\n" +"mToolEvidence count = "+mToolEvidence.size()+"\n"
                +"mToolInfer count = "+mToolInfer.size()+"\n" +"mToolGetMethod count = "+mToolGetMethod.size()+"\n"
                +"mCrimePeopleNumber count = "+mCrimePeopleNumber.size()+"\n" +"mCrimeMeans count = "+mCrimeMeans.size()+"\n"
                +"mCrimeCharacter count = "+mCrimeCharacter.size()+"\n" +"mCrimeEntranceExport count = "+mCrimeEntranceExport.size()+"\n"
                +"mCrimeTiming count = "+mCrimeTiming.size()+"\n" +"mSelectObject count = "+mSelectObject.size()+"\n"
                +"mCrimeFeature count = "+mCrimeFeature.size()+"\n" +"mIntrusiveMethod count = "+mIntrusiveMethod.size()+"\n"
                +"mSelectLocation count = "+mSelectLocation.size()+"\n" +"mCrimePurpose count = "+mCrimePurpose.size()+"\n");

        SharedPreferences.Editor editor = mContext.getSharedPreferences("InitialDevice", 0).edit();
        editor.putString("Initial", "1");
        editor.commit();

        return true;
    }

    //Command 11
    public boolean CreateBaseMsg(){
        XmlHandler xmlhandler = new XmlHandler();
        String[] object = xmlhandler.getSceneListCmd();

        if(object==null && object.length!=2) return false;

        String name = object[0];
        String password = object[1];

        IdentifyProvider mIdentify = new IdentifyProvider(mContext);
        boolean isCorrect = mIdentify.checkPasswordFromName(name,password);

        if(isCorrect) return false;

        CrimeProvider mCrimeProvider = new CrimeProvider(mContext);
        mCrimeProvider.createBaseMsgXml(-1);

        return true;
    }

    //Command 12
    public void CreateBaseMsgIdZip(int id){
        CrimeProvider mCrimeProvider = new CrimeProvider(mContext);
        CrimeItem crimeItem = mCrimeProvider.get(id);

        if(crimeItem == null) return ;

        mCrimeProvider.createBaseMsgXml(id);

        //Todo:move photo path and to zip
    }

    //Command 13
    public boolean WriteSceneId(){
        XmlHandler xmlhandler = new XmlHandler();
        String[] object = xmlhandler.writeSceneIdCmd();

        if(object == null && object.length==0) return false;

        long id = Integer.parseInt(object[0]);
        String SceneNo = object[1];
        CrimeProvider mCrime = new CrimeProvider(mContext);
        CrimeItem mCrimeItem = mCrime.get(id);

        if(mCrimeItem == null || SceneNo.length() ==0) return false;

        mCrimeItem.setSceneNo(SceneNo);
        mCrime.update(mCrimeItem);

        return true;
    }

    //Command 14
    public boolean deleteSceneInfo(){
        XmlHandler xmlhandler = new XmlHandler();
        String[] object = xmlhandler.deleteSceneInfoCmd();

        if(object == null && object.length==0) return false;

        CrimeProvider mCrime = new CrimeProvider(mContext);
        for(int i=0;i<object.length;i++){
            long id = Integer.parseInt(object[i]);
            if(mCrime.get(id)!=null) {
                mCrime.delete(id);
            }else{
                Log.d("Anita","Cannot get the id from databases");
            }
        }

        return true;
    }
}
