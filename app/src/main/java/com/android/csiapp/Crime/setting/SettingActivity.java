package com.android.csiapp.Crime.setting;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.csiapp.Databases.CrimeProvider;
import com.android.csiapp.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class SettingActivity extends AppCompatActivity {

    private Context mContext = null;
    private CrimeProvider mCrimeProvider;
    private ImageButton mBackupBtn;
    private ImageButton mRestoreBtn;
    private ImageButton mReactiveBtn;
    private int mSelectedItem = -1;
    private AlertDialog.Builder mBuilder;
    private AlertDialog mDialog;
    private ListView mResoteItems;
    private SimpleAdapter mAdapter;
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
            //dataBackup();
            CrimeProvider mCrimeProvider = new CrimeProvider(mContext);
            mCrimeProvider.createBaseMsgXml(-1);
        }
    };

    private View.OnClickListener btnRestoreOnClick = new View.OnClickListener () {
        @Override
        public void onClick(View view) {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "Csibackup";
            File file = new File(path);
            File[] files = file.listFiles(); // Read file
            ArrayList listData = getFileName(files);
            showRestoreListDialog(listData);

        }
    };

    private View.OnClickListener btnReactiveOnClick = new View.OnClickListener () {
        @Override
        public void onClick(View view) {
            mCrimeProvider.deleteAll();
            Toast.makeText(SettingActivity.this, "Reactive", Toast.LENGTH_SHORT).show();
        }
    };

    private void dataBackup() {
        // TODO Auto-generated method stub
        new BackupRestore(this).execute("backupDatabase");
    }

    private void dataRecover(String filename) {
        // TODO Auto-generated method stub
        new BackupRestore(this).execute("restroeDatabase", filename);
    }

    private ArrayList<Hashtable<String, String>> getFileName(File[] files) {
        ArrayList<Hashtable<String, String>> listData = new ArrayList<Hashtable<String, String>>();
        if (files != null) { // determine if the file is null
            for (File file : files) {
                if (file.isDirectory()) {
                    getFileName(file.listFiles());
                } else {
                    String fileName = file.getName();
                    String filePath = file.getPath();
                    if (fileName.endsWith(".zip")) {
                        Hashtable<String, String> fileItem = new Hashtable<String, String>();
                        fileItem.put("Name", fileName);
                        fileItem.put("Path", filePath);
                        listData.add(fileItem);
                    }
                }
            }
        }
        return  listData;
    }

    public void showRestoreListDialog(ArrayList listFile){
        if(listFile.size() == 0) {
            Log.i(TAG,"File Not Found");
            return;
        }

        LayoutInflater layout = LayoutInflater.from(this);
        View resorelistview = layout.inflate(R.layout.restore_list, null);

        mResoteItems = (ListView) resorelistview
                .findViewById(R.id.lvSudokuItems);
        mAdapter = new SimpleAdapter(this,
                listFile, R.layout.filelist, new String[] { "Name" }, new int[] { R.id.tvFileItem });
        mResoteItems.setAdapter(mAdapter);
        mResoteItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                mSelectedItem = position;
                String filename = ((Hashtable<String, String>) mAdapter.getItem(position)).get("Name");
                String filePath = ((Hashtable<String, String>) mAdapter.getItem(position)).get("Path");
                Log.d(TAG, "selectedItem="+mSelectedItem+", filename="+filename+", filePath="+filePath);
                dataRecover(filename);
                mDialog.cancel();
            }
        });

        mBuilder = new AlertDialog.Builder(this);
        mBuilder.setView(resorelistview);
        mBuilder.setCancelable(false);
        mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        mDialog = mBuilder.show();
    }
}
