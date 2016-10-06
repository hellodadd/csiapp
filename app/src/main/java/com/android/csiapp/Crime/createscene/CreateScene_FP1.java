package com.android.csiapp.Crime.createscene;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.csiapp.Crime.utils.ClearableEditText;
import com.android.csiapp.Crime.utils.DateTimePicker;
import com.android.csiapp.Databases.CrimeItem;
import com.android.csiapp.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateScene_FP1 extends Fragment implements View.OnClickListener {

    private Context context = null;
    private CrimeItem mItem;
    private int mEvent;

    private Spinner mCasetype_spinner;
    private ArrayList<String> mCasetype = new ArrayList<String>();
    private ArrayAdapter<String> mCasetype_adapter;
    private Spinner mArea_spinner;
    private ArrayList<String> mArea = new ArrayList<String>();
    private ArrayAdapter<String> mArea_adapter;

    private ClearableEditText mLocation;

    private TextView mOccurred_start_time;
    private TextView mOccurred_end_time;
    private TextView mGet_access_time;
    private Button mOccurred_start_button;
    private Button mOccurred_end_button;
    private Button mGet_access_button;

    private ClearableEditText mUnitsAssigned;
    private ClearableEditText mAccessPolicemen;

    private TextView mAccess_start_time;
    private TextView mAccess_end_time;
    private Button mAccess_start_button;
    private Button mAccess_end_button;

    private ClearableEditText mAccessLocation;
    private ClearableEditText mCaseOccurProcess;
    private Button mCaseOccurProcessBtn;

    private Spinner mSceneCondition_spinner;
    private ArrayList<String> mSceneCondition = new ArrayList<String>();;
    private ArrayAdapter<String> mSceneCondition_adapter;

    private Spinner mWeatherCondition_spinner;
    private ArrayList<String> mWeatherCondition = new ArrayList<String>();;
    private ArrayAdapter<String> mWeatherCondition_adapter;

    private Spinner mWindDirection_spinner;
    private ArrayList<String> mWindDirection = new ArrayList<String>();;
    private ArrayAdapter<String> mWindDirection_adapter;

    private ClearableEditText mTemperature;
    private ClearableEditText mHumidity;
    private ClearableEditText mAccessReason;
    private Button mAccessReasonBtn;

    public CreateScene_FP1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_scene_fp1, container, false);
        CreateSceneActivity activity  = (CreateSceneActivity) getActivity();
        mItem = activity.getItem();
        mEvent = activity.getEvent();
        context = getActivity().getApplicationContext();

        initView(view);
        initData();

        return view;
    }

    private void initView(View view){
        mCasetype = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.casetype)));
        mCasetype_spinner = (Spinner) view.findViewById(R.id.casetype_spinner);
        mCasetype_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, mCasetype);
        mCasetype_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCasetype_spinner.setAdapter(mCasetype_adapter);
        mCasetype_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                mItem.setCasetype(mCasetype.get(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mArea = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.area)));
        mArea_spinner = (Spinner) view.findViewById(R.id.area_spinner);
        mArea_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, mArea);
        mArea_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mArea_spinner.setAdapter(mArea_adapter);
        mArea_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                mItem.setArea(mArea.get(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mLocation = (ClearableEditText) view.findViewById(R.id.location);

        mOccurred_start_time = (TextView) view.findViewById(R.id.occurred_start_time);
        mOccurred_end_time = (TextView) view.findViewById(R.id.occurred_end_time);
        mGet_access_time = (TextView) view.findViewById(R.id.get_access_time);
        mOccurred_start_button = (Button) view.findViewById(R.id.occurred_start_time_button);
        mOccurred_end_button = (Button) view.findViewById(R.id.occurred_end_time_button);
        mGet_access_button = (Button) view.findViewById(R.id.get_access_time_button);
        mOccurred_start_button.setOnClickListener(this);
        mOccurred_end_button.setOnClickListener(this);
        mGet_access_button.setOnClickListener(this);

        mUnitsAssigned = (ClearableEditText) view.findViewById(R.id.unitsAssigned);
        mAccessPolicemen= (ClearableEditText) view.findViewById(R.id.accessPolicemen);

        mAccess_start_time = (TextView) view.findViewById(R.id.access_start_time);
        mAccess_end_time = (TextView) view.findViewById(R.id.access_end_time);
        mAccess_start_button = (Button) view.findViewById(R.id.access_start_time_button);
        mAccess_end_button= (Button) view.findViewById(R.id.access_end_time_button);
        mAccess_start_button.setOnClickListener(this);
        mAccess_end_button.setOnClickListener(this);

        mAccessLocation = (ClearableEditText) view.findViewById(R.id.accessLocation);
        mCaseOccurProcess = (ClearableEditText) view.findViewById(R.id.caseOccurProcess);
        mCaseOccurProcessBtn = (Button) view.findViewById(R.id.caseOccurProcess_button);
        mCaseOccurProcessBtn.setOnClickListener(this);

        mSceneCondition = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.sceneCondition)));
        mSceneCondition_spinner = (Spinner) view.findViewById(R.id.sceneCondition_spinner);
        mSceneCondition_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, mSceneCondition);
        mSceneCondition_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSceneCondition_spinner.setAdapter(mSceneCondition_adapter);
        mSceneCondition_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                mItem.setSceneCondition(mSceneCondition.get(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mWeatherCondition = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.weatherCondition)));
        mWeatherCondition_spinner = (Spinner) view.findViewById(R.id.weatherCondition_spinner);
        mWeatherCondition_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, mWeatherCondition);
        mWeatherCondition_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mWeatherCondition_spinner.setAdapter(mWeatherCondition_adapter);
        mWeatherCondition_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                mItem.setWeatherCondition(mWeatherCondition.get(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mWindDirection = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.windDirection)));
        mWindDirection_spinner = (Spinner) view.findViewById(R.id.windDirection_spinner);
        mWindDirection_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, mWindDirection);
        mWindDirection_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mWindDirection_spinner.setAdapter(mWindDirection_adapter);
        mWindDirection_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                mItem.setWindDirection(mWindDirection.get(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mTemperature = (ClearableEditText) view.findViewById(R.id.temperature);
        mHumidity = (ClearableEditText) view.findViewById(R.id.humidity);
        mAccessReason = (ClearableEditText) view.findViewById(R.id.accessReason);
        mAccessReasonBtn = (Button) view.findViewById(R.id.accessReason_button);
        mAccessReasonBtn.setOnClickListener(this);
    }

    private void initData(){
        mCasetype_spinner.setSelection(getCategory(mItem.getCasetype()));
        mArea_spinner.setSelection(getArea(mItem.getArea()));
        mLocation.setText(mItem.getLocation());
        mUnitsAssigned.setText(mItem.getUnitsAssigned());
        mAccessPolicemen.setText(mItem.getAccessPolicemen());
        mAccessLocation.setText(mItem.getAccessLocation());
        mCaseOccurProcess.setText(mItem.getCaseOccurProcess());
        mSceneCondition_spinner.setSelection(getSceneCondition(mItem.getSceneCondition()));
        mWeatherCondition_spinner.setSelection(getWeatherCondition(mItem.getWeatherCondition()));
        mWindDirection_spinner.setSelection(getWindDirection(mItem.getWindDirection()));
        mTemperature.setText(mItem.getTemperature());
        mHumidity.setText(mItem.getHumidity());
        mAccessReason.setText(mItem.getAccessReason());

        mOccurred_start_time.setText(DateTimePicker.getCurrentTime(mItem.getOccurredStartTime()));
        mOccurred_end_time.setText(DateTimePicker.getCurrentTime(mItem.getOccurredEndTime()));
        mGet_access_time.setText(DateTimePicker.getCurrentTime(mItem.getGetAccessTime()));
        mAccess_start_time.setText(DateTimePicker.getCurrentTime(mItem.getAccessStartTime()));
        mAccess_end_time.setText(DateTimePicker.getCurrentTime(mItem.getAccessEndTime()));
    }

    private int getCategory(String category){
        for(int i=0; i<mCasetype.size(); i++){
            if(category.equalsIgnoreCase(mCasetype.get(i))) return i;
        }
        return 0;
    }
    private int getArea(String category){
        for(int i=0; i<mArea.size(); i++){
            if(category.equalsIgnoreCase(mArea.get(i))) return i;
        }
        return 0;
    }
    private int getSceneCondition(String category){
        for(int i=0; i<mSceneCondition.size(); i++){
            if(category.equalsIgnoreCase(mSceneCondition.get(i))) return i;
        }
        return 0;
    }
    private int getWeatherCondition(String category){
        for(int i=0; i<mWeatherCondition.size(); i++){
            if(category.equalsIgnoreCase(mWeatherCondition.get(i))) return i;
        }
        return 0;
    }
    private int getWindDirection(String category){
        for(int i=0; i<mWindDirection.size(); i++){
            if(category.equalsIgnoreCase(mWindDirection.get(i))) return i;
        }
        return 0;
    }

    public void saveData(){
        mItem.setLocationa(mLocation.getText());
        mItem.setUnitsAssigned(mUnitsAssigned.getText());
        mItem.setAccessPolicemen(mAccessPolicemen.getText());
        mItem.setAccessLocation(mAccessLocation.getText());
        mItem.setCaseOccurProcess(mCaseOccurProcess.getText());
        mItem.setTemperature(mTemperature.getText());
        mItem.setHumidity(mHumidity.getText());
        mItem.setAccessReason(mAccessReason.getText());
    }

    @Override
    public void onResume(){
        super.onResume();
        initData();
    }

    @Override
    public void onPause(){
        super.onPause();
        saveData();
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.occurred_start_time_button:
                showDateTimeDialog(mOccurred_start_time,1);
                break;
            case R.id.occurred_end_time_button:
                showDateTimeDialog(mOccurred_end_time,2);
                break;
            case R.id.get_access_time_button:
                showDateTimeDialog(mGet_access_time,3);
                break;
            case R.id.access_start_time_button:
                showDateTimeDialog(mAccess_start_time,4);
                break;
            case R.id.access_end_time_button:
                showDateTimeDialog(mAccess_end_time,5);
                break;
            case R.id.caseOccurProcess_button:
                mCaseOccurProcess.setText("据<被害人/报案人>报称:<发案开始时间> 在<发案地点>，该处发现一起<案件类别>，后拨打电话报警");
                break;
            case R.id.accessReason_button:
                mAccessReason.setText("<发案区划><接警人>接到<指派单位>的指派: 在该所管界内<发案地点>发生一起<案件类别>，请速派人员勘查现场");
                break;
            default:
                break;
        }
    }

    public void showDateTimeDialog(final TextView textView,final int type) {
        // Create the dialog
        final Dialog mDateTimeDialog = new Dialog(getContext());
        // Inflate the root layout
        final RelativeLayout mDateTimeDialogView = (RelativeLayout) getActivity().getLayoutInflater().inflate(R.layout.date_time_dialog, null);
        // Grab widget instance
        final DateTimePicker mDateTimePicker = (DateTimePicker) mDateTimeDialogView.findViewById(R.id.DateTimePicker);

        // Update demo TextViews when the "OK" button is clicked
        ((Button) mDateTimeDialogView.findViewById(R.id.SetDateTime)).setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                mDateTimePicker.clearFocus();
                // TODO Auto-generated method stub
                long time = mDateTimePicker.get().getTimeInMillis();
                if(type == 1) mItem.setOccurredStartTime(time);
                else if(type == 2) mItem.setOccurredEndTime(time);
                else if(type == 3) mItem.setGetAccessTime(time);
                else if(type == 4) mItem.setAccessStartTime(time);
                else if(type == 5) mItem.setAccessEndTime(time);
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
