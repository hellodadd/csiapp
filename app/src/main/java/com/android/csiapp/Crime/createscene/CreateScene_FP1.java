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

import com.android.csiapp.Databases.CrimeItem;
import com.android.csiapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateScene_FP1 extends Fragment implements View.OnClickListener {

    private Context context = null;
    private CrimeItem mItem;
    private int mEvent;

    private Spinner casetype_spinner;
    private ArrayList<String> casetype = new ArrayList<String>();
    private ArrayAdapter<String> casetype_adapter;
    private Spinner area_spinner;
    private ArrayList<String> area = new ArrayList<String>();
    private ArrayAdapter<String> area_adapter;

    //Anita test start
    private Calendar c;
    private TextView occurred_start_time;
    private TextView occurred_end_time;
    private TextView get_access_time;
    private Button occurred_start_button;
    private Button occurred_end_button;
    private Button get_access_button;
    private Spinner unitsAssigned_spinner;
    private ArrayList<String> unitsAssigned = new ArrayList<String>();;
    private ArrayAdapter<String> unitsAssigned_adapter;
    private Spinner accessPolicemen_spinner;
    private ArrayList<String> accessPolicemen = new ArrayList<String>();;
    private ArrayAdapter<String> accessPolicemen_adapter;
    private Spinner sceneCondition_spinner;
    private ArrayList<String> sceneCondition = new ArrayList<String>();;
    private ArrayAdapter<String> sceneCondition_adapter;
    //Anita test end

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

        casetype = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.casetype)));
        casetype_spinner = (Spinner) view.findViewById(R.id.casetype_spinner);
        casetype_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, casetype);
        casetype_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        casetype_spinner.setAdapter(casetype_adapter);
        casetype_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                mItem.setCasetype(casetype.get(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        area = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.area)));
        area_spinner = (Spinner) view.findViewById(R.id.area_spinner);
        area_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, area);
        area_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        area_spinner.setAdapter(area_adapter);
        area_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                mItem.setArea(area.get(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        //Anita test start
        c = Calendar.getInstance();
        occurred_start_time = (TextView) view.findViewById(R.id.occurred_start_time);
        occurred_end_time = (TextView) view.findViewById(R.id.occurred_end_time);
        get_access_time = (TextView) view.findViewById(R.id.get_access_time);
        occurred_start_button = (Button) view.findViewById(R.id.occurred_start_time_button);
        occurred_end_button = (Button) view.findViewById(R.id.occurred_end_time_button);
        get_access_button = (Button) view.findViewById(R.id.get_access_time_button);
        if(mEvent == 2) {
            casetype_spinner.setSelection(getCategory(mItem.getCasetype()));
            area_spinner.setSelection(getArea(mItem.getArea()));
            occurred_start_time.setText(mItem.getTime());
            occurred_end_time.setText(mItem.getTime());
            get_access_time.setText(mItem.getTime());
        }else{
            occurred_start_time.setText(CrimeItem.getCurrentTime(c));
            mItem.setTime(occurred_start_time.getText().toString());
            occurred_end_time.setText(CrimeItem.getCurrentTime(c));
            get_access_time.setText(CrimeItem.getCurrentTime(c));
        }
        occurred_start_button.setOnClickListener(this);
        occurred_end_button.setOnClickListener(this);
        get_access_button.setOnClickListener(this);

        unitsAssigned = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.unitsAssigned)));
        unitsAssigned_spinner = (Spinner) view.findViewById(R.id.unitsAssigned_spinner);
        unitsAssigned_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, unitsAssigned);
        unitsAssigned_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitsAssigned_spinner.setAdapter(unitsAssigned_adapter);
        unitsAssigned_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        accessPolicemen = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.accessPolicemen)));
        accessPolicemen_spinner = (Spinner) view.findViewById(R.id.accessPolicemen_spinner);
        accessPolicemen_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, accessPolicemen);
        accessPolicemen_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accessPolicemen_spinner.setAdapter(accessPolicemen_adapter);
        accessPolicemen_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        sceneCondition = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.sceneCondition)));
        sceneCondition_spinner = (Spinner) view.findViewById(R.id.sceneCondition_spinner);
        sceneCondition_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, sceneCondition);
        sceneCondition_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sceneCondition_spinner.setAdapter(sceneCondition_adapter);
        sceneCondition_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        //Anita test end
        return view;
    }

    //Anita test start
    private int getCategory(String category){
        for(int i=0; i<casetype.size(); i++){
            if(category.equalsIgnoreCase(casetype.get(i))) return i;
        }
        return 0;
    }
    private int getArea(String category){
        for(int i=0; i<area.size(); i++){
            if(category.equalsIgnoreCase(area.get(i))) return i;
        }
        return 0;
    }
    //Anita test end

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.occurred_start_time_button:
                showDateTimeDialog(occurred_start_time);
                break;
            case R.id.occurred_end_time_button:
                showDateTimeDialog(occurred_end_time);
                break;
            case R.id.get_access_time_button:
                showDateTimeDialog(get_access_time);
                break;
            default:
                break;
        }
    }

    public void showDateTimeDialog(final TextView textView) {
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
                c = mDateTimePicker.get();
                textView.setText(CrimeItem.getCurrentTime(c));
                mItem.setTime(textView.getText().toString());
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
