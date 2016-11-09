package com.android.csiapp.Crime.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.csiapp.Databases.DictionaryProvider;
import com.android.csiapp.R;

import java.util.ArrayList;
import java.util.Arrays;

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

    public DictionaryInfo(Context context){
        this.mContext = context;
    }

    public static void getInitialDictionary(Context context){
        SharedPreferences prefs = context.getSharedPreferences("InitialDevice", 0);
        String initstatus = prefs.getString("Initial", "0");
        DictionaryProvider dictionaryProvider = new DictionaryProvider(context);

        if(initstatus.equalsIgnoreCase("1")) {
            mArea = (ArrayList<String>) dictionaryProvider.query(mAreaKey);
            mSceneCondition = (ArrayList<String>) dictionaryProvider.query(mSceneConditionKey);
            mWeatherCondition = (ArrayList<String>) dictionaryProvider.query(mWeatherConditionKey);
            mWindDirection = (ArrayList<String>) dictionaryProvider.query(mWindDirectionKey);
            mIlluminationCondition = (ArrayList<String>) dictionaryProvider.query(mIlluminationConditionKey);
            mSex = (ArrayList<String>) dictionaryProvider.query(mSexKey);
            mToolCategory = (ArrayList<String>) dictionaryProvider.query(mToolCategoryKey);
            mToolSource = (ArrayList<String>) dictionaryProvider.query(mToolSourceKey);
            mEvidenceHand = (ArrayList<String>) dictionaryProvider.query(mEvidenceHandKey);
            mMethodHand = (ArrayList<String>) dictionaryProvider.query(mMethodHandKey);
            mEvidenceFoot = (ArrayList<String>) dictionaryProvider.query(mEvidenceFootKey);
            mMethodFoot = (ArrayList<String>) dictionaryProvider.query(mMethodFootKey);
            mEvidenceTool = (ArrayList<String>) dictionaryProvider.query(mEvidenceToolKey);
            mToolInfer = (ArrayList<String>) dictionaryProvider.query(mToolInferKey);
            mMethodTool = (ArrayList<String>) dictionaryProvider.query(mMethodToolKey);
            mPeopleNumber = (ArrayList<String>) dictionaryProvider.query(mPeopleNumberKey);
            mCrimeEntranceExport = (ArrayList<String>) dictionaryProvider.query(mCrimeEntranceExportKey);
            mCrimeMeans = (ArrayList<String>) dictionaryProvider.query(mCrimeMeansKey);
            mCrimeCharacter = (ArrayList<String>) dictionaryProvider.query(mCrimeCharacterKey);
            mCrimeTiming = (ArrayList<String>) dictionaryProvider.query(mCrimeTimingKey);
            mSelectObject = (ArrayList<String>) dictionaryProvider.query(mSelectObjectKey);
            mCrimeFeature = (ArrayList<String>) dictionaryProvider.query(mCrimeFeatureKey);
            mIntrusiveMethod = (ArrayList<String>) dictionaryProvider.query(mIntrusiveMethodKey);
            mSelectLocation = (ArrayList<String>) dictionaryProvider.query(mSelectLocationKey);
            mCrimePurpose = (ArrayList<String>) dictionaryProvider.query(mCrimePurposeKey);
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
}
