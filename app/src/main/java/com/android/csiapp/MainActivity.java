package com.android.csiapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Context context = null;
    private Button mCreate;
    private Button mList;
    private Button mSetting;
    private Button mScene;
    private Button mSignal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this.getApplicationContext();

        mCreate = (Button) findViewById(R.id.imageButton_create);
        mCreate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mCreate.setBackground(context.getDrawable(R.drawable.ic_new_scene_press));
                Intent it = new Intent(MainActivity.this, CreateSceneActivity.class);
                startActivity(it);
            }
        });

        mList = (Button) findViewById(R.id.imageButton_list);
        mList.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                mList.setBackground(context.getDrawable(R.drawable.ic_scene_manage_press));
                Intent it = new Intent(MainActivity.this, ListActivity.class);
                startActivity(it);
            }
        });

        mSetting= (Button) findViewById(R.id.imageButton_setting);
        mSetting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mSetting.setBackground(context.getDrawable(R.drawable.icon_setting_press));
                Intent it = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(it);
            }
        });

        mScene= (Button) findViewById(R.id.imageButton_cell_scene);
        mScene.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mScene.setBackground(context.getDrawable(R.drawable.btn_info_hover));
                Intent it = new Intent(MainActivity.this, SceneActivity.class);
                startActivity(it);
            }
        });

        mSignal= (Button) findViewById(R.id.imageButton_cell_signal);
        mSignal.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mSignal.setBackground(context.getDrawable(R.drawable.btn_info_hover));
                Intent it = new Intent(MainActivity.this, SignalActivity.class);
                startActivity(it);
            }
        });
    }
}
