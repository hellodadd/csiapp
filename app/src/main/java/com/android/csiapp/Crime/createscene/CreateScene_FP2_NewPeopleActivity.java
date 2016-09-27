package com.android.csiapp.Crime.createscene;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.csiapp.ClearableEditText;
import com.android.csiapp.Databases.CrimeItem;
import com.android.csiapp.R;

import java.util.ArrayList;
import java.util.Arrays;

public class CreateScene_FP2_NewPeopleActivity extends AppCompatActivity {

    private Context context = null;
    private CrimeItem item;
    private int event;

    private Spinner mReleationPeople_spinner;
    private ArrayList<String> mReleationPeople = new ArrayList<String>();
    private ArrayAdapter<String> mReleationPeople_adapter;

    private ClearableEditText mName;

    private Spinner mSex_spinner;
    private ArrayList<String> mSex = new ArrayList<String>();
    private ArrayAdapter<String> mSex_adapter;

    private ClearableEditText mId;
    private ClearableEditText mNumber;
    private ClearableEditText mAddress;

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_click:
                    msg += "Save";
                    saveMessage();
                    Intent result = getIntent();
                    result.putExtra("com.android.csiapp.CrimeItem", item);
                    setResult(Activity.RESULT_OK, result);
                    break;
            }

            if(!msg.equals("")) {
                Toast.makeText(CreateScene_FP2_NewPeopleActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            finish();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_scene_fp2_new_people);

        context = this.getApplicationContext();
        item = (CrimeItem) getIntent().getSerializableExtra("com.android.csiapp.CrimeItem");
        event = (int) getIntent().getIntExtra("Event", 1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(context.getResources().getString(R.string.title_activity_peopleinformation));
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

        mReleationPeople = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.releation_people)));
        mReleationPeople_spinner = (Spinner) findViewById(R.id.releationPeople_spinner);
        mReleationPeople_adapter = new ArrayAdapter<String>(CreateScene_FP2_NewPeopleActivity.this, R.layout.spinnerview, mReleationPeople);
        mReleationPeople_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mReleationPeople_spinner.setAdapter(mReleationPeople_adapter);
        mReleationPeople_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                item.setPeopleReleation(mReleationPeople.get(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mName = (ClearableEditText) findViewById(R.id.name_editView);

        mSex = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.sex)));
        mSex_spinner = (Spinner) findViewById(R.id.sex_spinner);
        mSex_adapter = new ArrayAdapter<String>(CreateScene_FP2_NewPeopleActivity.this, R.layout.spinnerview, mSex);
        mSex_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSex_spinner.setAdapter(mSex_adapter);
        mSex_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                item.setPeopleSex(mSex.get(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mId = (ClearableEditText) findViewById(R.id.identity_number_editView);
        mNumber = (ClearableEditText) findViewById(R.id.contact_number_editView);
        mAddress = (ClearableEditText) findViewById(R.id.address_editView);

        if(event == 2) {
            getMessage();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_fp2_subactivity, menu);
        return true;
    }

    private int getPeople(String category){
        for(int i=0; i<mReleationPeople.size(); i++){
            if(category.equalsIgnoreCase(mReleationPeople.get(i))) return i;
        }
        return 0;
    }
    private int getSex(String sex){
        for(int i=0; i<mSex.size(); i++){
            if(sex.equalsIgnoreCase(mSex.get(i))) return i;
        }
        return 0;
    }

    public void getMessage(){
        mReleationPeople_spinner.setSelection(getPeople(item.getPeopleReleation()));
        mName.setText(item.getPeopleName());
        mSex_spinner.setSelection(getSex(item.getPeopleSex()));
        mId.setText(item.getPeopleId());
        mNumber.setText(item.getPeopleNumber());
        mAddress.setText(item.getPeopleAddress());
    }

    public void saveMessage(){
        item.setPeopleName(mName.getText());
        item.setPeopleId(mId.getText());
        item.setPeopleNumber(mNumber.getText());
        item.setPeopleAddress(mAddress.getText());
    }
}
