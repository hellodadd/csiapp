package com.android.csiapp.PcSocketTransmission;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

/**
 * Created by JOWE on 2016/9/29.
 */

public class ThreadReadWriterIOSocket implements Runnable {

    public static final String TAG = "ThreadRWIOSocket";
    private Socket client;
    private Context context;

    public ThreadReadWriterIOSocket(Context context, Socket client) {
        this.client = client;
        this.context = context;
    }

    @Override
    public void run() {
        Log.d(TAG, "A client has connected to server!");
        BufferedOutputStream out;
        BufferedInputStream in;
        try {
			/* PC端发来的数据msg */
            String currCMD = "";
            int[] currcmdinfo;
            //String currCMD = new String[4];
            out = new BufferedOutputStream(client.getOutputStream());
            in = new BufferedInputStream(client.getInputStream());
            SocketService.ioThreadFlag = true;
            while (SocketService.ioThreadFlag) {
                try {
                    if (!client.isConnected()) {
                        break;
                    }
					/* 接收PC发来的数据 */
                    Log.v(TAG, Thread.currentThread().getName() + "---->" + "will read......");

                    /* 读操作命令 */
                    currcmdinfo = readCMDFromSocket(in);

                    Log.v(TAG, Thread.currentThread().getName() + "---->" + "**currCMD ==== " + currCMD);


					/* 根据命令分别处理数据 */
                    if (currcmdinfo != null) {
                        switch (currcmdinfo[1]) {
                            case 1: //获取设备信息命令
                                receiveDataFromSocket(in, currcmdinfo);
                                File file = FileHelper.newFile("DeviceMsg.xml");
                                if (file.exists() == true) {
                                    byte[] abyte = FileHelper.readFile(file);
                                    concatCmdline(out, currcmdinfo, abyte.length);
                                    sendDeviceinfo(out, currcmdinfo, file);
                                } else {
                                    String errstr= "File Not Found";
                                    byte [] errbyte = errstr.getBytes("UTF-8");
                                    concatCmdline(out, currcmdinfo, errbyte.length);
                                    sendErrorString(out, errbyte);
                                }
                                SocketService.ioThreadFlag=false;
                                break;
                            case 2: //设备初始化命令
                                break;
                            case 3: //App更新命令
                                break;
                            case 4: //地图更新命令
                                break;
                            case 11: //获取现场列表命令
                                break;
                            case 12: //获取现场信息命令
                                break;
                            case 13: //回写现勘编号命令
                                break;
                            case 14: //删除现场信息命令
                                break;
                            case 21: //获取现场基站列表命令
                                break;
                            case 22: //获取现场基站信息命令
                                break;
                            case 23: //回写现场基站状态命令
                                receiveDataFromSocket(in, currcmdinfo);
                                concatCmdline(out, currcmdinfo,1);
                                sendResult(out,false);
                                break;
                            case 24: //删除现场基站信息命令
                                break;
                            default:
                                Log.e(TAG, "incorrect cmd =" + currcmdinfo[1]);
                                break;
                        }
                        SocketService.ioThreadFlag=false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (client != null) {
                    Log.v(TAG, Thread.currentThread().getName() + "---->" + "client.close()");
                    client.close();
                }
            } catch (IOException e) {
                Log.e(TAG, Thread.currentThread().getName() + "---->" + "read write error333333");
                e.printStackTrace();
            }
        }
    }

    public int[] readCMDFromSocket(InputStream in) {

        Log.d(TAG,"readCMDFromSocket");
        int MAX_BUFFER_BYTES = 2048;
        byte[] tempbuffer = new byte[MAX_BUFFER_BYTES];
        int[] cmdinfo = new int[4]; //int[0]:CmdId, int[1]:Cmd, int[2]:is File, int[3]:Data Length
        byte[] cmdidbytes = new byte[4];
        byte[] datalength = new byte[4];

        try {

            int numReadedBytes = in.read(tempbuffer, 0, tempbuffer.length);

            /* cmdline:10byte, byte 1~4:Cmd, byte 5:Cmd, byte 6:is File, byte 7~10:Data Length*/
            System.arraycopy(tempbuffer,0,cmdidbytes,0,4);
            cmdinfo[0] = Utils.bytesToInt(cmdidbytes);
            cmdinfo[1] = tempbuffer[4];
            cmdinfo[2] = tempbuffer[5];
            System.arraycopy(tempbuffer,6,datalength,0,4);
            cmdinfo[3] = Utils.bytesToInt(datalength);

            for(int i=0;i<4;i++) {
                Log.d(TAG,"cmdinfo["+i+"]="+cmdinfo[i]);
            }
        } catch (Exception e) {
            Log.v(TAG, Thread.currentThread().getName() + "---->" + "readFromSocket error");
            e.printStackTrace();
            cmdinfo = null;
        }
        // Log.v(Service139.TAG, "msg=" + msg);
        // tempbuffer =null;
        return cmdinfo;
    }

    public void receiveDataFromSocket(BufferedInputStream in, int[] cmdinfo) {
        int ch = 0;
        //int count = 0;
        int receivedatalength = cmdinfo[3];
        int count=0, readcount=0;
        byte[] b = null;


        try {
            while (count == 0) {
                count = in.available();
                Log.d(TAG,"count="+count);
            }
            if (receivedatalength == count){
                Log.d(TAG, "receive data is same");
            } else {
                Log.d(TAG, "receivedatalength="+receivedatalength+", count="+count);
            }
            b = new byte[count];
            while (readcount < count) {
                readcount += in.read(b, readcount, count - readcount);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        String filedata="";

        if (b!=null) {
            try {
                filedata = new String(b, "UTF-8");
            }catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        Log.d(TAG,"File Data="+ filedata);
        /*
              StringBuilder receiveStream = new StringBuilder();
              count=0;
              try {
                  while ((ch = in.read()) != -1) {
                      receiveStream.append((char)ch);
                      count++;
                      if(count == receivedatalength) {
                          Log.d(TAG,"Receive Finished: count="+count);
                          break;
                      }

                  }

             } catch (Exception e) {
                 e.printStackTrace();
             }
             Log.d(TAG,"receiveStream="+receiveStream.toString());
             */
    }

    public void sendDeviceinfo(BufferedOutputStream out, int[] cmdinfo, File file) {
        Log.d(TAG,"Enter sendDeviceinfo");
        byte[] buffer;
        int bufferSize, byteAvailable, byteRead;
        int maxBufferSize=1*1024*1024; //1024k

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byteAvailable = fileInputStream.available();
            Log.d(TAG,"byteAvailable(1)="+byteAvailable);
            bufferSize = Math.min(byteAvailable, maxBufferSize);
            buffer = new byte[bufferSize];

            byteRead = fileInputStream.read(buffer,0,bufferSize);

            while(byteRead > 0 ){
                Log.d(TAG,"byteRead="+byteRead);
                out.write(buffer,0,bufferSize);
                byteAvailable = fileInputStream.available();
                Log.d(TAG,"byteAvailable(2)="+byteAvailable);
                bufferSize = Math.min(byteAvailable, maxBufferSize);
                byteRead = fileInputStream.read(buffer,0,bufferSize);
            }
            fileInputStream.close();
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public byte[] concatCmdline(BufferedOutputStream out, int[] cmdinfo, int datalen) {

        Log.d(TAG,"Enter CombineCmdline");
        byte[] sendCmdline = new byte [10];
        byte[] cmdid = Utils.intToByteBySize(cmdinfo[0],4);
        byte[] cmd =  Utils.intToByteBySize(cmdinfo[1],1);
        byte[] isfile = Utils.intToByteBySize(cmdinfo[2],1);
        byte[] datalength = Utils.intToByteBySize(datalen,4);

        //cmdid = Utils.InttoByteBySize(cmdinfo[0],4);
        //cmd = Utils.InttoByteBySize(cmdinfo[1],1);
        //isfile = Utils.InttoByteBySize(cmdinfo[2],1);
        //datalength = Utils.InttoByteBySize(cmdinfo[3],4);

        System.arraycopy(cmdid,0,sendCmdline,0,cmdid.length);
        System.arraycopy(cmd,0,sendCmdline,cmdid.length,cmd.length);
        System.arraycopy(isfile,0,sendCmdline,cmdid.length+cmd.length,isfile.length);
        System.arraycopy(datalength,0,sendCmdline,cmdid.length+cmd.length+isfile.length,datalength.length);

        Log.d(TAG,"CombineCmdline sendCmdline len="+sendCmdline.length);
        try {
            out.write(sendCmdline, 0, 10);
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
        return sendCmdline;

    }

    public void sendResult(BufferedOutputStream out, boolean result) {
        byte[] resultbyte = new byte[]{(byte) (result?1:0)};
        try {
            out.write(resultbyte);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    public void sendErrorString(BufferedOutputStream out, byte[] errbyte) {
        try {
            out.write(errbyte, 0, errbyte.length);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
