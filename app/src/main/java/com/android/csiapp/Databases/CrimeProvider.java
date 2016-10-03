package com.android.csiapp.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnitaLin on 2016/9/9.
 */
public class CrimeProvider {
    // 表格名稱
    public static final String TABLE_NAME = "crime";

    // 編號表格欄位名稱，固定不變
    public static final String KEY_ID = "_id";

    public static final String SCENE_ITEM_NUMBER_COLUMN = "scene_item_number";
    public static final String ANALYSIS_ITEM_NUMBER_COLUMN = "analysis_item_number";

    // 使用上面宣告的變數建立表格的SQL指令
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    SCENE_ITEM_NUMBER_COLUMN + " INTEGER NOT NULL, " +
                    ANALYSIS_ITEM_NUMBER_COLUMN + " INTEGER NOT NULL)";

    // 資料庫物件
    private SQLiteDatabase db;
    private SceneProvider mSceneProvider;
    private AnalysisProvider mAnalysisProvider;

    // 建構子，一般的應用都不需要修改
    public CrimeProvider(Context context) {
        db = DatabasesHelper.getDatabase(context);
        mSceneProvider = new SceneProvider(context);
        mAnalysisProvider = new AnalysisProvider(context);
    }

    // 關閉資料庫，一般的應用都不需要修改
    public void close() {
        db.close();
    }

    // 新增參數指定的物件
    public CrimeItem insert(CrimeItem item) {
        // 建立準備新增資料的ContentValues物件
        ContentValues cv = new ContentValues();

        // 加入ContentValues物件包裝的新增資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        long scene_id = mSceneProvider.insert(item);
        cv.put(SCENE_ITEM_NUMBER_COLUMN, scene_id);
        long analysis_id = mAnalysisProvider.insert(item);
        cv.put(ANALYSIS_ITEM_NUMBER_COLUMN, analysis_id);
        // 新增一筆資料並取得編號
        // 第一個參數是表格名稱
        // 第二個參數是沒有指定欄位值的預設值
        // 第三個參數是包裝新增資料的ContentValues物件
        long id = db.insert(TABLE_NAME, null, cv);

        // 設定編號
        item.setId(id);
        // 回傳結果
        return item;
    }

    // 修改參數指定的物件
    public boolean update(CrimeItem item) {
        // 加入ContentValues物件包裝的修改資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        // 設定修改資料的條件為編號
        // 格式為「欄位名稱＝資料」
        String where = KEY_ID + "=" + item.getId();

        Cursor cursor = db.query(
                TABLE_NAME, null, where, null, null, null, null, null);

        if(cursor.moveToFirst()) {
            // 執行修改資料並回傳修改的資料數量是否成功
            boolean result1 = mSceneProvider.update(cursor.getLong(0),item);
            boolean result2 = mAnalysisProvider.update(cursor.getLong(1),item);
            //return db.update(TABLE_NAME, cv, where, null) > 0;
            return true;
        }
        return false;
    }

    // 刪除參數指定編號的資料
    public boolean delete(long id){
        // 設定條件為編號，格式為「欄位名稱=資料」
        String where = KEY_ID + "=" + id;
        Cursor cursor = db.query(
                TABLE_NAME, null, where, null, null, null, null, null);
        if(cursor.moveToFirst()) {
            // 刪除指定編號資料並回傳刪除是否成功
            mSceneProvider.delete(cursor.getLong(0));
            mAnalysisProvider.delete(cursor.getLong(1));
            //return db.delete(TABLE_NAME, where, null) > 0;
            return true;
        }
        return false;
    }

    // 讀取所有記事資料
    public List<CrimeItem> getAll() {
        List<CrimeItem> result = new ArrayList<>();
        Cursor cursor = db.query(
                TABLE_NAME, null, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            result.add(getRecord(cursor.getInt(0)));
        }

        cursor.close();
        return result;
    }

    // 刪除所有記事資料
    public void deleteAll(){
        // 刪除原有的表格
        db.execSQL("DROP TABLE IF EXISTS " + SceneProvider.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + AnalysisProvider.TABLE_NAME);
        // 建立新版的表格
        db.execSQL(SceneProvider.CREATE_TABLE);
        db.execSQL(AnalysisProvider.CREATE_TABLE);
    }

    // 取得指定編號的資料物件
    public CrimeItem get(long id) {
        // 準備回傳結果用的物件
        CrimeItem item = null;
        // 使用編號為查詢條件
        String where = KEY_ID + "=" + id;
        // 執行查詢
        Cursor result = db.query(
                TABLE_NAME, null, where, null, null, null, null, null);

        // 如果有查詢結果
        if (result.moveToFirst()) {
            // 讀取包裝一筆資料的物件
            item = getRecord(id);
        }

        // 關閉Cursor物件
        result.close();
        // 回傳結果
        return item;
    }

    //ToDo
    // 把Cursor目前的資料包裝為物件
    private CrimeItem getRecord(long id) {
        // 準備回傳結果用的物件
        CrimeItem result = new CrimeItem();
        result.setId(id);

        String where = KEY_ID + "=" + id;
        Cursor cursor = db.query(
                TABLE_NAME, null, where, null, null, null, null, null);

        if(cursor.moveToFirst()) {
            String where1 = KEY_ID + "=" + cursor.getLong(0);
            Cursor cursor1 = db.query(
                    SceneProvider.TABLE_NAME, null, where1, null, null, null, null, null);
            if (cursor1.moveToFirst()) {
                // 讀取包裝一筆資料的物件
                result.setCasetype(cursor1.getString(1));
                result.setArea(cursor1.getString(2));
                result.setLocationa(cursor1.getString(3));
                result.setOccurredStartTime(cursor1.getString(4));
                result.setOccurredEndTime(cursor1.getString(5));
                result.setGetAccessTime(cursor1.getString(6));
                result.setUnitsAssigned(cursor1.getString(7));
                result.setAccessPolicemen(cursor1.getString(8));
                result.setAccessStartTime(cursor1.getString(9));
                result.setAccessEndTime(cursor1.getString(10));
                result.setAccessLocation(cursor1.getString(11));
                result.setCaseOccurProcess(cursor1.getString(12));
                result.setSceneCondition(cursor1.getString(13));
                result.setWeatherCondition(cursor1.getString(14));
                result.setWindDirection(cursor1.getString(15));
                result.setTemperature(cursor1.getString(16));
                result.setHumidity(cursor1.getString(17));
                result.setAccessReason(cursor1.getString(18));
            }
            cursor1.close();

            String where2 = KEY_ID + "=" + cursor.getLong(1);
            Cursor cursor2 = db.query(
                    AnalysisProvider.TABLE_NAME, null, where2, null, null, null, null, null);
            if (cursor2.moveToFirst()) {
                // 讀取包裝一筆資料的物件
                result.setCrimePeopleNumber(cursor2.getString(1));
                result.setCrimeMeans(cursor2.getString(2));
                result.setCrimeCharacter(cursor2.getString(3));
                result.setCrimeEntrance(cursor2.getString(4));
                result.setCrimeTiming(cursor2.getString(5));
                result.setSelectObject(cursor2.getString(6));
                result.setCrimeExport(cursor2.getString(7));
                result.setCrimePeopleFeature(cursor2.getString(8));
                result.setCrimeFeature(cursor2.getString(9));
                result.setIntrusiveMethod(cursor2.getString(10));
                result.setSelectLocation(cursor2.getString(11));
                result.setCrimePurpose(cursor2.getString(12));
            }
            cursor2.close();
        }

        // 回傳結果
        return result;
    }

    // 取得資料數量
    public int getCount() {
        int result = 0;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);

        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }

        return result;
    }

    // 建立範例資料
    public void sample() {
        CrimeItem item = new CrimeItem(0, "故意伤害", "北京","1:30");
        CrimeItem item2 = new CrimeItem(0, "诈骗", "南京","2:30");

        insert(item);
        insert(item2);
    }
}
