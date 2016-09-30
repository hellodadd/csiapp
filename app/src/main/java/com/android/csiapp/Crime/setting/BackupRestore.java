package com.android.csiapp.Crime.setting;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

/**
 * Created by joanlin on 16/9/21.
 */
public class BackupRestore extends AsyncTask<String, Void, String> {

    private static final String COMMAND_BACKUP = "backupDatabase";
    public static final String COMMAND_RESTORE = "restroeDatabase";
    private Context mContext;
    private String sendResult = "";
    String backupPath = "";
    private File backup;

    public BackupRestore(Context context) {
        this.mContext = context;
    }

    @Override
    //protected Integer doInBackground(String... params) {
    protected String doInBackground(String... params) {
        // TODO Auto-generated method stub
        //Set backup path
        backupPath = Environment.getExternalStorageDirectory()+"/Csibackup/";
        
        // 默认路径是 /data/data/(包名)/databases/*.db
        File dbFile = mContext.getDatabasePath("csi_databases.db");
        File exportDir = new File(Environment.getExternalStorageDirectory(),"Csibackup");
        String cmd = params[0];

        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }

        boolean dbresult = false;
        boolean picresult = false;
        //Backup/Restore Db file
        dbresult = DbBackupRestore(cmd, exportDir, dbFile);

        //Backup/Restore Pic File
        String picPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/Report";
        String exportPath = Environment.getExternalStorageDirectory()+"/Csibackup/Report";
        picresult = picBackupRestore(cmd, picPath, exportPath);

        if (cmd.equals(COMMAND_BACKUP) ) {
            if (dbresult == true && picresult ==true) {
                sendResult  = "备份成功";
            } else {
                sendResult  = "备份失败";
            }

        } else if (cmd.equals(COMMAND_RESTORE)) {
            if (dbresult == true && picresult ==true) {
                sendResult = "恢复成功";
            } else {
                sendResult  ="恢复失败";
            }
        }

        return sendResult;
    }

    private boolean DbBackupRestore (String cmd, File exportdir, File dbfile) {

        backup = new File(exportdir, dbfile.getName());
        boolean result = false;
        String command = cmd;

        if (command.equals(COMMAND_BACKUP)) {
            try {
                backup.createNewFile();
                DbCopy(dbfile, backup);
                //result = "资料库备份成功";
                result = true;
                Log.d("backup", "ok");
                return result;
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                //result = "资料库备份失败";
                Log.d("backup", "fail");
                result = false;
                return result;
            }
        } else if (command.equals(COMMAND_RESTORE)) {
            try {
                DbCopy(backup, dbfile);
                //result = "资料库恢复成功";
                Log.d("restore", "success");
                result = true;
                return result;
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                Log.d("restore", "fail");
                e.printStackTrace();
                //result = "资料库恢复失败";
                result = false;
                return result;
            }
        } else {
            return result;
        }
    }

    private boolean picBackupRestore (String cmd, String picpath, String backuppath) {
        //String result = "";
        boolean result = false;
        if (cmd.equals(COMMAND_BACKUP)) {
            try {
                copyFolder(picpath, backuppath);
                //result = "图片备份成功";
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
                //result = "图片备份失败";
                result = false;
            }
            return result;
        } else if (cmd.equals(COMMAND_RESTORE)) {
            try {
                copyFolder(backuppath, picpath);
                //result = "图片恢复成功";
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
                //result = "图片恢复失败";
                result = false;
            }
            return result;
        } else {
            //return null;
            return result;
        }

    }

    private void DbCopy(File dbFile, File backup) throws IOException {
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

    /**
     * 复制单个文件
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ( (byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        }
        catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();

        }

    }

    /**
     * 复制整个文件夹内容
     * @param oldPath String 原文件路径 如：c:/fqf
     * @param newPath String 复制后路径 如：f:/fqf/ff
     * @return boolean
     */
    public void copyFolder(String oldPath, String newPath) {

        try {
            (new File(newPath)).mkdirs(); //如果文件夹不存在 则建立新文件夹
            File a=new File(oldPath);
            String[] file=a.list();
            File temp=null;
            for (int i = 0; i < file.length; i++) {
                if(oldPath.endsWith(File.separator)){
                    temp=new File(oldPath+file[i]);
                }
                else{
                    temp=new File(oldPath+File.separator+file[i]);
                }

                if(temp.isFile()){
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath + "/" +
                            (temp.getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ( (len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if(temp.isDirectory()){//如果是子文件夹
                    copyFolder(oldPath+"/"+file[i],newPath+"/"+file[i]);
                }
            }
        }
        catch (Exception e) {
            System.out.println("复制整个文件夹内容操作出错");
            e.printStackTrace();

        }

    }
    //onPostExecute方法用于在执行完后台任务后更新UI,显示结果
    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
        if (result.equals("备份成功")) {
            Toast.makeText(mContext, "备份路径:" + backupPath, Toast.LENGTH_SHORT).show();
            //Toast.makeText(mContext, "备份路径:" + backup, Toast.LENGTH_SHORT).show();
        }

    }

}
