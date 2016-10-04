package com.android.csiapp.Crime.createscene;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.csiapp.Crime.utils.DateTimePicker;
import com.android.csiapp.Databases.CrimeItem;
import com.android.csiapp.R;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreateScene_FP5_NewEvidenceActivity extends AppCompatActivity implements View.OnClickListener {
    private Context context = null;
    private Uri LocalFileUri = null;
    private ImageView mNew_evidence;
    private Spinner mEvidence_category_spinner;
    private ArrayList<String> mEvidence_category = new ArrayList<String>();
    private ArrayAdapter<String> mEvidence_category_adapter;
    private Spinner mEvidence_spinner;
    private ArrayList<String> mEvidence = new ArrayList<String>();
    private ArrayAdapter<String> mEvidence_adapter;
    private Spinner mMethod_spinner;
    private ArrayList<String> mMethod = new ArrayList<String>();
    private ArrayAdapter<String> mMethod_adapter;
    private Spinner mGetPeople_spinner;
    private ArrayList<String> mGetPeople = new ArrayList<String>();
    private ArrayAdapter<String> mGetPeople_adapter;

    private Calendar c;
    private TextView mTime;
    private Button mTime_button;

    public static final int PHOTO_TYPE_NEW_EVIDENCE = 1;

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
                    Intent result = getIntent().putExtra("photo_uri", LocalFileUri.getPath());
                    setResult(Activity.RESULT_OK, result);
                    finish();
                    break;
            }

            if (!msg.equals("")) {
                Toast.makeText(CreateScene_FP5_NewEvidenceActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            //finish();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_scene_fp5_new_evidence);

        context = this.getApplicationContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(context.getResources().getString(R.string.title_activity_new_evidence));
        toolbar.setTitleTextColor(context.getResources().getColor(R.color.titleBar));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.btn_back_mini);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //What to do on back clicked
            }
        });
        toolbar.setOnMenuItemClickListener(onMenuItemClick);

        mNew_evidence = (ImageView) findViewById(R.id.new_evidence);

        mEvidence_category = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.evidence_category)));
        mEvidence_category_spinner = (Spinner) findViewById(R.id.evidence_category_spinner);
        mEvidence_category_adapter = new ArrayAdapter<String>(CreateScene_FP5_NewEvidenceActivity.this, R.layout.spinnerview, mEvidence_category);
        mEvidence_category_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mEvidence_category_spinner.setAdapter(mEvidence_category_adapter);
        mEvidence_category_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                //item.setCasetype(tool_category.get(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mEvidence = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.evidence)));
        mEvidence_spinner = (Spinner) findViewById(R.id.evidence_spinner);
        mEvidence_adapter = new ArrayAdapter<String>(CreateScene_FP5_NewEvidenceActivity.this, R.layout.spinnerview, mEvidence);
        mEvidence_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mEvidence_spinner.setAdapter(mEvidence_adapter);
        mEvidence_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                //item.setCasetype(tool_category.get(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mMethod = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.method)));
        mMethod_spinner = (Spinner) findViewById(R.id.method_spinner);
        mMethod_adapter = new ArrayAdapter<String>(CreateScene_FP5_NewEvidenceActivity.this, R.layout.spinnerview, mMethod);
        mMethod_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mMethod_spinner.setAdapter(mMethod_adapter);
        mMethod_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                //item.setCasetype(tool_category.get(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        c = Calendar.getInstance();
        mTime = (TextView) findViewById(R.id.time);
        mTime_button = (Button) findViewById(R.id.time_button);
        mTime.setText(CrimeItem.getCurrentTime(c));
        mTime_button.setOnClickListener(this);

        mGetPeople = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.get_people)));
        mGetPeople_spinner = (Spinner) findViewById(R.id.getPeople_spinner);
        mGetPeople_adapter = new ArrayAdapter<String>(CreateScene_FP5_NewEvidenceActivity.this, R.layout.spinnerview, mGetPeople);
        mGetPeople_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mGetPeople_spinner.setAdapter(mGetPeople_adapter);
        mGetPeople_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                //item.setCasetype(tool_category.get(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_fp5_subactivity, menu);
        return true;
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

    public void showDateTimeDialog(final TextView textView) {
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
                c = mDateTimePicker.get();
                textView.setText(CrimeItem.getCurrentTime(c));
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

    public void takePhoto(Uri LocalFileUri, int PHOTO_TYPE) {
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        it.putExtra(MediaStore.EXTRA_OUTPUT, LocalFileUri);
        startActivityForResult(it, PHOTO_TYPE);
    }

    public static File getOutputMediaFile(Context context, int type){
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

    public static Bitmap loadBitmapFromFile(File f) {
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String path = LocalFileUri.getPath();
        if (path != null) {
            Log.d("Camera", "Set image to PHOTO_TYPE_NEW_EVIDENCE");
            Bitmap Bitmap = loadBitmapFromFile(new File(path));
            BitmapDrawable bDrawable = new BitmapDrawable(context.getResources(), Bitmap);
            mNew_evidence.setBackground(bDrawable);
            mNew_evidence.setVisibility(View.VISIBLE);
        }
    }
}
