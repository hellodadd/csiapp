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
public class CreateScene_FP7 extends Fragment{

    private Context context = null;
    private CrimeItem mItem;
    private int mEvent;

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
    private Spinner mCrimeTiming_spinner;
    private ArrayList<String> mCrimeTiming = new ArrayList<String>();
    private ArrayAdapter<String> mCrimeTiming_adapter;
    private Spinner mSelectObject_spinner;
    private ArrayList<String> mSelectObject = new ArrayList<String>();
    private ArrayAdapter<String> mSelectObject_adapter;
    private Spinner mCrimeExport_spinner;
    private ArrayList<String> mCrimeExport = new ArrayList<String>();
    private ArrayAdapter<String> mCrimeExport_adapter;

    private ClearableEditText mPeopleFeature;

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
        mEvent = activity.getEvent();
        context = getActivity().getApplicationContext();

        initView(view);
        initData();

        return view;
    }

    private void initView(View view){
        mPeopleNumber  = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.people_number)));
        mPeopleNumber_spinner = (Spinner) view.findViewById(R.id.peopleNumber_spinner);
        mPeopleNumber_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, mPeopleNumber);
        mPeopleNumber_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mPeopleNumber_spinner.setAdapter(mPeopleNumber_adapter);
        mPeopleNumber_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                mItem.setCrimePeopleNumber(mPeopleNumber.get(position));
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
                mItem.setCrimeMeans(mCrimeMeans.get(position));
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
                mItem.setCrimeCharacter(mCrimeCharacter.get(position));
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
                mItem.setCrimeEntrance(mCrimeEntrance.get(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mCrimeTiming  = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.crime_timing)));
        mCrimeTiming_spinner = (Spinner) view.findViewById(R.id.crimeTiming_spinner);
        mCrimeTiming_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, mCrimeTiming);
        mCrimeTiming_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCrimeTiming_spinner.setAdapter(mCrimeTiming_adapter);
        mCrimeTiming_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                mItem.setCrimeEntrance(mCrimeTiming.get(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mSelectObject  = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.select_object)));
        mSelectObject_spinner = (Spinner) view.findViewById(R.id.selectObject_spinner);
        mSelectObject_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, mSelectObject);
        mSelectObject_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSelectObject_spinner.setAdapter(mSelectObject_adapter);
        mSelectObject_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                mItem.setSelectObject(mSelectObject.get(position));
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
                mItem.setCrimeExport(mCrimeExport.get(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mPeopleFeature = (ClearableEditText) view.findViewById(R.id.peopleFeature);

        mCrimeFeature  = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.crime_feature)));
        mCrimeFeature_spinner = (Spinner) view.findViewById(R.id.crimeFeature_spinner);
        mCrimeFeature_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, mCrimeFeature);
        mCrimeFeature_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCrimeFeature_spinner.setAdapter(mCrimeFeature_adapter);
        mCrimeFeature_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                mItem.setCrimeFeature(mCrimeFeature.get(position));
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
                mItem.setIntrusiveMethod(mIntrusiveMethod.get(position));
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
                mItem.setSelectLocation(mSelectLocation.get(position));
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
                mItem.setCrimePurpose(mCrimePurpose.get(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    private void initData(){
        mPeopleNumber_spinner.setSelection(getPeopleNumber(mItem.getCrimePeopleNumber()));
        mCrimeMeans_spinner.setSelection(getCrimeMeans(mItem.getCrimeMeans()));
        mCrimeCharacter_spinner.setSelection(getCrimeCharacter(mItem.getCrimeCharacter()));
        mCrimeEntrance_spinner.setSelection(getCrimeEntrance(mItem.getCrimeEntrance()));
        mCrimeTiming_spinner.setSelection(getCrimeTiming(mItem.getCrimeTiming()));
        mSelectObject_spinner.setSelection(getSelectObject(mItem.getSelectObject()));
        mCrimeExport_spinner.setSelection(getCrimeExport(mItem.getCrimeExport()));
        mCrimeFeature_spinner.setSelection(getCrimeFeature(mItem.getCrimeFeature()));
        mIntrusiveMethod_spinner.setSelection(getIntrusiveMethod(mItem.getIntrusiveMethod()));
        mSelectLocation_spinner.setSelection(getSelectLocation(mItem.getSelectLocation()));
        mCrimePurpose_spinner.setSelection(getCrimePurpose(mItem.getCrimePurpose()));
    }

    public void saveData(){
        mItem.setCrimePeopleFeature(mPeopleFeature.getText());;
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

    private int getPeopleNumber(String peopleNumber){
        for(int i=0; i<mPeopleNumber.size(); i++){
            if(peopleNumber.equalsIgnoreCase(mPeopleNumber.get(i))) return i;
        }
        return 0;
    }

    private int getCrimeMeans(String crimeMeans){
        for(int i=0; i<mCrimeMeans.size(); i++){
            if(crimeMeans.equalsIgnoreCase(mCrimeMeans.get(i))) return i;
        }
        return 0;
    }

    private int getCrimeCharacter(String crimeCharacter){
        for(int i=0; i<mCrimeCharacter.size(); i++){
            if(crimeCharacter.equalsIgnoreCase(mCrimeCharacter.get(i))) return i;
        }
        return 0;
    }

    private int getCrimeEntrance(String crimeEntrance){
        for(int i=0; i<mCrimeEntrance.size(); i++){
            if(crimeEntrance.equalsIgnoreCase(mCrimeEntrance.get(i))) return i;
        }
        return 0;
    }

    private int getCrimeTiming(String crimeTiming){
        for(int i=0; i<mCrimeTiming.size(); i++){
            if(crimeTiming.equalsIgnoreCase(mCrimeTiming.get(i))) return i;
        }
        return 0;
    }

    private int getSelectObject(String selectObject){
        for(int i=0; i<mSelectObject.size(); i++){
            if(selectObject.equalsIgnoreCase(mSelectObject.get(i))) return i;
        }
        return 0;
    }

    private int getCrimeExport(String crimeExport){
        for(int i=0; i<mCrimeExport.size(); i++){
            if(crimeExport.equalsIgnoreCase(mCrimeExport.get(i))) return i;
        }
        return 0;
    }

    private int getCrimeFeature(String crimeFeature){
        for(int i=0; i<mCrimeFeature.size(); i++){
            if(crimeFeature.equalsIgnoreCase(mCrimeFeature.get(i))) return i;
        }
        return 0;
    }

    private int getIntrusiveMethod(String intrusiveMethod){
        for(int i=0; i<mIntrusiveMethod.size(); i++){
            if(intrusiveMethod.equalsIgnoreCase(mIntrusiveMethod.get(i))) return i;
        }
        return 0;
    }

    private int getSelectLocation(String selectLocation){
        for(int i=0; i<mSelectLocation.size(); i++){
            if(selectLocation.equalsIgnoreCase(mSelectLocation.get(i))) return i;
        }
        return 0;
    }

    private int getCrimePurpose(String crimePurpose){
        for(int i=0; i<mCrimePurpose.size(); i++){
            if(crimePurpose.equalsIgnoreCase(mCrimePurpose.get(i))) return i;
        }
        return 0;
    }
}
