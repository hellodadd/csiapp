package com.android.csiapp.Csi;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by joanlin on 16/9/21.
 */
public class BackupTask extends AsyncTask<String, Void, String> {

    private static final String COMMAND_BACKUP = "backupDatabase";
    public static final String COMMAND_RESTORE = "restroeDatabase";
    private Context mContext;
    private String result = "";
    private File backup;

    public BackupTask(Context context) {
        this.mContext = context;
    }

    @Override
    //protected Integer doInBackground(String... params) {
    protected String doInBackground(String... params) {
        // TODO Auto-generated method stub

        // 默认路径是 /data/data/(包名)/databases/*.db
        File dbFile = mContext.getDatabasePath("csi_databases.db");

        File exportDir = new File(Environment.getExternalStorageDirectory(),"Csibackup");

        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }

        backup = new File(exportDir, dbFile.getName());
        String command = params[0];

        if (command.equals(COMMAND_BACKUP)) {
            try {
                backup.createNewFile();
                fileCopy(dbFile, backup);
                result = "备份成功";
                Log.d("backup", "ok");
                return result;
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                result = "备份失败";
                Log.d("backup", "fail");
                return result;
            }
        } else if (command.equals(COMMAND_RESTORE)) {
            try {
                fileCopy(backup, dbFile);
                result = "恢复成功";
                Log.d("restore", "success");
                return result;
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                Log.d("restore", "fail");
                e.printStackTrace();
                result = "恢复失败";
                return result;
            }
        } else {
            return null;
        }
    }

    private void fileCopy(File dbFile, File backup) throws IOException {
        // TODO Auto-generated method stub
        FileChannel inChannel = new FileInputStream(dbFile).getChannel();
        FileChannel outChannel = new FileOutputStream(backup).getChannel();
        try {
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (inChannel != null) {
                inChannel.close();
            }
            if (outChannel != null) {
                outChannel.close();
            }
        }
    }

    //onPostExecute方法用于在执行完后台任务后更新UI,显示结果
    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
        if (result.equals("备份成功")) {
            Toast.makeText(mContext, "备份路径:" + backup, Toast.LENGTH_SHORT).show();
        }

    }

}
