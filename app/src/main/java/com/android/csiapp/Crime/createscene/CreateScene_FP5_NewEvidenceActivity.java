package com.android.csiapp.Crime.createscene;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.csiapp.Crime.utils.BackAlertDialog;
import com.android.csiapp.Crime.utils.ClearableEditText;
import com.android.csiapp.Crime.utils.DateTimePicker;
import com.android.csiapp.Crime.utils.DictionaryInfo;
import com.android.csiapp.Crime.utils.Priview_photo_Activity;
import com.android.csiapp.Crime.utils.SaveAlertDialog;
import com.android.csiapp.Crime.utils.UserInfo;
import com.android.csiapp.Crime.utils.tree.TreeViewListDemo;
import com.android.csiapp.Databases.CrimeItem;
import com.android.csiapp.Databases.CrimeProvider;
import com.android.csiapp.Databases.EvidenceItem;
import com.android.csiapp.R;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class CreateScene_FP5_NewEvidenceActivity extends AppCompatActivity implements View.OnClickListener {
    private Context context = null;
    private CrimeItem mItem;
    private EvidenceItem mEvidenceItem;
    private int mEvent;
    private int mPosition;
    private Uri LocalFileUri = null;
    public static final int PHOTO_TYPE_NEW_EVIDENCE = 0;

    private ImageView mNew_evidence;
    private Spinner mEvidence_category_spinner;
    private ArrayList<String> mEvidence_category = new ArrayList<String>();
    private ArrayAdapter<String> mEvidence_category_adapter;
    private TextView mEvidenceTextLabel;
    private ClearableEditText mOtherEvidence;

    private TextView mEvidenceText;
    private int EVENT_EVIDENCE_SELECT_ITEM = 1;

    private ClearableEditText mEvidenceName;
    private ClearableEditText mLegacySite;
    private ClearableEditText mBasiceFeature;

    private ImageView mBasiceFeatureLabel;
    private LinearLayout mInferLL;
    private TextView mInferText;
    private int EVENT_INFER_SELECT_ITEM = 2;

    private TextView mMethodText;
    private int EVENT_METHOD_SELECT_ITEM = 3;
    private ImageView mMethodLabel;
    private ClearableEditText mOtherMethod;

    private TextView mTime;
    private Button mTime_button;

    private TextView mGetPeopleText;
    private int EVENT_GET_PEOPLE_SLELECT_ITEM = 4;

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_camera:
                    msg += "Camera";
                    LocalFileUri = Uri.fromFile(getOutputMediaFile(context, PHOTO_TYPE_NEW_EVIDENCE));
                    takePhoto(LocalFileUri, PHOTO_TYPE_NEW_EVIDENCE);
                    break;
                case R.id.action_click:
                    msg += "Save";
                    saveData();
                    Intent result = getIntent();
                    if(mEvidenceItem.getPhotoPath().isEmpty()) result.putExtra("photo_uri", mEvidenceItem.getPhotoPath());
                    mEvidenceItem.setUuid(CrimeProvider.getUUID());
                    result.putExtra("com.android.csiapp.Databases.EvidenceItem", mEvidenceItem);
                    result.putExtra("Event", mEvent);
                    result.putExtra("Posiotion", mPosition);
                    if(mEvidenceItem.checkInformation()){
                        setResult(Activity.RESULT_OK, result);
                        finish();
                    }else {
                        SaveAlertDialog dialog = new SaveAlertDialog(CreateScene_FP5_NewEvidenceActivity.this);
                        dialog.onCreateDialog(result,false,null);
                        dialog.setOwnerActivity(CreateScene_FP5_NewEvidenceActivity.this);
                    }
                    break;
                default:
                    break;
            }

            if (!msg.equals("")) {
                //Toast.makeText(CreateScene_FP5_NewEvidenceActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_scene_fp5_new_evidence);

        context = this.getApplicationContext();
        mEvent = (int) getIntent().getIntExtra("Event", 1);
        mPosition = (int) getIntent().getIntExtra("Position", 0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(context.getResources().getString(R.string.title_activity_new_evidence));
        toolbar.setTitleTextColor(context.getResources().getColor(R.color.titleBar));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.btn_back_mini);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                BackAlertDialog dialog = new BackAlertDialog(CreateScene_FP5_NewEvidenceActivity.this);
                dialog.onCreateDialog(false,null);
                dialog.setOwnerActivity(CreateScene_FP5_NewEvidenceActivity.this);
            }
        });
        toolbar.setOnMenuItemClickListener(onMenuItemClick);

        initView();
        initData();

        if(mEvent == 1) {
            LocalFileUri = Uri.fromFile(getOutputMediaFile(context, PHOTO_TYPE_NEW_EVIDENCE));
            takePhoto(LocalFileUri, PHOTO_TYPE_NEW_EVIDENCE);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_fp5_subactivity, menu);
        return true;
    }

    private void initView(){
        DictionaryInfo info = new DictionaryInfo(context);
        UserInfo user = new UserInfo(context);

        mNew_evidence = (ImageView) findViewById(R.id.new_evidence);
        mNew_evidence.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent it = new Intent(CreateScene_FP5_NewEvidenceActivity.this, Priview_photo_Activity.class);
                it.putExtra("Path",mEvidenceItem.getPhotoPath());
                startActivityForResult(it, 100);
            }
        });

        mEvidence_category = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.evidence_category)));
        mEvidence_category_spinner = (Spinner) findViewById(R.id.evidence_category_spinner);
        mEvidence_category_adapter = new ArrayAdapter<String>(CreateScene_FP5_NewEvidenceActivity.this, R.layout.spinnerview, mEvidence_category);
        mEvidence_category_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mEvidence_category_spinner.setAdapter(mEvidence_category_adapter);
        mEvidence_category_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                refreshEvidenceItem(position);
                mEvidenceItem.setEvidenceCategory(mEvidence_category.get(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mEvidenceText = (TextView) findViewById(R.id.evidence);
        mEvidenceText.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent it = new Intent(CreateScene_FP5_NewEvidenceActivity.this, TreeViewListDemo.class);
                it.putExtra("Key",DictionaryInfo.mEvidenceHandKey);
                it.putExtra("Selected", mEvidenceItem.getEvidence());
                startActivityForResult(it, EVENT_EVIDENCE_SELECT_ITEM);
            }
        });
        mEvidenceTextLabel = (TextView) findViewById(R.id.evidenceTextLabel);
        mOtherEvidence = (ClearableEditText) findViewById(R.id.other_evidence);

        mEvidenceName = (ClearableEditText) findViewById(R.id.evidence_name);
        mLegacySite = (ClearableEditText) findViewById(R.id.legacy_site);
        mBasiceFeature = (ClearableEditText) findViewById(R.id.basice_feature);

        mBasiceFeatureLabel = (ImageView) findViewById(R.id.basicFeatureLabel);
        mInferLL = (LinearLayout) findViewById(R.id.inferLL);
        mInferText = (TextView) findViewById(R.id.infer);
        mInferText.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent it = new Intent(CreateScene_FP5_NewEvidenceActivity.this, TreeViewListDemo.class);
                it.putExtra("Key",DictionaryInfo.mToolInferKey);
                it.putExtra("Selected", mEvidenceItem.getInfer());
                startActivityForResult(it, EVENT_INFER_SELECT_ITEM);
            }
        });

        mMethodText = (TextView) findViewById(R.id.method);
        mMethodText.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent it = new Intent(CreateScene_FP5_NewEvidenceActivity.this, TreeViewListDemo.class);
                it.putExtra("Key",DictionaryInfo.mMethodHandKey);
                it.putExtra("Selected", mEvidenceItem.getMethod());
                startActivityForResult(it, EVENT_METHOD_SELECT_ITEM);
            }
        });
        mMethodLabel = (ImageView) findViewById(R.id.methodLabel);
        mOtherMethod = (ClearableEditText) findViewById(R.id.other_method);

        mTime = (TextView) findViewById(R.id.time);
        mTime_button = (Button) findViewById(R.id.time_button);
        mTime_button.setOnClickListener(this);

        mGetPeopleText = (TextView) findViewById(R.id.getPeople);
        mGetPeopleText.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent it = new Intent(CreateScene_FP5_NewEvidenceActivity.this, TreeViewListDemo.class);
                it.putExtra("Key",UserInfo.mExtractionPeople);
                it.putExtra("Selected", mEvidenceItem.getPeople());
                it.putExtra("DataInfo", "UserInfo");
                startActivityForResult(it, EVENT_GET_PEOPLE_SLELECT_ITEM);
            }
        });
    }

    private void initData(){
        if(mEvent == 1) {
            mEvidenceItem = new EvidenceItem();
            mItem = (CrimeItem) getIntent().getSerializableExtra("com.android.csiapp.Databases.Item");
            mEvidenceItem.setPeople(mItem.getAccessInspectors());
        }else{
            mEvidenceItem = (EvidenceItem) getIntent().getSerializableExtra("com.android.csiapp.Databases.EvidenceItem");
        }
        if(!mEvidenceItem.getPhotoPath().isEmpty()) setPhoto(mEvidenceItem.getPhotoPath());

        mEvidence_category_spinner.setSelection(getCategory(mEvidenceItem.getEvidenceCategory()));

        int category = getCategory(mEvidenceItem.getEvidenceCategory());
        if(category == 0 ) {
            mEvidenceText.setText(DictionaryInfo.getDictValue(DictionaryInfo.mEvidenceHandKey, mEvidenceItem.getEvidence()));
            mMethodText.setText(DictionaryInfo.getDictValue(DictionaryInfo.mMethodHandKey, mEvidenceItem.getMethod()));
        }else if(category == 1 ){
                mEvidenceText.setText(DictionaryInfo.getDictValue(DictionaryInfo.mEvidenceFootKey, mEvidenceItem.getEvidence()));
                mMethodText.setText(DictionaryInfo.getDictValue(DictionaryInfo.mMethodFootKey, mEvidenceItem.getMethod()));
        }else if(category == 2 ){
            mEvidenceText.setText(DictionaryInfo.getDictValue(DictionaryInfo.mEvidenceToolKey, mEvidenceItem.getEvidence()));
            mMethodText.setText(DictionaryInfo.getDictValue(DictionaryInfo.mMethodToolKey, mEvidenceItem.getMethod()));
        }else if(category == 3 ){
            mEvidenceText.setVisibility(View.GONE);
            mMethodText.setVisibility(View.GONE);
            mOtherEvidence.setVisibility(View.VISIBLE);
            mOtherMethod.setVisibility(View.VISIBLE);
            mOtherEvidence.setText(mEvidenceItem.getEvidence());
            mOtherMethod.setText(mEvidenceItem.getMethod());
        }
        mEvidenceName.setText(mEvidenceItem.getEvidenceName());

        mLegacySite.setText(mEvidenceItem.getLegacySite());
        mBasiceFeature.setText(mEvidenceItem.getBasiceFeature());
        mInferText.setText(mEvidenceItem.getInfer());
        mTime.setText(DateTimePicker.getCurrentTime(mEvidenceItem.getTime()));
        mGetPeopleText.setText(UserInfo.getUserName(mEvidenceItem.getPeople()));
    }

    private void saveData(){
        mEvidenceItem.setEvidenceName(mEvidenceName.getText());
        mEvidenceItem.setLegacySite(mLegacySite.getText());
        mEvidenceItem.setBasiceFeature(mBasiceFeature.getText());

        if(getCategory(mEvidenceItem.getEvidenceCategory())==3){
            mEvidenceItem.setEvidence(mOtherEvidence.getText());
            mEvidenceItem.setMethod(mOtherMethod.getText());
        }
    }

    private int getCategory(String category){
        for(int i=0; i<mEvidence_category.size(); i++){
            if(category.equalsIgnoreCase(mEvidence_category.get(i))) return i;
        }
        return 0;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.time_button:
                showDateTimeDialog(mTime);
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String result = "";
        int category = getCategory(mEvidenceItem.getEvidenceCategory());
        if(requestCode==PHOTO_TYPE_NEW_EVIDENCE) {
            String path = LocalFileUri.getPath();
            if (path != null) {
                mEvidenceItem.setPhotoPath(path);
                setPhoto(path);
            } else {
                finish();
            }
        }else if(requestCode == EVENT_EVIDENCE_SELECT_ITEM){
            if(data!=null) result = (String) data.getStringExtra("Select");
            else result = mEvidenceItem.getEvidence();
            mEvidenceItem.setEvidence(result);
            if(category==0) result = DictionaryInfo.getDictValue(DictionaryInfo.mEvidenceHandKey, result);
            else if(category==1) result = DictionaryInfo.getDictValue(DictionaryInfo.mEvidenceFootKey, result);
            else if(category==2) result = DictionaryInfo.getDictValue(DictionaryInfo.mEvidenceToolKey, result);
            mEvidenceText.setText(result);
            mEvidenceName.setText(result);
        }else if(requestCode == EVENT_INFER_SELECT_ITEM){
            if(data!=null) result = (String) data.getStringExtra("Select");
            else result = mEvidenceItem.getInfer();
            mEvidenceItem.setInfer(result);
            result = DictionaryInfo.getDictValue(DictionaryInfo.mToolInferKey, result);
            mInferText.setText(result);
        }else if(requestCode == EVENT_METHOD_SELECT_ITEM){
            if(data!=null) result = (String) data.getStringExtra("Select");
            else result = mEvidenceItem.getMethod();
            mEvidenceItem.setMethod(result);
            if(category==0) result = DictionaryInfo.getDictValue(DictionaryInfo.mMethodHandKey, result);
            else if(category==1) result = DictionaryInfo.getDictValue(DictionaryInfo.mMethodFootKey, result);
            else if(category==2) result = DictionaryInfo.getDictValue(DictionaryInfo.mMethodToolKey, result);
            mMethodText.setText(result);
        }else if(requestCode == EVENT_GET_PEOPLE_SLELECT_ITEM){
            if(data!=null) result = (String) data.getStringExtra("Select");
            else result = mEvidenceItem.getPeople();
            mEvidenceItem.setPeople(result);
            result = UserInfo.getUserName(result);
            mGetPeopleText.setText(result);
        }
    }

    private void refreshEvidenceItem(int category){
        mEvidenceText.setText("");
        mMethodText.setText("");
        mEvidenceName.setText("");
        mEvidenceItem.setEvidence("");
        mEvidenceItem.setMethod("");

        if(category==0){
            //手印
            mEvidenceTextLabel.setText(getResources().getString(R.string.evidence_hand));
            mBasiceFeatureLabel.setBackground(getResources().getDrawable(R.drawable.green_60dp));
            mMethodLabel.setBackground(getResources().getDrawable(R.drawable.green_60dp));
            mInferLL.setVisibility(View.GONE);
            mEvidenceItem.setInfer("");

            mEvidenceText.setVisibility(View.VISIBLE);
            mMethodText.setVisibility(View.VISIBLE);
            mOtherEvidence.setVisibility(View.GONE);
            mOtherMethod.setVisibility(View.GONE);

            mEvidenceText = (TextView) findViewById(R.id.evidence);
            mEvidenceText.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    Intent it = new Intent(CreateScene_FP5_NewEvidenceActivity.this, TreeViewListDemo.class);
                    it.putExtra("Key",DictionaryInfo.mEvidenceHandKey);
                    it.putExtra("Selected", mEvidenceItem.getEvidence());
                    startActivityForResult(it, EVENT_EVIDENCE_SELECT_ITEM);
                }
            });

            mMethodText = (TextView) findViewById(R.id.method);
            mMethodText.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    Intent it = new Intent(CreateScene_FP5_NewEvidenceActivity.this, TreeViewListDemo.class);
                    it.putExtra("Key",DictionaryInfo.mMethodHandKey);
                    it.putExtra("Selected", mEvidenceItem.getMethod());
                    startActivityForResult(it, EVENT_METHOD_SELECT_ITEM);
                }
            });
        }else if(category==1){
            //足跡
            mEvidenceTextLabel.setText(getResources().getString(R.string.evidence_foot));
            mBasiceFeatureLabel.setBackground(getResources().getDrawable(R.drawable.green_60dp));
            mMethodLabel.setBackground(getResources().getDrawable(R.drawable.green_60dp));
            mInferLL.setVisibility(View.GONE);
            mEvidenceItem.setInfer("");

            mEvidenceText.setVisibility(View.VISIBLE);
            mMethodText.setVisibility(View.VISIBLE);
            mOtherEvidence.setVisibility(View.GONE);
            mOtherMethod.setVisibility(View.GONE);

            mEvidenceText = (TextView) findViewById(R.id.evidence);
            mEvidenceText.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    Intent it = new Intent(CreateScene_FP5_NewEvidenceActivity.this, TreeViewListDemo.class);
                    it.putExtra("Key",DictionaryInfo.mEvidenceFootKey);
                    it.putExtra("Selected", mEvidenceItem.getEvidence());
                    startActivityForResult(it, EVENT_EVIDENCE_SELECT_ITEM);
                }
            });

            mMethodText = (TextView) findViewById(R.id.method);
            mMethodText.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    Intent it = new Intent(CreateScene_FP5_NewEvidenceActivity.this, TreeViewListDemo.class);
                    it.putExtra("Key",DictionaryInfo.mMethodFootKey);
                    it.putExtra("Selected", mEvidenceItem.getMethod());
                    startActivityForResult(it, EVENT_METHOD_SELECT_ITEM);
                }
            });
        }else if(category==2){
            //工痕
            mEvidenceTextLabel.setText(getResources().getString(R.string.evidence_tool));
            mBasiceFeatureLabel.setBackground(getResources().getDrawable(R.drawable.red_60dp));
            mMethodLabel.setBackground(getResources().getDrawable(R.drawable.green_60dp));
            mInferLL.setVisibility(View.VISIBLE);

            mEvidenceText.setVisibility(View.VISIBLE);
            mMethodText.setVisibility(View.VISIBLE);
            mOtherEvidence.setVisibility(View.GONE);
            mOtherMethod.setVisibility(View.GONE);

            mEvidenceText = (TextView) findViewById(R.id.evidence);
            mEvidenceText.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    Intent it = new Intent(CreateScene_FP5_NewEvidenceActivity.this, TreeViewListDemo.class);
                    it.putExtra("Key",DictionaryInfo.mEvidenceToolKey);
                    it.putExtra("Selected", mEvidenceItem.getEvidence());
                    startActivityForResult(it, EVENT_EVIDENCE_SELECT_ITEM);
                }
            });

            mMethodText = (TextView) findViewById(R.id.method);
            mMethodText.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    Intent it = new Intent(CreateScene_FP5_NewEvidenceActivity.this, TreeViewListDemo.class);
                    it.putExtra("Key",DictionaryInfo.mMethodToolKey);
                    it.putExtra("Selected", mEvidenceItem.getMethod());
                    startActivityForResult(it, EVENT_METHOD_SELECT_ITEM);
                }
            });
        }else if(category==3){
            //其他
            mEvidenceTextLabel.setText(getResources().getString(R.string.evidence_other));
            mBasiceFeatureLabel.setBackground(getResources().getDrawable(R.drawable.red_60dp));
            mMethodLabel.setBackground(getResources().getDrawable(R.drawable.red_60dp));
            mInferLL.setVisibility(View.GONE);

            mEvidenceText.setVisibility(View.GONE);
            mMethodText.setVisibility(View.GONE);
            mOtherEvidence.setVisibility(View.VISIBLE);
            mOtherMethod.setVisibility(View.VISIBLE);

            mEvidenceName.setText(getResources().getString(R.string.evidence_name_other));
        }
    }

    private void takePhoto(Uri LocalFileUri, int PHOTO_TYPE) {
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        it.putExtra(MediaStore.EXTRA_OUTPUT, LocalFileUri);
        startActivityForResult(it, PHOTO_TYPE);
    }

    private void setPhoto(String path){
        Bitmap Bitmap = loadBitmapFromFile(new File(path));
        BitmapDrawable bDrawable = new BitmapDrawable(context.getResources(), Bitmap);
        mNew_evidence.setBackground(bDrawable);
        mNew_evidence.setVisibility(View.VISIBLE);
    }

    private File getOutputMediaFile(Context context, int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "Report");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == PHOTO_TYPE_NEW_EVIDENCE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "NEW_EVIDENCE_"+ timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }

    private Bitmap loadBitmapFromFile(File f) {
        Bitmap b = null;
        BitmapFactory.Options option = new BitmapFactory.Options();
        // Bitmap sampling factor, size = (Original Size)/(inSampleSize)
        option.inSampleSize = 4;

        try {
            FileInputStream fis = new FileInputStream(f);
            b = BitmapFactory.decodeStream(fis, null, option);
            fis.close();

            // Rotate Bitmap by 90 degree
            Matrix matrix = new Matrix();
            matrix.setRotate(0, (float)b.getWidth()/2, (float)b.getHeight()/2);
            Bitmap resultImage = Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), matrix, true);

            return resultImage;
        } catch(Exception e) {
            return null;
        }
    }

    private void showDateTimeDialog(final TextView textView) {
        // Create the dialog
        final Dialog mDateTimeDialog = new Dialog(this);
        // Inflate the root layout
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        final RelativeLayout mDateTimeDialogView = (RelativeLayout) inflater.inflate(R.layout.date_time_dialog, null);
        // Grab widget instance
        final DateTimePicker mDateTimePicker = (DateTimePicker) mDateTimeDialogView.findViewById(R.id.DateTimePicker);

        // Update demo TextViews when the "OK" button is clicked
        ((Button) mDateTimeDialogView.findViewById(R.id.SetDateTime)).setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                mDateTimePicker.clearFocus();
                // TODO Auto-generated method stub
                long time = mDateTimePicker.get().getTimeInMillis();
                mEvidenceItem.setTime(time);
                textView.setText(DateTimePicker.getCurrentTime(mDateTimePicker.get().getTimeInMillis()));
                mDateTimeDialog.dismiss();
            }
        });

        // Cancel the dialog when the "Cancel" button is clicked
        ((Button) mDateTimeDialogView.findViewById(R.id.CancelDialog)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mDateTimeDialog.cancel();
            }
        });

        // Reset Date and Time pickers when the "Reset" button is clicked
        ((Button) mDateTimeDialogView.findViewById(R.id.ResetDateTime)).setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                mDateTimePicker.reset();
            }
        });

        // No title on the dialog window
        mDateTimeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Set the dialog content view
        mDateTimeDialog.setContentView(mDateTimeDialogView);
        // Display the dialog
        mDateTimeDialog.show();
    }
}
