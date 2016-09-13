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

/**
 * A simple {@link Fragment} subclass.
 */
public class Create_FragmentPage1 extends Fragment {

    private Item item;
    private int event;

    private Spinner casetype_spinner;
    private String[] casetype = {"Steal electric bicycle","Steal Animal","Pickpocketing","Steal motorcycle","Steal bicycle","Robbery","Hurt","Snatch","Bilk"};
    private ArrayAdapter<String> casetype_adapter;
    private Spinner area_spinner;
    private String[] area = {"Taipei", "Taoyuan", "Taichung", "Tainan", "Kaohsiung"};
    private ArrayAdapter<String> area_adapter;

    private EditText time;//Anita test

    public Create_FragmentPage1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_fragmentpage1, container, false);
        CreateSceneActivity activity  = (CreateSceneActivity) getActivity();
        item = activity.getItem();
        event = activity.getEvent();
        casetype_spinner = (Spinner) view.findViewById(R.id.casetype_spinner);
        casetype_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, casetype);
        casetype_spinner.setAdapter(casetype_adapter);
        casetype_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                item.setCasetype(casetype[position]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        area_spinner = (Spinner) view.findViewById(R.id.area_spinner);
        area_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, area);
        area_spinner.setAdapter(area_adapter);
        area_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                item.setArea(area[position]);
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
        //Anita test end
        return view;
    }

    //Anita test start
    private int getCategory(String category){
        for(int i=0; i<casetype.length; i++){
            if(category.equalsIgnoreCase(casetype[i])) return i;
        }
        return 0;
    }
    private int getArea(String category){
        for(int i=0; i<area.length; i++){
            if(category.equalsIgnoreCase(area[i])) return i;
        }
        return 0;
    }
    //Anita test end
}
