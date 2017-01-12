package com.android.csiapp.WebServiceTransmission;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.SystemProperties;
import android.util.Log;

import com.android.csiapp.XmlHandler.DataInitial;

import org.dom4j.io.SAXReader;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import org.dom4j.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by user on 2017/1/10.
 */
public class WebService {
    private static String TAG = "WebService";
    private static String mTCPAddress;
    //定义获取手机信息的SoapAction与命名空间,作为常量
    private static final String AddressnameSpace = "http://192.168.0.185:80/";
    //相关参数
    private static final String KKWebServiceUrl = "http://192.168.0.185/KKWebService.asmx?wsdl";

    private static final String GetServerAddressMethod = "GetServerAddress";
    private static final String GetServerAddressSoapAction = "http://tempuri.org/GetServerAddress";
    private static final String DevInitialiseMethod = "DevInitialise";
    private static final String DevInitialiseSoapAction = "http://tempuri.org/DevInitialise";
    private static final String AppUpdateVersionMethod = "AppUpdateVersion";
    private static final String AppUpdateVersionSoapAction = "http://tempuri.org/AppUpdateVersion";
    private static final String SubmitSceneInfoMethod = "SubmitSceneInfo";
    private static final String SubmitSceneInfoSoapAction = "http://tempuri.org/SubmitSceneInfo";
    private static final String GetSceneNoMethod = "GetSceneNo";
    private static final String GetSceneNoSoapAction = "http://tempuri.org/GetSceneNo";

    private Context mContext;

    public WebService(Context context){
        this.mContext = context;
    }

    public void DeviceInitial(){
        Log.d(TAG,"DeviceInitial");
        new Thread(new DeviceInitialTask()).start();
    }

    public class DeviceInitialTask implements Runnable{
        @Override
        public void run() {
            //Web Service
            String result = WebService.DevInitialise();

            //Initial Device
            if(!result.isEmpty()){
                try {
                    Document doc = DocumentHelper.parseText(result);
                    DataInitial dataInitial = new DataInitial(mContext);
                    Boolean InitResult = dataInitial.InitialDevice(doc);
                    Log.d(TAG,"InitResult = "+InitResult);
                }catch (DocumentException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void AppUpdate(){
        Log.d(TAG,"AppUpdate");
        new Thread(new AppUpdateTask()).start();
    }

    public class AppUpdateTask implements Runnable{
        @Override
        public void run() {
            //get swversion
            String swversion = "";
            try {
                PackageManager manager = mContext.getPackageManager();
                PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), 0);
                swversion = info.versionName;
            } catch (Exception e) {
                e.printStackTrace();
            }

            //Web service
            String result = WebService.AppUpdateVersion(swversion);

            //Check update status
            Boolean isUpdate = false;
            String updatestatus = "", appversion = "";
            if(!result.isEmpty()){
                try {
                    Document doc = DocumentHelper.parseText(result);
                    Element rootElt = doc.getRootElement();
                    updatestatus = rootElt.elementTextTrim("updatestatus");
                    appversion = rootElt.elementTextTrim("appversion");
                    Log.d(TAG, "updatestatus = " + updatestatus + ", appversion = " + appversion);

                    switch(updatestatus){
                        case "0":
                            isUpdate = false;
                            break;
                        case "1":
                            isUpdate = true;
                            break;
                        case "9":
                            isUpdate = false;
                            break;
                        default:
                            isUpdate = false;
                            break;
                    }
                }catch (DocumentException e){
                    e.printStackTrace();
                }catch (NoSuchElementException e){
                    e.printStackTrace();
                }
            }

            //downlad and install app
            if(isUpdate){
                WebService.GetServerAddress();
                if(!mTCPAddress.isEmpty()){
                    //Todo : Start download App socket
                }
            }
        }
    }

    public void UploadScene(){
        Log.d(TAG,"UploadScene");
        new Thread(new UploadSceneTask()).start();
    }

    public class UploadSceneTask implements Runnable{
        @Override
        public void run() {
            SharedPreferences prefs1 = mContext.getSharedPreferences("LoginName", 0);
            String LoginName = prefs1.getString("loginname", "");

            DataInitial dataInitial = new DataInitial(mContext);
            dataInitial.CreateBaseMsg(LoginName);

            File f=new File(Environment.getExternalStorageDirectory(), "ScenesMsg.xml");
            String sceneInfo = "";
            if(f.exists()){
                try {
                    SAXReader reader = new SAXReader();
                    Document doc = reader.read(new File(Environment.getExternalStorageDirectory(), "ScenesMsg.xml"));
                    sceneInfo = doc.asXML();
                    Log.d(TAG, "sceneInfo = "+sceneInfo);
                }catch (DocumentException e){
                    e.printStackTrace();
                }catch (NoSuchElementException e){
                    e.printStackTrace();
                }
            }

            String result = WebService.SubmitSceneInfo(sceneInfo);
            if(result.equalsIgnoreCase("1")){
                WebService.GetServerAddress();
                if(!mTCPAddress.isEmpty()){
                    //Todo : Start upload scene socket
                    String SceneNo = WebService.GetSceneNo("");
                    //Todo : listen to sceneno and write sceneno
                }
            }
        }
    }

    //定义一个获取TCP地址的方法：
    private static void GetServerAddress() {
        Log.d(TAG,"GetServerAddress");
        SoapObject soapObject = new SoapObject(AddressnameSpace, GetServerAddressMethod);
        soapObject.addProperty("deviceID", SystemProperties.get("ro.serialno"));
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = soapObject;
        envelope.dotNet = true;
        envelope.setOutputSoapObject(soapObject);
        HttpTransportSE httpTransportSE = new HttpTransportSE(KKWebServiceUrl);
        try {
            httpTransportSE.call(GetServerAddressSoapAction, envelope);
            Log.d(TAG,"调用WebService服务成功");
        } catch (Exception e) {
            Log.d(TAG,"调用WebService服务失败");
            e.printStackTrace();
        }

        //获得服务返回的数据,并且开始解析
        try {
            SoapObject object = (SoapObject) envelope.bodyIn;
            System.out.println("获得服务数据");
            mTCPAddress = object.getProperty(0).toString();
            if (null == mTCPAddress ||mTCPAddress.equals("anyType{}") || mTCPAddress.equals("[]")) {
                mTCPAddress="";
            }
            Log.d(TAG, "mTCPAddress : "+mTCPAddress);
            //handler.sendEmptyMessage(0x001);
        }catch (ClassCastException e){
            SoapFault fault = (SoapFault) envelope.bodyIn;
            Log.d(TAG, "Error message : "+fault.toString());
        }
    }

    //定义一个获取初始化信息的方法：
    private static String DevInitialise() {
        Log.d(TAG,"DevInitialise");
        String result = "";
        SoapObject soapObject = new SoapObject(AddressnameSpace, DevInitialiseMethod);
        soapObject.addProperty("deviceID", SystemProperties.get("ro.serialno"));
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = soapObject;
        envelope.dotNet = true;
        envelope.setOutputSoapObject(soapObject);
        HttpTransportSE httpTransportSE = new HttpTransportSE(KKWebServiceUrl);
        try {
            httpTransportSE.call(DevInitialiseSoapAction, envelope);
            Log.d(TAG,"调用WebService服务成功");
        } catch (Exception e) {
            Log.d(TAG,"调用WebService服务失败");
            e.printStackTrace();
        }

        //获得服务返回的数据,并且开始解析
        try {
            SoapObject object = (SoapObject) envelope.bodyIn;
            System.out.println("获得服务数据");
            result = object.getProperty(0).toString();
            if (null == result ||result.equals("anyType{}") || result.equals("[]")) {
                result="";
            }
            Log.d(TAG, "result : "+result);
            //handler.sendEmptyMessage(0x001);
        }catch (ClassCastException e){
            SoapFault fault = (SoapFault) envelope.bodyIn;
            Log.d(TAG, "Error message : "+fault.toString());
        }
        return result;
    }

    //定义一个检查APP更新版本信息的方法：
    private static String AppUpdateVersion(String swversion) {
        Log.d(TAG,"AppUpdateVersion");
        String result = "";
        SoapObject soapObject = new SoapObject(AddressnameSpace, AppUpdateVersionMethod);
        soapObject.addProperty("deviceID", SystemProperties.get("ro.serialno"));
        soapObject.addProperty("devVersion", swversion);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = soapObject;
        envelope.dotNet = true;
        envelope.setOutputSoapObject(soapObject);
        HttpTransportSE httpTransportSE = new HttpTransportSE(KKWebServiceUrl);
        try {
            httpTransportSE.call(AppUpdateVersionSoapAction, envelope);
            Log.d(TAG,"调用WebService服务成功");
        } catch (Exception e) {
            Log.d(TAG,"调用WebService服务失败");
            e.printStackTrace();
        }

        //获得服务返回的数据,并且开始解析
        try {
            SoapObject object = (SoapObject) envelope.bodyIn;
            System.out.println("获得服务数据");
            result = object.getProperty(0).toString();
            if (null == result ||result.equals("anyType{}") || result.equals("[]")) {
                result="";
            }
            Log.d(TAG, "result : "+result);
            //handler.sendEmptyMessage(0x001);
        }catch (ClassCastException e){
            SoapFault fault = (SoapFault) envelope.bodyIn;
            Log.d(TAG, "Error message : "+fault.toString());
        }
        return result;
    }

    //定义一个上报现勘信息的方法：
    private static String SubmitSceneInfo(String sceneInfo) {
        Log.d(TAG,"SubmitSceneInfo");
        String result = "";
        SoapObject soapObject = new SoapObject(AddressnameSpace, SubmitSceneInfoMethod);
        soapObject.addProperty("deviceID", SystemProperties.get("ro.serialno"));
        soapObject.addProperty("sceneInfo", sceneInfo);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = soapObject;
        envelope.dotNet = true;
        envelope.setOutputSoapObject(soapObject);
        HttpTransportSE httpTransportSE = new HttpTransportSE(KKWebServiceUrl);
        try {
            httpTransportSE.call(SubmitSceneInfoSoapAction, envelope);
            Log.d(TAG,"调用WebService服务成功");
        } catch (Exception e) {
            Log.d(TAG,"调用WebService服务失败");
            e.printStackTrace();
        }

        //获得服务返回的数据,并且开始解析
        try {
            SoapObject object = (SoapObject) envelope.bodyIn;
            System.out.println("获得服务数据");
            result = object.getProperty(0).toString();
            if (null == result ||result.equals("anyType{}") || result.equals("[]")) {
                result="";
            }
            Log.d(TAG, "result : "+result);
            //handler.sendEmptyMessage(0x001);
        }catch (ClassCastException e){
            SoapFault fault = (SoapFault) envelope.bodyIn;
            Log.d(TAG, "Error message : "+fault.toString());
        }
        return result;
    }

    //定义一个获取现勘编号的方法：
    private static String GetSceneNo(String sceneId) {
        Log.d(TAG,"GetSceneNo");
        String result = "";
        SoapObject soapObject = new SoapObject(AddressnameSpace, GetSceneNoMethod);
        soapObject.addProperty("deviceID", SystemProperties.get("ro.serialno"));
        soapObject.addProperty("sceneID", sceneId);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = soapObject;
        envelope.dotNet = true;
        envelope.setOutputSoapObject(soapObject);
        HttpTransportSE httpTransportSE = new HttpTransportSE(KKWebServiceUrl);
        try {
            httpTransportSE.call(GetSceneNoSoapAction, envelope);
            Log.d(TAG,"调用WebService服务成功");
        } catch (Exception e) {
            Log.d(TAG,"调用WebService服务失败");
            e.printStackTrace();
        }

        //获得服务返回的数据,并且开始解析
        try {
            SoapObject object = (SoapObject) envelope.bodyIn;
            System.out.println("获得服务数据");
            result = object.getProperty(0).toString();
            if (null == result ||result.equals("anyType{}") || result.equals("[]")) {
                result="";
            }
            Log.d(TAG, "result : "+result);
            //handler.sendEmptyMessage(0x001);
        }catch (ClassCastException e){
            SoapFault fault = (SoapFault) envelope.bodyIn;
            Log.d(TAG, "Error message : "+fault.toString());
        }
        return result;
    }
}
