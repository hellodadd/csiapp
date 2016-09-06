package com.android.csiapp;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Create_ViewPager1_Activity extends Activity {

    private Context context = null;
    private Spinner area_spinner;
    private String[] area = {"Taipei", "Taoyuan", "Taichung", "Tainan", "Kaohsiung"};
    private ArrayAdapter<String> area_Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_viewpager1);

        context = this.getApplicationContext();
        area_spinner = (Spinner)findViewById(R.id.area_spinner);
        area_Adapter = new ArrayAdapter<String>(Create_ViewPager1_Activity.this, android.R.layout.simple_spinner_item, area);
        area_spinner.setAdapter(area_Adapter);
    }
}
