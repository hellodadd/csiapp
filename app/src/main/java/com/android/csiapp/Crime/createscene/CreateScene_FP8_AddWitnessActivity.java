package com.android.csiapp.Crime.createscene;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.csiapp.Crime.utils.ClearableEditText;
import com.android.csiapp.Crime.utils.DateTimePicker;
import com.android.csiapp.Databases.WitnessItem;
import com.android.csiapp.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class CreateScene_FP8_AddWitnessActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context = null;
    private WitnessItem mWitnessItem;
    private int mEvent;
    private int mPosition;

    private ClearableEditText mName;

    private Spinner mSex_spinner;
    private ArrayList<String> mSex = new ArrayList<String>();
    private ArrayAdapter<String> mSex_adapter;

    private TextView mBirthday;
    private Button mBirthday_button;

    private ClearableEditText mNumber;
    private ClearableEditText mAddress;

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_click:
                    msg += "Save";
                    saveData();
                    Intent result = getIntent();
                    result.putExtra("com.android.csiapp.Databases.WitnessItem", mWitnessItem);
                    result.putExtra("Event", mEvent);
                    result.putExtra("Posiotion", mPosition);
                    setResult(Activity.RESULT_OK, result);
                    break;
            }

            if(!msg.equals("")) {
                Toast.makeText(CreateScene_FP8_AddWitnessActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            finish();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_scene_fp8_add_witness);

        context = this.getApplicationContext();
        mWitnessItem = (WitnessItem) getIntent().getSerializableExtra("com.android.csiapp.Databases.WitnessItem");
        mEvent = (int) getIntent().getIntExtra("Event", 1);
        mPosition = (int) getIntent().getIntExtra("Position", 0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(context.getResources().getString(R.string.title_activity_witness_peopleinformation));
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
        initData();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_fp8_subactivity, menu);
        return true;
    }

    private void initView(){
        mName = (ClearableEditText) findViewById(R.id.name_editView);

        mSex = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.sex)));
        mSex_spinner = (Spinner) findViewById(R.id.sex_spinner);
        mSex_adapter = new ArrayAdapter<String>(CreateScene_FP8_AddWitnessActivity.this, R.layout.spinnerview, mSex);
        mSex_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSex_spinner.setAdapter(mSex_adapter);
        mSex_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                mWitnessItem.setWitnessSex(mSex.get(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mBirthday = (TextView) findViewById(R.id.birthday_date);
        mBirthday_button = (Button) findViewById(R.id.birthday_date_button);
        mBirthday_button.setOnClickListener(this);

        mNumber = (ClearableEditText) findViewById(R.id.contact_number_editView);
        mAddress = (ClearableEditText) findViewById(R.id.address_editView);
    }

    private void initData(){
        mName.setText(mWitnessItem.getWitnessName());
        mSex_spinner.setSelection(getSex(mWitnessItem.getWitnessSex()));
        mBirthday.setText(DateTimePicker.getCurrentTime(mWitnessItem.getWitnessBirthday()));
        mNumber.setText(mWitnessItem.getWitnessNumber());
        mAddress.setText(mWitnessItem.getWitnessAddress());
    }

    private void saveData(){
        mWitnessItem.setWitnessName(mName.getText());
        mWitnessItem.setWitnessNumber(mNumber.getText());
        mWitnessItem.setWitnessAddress(mAddress.getText());
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.birthday_date_button:
                showDateTimeDialog(mBirthday);
                break;
            default:
                break;
        }
    }

    private int getSex(String sex){
        for(int i=0; i<mSex.size(); i++){
            if(sex.equalsIgnoreCase(mSex.get(i))) return i;
        }
        return 0;
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
                mWitnessItem.setWitnessBirthday(time);
                textView.setText(DateTimePicker.getCurrentTime(time));
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
