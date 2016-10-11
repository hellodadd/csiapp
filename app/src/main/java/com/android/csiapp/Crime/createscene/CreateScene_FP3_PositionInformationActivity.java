package com.android.csiapp.Crime.createscene;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.csiapp.Crime.utils.DateTimePicker;
import com.android.csiapp.Databases.CrimeItem;
import com.android.csiapp.Databases.PhotoItem;
import com.android.csiapp.R;

import java.util.Calendar;

public class CreateScene_FP3_PositionInformationActivity extends AppCompatActivity {

    private Context context = null;
    private CrimeItem mItem;
    private PhotoItem mPositionItem;
    private int mEvent;
    private int mPosition;
    private ImageView mNew_Position;
    private TextView mInformationText;
    private String mFilepath;

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_camera:
                    msg += "Camera";
                    Intent it = new Intent(CreateScene_FP3_PositionInformationActivity.this, CreateScene_FP3_NewPositionActivity.class);
                    startActivityForResult(it, 0);
                    break;
                case R.id.action_click:
                    msg += "Save";
                    Intent result = getIntent();
                    result.putExtra("com.android.csiapp.Databases.PositionItem", mPositionItem);
                    result.putExtra("Event",mEvent);
                    result.putExtra("Position",mPosition);
                    setResult(Activity.RESULT_OK, result);
                    finish();
                    break;
            }

            if(!msg.equals("")) {
                Toast.makeText(CreateScene_FP3_PositionInformationActivity.this, msg, Toast.LENGTH_SHORT).show();
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
        mPosition = (int) getIntent().getIntExtra("Position", 0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(context.getResources().getString(R.string.title_activity_position_information));
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

        initView();

        if(mEvent == 1) {
            mPositionItem = new PhotoItem();
            Intent it = new Intent(CreateScene_FP3_PositionInformationActivity.this, CreateScene_FP3_NewPositionActivity.class);
            startActivityForResult(it, 0);
        }else{
            mPositionItem = mItem.getPosition().get(mPosition);
            setPhoto(mPositionItem.getPhotoPath());
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_fp3_position_information, menu);
        return true;
    }

    private void initView(){
        mNew_Position = (ImageView) findViewById(R.id.new_position);
        mInformationText = (TextView) findViewById(R.id.information);
        mInformationText.setText(getInformation());
    }

    private void setPhoto(String path){
        Bitmap Bitmap = BitmapFactory.decodeFile(path);
        mNew_Position.setImageBitmap(Bitmap);
        mNew_Position.setVisibility(View.VISIBLE);
    }

    private String getInformation(){
        StringBuffer text=new StringBuffer();
        text.append("发案时间 : ");
        long time = mItem.getOccurredStartTime();
        text.append(DateTimePicker.getCurrentDate(time));
        text.append("\n");
        text.append("发案地点 : ");
        text.append(mItem.getLocation());
        text.append("\n");
        text.append("制图单位 : ");
        text.append(mItem.getArea());
        text.append("\n");
        text.append("制图人     : ");
        text.append(mItem.getAccessPolicemen());
        text.append("\n");
        text.append("制图时间 : ");
        text.append(DateTimePicker.getCurrentDate(Calendar.getInstance().getTimeInMillis()));
        text.append("\n");
        return text.toString();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == 0) {
            mPositionItem.setPhotoPath(data.getStringExtra("BaiduMap_ScreenShot"));
            setPhoto(mPositionItem.getPhotoPath());
        }
    }
}
