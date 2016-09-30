package com.android.csiapp.Crime.setting;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.csiapp.Databases.CrimeProvider;
import com.android.csiapp.R;

public class SettingActivity extends AppCompatActivity {

    private Context context = null;
    private CrimeProvider mCsi_Item;
    private ImageButton mBackupBtn;
    private ImageButton mRestoreBtn;
    private ImageButton mReactiveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        context = this.getApplicationContext();

        mCsi_Item = new CrimeProvider(context);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
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

        mBackupBtn = (ImageButton)findViewById(R.id.Setting_backup);
        mBackupBtn.setOnClickListener(btnBackupOnClick);
        mRestoreBtn = (ImageButton)findViewById(R.id.Setting_restore);
        mRestoreBtn.setOnClickListener(btnRestoreOnClick);
        mReactiveBtn = (ImageButton)findViewById(R.id.Setting_reactive);
        mReactiveBtn.setOnClickListener(btnReactiveOnClick);
    }

    private View.OnClickListener btnBackupOnClick = new View.OnClickListener () {
        @Override
        public void onClick(View view) {
            dataBackup();
        }
    };

    private View.OnClickListener btnRestoreOnClick = new View.OnClickListener () {
        @Override
        public void onClick(View view) {
            dataRecover();
        }
    };

    private View.OnClickListener btnReactiveOnClick = new View.OnClickListener () {
        @Override
        public void onClick(View view) {
            mCsi_Item.deleteAll();
        }
    };

    private void dataBackup() {
        // TODO Auto-generated method stub
        new BackupRestore(this).execute("backupDatabase");
    }

    private void dataRecover() {
        // TODO Auto-generated method stub
        new BackupRestore(this).execute("restroeDatabase");
    }
}
