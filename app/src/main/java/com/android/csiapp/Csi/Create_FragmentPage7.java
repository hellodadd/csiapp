package com.android.csiapp.Csi;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.csiapp.Item;
import com.android.csiapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Create_FragmentPage7 extends Fragment {

    private Context context = null;
    private Item item;

    private Spinner peopleNumber_spinner;
    private String[] peopleNumber = {"单人 ", "两人", "多人","不确定"};
    private ArrayAdapter<String> peopleNumber_adapter;
    private Spinner crimeMeans_spinner;
    private String[] crimeMeans = {"盗窃", "扒窃 ", "挡路抢劫", "故意伤害", "抢夺", "诈骗"};
    private ArrayAdapter<String> crimeMeans_adapter;
    private Spinner crimeCharacter_spinner;
    private String[] crimeCharacter = {"盗窃", "扒窃 ", "挡路抢劫", "故意伤害", "抢夺", "诈骗"};
    private ArrayAdapter<String> crimeCharacter_adapter;
    private Spinner crimeEntrance_spinner;
    private String[] crimeEntrance = {"门", "窗"};
    private ArrayAdapter<String> crimeEntrance_adapter;
    private Spinner selectObject_spinner;
    private String[] selectObject = {"老人", "小孩", "女人", "男人"};
    private ArrayAdapter<String> selectObject_adapter;
    private Spinner crimeExport_spinner;
    private String[] crimeExport = {"门", "窗"};
    private ArrayAdapter<String> crimeExport_adapter;
    private Spinner crimeFeature_spinner;
    private String[] crimeFeature = {"单独作案", "内部勾结", "外来勾结"};
    private ArrayAdapter<String> crimeFeature_adapter;
    private Spinner intrusiveMethod_spinner;
    private String[] intrusiveMethod = {"从门侵入", "从窗侵入"};
    private ArrayAdapter<String> intrusiveMethod_adapter;
    private Spinner selectLocation_spinner;
    private String[] selectLocation = {"工厂", "农场", "果园"};
    private ArrayAdapter<String> selectLocation_adapter;
    private Spinner crimePurpose_spinner;
    private String[] crimePurpose = {"报复", "图财", "嫉妒"};
    private ArrayAdapter<String> crimePurpose_adapter;

    public Create_FragmentPage7() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_fragmentpage7, container, false);
        CreateSceneActivity activity  = (CreateSceneActivity) getActivity();
        item = activity.getItem();
        context = getActivity().getApplicationContext();

        peopleNumber_spinner = (Spinner) view.findViewById(R.id.peopleNumber_spinner);
        peopleNumber_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, peopleNumber);
        peopleNumber_spinner.setAdapter(peopleNumber_adapter);
        peopleNumber_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        crimeMeans_spinner = (Spinner) view.findViewById(R.id.crimeMeans_spinner);
        crimeMeans_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, crimeMeans);
        crimeMeans_spinner.setAdapter(crimeMeans_adapter);
        crimeMeans_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        crimeCharacter_spinner = (Spinner) view.findViewById(R.id.crimeCharacter_spinner);
        crimeCharacter_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, crimeCharacter);
        crimeCharacter_spinner.setAdapter(crimeCharacter_adapter);
        crimeCharacter_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        crimeEntrance_spinner = (Spinner) view.findViewById(R.id.crimeEntrance_spinner);
        crimeEntrance_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, crimeEntrance);
        crimeEntrance_spinner.setAdapter(crimeEntrance_adapter);
        crimeEntrance_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        selectObject_spinner = (Spinner) view.findViewById(R.id.selectObject_spinner);
        selectObject_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, selectObject);
        selectObject_spinner.setAdapter(selectObject_adapter);
        selectObject_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        crimeExport_spinner = (Spinner) view.findViewById(R.id.crimeExport_spinner);
        crimeExport_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, crimeExport);
        crimeExport_spinner.setAdapter(crimeExport_adapter);
        crimeExport_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        crimeFeature_spinner = (Spinner) view.findViewById(R.id.crimeFeature_spinner);
        crimeFeature_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, crimeFeature);
        crimeFeature_spinner.setAdapter(crimeFeature_adapter);
        crimeFeature_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        intrusiveMethod_spinner = (Spinner) view.findViewById(R.id.intrusiveMethod_spinner);
        intrusiveMethod_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, intrusiveMethod);
        intrusiveMethod_spinner.setAdapter(intrusiveMethod_adapter);
        intrusiveMethod_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        selectLocation_spinner = (Spinner) view.findViewById(R.id.selectLocation_spinner);
        selectLocation_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, selectLocation);
        selectLocation_spinner.setAdapter(selectLocation_adapter);
        selectLocation_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        crimePurpose_spinner = (Spinner) view.findViewById(R.id.crimePurpose_spinner);
        crimePurpose_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, crimePurpose);
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
