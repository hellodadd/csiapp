package com.android.csiapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.android.csiapp.Crime.listscene.ListActivity;
import com.android.csiapp.Crime.setting.SettingActivity;
import com.android.csiapp.Crime.utils.DictionaryInfo;
import com.android.csiapp.Crime.utils.UserInfo;
import com.android.csiapp.Databases.CrimeProvider;
import com.android.csiapp.Databases.CrimeItem;

public class MainActivity extends AppCompatActivity {
    private Context context = null;

    private CrimeProvider mCrimeProvider;

    private Button mCreate;
    private Button mList;

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_setting:
                    msg += "Setting";
                    Intent it = new Intent(MainActivity.this, SettingActivity.class);
                    startActivity(it);
                    break;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        context = this.getApplicationContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(onMenuItemClick);

        // 建立資料庫物件
        mCrimeProvider = new CrimeProvider(context);

        // 如果資料庫是空的，就建立一些範例資料
        // 這是為了方便測試用的，完成應用程式以後可以拿掉
        //if (mCrimeProvider.getCount() == 0) {
        //    mCrimeProvider.sample();
        //}

        //Initial Device Dictionary
        DictionaryInfo.getInitialDictionary(context);
        UserInfo.getInitialUser(context);

        mCreate = (Button) findViewById(R.id.imageButton_create);
        mCreate.setOnClickListener(new OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Intent it = new Intent("com.android.csiapp.ADD_SCENE");
                startActivityForResult(it,0);
            }
        });

        mList = (Button) findViewById(R.id.imageButton_list);
        mList.setOnClickListener(new OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, ListActivity.class);
                startActivity(it);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            CrimeItem item = (CrimeItem) data.getExtras().getSerializable("com.android.csiapp.CrimeItem");
            if (requestCode == 0) {
                // 新增記事資料到資料庫
                item = mCrimeProvider.insert(item);
            }
        }
    }
}
