package com.android.csiapp.Crime.createscene;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.csiapp.R;

import java.util.ArrayList;
import java.util.Arrays;

public class CreateScene_FP2_NewToolActivity extends AppCompatActivity {

    private Context context = null;

    private Spinner tool_category_spinner;
    private ArrayList<String> tool_category = new ArrayList<String>();
    private ArrayAdapter<String> tool_category_adapter;

    private Spinner tool_source_spinner;
    private ArrayList<String> tool_source = new ArrayList<String>();
    private ArrayAdapter<String> tool_source_adapter;

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_click:
                    msg += "Save";
                    Intent result = getIntent();
                    setResult(Activity.RESULT_OK, result);
                    break;
            }

            if(!msg.equals("")) {
                Toast.makeText(CreateScene_FP2_NewToolActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            finish();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_scene_fp2_new_tool);

        context = this.getApplicationContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(context.getResources().getString(R.string.title_activity_crimetool));
        toolbar.setTitleTextColor(context.getResources().getColor(R.color.titleBar));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.btn_back_mini);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //What to do on back clicked
            }
        });
        toolbar.setOnMenuItemClickListener(onMenuItemClick);

        tool_category = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.tool_category)));
        tool_category_spinner = (Spinner) findViewById(R.id.tool_category_spinner);
        tool_category_adapter = new ArrayAdapter<String>(context, R.layout.spinnerview, tool_category);
        tool_category_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tool_category_spinner.setAdapter(tool_category_adapter);
        tool_category_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                //item.setCasetype(tool_category.get(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        tool_source = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.tool_source)));
        tool_source_spinner = (Spinner) findViewById(R.id.tool_source_spinner);
        tool_source_adapter = new ArrayAdapter<String>(context, R.layout.spinnerview, tool_source);
        tool_source_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tool_source_spinner.setAdapter(tool_source_adapter);
        tool_source_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                //item.setCasetype(tool_category.get(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_fp2_subactivity, menu);
        return true;
    }
}
