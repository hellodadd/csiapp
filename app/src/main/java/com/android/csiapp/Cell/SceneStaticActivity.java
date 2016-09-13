package com.android.csiapp.Cell;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.android.csiapp.R;

public class SceneStaticActivity extends AppCompatActivity {

    private Context context = null;
    private Button gps_button;
    private Button ct_cdma_button;
    private Button cmcc_gsm_button;
    private Button cu_gsm_button;
    private Button cu_wcdma_button;
    private Button cmcc_tdscdma_button;
    private Button one_key_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene_static);

        context = this.getApplicationContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(context.getResources().getString(R.string.title_activity_scene_static));
        toolbar.setTitleTextColor(context.getResources().getColor(R.color.titleBar));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.btn_back_mini);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //What to do on back clicked
            }
        });

        gps_button = (Button) findViewById(R.id.gps_button);
        gps_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(SceneStaticActivity.this, SceneStaticListActivity.class);
                startActivity(it);
            }
        });

        ct_cdma_button = (Button) findViewById(R.id.ct_cdma_button);
        ct_cdma_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(SceneStaticActivity.this, SceneStaticListActivity.class);
                startActivity(it);
            }
        });

        cmcc_gsm_button = (Button) findViewById(R.id.cmcc_gsm_button);
        cmcc_gsm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(SceneStaticActivity.this, SceneStaticListActivity.class);
                startActivity(it);
            }
        });

        cu_gsm_button = (Button) findViewById(R.id.cu_gsm_button);
        cu_gsm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(SceneStaticActivity.this, SceneStaticListActivity.class);
                startActivity(it);
            }
        });

        cu_wcdma_button = (Button) findViewById(R.id.cu_wcdma_button);
        cu_wcdma_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(SceneStaticActivity.this, SceneStaticListActivity.class);
                startActivity(it);
            }
        });

        cmcc_tdscdma_button = (Button) findViewById(R.id.cmcc_tdscdma_button);
        cmcc_tdscdma_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(SceneStaticActivity.this, SceneStaticListActivity.class);
                startActivity(it);
            }
        });

        one_key_button = (Button) findViewById(R.id.one_key_button);
        one_key_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(SceneStaticActivity.this, SceneStaticOneKeyActivity.class);
                startActivity(it);
            }
        });
    }
}
