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

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateScene_FP7 extends Fragment implements View.OnClickListener{

    private Context context = null;
    private CrimeItem mItem;

    private Spinner mPeopleNumber_spinner;
    private ArrayList<String> mPeopleNumber = new ArrayList<String>();
    private ArrayAdapter<String> mPeopleNumber_adapter;
    private Spinner mCrimeMeans_spinner;
    private ArrayList<String> mCrimeMeans = new ArrayList<String>();
    private ArrayAdapter<String> mCrimeMeans_adapter;
    private Spinner mCrimeCharacter_spinner;
    private ArrayList<String> mCrimeCharacter = new ArrayList<String>();
    private ArrayAdapter<String> mCrimeCharacter_adapter;
    private Spinner mCrimeEntrance_spinner;
    private ArrayList<String> mCrimeEntrance = new ArrayList<String>();
    private ArrayAdapter<String> mCrimeEntrance_adapter;

    private Calendar c;
    private TextView mCrimeTiming;
    private Button mCrimeTiming_button;

    private Spinner mSelectObject_spinner;
    private ArrayList<String> mSelectObject = new ArrayList<String>();
    private ArrayAdapter<String> mSelectObject_adapter;
    private Spinner mCrimeExport_spinner;
    private ArrayList<String> mCrimeExport = new ArrayList<String>();
    private ArrayAdapter<String> mCrimeExport_adapter;
    private Spinner mCrimeFeature_spinner;
    private ArrayList<String> mCrimeFeature = new ArrayList<String>();
    private ArrayAdapter<String> mCrimeFeature_adapter;
    private Spinner mIntrusiveMethod_spinner;
    private ArrayList<String> mIntrusiveMethod = new ArrayList<String>();
    private ArrayAdapter<String> mIntrusiveMethod_adapter;
    private Spinner mSelectLocation_spinner;
    private ArrayList<String> mSelectLocation = new ArrayList<String>();
    private ArrayAdapter<String> mSelectLocation_adapter;
    private Spinner mCrimePurpose_spinner;
    private ArrayList<String> mCrimePurpose = new ArrayList<String>();
    private ArrayAdapter<String> mCrimePurpose_adapter;

    public CreateScene_FP7() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_scene_fp7, container, false);
        CreateSceneActivity activity  = (CreateSceneActivity) getActivity();
        mItem = activity.getItem();
        context = getActivity().getApplicationContext();

        mPeopleNumber  = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.people_number)));
        mPeopleNumber_spinner = (Spinner) view.findViewById(R.id.peopleNumber_spinner);
        mPeopleNumber_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, mPeopleNumber);
        mPeopleNumber_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mPeopleNumber_spinner.setAdapter(mPeopleNumber_adapter);
        mPeopleNumber_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mCrimeMeans  = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.crime_means)));
        mCrimeMeans_spinner = (Spinner) view.findViewById(R.id.crimeMeans_spinner);
        mCrimeMeans_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, mCrimeMeans);
        mCrimeMeans_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCrimeMeans_spinner.setAdapter(mCrimeMeans_adapter);
        mCrimeMeans_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mCrimeCharacter  = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.crime_character)));
        mCrimeCharacter_spinner = (Spinner) view.findViewById(R.id.crimeCharacter_spinner);
        mCrimeCharacter_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, mCrimeCharacter);
        mCrimeCharacter_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCrimeCharacter_spinner.setAdapter(mCrimeCharacter_adapter);
        mCrimeCharacter_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mCrimeEntrance  = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.crime_entrance)));
        mCrimeEntrance_spinner = (Spinner) view.findViewById(R.id.crimeEntrance_spinner);
        mCrimeEntrance_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, mCrimeEntrance);
        mCrimeEntrance_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCrimeEntrance_spinner.setAdapter(mCrimeEntrance_adapter);
        mCrimeEntrance_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        c = Calendar.getInstance();
        mCrimeTiming = (TextView) view.findViewById(R.id.crimeTiming);
        mCrimeTiming_button = (Button) view.findViewById(R.id.crimeTiming_button);
        mCrimeTiming.setText(CrimeItem.getCurrentTime(c));
        mCrimeTiming_button.setOnClickListener(this);

        mSelectObject  = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.select_object)));
        mSelectObject_spinner = (Spinner) view.findViewById(R.id.selectObject_spinner);
        mSelectObject_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, mSelectObject);
        mSelectObject_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSelectObject_spinner.setAdapter(mSelectObject_adapter);
        mSelectObject_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mCrimeExport  = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.crime_export)));
        mCrimeExport_spinner = (Spinner) view.findViewById(R.id.crimeExport_spinner);
        mCrimeExport_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, mCrimeExport);
        mCrimeExport_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCrimeExport_spinner.setAdapter(mCrimeExport_adapter);
        mCrimeExport_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mCrimeFeature  = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.crime_feature)));
        mCrimeFeature_spinner = (Spinner) view.findViewById(R.id.crimeFeature_spinner);
        mCrimeFeature_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, mCrimeFeature);
        mCrimeFeature_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCrimeFeature_spinner.setAdapter(mCrimeFeature_adapter);
        mCrimeFeature_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mIntrusiveMethod  = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.intrusive_method)));
        mIntrusiveMethod_spinner = (Spinner) view.findViewById(R.id.intrusiveMethod_spinner);
        mIntrusiveMethod_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, mIntrusiveMethod);
        mIntrusiveMethod_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mIntrusiveMethod_spinner.setAdapter(mIntrusiveMethod_adapter);
        mIntrusiveMethod_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mSelectLocation  = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.select_location)));
        mSelectLocation_spinner = (Spinner) view.findViewById(R.id.selectLocation_spinner);
        mSelectLocation_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, mSelectLocation);
        mSelectLocation_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSelectLocation_spinner.setAdapter(mSelectLocation_adapter);
        mSelectLocation_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mCrimePurpose  = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.crime_purpose)));
        mCrimePurpose_spinner = (Spinner) view.findViewById(R.id.crimePurpose_spinner);
        mCrimePurpose_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, mCrimePurpose);
        mCrimePurpose_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCrimePurpose_spinner.setAdapter(mCrimePurpose_adapter);
        mCrimePurpose_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.crimeTiming_button:
                showDateTimeDialog(mCrimeTiming);
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
