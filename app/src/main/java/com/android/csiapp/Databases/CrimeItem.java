package com.android.csiapp.Databases;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by AnitaLin on 2016/9/9.
 */
public class CrimeItem implements Serializable {
    // 編號、日期時間、顏色、標題、內容、檔案名稱、經緯度、修改、已選擇
    //Page 1
    private long id;
    private String mCasetype;
    private String mArea;
    private String mLocation;
    private String mOccurred_start_time;
    private String mOccurred_end_time;
    private String mGet_access_time;
    private String mUnitsAssigned;
    private String mAccessPolicemen;
    private String mAccess_start_time;
    private String mAccess_end_time;
    private String mAccessLocation;
    private String mCaseOccurProcess;
    private String mSceneCondition;
    private String mWeatherCondition;
    private String mWindDirection;
    private String mTemperature;
    private String mHumidity;
    private String mAccessReason;

    //Page 2 (New people)
    private String mPeople_releation;
    private String mPeople_name;
    private String mPeople_sex;
    private String mPeople_id;
    private String mPeople_number;
    private String mPeople_address;

    //Page 2 (New Item)
    private String mItem_name;
    private String mItem_brand;
    private String mItem_amount;
    private String mItem_value;
    private String mItem_feature;

    //Page 2 (New Tool)
    private String mTool_name;
    private String mTool_category;
    private String mTool_source;

    //Page 7
    private String mCrimePeopleNumber;
    private String mCrimeMeans;
    private String mCrimeCharacter;
    private String mCrimeEntrance;
    private String mCrimeTiming;
    private String mSelectObject;
    private String mCrimeExport;
    private String mCrimePeopleFeature;
    private String mCrimeFeature;
    private String mIntrusiveMethod;
    private String mSelectLocation;
    private String mCrimePurpose;

    //Page 8 (New Witness)
    private String mWitness_name;
    private String mWitness_sex;
    private String mWitness_birthday;
    private String mWitness_number;
    private String mWitness_address;

    public CrimeItem() {
        this.id = 0;
        this.mCasetype = "";
        this.mArea = "";
        this.mLocation = "";
        this.mOccurred_start_time = "";
        this.mOccurred_end_time = "";
        this.mGet_access_time = "";
        this.mUnitsAssigned = "";
        this.mAccessPolicemen = "";
        this.mAccess_start_time = "";
        this.mAccess_end_time = "";
        this.mAccessLocation = "";
        this.mCaseOccurProcess = "";
        this.mSceneCondition = "";
        this.mWeatherCondition = "";
        this.mWindDirection = "";
        this.mTemperature = "";
        this.mHumidity = "";
        this.mAccessReason = "";

        this.mPeople_releation = "";
        this.mPeople_name = "";
        this.mPeople_sex = "";
        this.mPeople_id = "";
        this.mPeople_number = "";
        this.mPeople_address = "";

        this.mItem_name = "";
        this.mItem_brand = "";
        this.mItem_amount = "";
        this.mItem_value = "";
        this.mItem_feature = "";

        this.mTool_name = "";
        this.mTool_category = "";
        this.mTool_source = "";

        this.mCrimePeopleNumber = "";
        this.mCrimeMeans = "";
        this.mCrimeCharacter = "";
        this.mCrimeEntrance = "";
        this.mCrimeTiming = "";
        this.mSelectObject = "";
        this.mCrimeExport = "";
        this.mCrimePeopleFeature = "";
        this.mCrimeFeature = "";
        this.mIntrusiveMethod = "";
        this.mSelectLocation = "";
        this.mCrimePurpose = "";

        this.mWitness_name = "";
        this.mWitness_sex = "";
        this.mWitness_birthday = "";
        this.mWitness_number = "";
        this.mWitness_address = "";
    }

    public CrimeItem(long id, String casetype, String area, String time) {
        this.id = id;
        this.mCasetype = casetype;
        this.mArea = area;
        this.mOccurred_start_time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    //Page 1
    public String getCasetype() {
        return mCasetype;
    }

    public void setCasetype(String casetype) {
        this.mCasetype = casetype;
    }

    public String getArea() {
        return mArea;
    }

    public void setArea(String area) {
        this.mArea = area;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocationa(String location) {
        this.mLocation = location;
    }

    public String getOccurredStartTime() {
        return mOccurred_start_time;
    }

    public void setOccurredStartTime(String time) {
        this.mOccurred_start_time = time;
    }

    public String getOccurredEndTime() {
        return mOccurred_end_time;
    }

    public void setOccurredEndTime(String time) {
        this.mOccurred_end_time = time;
    }

    public String getGetAccessTime() {
        return mGet_access_time;
    }

    public void setGetAccessTime(String time) {
        this.mGet_access_time = time;
    }

    public String getUnitsAssigned () {
        return mUnitsAssigned;
    }

    public void setUnitsAssigned (String unitsAssigned ) {
        this.mUnitsAssigned = unitsAssigned ;
    }

    public String getAccessPolicemen () {
        return mAccessPolicemen;
    }

    public void setAccessPolicemen (String accessPolicemen ) {
        this.mAccessPolicemen = accessPolicemen ;
    }

    public String getAccessStartTime () {
        return mAccess_start_time;
    }

    public void setAccessStartTime (String time ) {
        this.mAccess_start_time = time ;
    }

    public String getAccessEndTime () {
        return mAccess_end_time;
    }

    public void setAccessEndTime (String time ) {
        this.mAccess_end_time = time ;
    }

    public String getAccessLocation () {
        return mAccessLocation;
    }

    public void setAccessLocation (String accessLocation ) {
        this.mAccessLocation = accessLocation ;
    }

    public String getCaseOccurProcess () {
        return mCaseOccurProcess;
    }

    public void setCaseOccurProcess(String caseOccurProcess ) {
        this.mCaseOccurProcess = caseOccurProcess ;
    }

    public String getSceneCondition () {
        return mSceneCondition;
    }

    public void setSceneCondition (String sceneCondition ) {
        this.mSceneCondition = sceneCondition ;
    }

    public String getWeatherCondition () {
        return mWeatherCondition;
    }

    public void setWeatherCondition (String weatherCondition ) {
        this.mWeatherCondition = weatherCondition ;
    }

    public String getWindDirection () {
        return mWindDirection;
    }

    public void setWindDirection (String windDirection ) {
        this.mWindDirection = windDirection ;
    }

    public String getTemperature() {
        return mTemperature;
    }

    public void setTemperature (String temperature ) {
        this.mTemperature = temperature ;
    }

    public String getHumidity () {
        return mHumidity;
    }

    public void setHumidity (String humidity ) {
        this.mHumidity = humidity ;
    }

    public String getAccessReason () {
        return mAccessReason;
    }

    public void setAccessReason (String accessReason ) {
        this.mAccessReason = accessReason ;
    }

    //Page 2 (New People)
    public String getPeopleReleation() {return mPeople_releation; }

    public void setPeopleReleation(String people_releation) {this.mPeople_releation = people_releation; }

    public String getPeopleName() {return mPeople_name; }

    public void setPeopleName(String people_name) {this.mPeople_name = people_name; }

    public String getPeopleSex() {return mPeople_sex; }

    public void setPeopleSex(String people_sex) {this.mPeople_sex = people_sex; }

    public String getPeopleId() {return mPeople_id; }

    public void setPeopleId(String people_id) {this.mPeople_id = people_id; }

    public String getPeopleNumber() {return mPeople_number; }

    public void setPeopleNumber(String people_number) {this.mPeople_number = people_number; }

    public String getPeopleAddress() {return mPeople_address; }

    public void setPeopleAddress(String people_address) {this.mPeople_address = people_address; }

    //Page 2 (New Item)
    public String getItemName() {return mItem_name; }

    public void setItemName(String item_name) {this.mItem_name = item_name; }

    public String getItemBrand() {return mItem_brand; }

    public void setItemBrand(String item_brand) {this.mItem_brand = item_brand; }

    public String getItemAmount() {return mItem_amount; }

    public void setItemAmount(String item_amount) {this.mItem_amount = item_amount; }

    public String getItemValue() {return mItem_value; }

    public void setItemVlaue(String item_value) {this.mItem_value = item_value; }

    public String getItemFeature() {return mItem_feature; }

    public void setItemFeatue(String item_feature) {this.mItem_feature = item_feature; }

    //Page 2  (New Tool)
    public String getToolName() {return mTool_name; }

    public void setToolName(String tool_name) {this.mTool_name = tool_name; }

    public String getToolCategory() {return mTool_category; }

    public void setToolCategory(String tool_category) {this.mTool_category = tool_category; }

    public String getToolSource() {return mTool_source; }

    public void setToolSource(String tool_source) {this.mTool_source = tool_source; }

    //Page 7
    public String getCrimePeopleNumber() {return mCrimePeopleNumber; }

    public void setCrimePeopleNumber(String crimePeopleNumber) {this.mCrimePeopleNumber = crimePeopleNumber; }

    public String getCrimeMeans() {return mCrimeMeans; }

    public void setCrimeMeans(String crimeMeans) {this.mCrimeMeans = crimeMeans; }

    public String getCrimeCharacter() {return mCrimeCharacter; }

    public void setCrimeCharacter(String crimeCharacter) {this.mCrimeCharacter = crimeCharacter; }

    public String getCrimeEntrance() {return mCrimeEntrance; }

    public void setCrimeEntrance(String crimeEntrance) {this.mCrimeEntrance = crimeEntrance; }

    public String getCrimeTiming() {return mCrimeTiming; }

    public void setCrimeTiming(String crimeTiming) {this.mCrimeTiming = crimeTiming; }

    public String getSelectObject() {return mSelectObject; }

    public void setSelectObject(String selectObject) {this.mSelectObject = selectObject; }

    public String getCrimeExport() {return mCrimeExport; }

    public void setCrimeExport(String crimeExport) {this.mCrimeExport = crimeExport; }

    public String getCrimePeopleFeature() {return mCrimePeopleFeature; }

    public void setCrimePeopleFeature(String peopleFeature) {this.mCrimePeopleFeature = peopleFeature; }

    public String getCrimeFeature() {return mCrimeFeature; }

    public void setCrimeFeature(String crimeFeature) {this.mCrimeFeature = crimeFeature; }

    public String getIntrusiveMethod() {return mIntrusiveMethod; }

    public void setIntrusiveMethod(String intrusiveMethod) {this.mIntrusiveMethod = intrusiveMethod; }

    public String getSelectLocation() {return mSelectLocation; }

    public void setSelectLocation(String selectLocation) {this.mSelectLocation = selectLocation; }

    public String getCrimePurpose() {return mCrimePurpose; }

    public void setCrimePurpose(String crimePurpose) {this.mCrimePurpose = crimePurpose; }

    //Page 8 (New Witness)
    public String getWitnessName() {return mWitness_name; }

    public void setWitnessName(String witness_name) {this.mWitness_name = witness_name; }

    public String getWitnessSex() {return mWitness_sex; }

    public void setWitnessSex(String witness_sex) {this.mWitness_sex = witness_sex; }

    public String getWitnessBirthday() {return mWitness_birthday; }

    public void setWitnessBirthday(String witness_birthday) {this.mWitness_birthday = witness_birthday; }

    public String getWitnessNumber() {return mWitness_number; }

    public void setWitnessNumber(String witness_number) {this.mWitness_number = witness_number; }

    public String getWitnessAddress() {return mWitness_address; }

    public void setWitnessAddress(String witness_address) {this.mWitness_address = witness_address; }


    public static String getCurrentTime(Calendar c) { //輸出格式製作
        int[] a={c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH),
                c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE),
                c.get(Calendar.SECOND)
        };
        StringBuffer sb=new StringBuffer();
        sb.append(a[0]);
        sb.append("年");
        if (a[1]<9) {sb.append("0" + (a[1] + 1));}   //加 1 才會得到實際月份
        else {sb.append("" + (a[1] + 1));}
        sb.append("月");
        if (a[2]<10) {sb.append("0" + (a[2]));}
        else {sb.append("" + (a[2]));}
        sb.append("日\n");
        if (a[3]<10) {sb.append(" 0" + (a[3]));}
        else {sb.append("" + (a[3]));}
        sb.append("時");
        if (a[4]<10) {sb.append("0" + a[4]);}
        else {sb.append("" + a[4]);}
        sb.append("分");
        //if (a[5]<10) {sb.append(":0" + a[5]);}
        //selse {sb.append(":" + a[5]);}
        return sb.toString();
    }
}
