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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlHandler {

    private final static String TAG = "XmlHandler";
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

    public Object[] getInitialDeviceCmd() {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "InitDeviceCmd.xml");
            FileInputStream fileInputStream = new FileInputStream(file);
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(fileInputStream, "utf-8");
            int eventType = parser.getEventType();
            Dictionary dictionary =null;
            User user = null;
            List<Dictionary> dictionarys = null;
            List<User> users = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        String name = parser.getName();
                        if (name.equalsIgnoreCase("dictionarys")) {
                            dictionarys = new ArrayList<Dictionary>();
                        } else if (name.equalsIgnoreCase("dictionary")) {
                            dictionary = new Dictionary();
                        } else if (name.equalsIgnoreCase("dictkey")) {
                            eventType = parser.next();
                            dictionary.setDictKey(parser.getText());
                        } else if (name.equalsIgnoreCase("parentkey")) {
                            eventType = parser.next();
                            dictionary.setParentKey(parser.getText());
                        } else if (name.equalsIgnoreCase("rootkey")) {
                            eventType = parser.next();
                            dictionary.setRootKey(parser.getText());
                        } else if (name.equalsIgnoreCase("dictvalue")) {
                            eventType = parser.next();
                            dictionary.setDictValue(parser.getText());
                        } else if (name.equalsIgnoreCase("dictremark")) {
                            eventType = parser.next();
                            dictionary.setDictRemark(parser.getText());
                        } else if (name.equalsIgnoreCase("dictspell")) {
                            eventType = parser.next();
                            dictionary.setDictSpell(parser.getText());
                        } else if (name.equalsIgnoreCase("users")) {
                            users = new ArrayList<User>();
                        } else if (name.equalsIgnoreCase("user")) {
                            user = new User();
                        } else if (name.equalsIgnoreCase("loginname")) {
                            eventType = parser.next();
                            user.setLoginName(parser.getText());
                        } else if (name.equalsIgnoreCase("password")) {
                            eventType = parser.next();
                            user.setPassword(parser.getText());
                        } else if (name.equalsIgnoreCase("username")) {
                            eventType = parser.next();
                            user.setUserName(parser.getText());
                        } else if (name.equalsIgnoreCase("unitcode")) {
                            eventType = parser.next();
                            user.setUnitCode(parser.getText());
                        } else if (name.equalsIgnoreCase("unitname")) {
                            eventType = parser.next();
                            user.setUnitName(parser.getText());
                        } else if (name.equalsIgnoreCase("idcardno")) {
                            eventType = parser.next();
                            user.setIdCardNo(parser.getText());
                        } else if (name.equalsIgnoreCase("contact")) {
                            eventType = parser.next();
                            user.setContact(parser.getText());
                        } else if (name.equalsIgnoreCase("duty")) {
                            eventType = parser.next();
                            user.setDuty(parser.getText());
                        }

                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equalsIgnoreCase("dictionary")) {
                            if (dictionarys!=null) {
                                dictionarys.add(dictionary);
                            }
                            dictionary = null;
                        } else if(parser.getName().equalsIgnoreCase("user")) {
                            if (users != null) {
                                users.add(user);
                            }
                            user = null;
                        }
                        break;
                }

                eventType = parser.next();

            }
            fileInputStream.close();
            return new Object[]{dictionarys, users};

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
