package com.android.csiapp.Databases;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by user on 2016/10/7.
 */
public class EvidenceItem implements Serializable {

    private long id;
    private String mPhotoPath;
    private String mEvidenceCategory;
    private String mEvidence;
    private String mEvidenceName;
    private String mLegacySite;
    private String mBasiceFeature;
    private String mMethod;
    private long mTime;
    private String mPeople;

    public EvidenceItem(){
        Calendar c = Calendar.getInstance();
        long time = c.getTimeInMillis();
        this.id = 0;
        this.mPhotoPath = "";
        this.mEvidenceCategory = "";
        this.mEvidence = "";
        this.mEvidenceName = "";
        this.mLegacySite = "";
        this.mBasiceFeature = "";
        this.mMethod = "";
        this.mTime = time;
        this.mPeople = "";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhotoPath() {
        return mPhotoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.mPhotoPath = photoPath;
    }

    public String getEvidenceCategory() {
        return mEvidenceCategory;
    }

    public void setEvidenceCategory(String evidenceCategory) {
        this.mEvidenceCategory = evidenceCategory;
    }

    public String getEvidence() {
        return mEvidence;
    }

    public void setEvidence(String evidence) {
        this.mEvidence = evidence;
    }

    public String getEvidenceName() {
        return mEvidenceName;
    }

    public void setEvidenceName(String evidenceName) {
        this.mEvidenceName = evidenceName;
    }

    public String getLegacySite() {
        return mLegacySite;
    }

    public void setLegacySite(String legacySite) {
        this.mLegacySite = legacySite;
    }

    public String getBasiceFeature() {
        return mBasiceFeature;
    }

    public void setBasiceFeature(String basiceFeature) {
        this.mBasiceFeature = basiceFeature;
    }

    public String getMethod() {
        return mMethod;
    }

    public void setMethod(String method) {
        this.mMethod = method;
    }

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        this.mTime = time;
    }

    public String getPeople() {
        return mPeople;
    }

    public void setPeople(String People) {
        this.mPeople = People;
    }
}