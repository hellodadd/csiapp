package com.android.csiapp.Crime.createscene;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.csiapp.Crime.utils.DateTimePicker;
import com.android.csiapp.Crime.utils.DictionaryInfo;
import com.android.csiapp.Crime.utils.Priview_photo_Activity;
import com.android.csiapp.Crime.utils.ScreenShot;
import com.android.csiapp.Databases.CrimeItem;
import com.android.csiapp.Databases.CrimeProvider;
import com.android.csiapp.Databases.PhotoItem;
import com.android.csiapp.R;

import java.util.Calendar;

public class CreateScene_FP3_PositionInformationActivity extends AppCompatActivity {

    private Context context = null;
    private CrimeItem mItem;
    private PhotoItem mPositionItem;
    private int mEvent;
    private String mAdd;
    private ImageView mNew_Position;
    private TableLayout mTablePosition1, mTablePosition2, mTableFlat;
    private TextView mIncidentTime, mIncidentLocation, mCreateUnit, mCreatePeople, mCreateTime;
    private String mFilepath;

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_camera:
                    msg += "Camera";
                    if(mAdd.equalsIgnoreCase("Position")) {
                        Intent it = new Intent(CreateScene_FP3_PositionInformationActivity.this, CreateScene_FP3_NewPositionActivity_Amap.class);
                        startActivityForResult(it, 0);
                    }else if(mAdd.equalsIgnoreCase("Flat")){
                        Intent it = new Intent(CreateScene_FP3_PositionInformationActivity.this, CreateScene_FP3_NewFlatActivity.class);
                        startActivityForResult(it, 0);
                    }
                    break;
                case R.id.action_click:
                    msg += "Save";
                    String path = ScreenShot.shoot(CreateScene_FP3_PositionInformationActivity.this);
                    mPositionItem.setPhotoPath(path);
                    mPositionItem.setUuid(CrimeProvider.getUUID());
                    Intent result = getIntent();
                    result.putExtra("com.android.csiapp.Databases.PhotoItem", mPositionItem);
                    result.putExtra("Event",mEvent);
                    setResult(Activity.RESULT_OK, result);
                    finish();
                    break;
                default:
                    break;
            }

            if(!msg.equals("")) {
                //Toast.makeText(CreateScene_FP3_PositionInformationActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_scene_fp3_position_information);

        context = this.getApplicationContext();
        mItem = (CrimeItem) getIntent().getSerializableExtra("com.android.csiapp.Databases.CrimeItem");
        mEvent = (int) getIntent().getIntExtra("Event", 1);
        mAdd = (String) getIntent().getStringExtra("Add");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        String title = (IsPositionInformation())
                ?context.getResources().getString(R.string.title_activity_position_information)
                :context.getResources().getString(R.string.title_activity_flat_information);
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(context.getResources().getColor(R.color.titleBar));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.btn_back_mini);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                finish();
            }
        });
        toolbar.setOnMenuItemClickListener(onMenuItemClick);

        initView();

        if(mEvent == 1) {
            mPositionItem = new PhotoItem();
            if(IsPositionInformation()) {
                Intent it = new Intent(CreateScene_FP3_PositionInformationActivity.this, CreateScene_FP3_NewPositionActivity_Amap.class);
                startActivityForResult(it, 0);
            }else if(!IsPositionInformation()){
                Intent it = new Intent(CreateScene_FP3_PositionInformationActivity.this, CreateScene_FP3_NewFlatActivity.class);
                startActivityForResult(it, 0);
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_fp3_position_information, menu);
        return true;
    }

    private void initView(){
        mNew_Position = (ImageView) findViewById(R.id.new_position);
        mNew_Position.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent it = new Intent(CreateScene_FP3_PositionInformationActivity.this, Priview_photo_Activity.class);
                it.putExtra("Path",mPositionItem.getPhotoPath());
                startActivityForResult(it, 100);
            }
        });

        mTablePosition1 = (TableLayout) findViewById(R.id.table_position1);
        mTablePosition2 = (TableLayout) findViewById(R.id.table_position2);
        mTableFlat = (TableLayout) findViewById(R.id.table_flat);

        if(IsPositionInformation()) {
            mTablePosition1.setVisibility(View.VISIBLE);
            mTablePosition2.setVisibility(View.VISIBLE);
        }else if(!IsPositionInformation()) {
            mTableFlat.setVisibility(View.VISIBLE);
        }

        mIncidentTime = (TextView) findViewById(R.id.incident_time);
        mIncidentLocation = (TextView) findViewById(R.id.incident_location);
        mCreateUnit = (TextView) findViewById(R.id.create_unit);
        mCreatePeople = (TextView) findViewById(R.id.create_people);
        mCreateTime = (TextView) findViewById(R.id.create_time);
        getInformation();
    }

    private void setPhoto(String path){
        Bitmap Bitmap = BitmapFactory.decodeFile(path);
        mNew_Position.setImageBitmap(Bitmap);
        mNew_Position.setVisibility(View.VISIBLE);
    }

    private void getInformation(){
        long time = mItem.getOccurredStartTime();
        mIncidentTime.setText(DateTimePicker.getCurrentDate(time));
        mIncidentLocation.setText(mItem.getLocation());
        mCreateUnit.setText(DictionaryInfo.getDictValue(DictionaryInfo.mAreaKey,mItem.getArea()));
        mCreatePeople.setText(mItem.getAccessPolicemen());
        mCreateTime.setText(DateTimePicker.getCurrentDate(Calendar.getInstance().getTimeInMillis()));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == 0) {
            mPositionItem.setPhotoPath(data.getStringExtra("Map_ScreenShot"));
            setPhoto(mPositionItem.getPhotoPath());
        }
    }

    private boolean IsPositionInformation(){
        return mAdd.equalsIgnoreCase("Position");
    }
}
