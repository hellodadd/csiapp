package com.android.csiapp.Databases;

import java.io.Serializable;

/**
 * Created by user on 2016/10/4.
 */
public class WitnessItem implements Serializable {
    private long id;
    private String mWitness_name;
    private String mWitness_sex;
    private String mWitness_birthday;
    private String mWitness_number;
    private String mWitness_address;

    public WitnessItem(){
        this.id = 0;
        this.mWitness_name = "";
        this.mWitness_sex = "";
        this.mWitness_birthday = "";
        this.mWitness_number = "";
        this.mWitness_address = "";
    }

    public WitnessItem(long id, String name, String sex, String birthday, String number, String address) {
        this.id = id;
        this.mWitness_name = name;
        this.mWitness_sex = sex;
        this.mWitness_birthday = birthday;
        this.mWitness_number = number;
        this.mWitness_address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
}