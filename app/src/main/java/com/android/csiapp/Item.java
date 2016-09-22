package com.android.csiapp;

import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Calendar;

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
