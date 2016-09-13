package com.android.csiapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.android.csiapp.Cell.SceneActivity;
import com.android.csiapp.Cell.SignalActivity;
import com.android.csiapp.Csi.ListActivity;
import com.android.csiapp.Csi.SettingActivity;

public class MainActivity extends AppCompatActivity {
    private Context context = null;

    private Csi_provider csi_item;

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

        // 建立資料庫物件
        csi_item = new Csi_provider(context);

        // 如果資料庫是空的，就建立一些範例資料
        // 這是為了方便測試用的，完成應用程式以後可以拿掉
        if (csi_item.getCount() == 0) {
            csi_item.sample();
        }

        mCreate = (Button) findViewById(R.id.imageButton_create);
        mCreate.setOnClickListener(new OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                mCreate.setBackground(context.getDrawable(R.drawable.ic_new_scene_press));
                Intent it = new Intent("com.android.csiapp.ADD_SCENE");
                startActivityForResult(it,0);
            }
        });

        mList = (Button) findViewById(R.id.imageButton_list);
        mList.setOnClickListener(new OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            public void onClick(View view) {
                mList.setBackground(context.getDrawable(R.drawable.ic_scene_manage_press));
                Intent it = new Intent(MainActivity.this, ListActivity.class);
                startActivity(it);
            }
        });

        mSetting= (Button) findViewById(R.id.imageButton_setting);
        mSetting.setOnClickListener(new OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                mSetting.setBackground(context.getDrawable(R.drawable.icon_setting_press));
                Intent it = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(it);
            }
        });

        mScene= (Button) findViewById(R.id.imageButton_cell_scene);
        mScene.setOnClickListener(new OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                mScene.setBackground(context.getDrawable(R.drawable.btn_info_hover));
                Intent it = new Intent(MainActivity.this, SceneActivity.class);
                startActivity(it);
            }
        });

        mSignal= (Button) findViewById(R.id.imageButton_cell_signal);
        mSignal.setOnClickListener(new OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                mSignal.setBackground(context.getDrawable(R.drawable.btn_info_hover));
                Intent it = new Intent(MainActivity.this, SignalActivity.class);
                startActivity(it);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Item item = (Item) data.getExtras().getSerializable("com.android.csiapp.Item");
            if (requestCode == 0) {
                // 新增記事資料到資料庫
                item = csi_item.insert(item);
            }
        }
    }
}
