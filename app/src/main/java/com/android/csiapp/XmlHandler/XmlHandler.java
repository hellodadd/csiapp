package com.android.csiapp.XmlHandler;

/**
 * Created by JOWE on 2016/9/29.
 */

import android.bluetooth.BluetoothClass;
import android.os.Environment;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlHandler {

    private void createDeviceMsgXmlFile(Object obj) {
        XmlSerializer xmlSerializer = null;
        FileOutputStream fileOutputStream = null;
        String enter = System.getProperty("line.separator");
        List<Device> devices = (List<Device>)obj;
        //List<Dictionary> dictionaryList = createData();
        //List<Dictionary> dictionaryList = createData();

        try {
            //获取xmlSerializer
            xmlSerializer = Xml.newSerializer();
            File file = new File(Environment.getExternalStorageDirectory(), "DeviceMsg.xml");
            fileOutputStream = new FileOutputStream(file);


            String encoding = "utf-8";
            xmlSerializer.setOutput(fileOutputStream, encoding);
            xmlSerializer.startDocument(encoding, null);
            changeLine(xmlSerializer, enter);
            //根节点开始

            xmlSerializer.startTag(null, "device");
            changeLine(xmlSerializer, enter);


            //内容结点
            for(Device d: devices) {

                xmlSerializer.startTag(null, d.getDeviceId()[0]);
                xmlSerializer.text(d.getDeviceId()[1]);
                xmlSerializer.endTag(null, d.getDeviceId()[0]);
                changeLine(xmlSerializer, enter);

                xmlSerializer.startTag(null, d.getInitStatus()[0]);
                xmlSerializer.text(d.getInitStatus()[1]);
                xmlSerializer.endTag(null, d.getInitStatus()[0]);
                changeLine(xmlSerializer, enter);

                xmlSerializer.startTag(null, d.getSwVersion()[0]);
                xmlSerializer.text(d.getSwVersion()[1]);
                xmlSerializer.endTag(null, d.getSwVersion()[0]);
                changeLine(xmlSerializer, enter);

                xmlSerializer.startTag(null, d.getMapVersion()[0]);
                xmlSerializer.text(d.getMapVersion()[1]);
                xmlSerializer.endTag(null, d.getMapVersion()[0]);
                changeLine(xmlSerializer, enter);

            }

            //根节点结束
            xmlSerializer.endTag(null, "device");
            xmlSerializer.endDocument();
            Log.d("aaaa", "endDocument");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void changeLine(XmlSerializer serializer, String enter){
        try{
            serializer.text(enter);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void createDeviceMsg(String deviceid, String initstatus, String swversion, String mapversion ) {
        List<Device> devices = new ArrayList<Device>();
        Device device = new Device();

        device.setDeviceId(deviceid);
        device.setInitStatus(initstatus);
        device.setSwVersion(swversion);
        device.setMapVersion(mapversion);
        devices.add(device);
        createDeviceMsgXmlFile(devices);

    }
}
