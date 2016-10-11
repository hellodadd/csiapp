package com.android.csiapp.Databases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
    private long mOccurred_start_time;
    private long mOccurred_end_time;
    private long mGet_access_time;
    private String mUnitsAssigned;
    private String mAccessPolicemen;
    private long mAccess_start_time;
    private long mAccess_end_time;
    private String mAccessLocation;
    private String mCaseOccurProcess;
    private String mSceneCondition;
    private String mChangeReason;
    private String mWeatherCondition;
    private String mWindDirection;
    private String mTemperature;
    private String mHumidity;
    private String mAccessReason;
    private String mIlluminationCondition;
    private String mProductPeopleName;
    private String mProductPeopleUnit;
    private String mProductPeopleDuties;
    private String mSafeguard;
    private String mSceneConductor;
    private String mAccessInspectors;

    //Page 2 (New people)
    private List<RelatedPeopleItem> mReleatedPeopleItem;

    //Page 2 (New Item)
    private List<LostItem> mLostItem;

    //Page 2 (New Tool)
    private List<CrimeToolItem> mCrimeToolItem;

    //Page 3 (Position)
    private List<PhotoItem> mPositionItem;

    //Page 4 (PositionPhoto)
    private List<PhotoItem> mPositionPhotoItem;

    //Page 4 (Overview)
    private List<PhotoItem> mOverviewPhotoItem;

    //Page 4 (Important)
    private List<PhotoItem> mImportantPhotoItem;

    //Page 5 (Evidence)
    private List<EvidenceItem> mEvidenceItem;

    //Page 7
    private String mCrimePeopleNumber;
    private String mCrimeMeans;
    private String mCrimeCharacter;
    private String mCrimeEntrance;
    private long mCrimeTiming;
    private String mSelectObject;
    private String mCrimeExport;
    private String mCrimePeopleFeature;
    private String mCrimeFeature;
    private String mIntrusiveMethod;
    private String mSelectLocation;
    private String mCrimePurpose;

    //Page 8 (New Witness)
    private List<WitnessItem> mWitnessItem;

    public CrimeItem() {
        Calendar c = Calendar.getInstance();
        long time = c.getTimeInMillis();
        this.id = 0;
        this.mCasetype = "";
        this.mArea = "";
        this.mLocation = "";
        this.mOccurred_start_time = time;
        this.mOccurred_end_time = time;
        this.mGet_access_time = time;
        this.mUnitsAssigned = "";
        this.mAccessPolicemen = "";
        this.mAccess_start_time = time;
        this.mAccess_end_time = time;
        this.mAccessLocation = "";
        this.mCaseOccurProcess = "";
        this.mSceneCondition = "";
        this.mChangeReason = "";
        this.mWeatherCondition = "";
        this.mWindDirection = "";
        this.mTemperature = "";
        this.mHumidity = "";
        this.mAccessReason = "";
        this.mIlluminationCondition = "";
        this.mProductPeopleName = "";
        this.mProductPeopleUnit = "";
        this.mProductPeopleDuties = "";
        this.mSafeguard = "";
        this.mSceneConductor = "";
        this.mAccessInspectors = "";

        this.mReleatedPeopleItem = new ArrayList<RelatedPeopleItem>();
        this.mLostItem = new ArrayList<LostItem>();
        this.mCrimeToolItem = new ArrayList<CrimeToolItem>();

        this.mPositionItem = new ArrayList<PhotoItem>();

        this.mPositionPhotoItem = new ArrayList<PhotoItem>();
        this.mOverviewPhotoItem = new ArrayList<PhotoItem>();
        this.mImportantPhotoItem = new ArrayList<PhotoItem>();

        this.mEvidenceItem = new ArrayList<EvidenceItem>();

        this.mCrimePeopleNumber = "";
        this.mCrimeMeans = "";
        this.mCrimeCharacter = "";
        this.mCrimeEntrance = "";
        this.mCrimeTiming = time;
        this.mSelectObject = "";
        this.mCrimeExport = "";
        this.mCrimePeopleFeature = "";
        this.mCrimeFeature = "";
        this.mIntrusiveMethod = "";
        this.mSelectLocation = "";
        this.mCrimePurpose = "";

        this.mWitnessItem = new ArrayList<WitnessItem>();
    }

    public CrimeItem(long id, String casetype, String area, long time) {
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

    public long getOccurredStartTime() {
        return mOccurred_start_time;
    }

    public void setOccurredStartTime(long time) {
        this.mOccurred_start_time = time;
    }

    public long getOccurredEndTime() {
        return mOccurred_end_time;
    }

    public void setOccurredEndTime(long time) {
        this.mOccurred_end_time = time;
    }

    public long getGetAccessTime() {
        return mGet_access_time;
    }

    public void setGetAccessTime(long time) {
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

    public long getAccessStartTime () {
        return mAccess_start_time;
    }

    public void setAccessStartTime (long time ) {
        this.mAccess_start_time = time ;
    }

    public long getAccessEndTime () {
        return mAccess_end_time;
    }

    public void setAccessEndTime (long time ) {
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

    public String getChangeReason () {
        return mChangeReason;
    }

    public void setChangeReason (String changeReason ) {
        this.mChangeReason = changeReason ;
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

    public String getIlluminationCondition () {
        return mIlluminationCondition;
    }

    public void setIlluminationCondition (String illuminationCondition ) {
        this.mIlluminationCondition = illuminationCondition ;
    }

    public String getProductPeopleName () {
        return mProductPeopleName;
    }

    public void setProductPeopleName (String productPeopleName ) {
        this.mProductPeopleName = productPeopleName ;
    }

    public String getProductPeopleUnit () {
        return mProductPeopleUnit;
    }

    public void setProductPeopleUnit (String productPeopleUnit ) {
        this.mProductPeopleUnit = productPeopleUnit ;
    }

    public String getProductPeopleDuties () {
        return mProductPeopleDuties;
    }

    public void setProductPeopleDuties (String productPeopleDuties ) {
        this.mProductPeopleDuties = productPeopleDuties ;
    }

    public String getSafeguard() {
        return mSafeguard;
    }

    public void setSafeguard (String safeguard ) {
        this.mSafeguard = safeguard ;
    }

    public String getSceneConductor () {
        return mSceneConductor;
    }

    public void setSceneConductor (String sceneConductor ) {
        this.mSceneConductor = sceneConductor ;
    }

    public String getAccessInspectors  () {
        return mAccessInspectors ;
    }

    public void setAccessInspectors  (String accessInspectors  ) {
        this.mAccessInspectors  = accessInspectors ;
    }

    //Page 2 (New People)
    public List<RelatedPeopleItem> getReleatedPeople() {return mReleatedPeopleItem; }

    public void setReleatedPeople(List<RelatedPeopleItem> relatedPeopleItem) {this.mReleatedPeopleItem = relatedPeopleItem; }

    //Page 2 (New Item)
    public List<LostItem> getLostItem() {return mLostItem; }

    public void setLostItem(List<LostItem> lostItem) {this.mLostItem = lostItem; }

    //Page 2  (New Tool)
    public List<CrimeToolItem>  getCrimeTool() {return mCrimeToolItem; }

    public void setCrimeTool(List<CrimeToolItem>  crimeTool) {this.mCrimeToolItem = crimeTool; }

    //Page 3 (Position)
    public List<PhotoItem>  getPosition() {return mPositionItem; }

    public void setPosition(List<PhotoItem>  positionItem) {this.mPositionItem = positionItem; }

    //Page 4 (PositionPhoto)
    public List<PhotoItem>  getPositionPhoto() {return mPositionPhotoItem; }

    public void setPositionPhoto(List<PhotoItem>  positionPhotoItem) {this.mPositionPhotoItem = positionPhotoItem; }

    //Page 4 (OverviewPhoto)
    public List<PhotoItem>  getOverviewPhoto() {return mOverviewPhotoItem; }

    public void setOverviewPhoto(List<PhotoItem>  overviewPhotoItem) {this.mOverviewPhotoItem = overviewPhotoItem; }

    //Page 4 (ImportantPhoto)
    public List<PhotoItem>  getImportantPhoto() {return mImportantPhotoItem; }

    public void setImportantPhoto(List<PhotoItem>  importantPhotoItem) {this.mImportantPhotoItem = importantPhotoItem; }

    //Page 5 (Evidence)
    public List<EvidenceItem>  getEvidenceItem() {return mEvidenceItem; }

    public void setEvidenceItem(List<EvidenceItem>  evidenceItem) {this.mEvidenceItem = evidenceItem; }

    //Page 7
    public String getCrimePeopleNumber() {return mCrimePeopleNumber; }

    public void setCrimePeopleNumber(String crimePeopleNumber) {this.mCrimePeopleNumber = crimePeopleNumber; }

    public String getCrimeMeans() {return mCrimeMeans; }

    public void setCrimeMeans(String crimeMeans) {this.mCrimeMeans = crimeMeans; }

    public String getCrimeCharacter() {return mCrimeCharacter; }

    public void setCrimeCharacter(String crimeCharacter) {this.mCrimeCharacter = crimeCharacter; }

    public String getCrimeEntrance() {return mCrimeEntrance; }

    public void setCrimeEntrance(String crimeEntrance) {this.mCrimeEntrance = crimeEntrance; }

    public long getCrimeTiming() {return mCrimeTiming; }

    public void setCrimeTiming(long crimeTiming) {this.mCrimeTiming = crimeTiming; }

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
    public List<WitnessItem> getWitness() {return mWitnessItem; }

    public void setWitness(List<WitnessItem> witness) {this.mWitnessItem = witness; }
}
