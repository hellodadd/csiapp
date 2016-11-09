package com.android.csiapp.Crime.createscene;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.method.DigitsKeyListener;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.csiapp.Crime.utils.ClearableEditText;
import com.android.csiapp.Crime.utils.DateTimePicker;
import com.android.csiapp.Crime.utils.DictionaryInfo;
import com.android.csiapp.Databases.CrimeItem;
import com.android.csiapp.Databases.CrimeProvider;
import com.android.csiapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private LinearLayout mChangeReasonLinearLayout;
    private ClearableEditText mChangeReason;
    private CheckBox mInformantCkBx, mVictimCkBx, mOtherCkBx;

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

    private Spinner mIlluminationCondition_spinner;
    private ArrayList<String> mIlluminationCondition = new ArrayList<String>();;
    private ArrayAdapter<String> mIlluminationCondition_adapter;

    private ClearableEditText mProductPeopleName;
    private ClearableEditText mProductPeopleUnit;
    private ClearableEditText mProductPeopleDuties;
    private ClearableEditText mSafeguard;

    private Spinner mSceneConductor_spinner;
    private ArrayList<String> mSceneConductor = new ArrayList<String>();;
    private ArrayAdapter<String> mSceneConductor_adapter;
    private Spinner mAccessInspectors_spinner;
    private ArrayList<String> mAccessInspectors = new ArrayList<String>();;
    private ArrayAdapter<String> mAccessInspectors_adapter;

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
        getLastData();

        return view;
    }

    private void initView(View view){
        DictionaryInfo info = new DictionaryInfo(context);

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

        mArea = info.getArray(info.mAreaKey);
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

        mChangeReasonLinearLayout = (LinearLayout) view.findViewById(R.id.change_reason_linear);
        mSceneCondition = info.getArray(info.mSceneConditionKey);
        mSceneCondition_spinner = (Spinner) view.findViewById(R.id.sceneCondition_spinner);
        mSceneCondition_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, mSceneCondition);
        mSceneCondition_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSceneCondition_spinner.setAdapter(mSceneCondition_adapter);
        mSceneCondition_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                mItem.setSceneCondition(mSceneCondition.get(position));
                if(position==0){
                    mChangeReasonLinearLayout.setVisibility(View.GONE);
                }else{
                    mChangeReasonLinearLayout.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        mChangeReason = (ClearableEditText) view.findViewById(R.id.change_reason);
        mInformantCkBx = (CheckBox) view.findViewById(R.id.InformantCkBx);
        mInformantCkBx.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mChangeReason.setText("报案人进入");
                mVictimCkBx.setChecked(false);
                mOtherCkBx.setChecked(false);
            }
        });
        mVictimCkBx = (CheckBox) view.findViewById(R.id.VictimCkBx);
        mVictimCkBx.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mChangeReason.setText("事主进入");
                mInformantCkBx.setChecked(false);
                mOtherCkBx.setChecked(false);
            }
        });
        mOtherCkBx = (CheckBox) view.findViewById(R.id.OtherCkBx);
        mOtherCkBx.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mChangeReason.setText("其他");
                mInformantCkBx.setChecked(false);
                mVictimCkBx.setChecked(false);
            }
        });

        mWeatherCondition = info.getArray(info.mWeatherConditionKey);
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

        mWindDirection = info.getArray(info.mWindDirectionKey);
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
        mTemperature.setKeyListener(DigitsKeyListener.getInstance("0123456789abcdefghigklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"));
        mHumidity = (ClearableEditText) view.findViewById(R.id.humidity);
        mHumidity.setKeyListener(DigitsKeyListener.getInstance("0123456789abcdefghigklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"));
        mAccessReason = (ClearableEditText) view.findViewById(R.id.accessReason);
        mAccessReasonBtn = (Button) view.findViewById(R.id.accessReason_button);
        mAccessReasonBtn.setOnClickListener(this);

        mIlluminationCondition = info.getArray(info.mIlluminationConditionKey);
        mIlluminationCondition_spinner = (Spinner) view.findViewById(R.id.illuminationCondition_spinner);
        mIlluminationCondition_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, mIlluminationCondition);
        mIlluminationCondition_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mIlluminationCondition_spinner.setAdapter(mIlluminationCondition_adapter);
        mIlluminationCondition_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                mItem.setIlluminationCondition(mIlluminationCondition.get(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mProductPeopleName = (ClearableEditText) view.findViewById(R.id.productPeople_name);
        mProductPeopleUnit = (ClearableEditText) view.findViewById(R.id.productPeople_unit);
        mProductPeopleDuties = (ClearableEditText) view.findViewById(R.id.productPeople_duties);
        mSafeguard = (ClearableEditText) view.findViewById(R.id.safeguard);

        mSceneConductor = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.sceneConductor)));
        mSceneConductor_spinner = (Spinner) view.findViewById(R.id.sceneConductor_spinner);
        mSceneConductor_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, mSceneConductor);
        mSceneConductor_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSceneConductor_spinner.setAdapter(mSceneConductor_adapter);
        mSceneConductor_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                mItem.setSceneConductor(mSceneConductor.get(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mAccessInspectors = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.accessInspectors)));
        mAccessInspectors_spinner = (Spinner) view.findViewById(R.id.accessInspectors_spinner);
        mAccessInspectors_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, mAccessInspectors);
        mAccessInspectors_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mAccessInspectors_spinner.setAdapter(mAccessInspectors_adapter);
        mAccessInspectors_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                mItem.setAccessInspectors(mAccessInspectors.get(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mLocation.addTextChangedListener(new TextWatcher()
        {

            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                mAccessLocation.setText(mLocation.getText());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            public void afterTextChanged(Editable s)
            {

            }
        });
    }

    private void getLastData(){
        if(mEvent == 1){
            CrimeItem lastItem = getLastRecord();
            if(lastItem!=null){
                mAccessPolicemen.setText(lastItem.getAccessPolicemen());
                mWeatherCondition_spinner.setSelection(getWeatherCondition(lastItem.getWeatherCondition()));
                mWindDirection_spinner.setSelection(getWindDirection(lastItem.getWindDirection()));
                mTemperature.setText(lastItem.getTemperature());
                mHumidity.setText(lastItem.getHumidity());
                mProductPeopleName.setText(lastItem.getProductPeopleName());
                mProductPeopleUnit.setText(lastItem.getProductPeopleUnit());
                mProductPeopleDuties.setText(lastItem.getProductPeopleDuties());
                mSafeguard.setText(lastItem.getSafeguard());
                saveData();
            }
        }
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

        if(mItem.getChangeReason().equalsIgnoreCase("报案人进入")) mInformantCkBx.setChecked(true);
        else if(mItem.getChangeReason().equalsIgnoreCase("事主进入")) mVictimCkBx.setChecked(true);
        else if(!mItem.getChangeReason().isEmpty()) mOtherCkBx.setChecked(true);
        mChangeReason.setText(mItem.getChangeReason());

        mWeatherCondition_spinner.setSelection(getWeatherCondition(mItem.getWeatherCondition()));
        mWindDirection_spinner.setSelection(getWindDirection(mItem.getWindDirection()));
        mTemperature.setText(mItem.getTemperature());
        mHumidity.setText(mItem.getHumidity());
        mAccessReason.setText(mItem.getAccessReason());
        mIlluminationCondition_spinner.setSelection(getIlluminationCondition(mItem.getIlluminationCondition()));
        mProductPeopleName.setText(mItem.getProductPeopleName());
        mProductPeopleUnit.setText(mItem.getProductPeopleUnit());
        mProductPeopleDuties.setText(mItem.getProductPeopleDuties());
        mSafeguard.setText(mItem.getSafeguard());
        mSceneConductor_spinner.setSelection(getSceneConductor(mItem.getSceneConductor()));
        mAccessInspectors_spinner.setSelection(getAccessInspectors(mItem.getAccessInspectors()));

        mOccurred_start_time.setText(DateTimePicker.getCurrentTime(mItem.getOccurredStartTime()));
        mOccurred_end_time.setText(DateTimePicker.getCurrentTime(mItem.getOccurredEndTime()));
        mGet_access_time.setText(DateTimePicker.getCurrentTime(mItem.getGetAccessTime()));
        mAccess_start_time.setText(DateTimePicker.getCurrentTime(mItem.getAccessStartTime()));
        mAccess_end_time.setText(DateTimePicker.getCurrentTime(mItem.getAccessEndTime()));
    }

    public void saveData(){
        mItem.setLocationa(mLocation.getText());
        mItem.setUnitsAssigned(mUnitsAssigned.getText());
        mItem.setAccessPolicemen(mAccessPolicemen.getText());
        mItem.setAccessLocation(mAccessLocation.getText());
        mItem.setCaseOccurProcess(mCaseOccurProcess.getText());

        if(mInformantCkBx.isChecked()) mItem.setChangeReason("报案人进入");
        else if(mVictimCkBx.isChecked()) mItem.setChangeReason("事主进入");
        else mItem.setChangeReason(mChangeReason.getText());

        mItem.setTemperature(mTemperature.getText());
        mItem.setHumidity(mHumidity.getText());
        mItem.setAccessReason(mAccessReason.getText());
        mItem.setProductPeopleName(mProductPeopleName.getText());
        mItem.setProductPeopleUnit(mProductPeopleUnit.getText());
        mItem.setProductPeopleDuties(mProductPeopleDuties.getText());
        mItem.setSafeguard(mSafeguard.getText());
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
                mCaseOccurProcess.setText(getCaseOccurProcess());
                break;
            case R.id.accessReason_button:
                mAccessReason.setText(getAccessReason());
                break;
            default:
                break;
        }
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

    private int getIlluminationCondition(String illuminationCondition){
        for(int i=0; i<mIlluminationCondition.size(); i++){
            if(illuminationCondition.equalsIgnoreCase(mIlluminationCondition.get(i))) return i;
        }
        return 0;
    }

    private int getSceneConductor(String sceneConductor){
        for(int i=0; i<mSceneConductor.size(); i++){
            if(sceneConductor.equalsIgnoreCase(mSceneConductor.get(i))) return i;
        }
        return 0;
    }

    private int getAccessInspectors(String accessInspectors){
        for(int i=0; i<mAccessInspectors.size(); i++){
            if(accessInspectors.equalsIgnoreCase(mAccessInspectors.get(i))) return i;
        }
        return 0;
    }

    private String getCaseOccurProcess(){
        saveData();
        //Example : "据<被害人/报案人>报称:<发案开始时间> 在<发案地点>，该处发现一起<案件类别>，后拨打电话报警"
        String result = "据";
        if(mItem.getReleatedPeople().size()>0){
            for(int i = 0;i<mItem.getReleatedPeople().size();i++){
                result = result + mItem.getReleatedPeople().get(i).getPeopleName();
                if(i!=mItem.getReleatedPeople().size()-1) result = result + ",";
            }
        }else{
            result = result + "<被害人/报案人>";
        }

        result = result + "报称:"+ DateTimePicker.getCurrentTime(mItem.getOccurredStartTime())
                        + "，在" + mItem.getLocation()
                        + "，该处发现一起" + mItem.getCasetype()
                        + "，后拨打电话报警。";

        return result;
    }

    private String getAccessReason(){
        saveData();
        //Example : "<发案区划><接警人>接到<指派单位>的指派: 在该所管界内<发案地点>发生一起<案件类别>，请速派人员勘查现场"
        String result = mItem.getArea() + mItem.getAccessPolicemen() + "接到"
                        + mItem.getUnitsAssigned() + "的指派: 在该所管界内"
                        + mItem.getLocation() + "发生一起" + mItem.getCasetype()
                        + "，请速派人员勘查现场。";
        return result;
    }

    private void showDateTimeDialog(final TextView textView,final int type) {
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

    private CrimeItem getLastRecord(){
        CrimeProvider crimeProvider = new CrimeProvider(context);
        List<CrimeItem> items = crimeProvider.getAll();
        CrimeItem item = null;
        if(items.size()>0) item = items.get(items.size()-1);
        return item;
    }
}
