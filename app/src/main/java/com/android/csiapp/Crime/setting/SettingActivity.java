package com.android.csiapp.Crime.setting;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.android.csiapp.Crime.utils.RestoreListDialog;
import com.android.csiapp.Databases.CrimeProvider;
import com.android.csiapp.R;

public class SettingActivity extends AppCompatActivity {

    private Context mContext = null;
    private CrimeProvider mCrimeProvider;
    private ImageButton mBackupBtn;
    private ImageButton mRestoreBtn;
    private ImageButton mReactiveBtn;
    private AlertDialog.Builder mBuilder;
    private AlertDialog mDialog;
    private final String TAG = "SettingActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        mContext = this.getApplicationContext();

        mCrimeProvider = new CrimeProvider(mContext);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setTitleTextColor(mContext.getResources().getColor(R.color.titleBar));
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

            mBuilder = new AlertDialog.Builder(SettingActivity.this);
            mBuilder.setCancelable(false);
            mBuilder.setTitle(mContext.getResources().getString(R.string.bcakup_prompt_title));
            mBuilder.setMessage(mContext.getResources().getString(R.string.backup_prompt_msg));
            mBuilder.setNegativeButton(mContext.getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            mBuilder.setPositiveButton(mContext.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
            dataBackup();
        }
            });

            mDialog = mBuilder.create();
            mDialog.show();
        }
    };

    private View.OnClickListener btnRestoreOnClick = new View.OnClickListener () {
        @Override
        public void onClick(View view) {
            RestoreListDialog restoreListDialog = new RestoreListDialog(SettingActivity.this);
            restoreListDialog.createRestoreListDialog();
        }
    };

    private View.OnClickListener btnReactiveOnClick = new View.OnClickListener () {
        @Override
        public void onClick(View view) {

            mBuilder = new AlertDialog.Builder(SettingActivity.this);
            mBuilder.setCancelable(false);
            mBuilder.setTitle(mContext.getResources().getString(R.string.reactive_promp_title));
            mBuilder.setMessage(mContext.getResources().getString(R.string.reactive_prompt_msg));
            mBuilder.setNegativeButton(mContext.getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            mBuilder.setPositiveButton(mContext.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
            mCrimeProvider.deleteAll();
                    restartApp();
                }
            });

            mDialog = mBuilder.show();
        }
    };

    public void restartApp() {
        final Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void dataBackup() {
        // TODO Auto-generated method stub
        new BackupRestore(this).execute("backupDatabase");
    }

    private void dataRecover(String filename) {
        // TODO Auto-generated method stub
        new BackupRestore(this).execute("restroeDatabase", filename);
    }
}
