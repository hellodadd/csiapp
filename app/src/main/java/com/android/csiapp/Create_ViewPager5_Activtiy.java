package com.android.csiapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Create_ViewPager5_Activtiy extends Activity {

    private Context context = null;
    private ImageButton Scene_evidence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_viewpager5);

        context = this.getApplicationContext();

        Scene_evidence = (ImageButton) findViewById(R.id.Scene_evidence);
        Scene_evidence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Create_ViewPager5_Activtiy.this, Create_VP5_NewEvidence_Activity.class);
                startActivity(it);
            }
        });
    }
}
