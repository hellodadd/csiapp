package com.android.csiapp.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
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
    public static final String OVERVIEW_COLUMN = "overview_number";
    public static final String RELATEDPEOPLE_ITEM_NUMBER_COLUMN = "relatedpeople_item_number";
    public static final String LOST_ITEM_NUMBER_COLUMN = "lost_item_number";
    public static final String CRIMETOOL_ITEM_NUMBER_COLUMN = "crimetool_item_number";
    public static final String POSITION_ITEM_NUMBER_COLUMN = "position_item_number";
    public static final String POSITIONPHOTO_ITEM_NUMBER_COLUMN = "positionphoto_item_number";
    public static final String OVERVIEWPHOTO_ITEM_NUMBER_COLUMN = "overviewphoto_item_number";
    public static final String IMPORTANTPHOTO_ITEM_NUMBER_COLUMN = "importantphoto_item_number";
    public static final String EVIDENCE_ITEM_NUMBER_COLUMN = "evidence_item_number";
    public static final String WITNESS_ITEM_NUMBER_COLUMN = "witness_item_number";

    // 使用上面宣告的變數建立表格的SQL指令
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    SCENE_ITEM_NUMBER_COLUMN + " INTEGER NOT NULL, " +
                    ANALYSIS_ITEM_NUMBER_COLUMN + " INTEGER NOT NULL, " +
                    OVERVIEW_COLUMN + " INTEGER NOT NULL, " +
                    RELATEDPEOPLE_ITEM_NUMBER_COLUMN + " INTEGER NOT NULL, " +
                    LOST_ITEM_NUMBER_COLUMN + " INTEGER NOT NULL, " +
                    CRIMETOOL_ITEM_NUMBER_COLUMN + " INTEGER NOT NULL, " +
                    POSITION_ITEM_NUMBER_COLUMN + " INTEGER NOT NULL, " +
                    POSITIONPHOTO_ITEM_NUMBER_COLUMN + " INTEGER NOT NULL, " +
                    OVERVIEWPHOTO_ITEM_NUMBER_COLUMN + " INTEGER NOT NULL, " +
                    IMPORTANTPHOTO_ITEM_NUMBER_COLUMN + " INTEGER NOT NULL, " +
                    EVIDENCE_ITEM_NUMBER_COLUMN + " INTEGER NOT NULL, " +
                    WITNESS_ITEM_NUMBER_COLUMN + " INTEGER NOT NULL)";

    // 資料庫物件
    private SQLiteDatabase db;
    private SceneProvider mSceneProvider;
    private AnalysisProvider mAnalysisProvider;
    private OverviewProvider mOverviewProvider;
    private RelatedPeopleProvider mRelatedPeopleProvider;
    private LostProvider mLostProvider;
    private CrimeToolProvider mCrimeToolProvider;
    private PositionProvider mPositionProvider;
    private PositionPhotoProvider mPositionPhotoProvider;
    private OverviewPhotoProvider mOverviewPhotoProvider;
    private ImportantPhotoProvider mImportantPhotoProvider;
    private EvidenceProvider mEvidenceProvider;
    private WitnessProvider mWitnessProvider;

    // 建構子，一般的應用都不需要修改
    public CrimeProvider(Context context) {
        db = DatabasesHelper.getDatabase(context);
        mSceneProvider = new SceneProvider(context);
        mAnalysisProvider = new AnalysisProvider(context);
        mOverviewProvider = new OverviewProvider(context);
        mRelatedPeopleProvider = new RelatedPeopleProvider(context);
        mLostProvider = new LostProvider(context);
        mCrimeToolProvider = new CrimeToolProvider(context);
        mPositionProvider = new PositionProvider(context);
        mPositionPhotoProvider = new PositionPhotoProvider(context);
        mOverviewPhotoProvider = new OverviewPhotoProvider(context);
        mImportantPhotoProvider = new ImportantPhotoProvider(context);
        mEvidenceProvider = new EvidenceProvider(context);
        mWitnessProvider = new WitnessProvider(context);
    }

    // 關閉資料庫，一般的應用都不需要修改
    public void close() {
        db.close();
    }

    //ToDo
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
        long overview_id = mOverviewProvider.insert(item);
        cv.put(OVERVIEW_COLUMN, overview_id);

        String related_id = mRelatedPeopleProvider.inserts(item.getReleatedPeople());
        cv.put(RELATEDPEOPLE_ITEM_NUMBER_COLUMN, related_id);

        String lost_id = mLostProvider.inserts(item.getLostItem());
        cv.put(LOST_ITEM_NUMBER_COLUMN, lost_id);

        String crimetool_id = mCrimeToolProvider.inserts(item.getCrimeTool());
        cv.put(CRIMETOOL_ITEM_NUMBER_COLUMN, crimetool_id);

        String position_id = mPositionProvider.inserts(item.getPosition());
        cv.put(POSITION_ITEM_NUMBER_COLUMN, position_id);

        String positionPhoto_id = mPositionPhotoProvider.inserts(item.getPositionPhoto());
        cv.put(POSITIONPHOTO_ITEM_NUMBER_COLUMN, positionPhoto_id);

        String overviewPhoto_id = mOverviewPhotoProvider.inserts(item.getOverviewPhoto());
        cv.put(OVERVIEWPHOTO_ITEM_NUMBER_COLUMN, overviewPhoto_id);

        String importantPhoto_id = mImportantPhotoProvider.inserts(item.getImportantPhoto());
        cv.put(IMPORTANTPHOTO_ITEM_NUMBER_COLUMN, importantPhoto_id);

        String evidence_id = mEvidenceProvider.inserts(item.getEvidenceItem());
        cv.put(EVIDENCE_ITEM_NUMBER_COLUMN, evidence_id);

        String witness_id = mWitnessProvider.inserts(item.getWitness());
        cv.put(WITNESS_ITEM_NUMBER_COLUMN, witness_id);
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

    //Todo
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
            boolean result = false;
            result = mSceneProvider.update(cursor.getLong(1),item);
            result = mAnalysisProvider.update(cursor.getLong(2),item);
            result = mOverviewProvider.update(cursor.getLong(3),item);
            result = mRelatedPeopleProvider.updates(cursor.getString(4),item.getReleatedPeople());
            result = mLostProvider.updates(cursor.getString(5),item.getLostItem());
            result = mCrimeToolProvider.updates(cursor.getString(6),item.getCrimeTool());
            result = mPositionProvider.updates(cursor.getString(7),item.getPosition());
            result = mPositionPhotoProvider.updates(cursor.getString(8),item.getPositionPhoto());
            result = mOverviewPhotoProvider.updates(cursor.getString(9),item.getOverviewPhoto());
            result = mImportantPhotoProvider.updates(cursor.getString(10),item.getImportantPhoto());
            result = mEvidenceProvider.updates(cursor.getString(11),item.getEvidenceItem());
            result = mWitnessProvider.updates(cursor.getString(12),item.getWitness());
            //return db.update(TABLE_NAME, cv, where, null) > 0;
            return result;
        }
        return false;
    }

    //Todo
    // 刪除參數指定編號的資料
    public boolean delete(long id){
        // 設定條件為編號，格式為「欄位名稱=資料」
        String where = KEY_ID + "=" + id;
        Cursor cursor = db.query(
                TABLE_NAME, null, where, null, null, null, null, null);
        if(cursor.moveToFirst()) {
            // 刪除指定編號資料並回傳刪除是否成功
            boolean result = false;
            result = mSceneProvider.delete(cursor.getLong(1));
            result = mAnalysisProvider.delete(cursor.getLong(2));
            result = mOverviewProvider.delete(cursor.getLong(3));
            result = mRelatedPeopleProvider.deletes(cursor.getString(4));
            result = mLostProvider.deletes(cursor.getString(5));
            result = mCrimeToolProvider.deletes(cursor.getString(6));
            result = mPositionProvider.deletes(cursor.getString(7));
            result = mPositionPhotoProvider.deletes(cursor.getString(8));
            result = mOverviewPhotoProvider.deletes(cursor.getString(9));
            result = mImportantPhotoProvider.deletes(cursor.getString(10));
            result = mEvidenceProvider.deletes(cursor.getString(11));
            result = mWitnessProvider.deletes(cursor.getString(12));
            //return db.delete(TABLE_NAME, where, null) > 0;
            return result;
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
        //Login
        db.execSQL("DROP TABLE IF EXISTS " + IdentifyProvider.TABLE_NAME);
        //Whole key
        db.execSQL("DROP TABLE IF EXISTS " + CrimeProvider.TABLE_NAME);
        //Page 1
        db.execSQL("DROP TABLE IF EXISTS " + SceneProvider.TABLE_NAME);
        //Page 2
        db.execSQL("DROP TABLE IF EXISTS " + RelatedPeopleProvider.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + LostProvider.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CrimeToolProvider.TABLE_NAME);
        //Page 3
        db.execSQL("DROP TABLE IF EXISTS " + PositionProvider.TABLE_NAME);
        //Page 4
        db.execSQL("DROP TABLE IF EXISTS " + PositionPhotoProvider.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + OverviewPhotoProvider.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ImportantPhotoProvider.TABLE_NAME);
        //Page 5
        db.execSQL("DROP TABLE IF EXISTS " + EvidenceProvider.TABLE_NAME);
        //Page 6
        db.execSQL("DROP TABLE IF EXISTS " + OverviewProvider.TABLE_NAME);
        //Page 7
        db.execSQL("DROP TABLE IF EXISTS " + AnalysisProvider.TABLE_NAME);
        //Page 8
        db.execSQL("DROP TABLE IF EXISTS " + WitnessProvider.TABLE_NAME);


        // 建立新版的表格
        //Login
        db.execSQL(IdentifyProvider.IDENTIFY_TABLE);
        //Whole key
        db.execSQL(CrimeProvider.CREATE_TABLE);
        //Page 1
        db.execSQL(SceneProvider.CREATE_TABLE);
        //Page 2
        db.execSQL(RelatedPeopleProvider.CREATE_TABLE);
        db.execSQL(LostProvider.CREATE_TABLE);
        db.execSQL(CrimeToolProvider.CREATE_TABLE);
        //Page 3
        db.execSQL(PositionProvider.CREATE_TABLE);
        //Page 4
        db.execSQL(PositionPhotoProvider.CREATE_TABLE);
        db.execSQL(OverviewPhotoProvider.CREATE_TABLE);
        db.execSQL(ImportantPhotoProvider.CREATE_TABLE);
        //Page 5
        db.execSQL(EvidenceProvider.CREATE_TABLE);
        //Page 6
        db.execSQL(OverviewProvider.CREATE_TABLE);
        //Page 7
        db.execSQL(AnalysisProvider.CREATE_TABLE);
        //Page 8
        db.execSQL(WitnessProvider.CREATE_TABLE);
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

    //Todo
    // 把Cursor目前的資料包裝為物件
    private CrimeItem getRecord(long id) {
        // 準備回傳結果用的物件
        CrimeItem result = new CrimeItem();
        result.setId(id);

        String where = KEY_ID + "=" + id;
        Cursor cursor = db.query(
                TABLE_NAME, null, where, null, null, null, null, null);

        if(cursor.moveToFirst()) {
            String where1 = KEY_ID + "=" + cursor.getLong(1);
            Cursor cursor1 = db.query(
                    SceneProvider.TABLE_NAME, null, where1, null, null, null, null, null);
            if (cursor1.moveToFirst()) {
                // 讀取包裝一筆資料的物件
                result.setCasetype(cursor1.getString(1));
                result.setArea(cursor1.getString(2));
                result.setLocationa(cursor1.getString(3));
                result.setOccurredStartTime(cursor1.getLong(4));
                result.setOccurredEndTime(cursor1.getLong(5));
                result.setGetAccessTime(cursor1.getLong(6));
                result.setUnitsAssigned(cursor1.getString(7));
                result.setAccessPolicemen(cursor1.getString(8));
                result.setAccessStartTime(cursor1.getLong(9));
                result.setAccessEndTime(cursor1.getLong(10));
                result.setAccessLocation(cursor1.getString(11));
                result.setCaseOccurProcess(cursor1.getString(12));
                result.setSceneCondition(cursor1.getString(13));
                result.setChangeReason(cursor1.getString(14));
                result.setWeatherCondition(cursor1.getString(15));
                result.setWindDirection(cursor1.getString(16));
                result.setTemperature(cursor1.getString(17));
                result.setHumidity(cursor1.getString(18));
                result.setAccessReason(cursor1.getString(19));
                result.setIlluminationCondition(cursor1.getString(20));
                result.setProductPeopleName(cursor1.getString(21));
                result.setProductPeopleUnit(cursor1.getString(22));
                result.setProductPeopleDuties(cursor1.getString(23));
                result.setSafeguard(cursor1.getString(24));
                result.setSceneConductor(cursor1.getString(25));
                result.setAccessInspectors(cursor1.getString(26));
            }
            cursor1.close();

            String where2 = KEY_ID + "=" + cursor.getLong(2);
            Cursor cursor2 = db.query(
                    AnalysisProvider.TABLE_NAME, null, where2, null, null, null, null, null);
            if (cursor2.moveToFirst()) {
                // 讀取包裝一筆資料的物件
                result.setCrimePeopleNumber(cursor2.getString(1));
                result.setCrimeMeans(cursor2.getString(2));
                result.setCrimeCharacter(cursor2.getString(3));
                result.setCrimeEntrance(cursor2.getString(4));
                result.setCrimeTiming(cursor2.getLong(5));
                result.setSelectObject(cursor2.getString(6));
                result.setCrimeExport(cursor2.getString(7));
                result.setCrimePeopleFeature(cursor2.getString(8));
                result.setCrimeFeature(cursor2.getString(9));
                result.setIntrusiveMethod(cursor2.getString(10));
                result.setSelectLocation(cursor2.getString(11));
                result.setCrimePurpose(cursor2.getString(12));
            }
            cursor2.close();

            String where3 = KEY_ID + "=" + cursor.getLong(3);
            Cursor cursor3 = db.query(
                    OverviewProvider.TABLE_NAME, null, where3, null, null, null, null, null);
            if (cursor3.moveToFirst()) {
                // 讀取包裝一筆資料的物件
                result.setOverview(cursor3.getString(1));
            }
            cursor3.close();

            List<RelatedPeopleItem> RelatedPeople_items = mRelatedPeopleProvider.querys(cursor.getString(4));
            result.setReleatedPeople(RelatedPeople_items);

            List<LostItem> Lost_items = mLostProvider.querys(cursor.getString(5));
            result.setLostItem(Lost_items);

            List<CrimeToolItem> CrimeTool_items = mCrimeToolProvider.querys(cursor.getString(6));
            result.setCrimeTool(CrimeTool_items);

            List<PhotoItem> Position_items = mPositionProvider.querys(cursor.getString(7));
            result.setPosition(Position_items);

            List<PhotoItem> PositionPhoto_items = mPositionPhotoProvider.querys(cursor.getString(8));
            result.setPositionPhoto(PositionPhoto_items);

            List<PhotoItem> OverviewPhoto_items = mOverviewPhotoProvider.querys(cursor.getString(9));
            result.setOverviewPhoto(OverviewPhoto_items);

            List<PhotoItem> ImportantPhoto_items = mImportantPhotoProvider.querys(cursor.getString(10));
            result.setImportantPhoto(ImportantPhoto_items);

            List<EvidenceItem> Evidence_items = mEvidenceProvider.querys(cursor.getString(11));
            result.setEvidenceItem(Evidence_items);

            List<WitnessItem> Witness_items = mWitnessProvider.querys(cursor.getString(11));
            result.setWitness(Witness_items);
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
        long time = Calendar.getInstance().getTimeInMillis();
        CrimeItem item = new CrimeItem(0, "故意伤害", "北京",time);
        CrimeItem item2 = new CrimeItem(0, "诈骗", "南京",time);

        insert(item);
        insert(item2);
    }
}
