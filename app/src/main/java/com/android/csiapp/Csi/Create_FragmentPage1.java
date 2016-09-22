package com.android.csiapp.Csi;

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

import com.android.csiapp.DateTimePicker;
import com.android.csiapp.Item;
import com.android.csiapp.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class Create_FragmentPage1 extends Fragment implements View.OnClickListener {

    private Context context = null;
    private Item item;
    private int event;

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
    private String[] unitsAssigned = {"110指挥中心 ", "112指挥中心", "999指挥中心"};
    private ArrayAdapter<String> unitsAssigned_adapter;
    private Spinner accessPolicemen_spinner;
    private String[] accessPolicemen = {"杨警官 ", "林警官", "陈警官"};
    private ArrayAdapter<String> accessPolicemen_adapter;
    private Spinner sceneCondition_spinner;
    private String[] sceneCondition = {"原始现场 ", "变动现场"};
    private ArrayAdapter<String> sceneCondition_adapter;
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
        context = getActivity().getApplicationContext();

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
        c = Calendar.getInstance();
        occurred_start_time = (TextView) view.findViewById(R.id.occurred_start_time);
        occurred_end_time = (TextView) view.findViewById(R.id.occurred_end_time);
        get_access_time = (TextView) view.findViewById(R.id.get_access_time);
        occurred_start_button = (Button) view.findViewById(R.id.occurred_start_time_button);
        occurred_end_button = (Button) view.findViewById(R.id.occurred_end_time_button);
        get_access_button = (Button) view.findViewById(R.id.get_access_time_button);
        if(event == 2) {
            casetype_spinner.setSelection(getCategory(item.getCasetype()));
            area_spinner.setSelection(getArea(item.getArea()));
            occurred_start_time.setText(item.getTime());
            occurred_end_time.setText(item.getTime());
            get_access_time.setText(item.getTime());
        }else{
            occurred_start_time.setText(Item.getCurrentTime(c));
            occurred_end_time.setText(Item.getCurrentTime(c));
            get_access_time.setText(Item.getCurrentTime(c));
        }
        occurred_start_button.setOnClickListener(this);
        occurred_end_button.setOnClickListener(this);
        get_access_button.setOnClickListener(this);

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

        sceneCondition_spinner = (Spinner) view.findViewById(R.id.sceneCondition_spinner);
        sceneCondition_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, sceneCondition);
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
                showDateTimeDialog();
                occurred_start_time.setText(Item.getCurrentTime(c));
                break;
            case R.id.occurred_end_time_button:
                showDateTimeDialog();
                occurred_end_time.setText(Item.getCurrentTime(c));
                break;
            case R.id.get_access_time_button:
                showDateTimeDialog();
                get_access_time.setText(Item.getCurrentTime(c));
                break;
            default:
                break;
        }
    }


    public void showDateTimeDialog() {
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
