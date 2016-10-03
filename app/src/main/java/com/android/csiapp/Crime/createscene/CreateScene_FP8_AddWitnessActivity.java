package com.android.csiapp.Crime.createscene;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
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

import com.android.csiapp.ClearableEditText;
import com.android.csiapp.Databases.CrimeItem;
import com.android.csiapp.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class CreateScene_FP8_AddWitnessActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context = null;
    private CrimeItem mItem;
    private int mEvent;

    private ClearableEditText mName;

    private Spinner mSex_spinner;
    private ArrayList<String> mSex = new ArrayList<String>();
    private ArrayAdapter<String> mSex_adapter;

    private Calendar c;
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
                    saveMessage();
                    Intent result = getIntent();
                    result.putExtra("com.android.csiapp.CrimeItem", mItem);
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
        mItem = (CrimeItem) getIntent().getSerializableExtra("com.android.csiapp.CrimeItem");
        mEvent = (int) getIntent().getIntExtra("Event", 1);

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

        mName = (ClearableEditText) findViewById(R.id.name_editView);

        mSex = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.sex)));
        mSex_spinner = (Spinner) findViewById(R.id.sex_spinner);
        mSex_adapter = new ArrayAdapter<String>(CreateScene_FP8_AddWitnessActivity.this, R.layout.spinnerview, mSex);
        mSex_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSex_spinner.setAdapter(mSex_adapter);
        mSex_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                mItem.setWitnessSex(mSex.get(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        c = Calendar.getInstance();
        mBirthday = (TextView) findViewById(R.id.birthday_date);
        mBirthday_button = (Button) findViewById(R.id.birthday_date_button);
        mBirthday.setText(CrimeItem.getCurrentTime(c));
        mBirthday_button.setOnClickListener(this);

        mNumber = (ClearableEditText) findViewById(R.id.contact_number_editView);
        mAddress = (ClearableEditText) findViewById(R.id.address_editView);

        if(mEvent == 2) {
            getMessage();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_fp8_subactivity, menu);
        return true;
    }

    private int getSex(String sex){
        for(int i=0; i<mSex.size(); i++){
            if(sex.equalsIgnoreCase(mSex.get(i))) return i;
        }
        return 0;
    }

    public void getMessage(){
        mName.setText(mItem.getWitnessName());
        mSex_spinner.setSelection(getSex(mItem.getWitnessSex()));
        mBirthday.setText(mItem.getWitnessBirthday());
        mNumber.setText(mItem.getWitnessNumber());
        mAddress.setText(mItem.getWitnessAddress());
    }

    public void saveMessage(){
        mItem.setWitnessName(mName.getText());
        mItem.setWitnessBirthday(mBirthday.getText().toString());
        mItem.setWitnessNumber(mNumber.getText());
        mItem.setWitnessAddress(mAddress.getText());
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

    public void showDateTimeDialog(final TextView textView) {
        // Create the dialog
        final Dialog mDateTimeDialog = new Dialog(this.getBaseContext());
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
}
