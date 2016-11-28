package com.android.csiapp.Crime.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.csiapp.Databases.DictionaryProvider;
import com.android.csiapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by user on 2016/11/7.
 */
public class DictionaryInfo {
    private Context mContext;
    //private static HashMap<String,String> ParentData = new HashMap<>();
    //private static HashMap<String, HashMap<String,List<String>>> Data = new HashMap<>();
    public final static String mAreaKey = "GXSDM";
    public final static String mSceneConditionKey = "XCTJDM";
    public final static String mWeatherConditionKey = "XCKYTQQKDM";
    public final static String mWindDirectionKey = "XCFXDM";
    public final static String mIlluminationConditionKey = "XCKYGZTJDM";
    public final static String mSexKey = "XBDM";
    public final static String mToolCategoryKey = "ZAGJLMDM";
    public final static String mToolSourceKey = "ZAGJLYDM";
    public final static String mEvidenceHandKey = "SYHJLXDM";
    public final static String mMethodHandKey = "ZWTQFFDM";
    public final static String mEvidenceFootKey = "ZJHJLXDM";
    public final static String mMethodFootKey = "ZJTQFFDM";
    public final static String mEvidenceToolKey = "GJHJZLDM";
    public final static String mToolInferKey = "GJTDZLDM";
    public final static String mMethodToolKey = "GJHJTQFFDM";
    public final static String mPeopleNumberKey = "ZARSLDM";
    public final static String mCrimeEntranceExportKey = "ZACRKDM";
    public final static String mCrimeMeansKey = "ZASDFLDM";
    public final static String mCrimeCharacterKey = "AJXZDM";
    public final static String mCrimeTimingKey = "ZASJDFLDM";
    public final static String mSelectObjectKey = "XZDXFLDM";
    public final static String mCrimeFeatureKey = "ZATDFLDM";
    public final static String mIntrusiveMethodKey = "QRFSFLDM";
    public final static String mSelectLocationKey = "XZCSFLDM";
    public final static String mCrimePurposeKey = "ZADJMDDM";

    private static ArrayList<String> mArea = new ArrayList<String>();
    private static ArrayList<String> mSceneCondition = new ArrayList<String>();
    private static ArrayList<String> mWeatherCondition = new ArrayList<String>();
    private static ArrayList<String> mWindDirection = new ArrayList<String>();
    private static ArrayList<String> mIlluminationCondition = new ArrayList<String>();
    private static ArrayList<String> mSex = new ArrayList<String>();
    private static ArrayList<String> mToolCategory = new ArrayList<String>();
    private static ArrayList<String> mToolSource = new ArrayList<String>();
    private static ArrayList<String> mEvidenceHand = new ArrayList<String>();
    private static ArrayList<String> mMethodHand = new ArrayList<String>();
    private static ArrayList<String> mEvidenceFoot = new ArrayList<String>();
    private static ArrayList<String> mMethodFoot = new ArrayList<String>();
    private static ArrayList<String> mEvidenceTool = new ArrayList<String>();
    private static ArrayList<String> mToolInfer = new ArrayList<String>();
    private static ArrayList<String> mMethodTool = new ArrayList<String>();
    private static ArrayList<String> mPeopleNumber = new ArrayList<String>();
    private static ArrayList<String> mCrimeEntranceExport = new ArrayList<String>();
    private static ArrayList<String> mCrimeMeans = new ArrayList<String>();
    private static ArrayList<String> mCrimeCharacter = new ArrayList<String>();
    private static ArrayList<String> mCrimeTiming = new ArrayList<String>();
    private static ArrayList<String> mSelectObject = new ArrayList<String>();
    private static ArrayList<String> mCrimeFeature = new ArrayList<String>();
    private static ArrayList<String> mIntrusiveMethod = new ArrayList<String>();
    private static ArrayList<String> mSelectLocation = new ArrayList<String>();
    private static ArrayList<String> mCrimePurpose = new ArrayList<String>();

    private static HashMap<String,String> mAreaHashMap = new HashMap<String,String>();
    private static HashMap<String,String> mSceneConditionHashMap  = new HashMap<String,String>();
    private static HashMap<String,String> mWeatherConditionHashMap  = new HashMap<String,String>();
    private static HashMap<String,String> mWindDirectionHashMap  = new HashMap<String,String>();
    private static HashMap<String,String> mIlluminationConditionHashMap  = new HashMap<String,String>();
    private static HashMap<String,String> mSexHashMap  = new HashMap<String,String>();
    private static HashMap<String,String> mToolCategoryHashMap  = new HashMap<String,String>();
    private static HashMap<String,String> mToolSourceHashMap  = new HashMap<String,String>();
    private static HashMap<String,String> mEvidenceHandHashMap  = new HashMap<String,String>();
    private static HashMap<String,String> mMethodHandHashMap  = new HashMap<String,String>();
    private static HashMap<String,String> mEvidenceFootHashMap  = new HashMap<String,String>();
    private static HashMap<String,String> mMethodFootHashMap  = new HashMap<String,String>();
    private static HashMap<String,String> mEvidenceToolHashMap  = new HashMap<String,String>();
    private static HashMap<String,String> mToolInferHashMap  = new HashMap<String,String>();
    private static HashMap<String,String> mMethodToolHashMap  = new HashMap<String,String>();
    private static HashMap<String,String> mPeopleNumberHashMap  = new HashMap<String,String>();
    private static HashMap<String,String> mCrimeEntranceExportHashMap  = new HashMap<String,String>();
    private static HashMap<String,String> mCrimeMeansHashMap  = new HashMap<String,String>();
    private static HashMap<String,String> mCrimeCharacterHashMap  = new HashMap<String,String>();
    private static HashMap<String,String> mCrimeTimingHashMap  = new HashMap<String,String>();
    private static HashMap<String,String> mSelectObjectHashMap  = new HashMap<String,String>();
    private static HashMap<String,String> mCrimeFeatureHashMap  = new HashMap<String,String>();
    private static HashMap<String,String> mIntrusiveMethodHashMap  = new HashMap<String,String>();
    private static HashMap<String,String> mSelectLocationHashMap  = new HashMap<String,String>();
    private static HashMap<String,String> mCrimePurposeHashMap  = new HashMap<String,String>();

    public DictionaryInfo(Context context){
        this.mContext = context;
    }

    public static void getInitialDictionary(Context context){
        SharedPreferences prefs = context.getSharedPreferences("InitialDevice", 0);
        String initstatus = prefs.getString("Initial", "0");
        DictionaryProvider dictionaryProvider = new DictionaryProvider(context);

        if(initstatus.equalsIgnoreCase("1")) {
            mArea = (ArrayList<String>) dictionaryProvider.queryToGetList(mAreaKey);
            mSceneCondition = (ArrayList<String>) dictionaryProvider.queryToGetList(mSceneConditionKey);
            mWeatherCondition = (ArrayList<String>) dictionaryProvider.queryToGetList(mWeatherConditionKey);
            mWindDirection = (ArrayList<String>) dictionaryProvider.queryToGetList(mWindDirectionKey);
            mIlluminationCondition = (ArrayList<String>) dictionaryProvider.queryToGetList(mIlluminationConditionKey);
            mSex = (ArrayList<String>) dictionaryProvider.queryToGetList(mSexKey);
            mToolCategory = (ArrayList<String>) dictionaryProvider.queryToGetList(mToolCategoryKey);
            mToolSource = (ArrayList<String>) dictionaryProvider.queryToGetList(mToolSourceKey);
            mEvidenceHand = (ArrayList<String>) dictionaryProvider.queryToGetList(mEvidenceHandKey);
            mMethodHand = (ArrayList<String>) dictionaryProvider.queryToGetList(mMethodHandKey);
            mEvidenceFoot = (ArrayList<String>) dictionaryProvider.queryToGetList(mEvidenceFootKey);
            mMethodFoot = (ArrayList<String>) dictionaryProvider.queryToGetList(mMethodFootKey);
            mEvidenceTool = (ArrayList<String>) dictionaryProvider.queryToGetList(mEvidenceToolKey);
            mToolInfer = (ArrayList<String>) dictionaryProvider.queryToGetList(mToolInferKey);
            mMethodTool = (ArrayList<String>) dictionaryProvider.queryToGetList(mMethodToolKey);
            mPeopleNumber = (ArrayList<String>) dictionaryProvider.queryToGetList(mPeopleNumberKey);
            mCrimeEntranceExport = (ArrayList<String>) dictionaryProvider.queryToGetList(mCrimeEntranceExportKey);
            mCrimeMeans = (ArrayList<String>) dictionaryProvider.queryToGetList(mCrimeMeansKey);
            mCrimeCharacter = (ArrayList<String>) dictionaryProvider.queryToGetList(mCrimeCharacterKey);
            mCrimeTiming = (ArrayList<String>) dictionaryProvider.queryToGetList(mCrimeTimingKey);
            mSelectObject = (ArrayList<String>) dictionaryProvider.queryToGetList(mSelectObjectKey);
            mCrimeFeature = (ArrayList<String>) dictionaryProvider.queryToGetList(mCrimeFeatureKey);
            mIntrusiveMethod = (ArrayList<String>) dictionaryProvider.queryToGetList(mIntrusiveMethodKey);
            mSelectLocation = (ArrayList<String>) dictionaryProvider.queryToGetList(mSelectLocationKey);
            mCrimePurpose = (ArrayList<String>) dictionaryProvider.queryToGetList(mCrimePurposeKey);

            mAreaHashMap  = (HashMap<String,String>) dictionaryProvider.queryToGetHashMap(mAreaKey);
            mSceneConditionHashMap  = (HashMap<String,String>) dictionaryProvider.queryToGetHashMap(mSceneConditionKey);
            mWeatherConditionHashMap  = (HashMap<String,String>) dictionaryProvider.queryToGetHashMap(mWeatherConditionKey);
            mWindDirectionHashMap  = (HashMap<String,String>) dictionaryProvider.queryToGetHashMap(mWindDirectionKey);
            mIlluminationConditionHashMap  = (HashMap<String,String>) dictionaryProvider.queryToGetHashMap(mIlluminationConditionKey);
            mSexHashMap  = (HashMap<String,String>) dictionaryProvider.queryToGetHashMap(mSexKey);
            mToolCategoryHashMap  = (HashMap<String,String>) dictionaryProvider.queryToGetHashMap(mToolCategoryKey);
            mToolSourceHashMap  = (HashMap<String,String>) dictionaryProvider.queryToGetHashMap(mToolSourceKey);
            mEvidenceHandHashMap  = (HashMap<String,String>) dictionaryProvider.queryToGetHashMap(mEvidenceHandKey);
            mMethodHandHashMap  = (HashMap<String,String>) dictionaryProvider.queryToGetHashMap(mMethodHandKey);
            mEvidenceFootHashMap  = (HashMap<String,String>) dictionaryProvider.queryToGetHashMap(mEvidenceFootKey);
            mMethodFootHashMap  = (HashMap<String,String>) dictionaryProvider.queryToGetHashMap(mMethodFootKey);
            mEvidenceToolHashMap  = (HashMap<String,String>) dictionaryProvider.queryToGetHashMap(mEvidenceToolKey);
            mToolInferHashMap  = (HashMap<String,String>) dictionaryProvider.queryToGetHashMap(mToolInferKey);
            mMethodToolHashMap  = (HashMap<String,String>) dictionaryProvider.queryToGetHashMap(mMethodToolKey);
            mPeopleNumberHashMap  = (HashMap<String,String>) dictionaryProvider.queryToGetHashMap(mPeopleNumberKey);
            mCrimeEntranceExportHashMap  = (HashMap<String,String>) dictionaryProvider.queryToGetHashMap(mCrimeEntranceExportKey);
            mCrimeMeansHashMap  = (HashMap<String,String>) dictionaryProvider.queryToGetHashMap(mCrimeMeansKey);
            mCrimeCharacterHashMap  = (HashMap<String,String>) dictionaryProvider.queryToGetHashMap(mCrimeCharacterKey);
            mCrimeTimingHashMap  = (HashMap<String,String>) dictionaryProvider.queryToGetHashMap(mCrimeTimingKey);
            mSelectObjectHashMap  = (HashMap<String,String>) dictionaryProvider.queryToGetHashMap(mSelectObjectKey);
            mCrimeFeatureHashMap  = (HashMap<String,String>) dictionaryProvider.queryToGetHashMap(mCrimeFeatureKey);
            mIntrusiveMethodHashMap  = (HashMap<String,String>) dictionaryProvider.queryToGetHashMap(mIntrusiveMethodKey);
            mSelectLocationHashMap  = (HashMap<String,String>) dictionaryProvider.queryToGetHashMap(mSelectLocationKey);
            mCrimePurposeHashMap  = (HashMap<String,String>) dictionaryProvider.queryToGetHashMap(mCrimePurposeKey);
        }
    }

    public ArrayList<String> getArray(String rootkey){
        ArrayList<String> result = new ArrayList<String>();
        switch (rootkey){
            case mAreaKey:
                result = (mArea.size()!=0)?mArea:new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.area)));
                break;
            case mSceneConditionKey:
                result = (mSceneCondition.size()!=0)?mSceneCondition:new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.sceneCondition)));
                break;
            case mWeatherConditionKey:
                result = (mWeatherCondition.size()!=0)?mWeatherCondition:new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.weatherCondition)));
                break;
            case mWindDirectionKey:
                result = (mWindDirection.size()!=0)?mWindDirection:new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.windDirection)));
                break;
            case mIlluminationConditionKey:
                result = (mIlluminationCondition.size()!=0)?mIlluminationCondition:new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.illuminationCondition)));
                break;
            case mSexKey:
                result = (mSex.size()!=0)?mSex:new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.sex)));
                break;
            case mToolCategoryKey:
                result = (mToolCategory.size()!=0)?mToolCategory:new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.tool_category)));
                break;
            case mToolSourceKey:
                result = (mToolSource.size()!=0)?mToolSource:new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.tool_source)));
                break;
            case mEvidenceHandKey:
                result = (mEvidenceHand.size()!=0)?mEvidenceHand:new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.evidence_hand)));
                break;
            case mMethodHandKey:
                result = (mMethodHand.size()!=0)?mMethodHand:new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.method_hand)));
                break;
            case mEvidenceFootKey:
                result = (mEvidenceFoot.size()!=0)?mEvidenceFoot:new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.evidence_foot)));
                break;
            case mMethodFootKey:
                result = (mMethodFoot.size()!=0)?mMethodFoot:new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.method_foot)));
                break;
            case mEvidenceToolKey:
                result = (mEvidenceTool.size()!=0)?mEvidenceTool:new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.evidence_tool)));
                break;
            case mToolInferKey:
                result = (mToolInfer.size()!=0)?mToolInfer:new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.tool_infer)));
                break;
            case mMethodToolKey:
                result = (mMethodTool.size()!=0)?mMethodTool:new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.method_tool)));
                break;
            case mPeopleNumberKey:
                result = (mPeopleNumber.size()!=0)?mPeopleNumber:new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.people_number)));
                break;
            case mCrimeEntranceExportKey:
                result = (mCrimeEntranceExport.size()!=0)?mCrimeEntranceExport:new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.crime_entrance)));
                break;
            case mCrimeMeansKey:
                result = (mCrimeMeans.size()!=0)?mCrimeMeans:new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.crime_means)));
                break;
            case mCrimeCharacterKey:
                result = (mCrimeCharacter.size()!=0)?mCrimeCharacter:new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.crime_character)));
                break;
            case mCrimeTimingKey:
                result = (mCrimeTiming.size()!=0)?mCrimeTiming:new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.crime_timing)));
                break;
            case mSelectObjectKey:
                result = (mSelectObject.size()!=0)?mSelectObject:new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.select_object)));
                break;
            case mCrimeFeatureKey:
                result = (mCrimeFeature.size()!=0)?mCrimeFeature:new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.crime_feature)));
                break;
            case mIntrusiveMethodKey:
                result = (mIntrusiveMethod.size()!=0)?mIntrusiveMethod:new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.intrusive_method)));
                break;
            case mSelectLocationKey:
                result = (mSelectLocation.size()!=0)?mSelectLocation:new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.select_location)));
                break;
            case mCrimePurposeKey:
                result = (mCrimePurpose.size()!=0)?mCrimePurpose:new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.crime_purpose)));
                break;
            default:
                break;
        }
        return result;
    }

    public static String getDictValue(String rootkey, String DictKey){
        String result = "";
        switch (rootkey){
            case mAreaKey:
                if(mAreaHashMap.size()!=0) result = mAreaHashMap.get(DictKey);
                break;
            case mSceneConditionKey:
                if(mSceneConditionHashMap.size()!=0) result = mSceneConditionHashMap.get(DictKey);
                break;
            case mWeatherConditionKey:
                if(mWeatherConditionHashMap.size()!=0) result = mWeatherConditionHashMap.get(DictKey);
                break;
            case mWindDirectionKey:
                if(mWindDirectionHashMap.size()!=0) result = mWindDirectionHashMap.get(DictKey);
                break;
            case mIlluminationConditionKey:
                if(mIlluminationConditionHashMap.size()!=0) result = mIlluminationConditionHashMap.get(DictKey);
                break;
            case mSexKey:
                if(mSexHashMap.size()!=0) result = mSexHashMap.get(DictKey);
                break;
            case mToolCategoryKey:
                if(mToolCategoryHashMap.size()!=0) result = mToolCategoryHashMap.get(DictKey);
                break;
            case mToolSourceKey:
                if(mToolSourceHashMap.size()!=0) result = mToolSourceHashMap.get(DictKey);
                break;
            case mEvidenceHandKey:
                if(mEvidenceHandHashMap.size()!=0) result = mEvidenceHandHashMap.get(DictKey);
                break;
            case mMethodHandKey:
                if(mMethodHandHashMap.size()!=0) result = mMethodHandHashMap.get(DictKey);
                break;
            case mEvidenceFootKey:
                if(mEvidenceFootHashMap.size()!=0) result = mEvidenceFootHashMap.get(DictKey);
                break;
            case mMethodFootKey:
                if(mMethodFootHashMap.size()!=0) result = mMethodFootHashMap.get(DictKey);
                break;
            case mEvidenceToolKey:
                if(mEvidenceToolHashMap.size()!=0) result = mEvidenceToolHashMap.get(DictKey);
                break;
            case mToolInferKey:
                if(mToolInferHashMap.size()!=0) result = mToolInferHashMap.get(DictKey);
                break;
            case mMethodToolKey:
                if(mMethodToolHashMap.size()!=0) result = mMethodToolHashMap.get(DictKey);
                break;
            case mPeopleNumberKey:
                if(mPeopleNumberHashMap.size()!=0) result = mPeopleNumberHashMap.get(DictKey);
                break;
            case mCrimeMeansKey:
                if(mCrimeMeansHashMap.size()!=0) result = mCrimeMeansHashMap.get(DictKey);
                break;
            case mCrimeCharacterKey:
                if(mCrimeCharacterHashMap.size()!=0) result = mCrimeCharacterHashMap.get(DictKey);
                break;
            case mCrimeEntranceExportKey:
                if(mCrimeEntranceExportHashMap.size()!=0) result = mCrimeEntranceExportHashMap.get(DictKey);
                break;
            case mCrimeTimingKey:
                if(mCrimeTimingHashMap.size()!=0) result = mCrimeTimingHashMap.get(DictKey);
                break;
            case mSelectObjectKey:
                if(mSelectObjectHashMap.size()!=0) result = mSelectObjectHashMap.get(DictKey);
                break;
            case mCrimeFeatureKey:
                if(mCrimeFeatureHashMap.size()!=0) result = mCrimeFeatureHashMap.get(DictKey);
                break;
            case mIntrusiveMethodKey:
                if(mIntrusiveMethodHashMap.size()!=0) result = mIntrusiveMethodHashMap.get(DictKey);
                break;
            case mSelectLocationKey:
                if(mSelectLocationHashMap.size()!=0) result = mSelectLocationHashMap.get(DictKey);
                break;
            case mCrimePurposeKey:
                if(mCrimePurposeHashMap.size()!=0) result = mCrimePurposeHashMap.get(DictKey);
                break;
            default:
                break;
        }
        if(result==null) return "";
        return result;
    }

    public static String getDictKey(String rootkey, String DictValue){
        String result = "";
        switch (rootkey){
            case mAreaKey:
                if(mAreaHashMap.size()!=0) result = (String) valueGetKey(mAreaHashMap, DictValue);
                break;
            case mSceneConditionKey:
                if(mSceneConditionHashMap.size()!=0) result = (String) valueGetKey(mSceneConditionHashMap, DictValue);
                break;
            case mWeatherConditionKey:
                if(mWeatherConditionHashMap.size()!=0) result = (String) valueGetKey(mWeatherConditionHashMap, DictValue);
                break;
            case mWindDirectionKey:
                if(mWindDirectionHashMap.size()!=0) result = (String) valueGetKey(mWindDirectionHashMap, DictValue);
                break;
            case mIlluminationConditionKey:
                if(mIlluminationConditionHashMap.size()!=0) result = (String) valueGetKey(mIlluminationConditionHashMap, DictValue);
                break;
            case mSexKey:
                if(mSexHashMap.size()!=0) result = (String) valueGetKey(mSexHashMap, DictValue);
                break;
            case mToolCategoryKey:
                if(mToolCategoryHashMap.size()!=0) result = (String) valueGetKey(mToolCategoryHashMap, DictValue);
                break;
            case mToolSourceKey:
                if(mToolSourceHashMap.size()!=0) result = (String) valueGetKey(mToolSourceHashMap, DictValue);
                break;
            case mEvidenceHandKey:
                if(mEvidenceHandHashMap.size()!=0) result = (String) valueGetKey(mEvidenceHandHashMap, DictValue);
                break;
            case mMethodHandKey:
                if(mMethodHandHashMap.size()!=0) result = (String) valueGetKey(mMethodHandHashMap, DictValue);
                break;
            case mEvidenceFootKey:
                if(mEvidenceFootHashMap.size()!=0) result = (String) valueGetKey(mEvidenceFootHashMap, DictValue);
                break;
            case mMethodFootKey:
                if(mMethodFootHashMap.size()!=0) result = (String) valueGetKey(mMethodFootHashMap, DictValue);
                break;
            case mEvidenceToolKey:
                if(mEvidenceToolHashMap.size()!=0) result = (String) valueGetKey(mEvidenceToolHashMap, DictValue);
                break;
            case mToolInferKey:
                if(mToolInferHashMap.size()!=0) result = (String) valueGetKey(mToolInferHashMap, DictValue);
                break;
            case mMethodToolKey:
                if(mMethodToolHashMap.size()!=0) result = (String) valueGetKey(mMethodToolHashMap, DictValue);
                break;
            case mPeopleNumberKey:
                if(mPeopleNumberHashMap.size()!=0) result = (String) valueGetKey(mPeopleNumberHashMap, DictValue);
                break;
            case mCrimeEntranceExportKey:
                if(mCrimeEntranceExportHashMap.size()!=0) result = (String) valueGetKey(mCrimeEntranceExportHashMap, DictValue);
                break;
            case mCrimeMeansKey:
                if(mCrimeMeansHashMap.size()!=0) result = (String) valueGetKey(mCrimeMeansHashMap, DictValue);
                break;
            case mCrimeCharacterKey:
                if(mCrimeCharacterHashMap.size()!=0) result = (String) valueGetKey(mCrimeCharacterHashMap, DictValue);
                break;
            case mCrimeTimingKey:
                if(mCrimeTimingHashMap.size()!=0) result = (String) valueGetKey(mCrimeTimingHashMap, DictValue);
                break;
            case mSelectObjectKey:
                if(mSelectObjectHashMap.size()!=0) result = (String) valueGetKey(mSelectObjectHashMap, DictValue);
                break;
            case mCrimeFeatureKey:
                if(mCrimeFeatureHashMap.size()!=0) result = (String) valueGetKey(mCrimeFeatureHashMap, DictValue);
                break;
            case mIntrusiveMethodKey:
                if(mIntrusiveMethodHashMap.size()!=0) result = (String) valueGetKey(mIntrusiveMethodHashMap, DictValue);
                break;
            case mSelectLocationKey:
                if(mSelectLocationHashMap.size()!=0) result = (String) valueGetKey(mSelectLocationHashMap, DictValue);
                break;
            case mCrimePurposeKey:
                if(mCrimePurposeHashMap.size()!=0) result = (String) valueGetKey(mCrimePurposeHashMap, DictValue);
                break;
            default:
                break;
        }

        if(result==null) return "";
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
