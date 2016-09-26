package com.android.csiapp.Crime.createscene;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.csiapp.Databases.CrimeItem;
import com.android.csiapp.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateScene_FP7 extends Fragment {

    private Context context = null;
    private CrimeItem item;

    private Spinner peopleNumber_spinner;
    private ArrayList<String> peopleNumber = new ArrayList<String>();
    private ArrayAdapter<String> peopleNumber_adapter;
    private Spinner crimeMeans_spinner;
    private ArrayList<String> crimeMeans = new ArrayList<String>();
    private ArrayAdapter<String> crimeMeans_adapter;
    private Spinner crimeCharacter_spinner;
    private ArrayList<String> crimeCharacter = new ArrayList<String>();
    private ArrayAdapter<String> crimeCharacter_adapter;
    private Spinner crimeEntrance_spinner;
    private ArrayList<String> crimeEntrance = new ArrayList<String>();
    private ArrayAdapter<String> crimeEntrance_adapter;
    private Spinner selectObject_spinner;
    private ArrayList<String> selectObject = new ArrayList<String>();
    private ArrayAdapter<String> selectObject_adapter;
    private Spinner crimeExport_spinner;
    private ArrayList<String> crimeExport = new ArrayList<String>();
    private ArrayAdapter<String> crimeExport_adapter;
    private Spinner crimeFeature_spinner;
    private ArrayList<String> crimeFeature = new ArrayList<String>();
    private ArrayAdapter<String> crimeFeature_adapter;
    private Spinner intrusiveMethod_spinner;
    private ArrayList<String> intrusiveMethod = new ArrayList<String>();
    private ArrayAdapter<String> intrusiveMethod_adapter;
    private Spinner selectLocation_spinner;
    private ArrayList<String> selectLocation = new ArrayList<String>();
    private ArrayAdapter<String> selectLocation_adapter;
    private Spinner crimePurpose_spinner;
    private ArrayList<String> crimePurpose = new ArrayList<String>();
    private ArrayAdapter<String> crimePurpose_adapter;

    public CreateScene_FP7() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_scene_fp7, container, false);
        CreateSceneActivity activity  = (CreateSceneActivity) getActivity();
        item = activity.getItem();
        context = getActivity().getApplicationContext();

        peopleNumber  = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.people_number)));
        peopleNumber_spinner = (Spinner) view.findViewById(R.id.peopleNumber_spinner);
        peopleNumber_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, peopleNumber);
        peopleNumber_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        peopleNumber_spinner.setAdapter(peopleNumber_adapter);
        peopleNumber_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        crimeMeans  = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.crime_means)));
        crimeMeans_spinner = (Spinner) view.findViewById(R.id.crimeMeans_spinner);
        crimeMeans_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, crimeMeans);
        crimeMeans_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        crimeMeans_spinner.setAdapter(crimeMeans_adapter);
        crimeMeans_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        crimeCharacter  = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.crime_character)));
        crimeCharacter_spinner = (Spinner) view.findViewById(R.id.crimeCharacter_spinner);
        crimeCharacter_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, crimeCharacter);
        crimeCharacter_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        crimeCharacter_spinner.setAdapter(crimeCharacter_adapter);
        crimeCharacter_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        crimeEntrance  = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.crime_entrance)));
        crimeEntrance_spinner = (Spinner) view.findViewById(R.id.crimeEntrance_spinner);
        crimeEntrance_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, crimeEntrance);
        crimeEntrance_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        crimeEntrance_spinner.setAdapter(crimeEntrance_adapter);
        crimeEntrance_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        selectObject  = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.select_object)));
        selectObject_spinner = (Spinner) view.findViewById(R.id.selectObject_spinner);
        selectObject_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, selectObject);
        selectObject_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectObject_spinner.setAdapter(selectObject_adapter);
        selectObject_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        crimeExport  = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.crime_export)));
        crimeExport_spinner = (Spinner) view.findViewById(R.id.crimeExport_spinner);
        crimeExport_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, crimeExport);
        crimeExport_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        crimeExport_spinner.setAdapter(crimeExport_adapter);
        crimeExport_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        crimeFeature  = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.crime_feature)));
        crimeFeature_spinner = (Spinner) view.findViewById(R.id.crimeFeature_spinner);
        crimeFeature_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, crimeFeature);
        crimeFeature_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        crimeFeature_spinner.setAdapter(crimeFeature_adapter);
        crimeFeature_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        intrusiveMethod  = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.intrusive_method)));
        intrusiveMethod_spinner = (Spinner) view.findViewById(R.id.intrusiveMethod_spinner);
        intrusiveMethod_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, intrusiveMethod);
        intrusiveMethod_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        intrusiveMethod_spinner.setAdapter(intrusiveMethod_adapter);
        intrusiveMethod_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        selectLocation  = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.select_location)));
        selectLocation_spinner = (Spinner) view.findViewById(R.id.selectLocation_spinner);
        selectLocation_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, selectLocation);
        selectLocation_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectLocation_spinner.setAdapter(selectLocation_adapter);
        selectLocation_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        crimePurpose  = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.crime_purpose)));
        crimePurpose_spinner = (Spinner) view.findViewById(R.id.crimePurpose_spinner);
        crimePurpose_adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerview, crimePurpose);
        crimePurpose_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        crimePurpose_spinner.setAdapter(crimePurpose_adapter);
        crimePurpose_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        return view;
    }
}
