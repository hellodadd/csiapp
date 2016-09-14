package com.android.csiapp.Csi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.csiapp.Item;
import com.android.csiapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Create_FragmentPage1 extends Fragment {

    private Item item;
    private int event;

    private Spinner casetype_spinner;
    private ArrayList<String> casetype = new ArrayList<String>();
    private ArrayAdapter<String> casetype_adapter;
    private Spinner area_spinner;
    private ArrayList<String> area = new ArrayList<String>();
    private ArrayAdapter<String> area_adapter;

    //Anita test start
    private EditText time;
    private Spinner unitsAssigned_spinner;
    private String[] unitsAssigned = {"110指挥中心 ", "112指挥中心", "999指挥中心"};
    private ArrayAdapter<String> unitsAssigned_adapter;
    private Spinner accessPolicemen_spinner;
    private String[] accessPolicemen = {"杨警官 ", "林警官", "陈警官"};
    private ArrayAdapter<String> accessPolicemen_adapter;
    //Anita test end

    public Create_FragmentPage1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_fragmentpage1, container, false);
        CreateSceneActivity activity  = (CreateSceneActivity) getActivity();
        item = activity.getItem();
        event = activity.getEvent();

        casetype.add(getString(R.string.steal_electric_bicycle));
        casetype.add(getString(R.string.steal_animal));
        casetype.add(getString(R.string.pickpocketing));
        casetype.add(getString(R.string.steal_motorcycle));
        casetype.add(getString(R.string.steal_bicycle));
        casetype.add(getString(R.string.robbery));
        casetype.add(getString(R.string.hurt));
        casetype.add(getString(R.string.snatch));
        casetype.add(getString(R.string.bilk));
        casetype_spinner = (Spinner) view.findViewById(R.id.casetype_spinner);
        casetype_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, casetype);
        casetype_spinner.setAdapter(casetype_adapter);
        casetype_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                item.setCasetype(casetype.get(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        area.add(getString(R.string.shanghai));
        area.add(getString(R.string.beijing));
        area.add(getString(R.string.nanjing));
        area.add(getString(R.string.shenzhen));
        area.add(getString(R.string.tianjin));
        area_spinner = (Spinner) view.findViewById(R.id.area_spinner);
        area_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, area);
        area_spinner.setAdapter(area_adapter);
        area_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                item.setArea(area.get(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        //Anita test start
        time = (EditText) view.findViewById(R.id.time);
        if(event == 2) {
            casetype_spinner.setSelection(getCategory(item.getCasetype()));
            area_spinner.setSelection(getArea(item.getArea()));
            time.setText(item.getTime());
        }
        time.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                item.setTime(time.getText().toString());
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        unitsAssigned_spinner = (Spinner) view.findViewById(R.id.unitsAssigned_spinner);
        unitsAssigned_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, unitsAssigned);
        unitsAssigned_spinner.setAdapter(unitsAssigned_adapter);
        unitsAssigned_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        accessPolicemen_spinner = (Spinner) view.findViewById(R.id.accessPolicemen_spinner);
        accessPolicemen_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, accessPolicemen);
        accessPolicemen_spinner.setAdapter(accessPolicemen_adapter);
        accessPolicemen_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
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
}
