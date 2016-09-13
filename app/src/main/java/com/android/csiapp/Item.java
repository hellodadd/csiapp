package com.android.csiapp;

import java.io.Serializable;

/**
 * Created by AnitaLin on 2016/9/9.
 */
public class Item implements Serializable {
    // 編號、日期時間、顏色、標題、內容、檔案名稱、經緯度、修改、已選擇
    private long id;
    private String casetype;
    private String area;
    private String time;

    public Item() {
        this.id = 0;
        this.casetype = "";
        this.area = "";
        this.time = "";
    }

    public Item(long id, String casetype, String area, String time) {
        this.id = id;
        this.casetype = casetype;
        this.area = area;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCasetype() {
        return casetype;
    }

    public void setCasetype(String casetype) {
        this.casetype = casetype;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
