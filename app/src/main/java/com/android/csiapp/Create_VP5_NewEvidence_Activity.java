package com.android.csiapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Create_VP5_NewEvidence_Activity extends AppCompatActivity {
    private Context context = null;
    private Button handprint;
    private Button footprint;
    private Button toolMark;

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_click:
                    msg += "Save";
                    break;
            }

            if (!msg.equals("")) {
                Toast.makeText(Create_VP5_NewEvidence_Activity.this, msg, Toast.LENGTH_SHORT).show();
            }
            finish();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_vp5_new_evidence);

        context = this.getApplicationContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(context.getResources().getString(R.string.title_activity_new_evidence));
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

        handprint = (Button) findViewById(R.id.handprint);
        handprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handprint.setBackground(context.getDrawable(R.drawable.form_radiobutton_checked));
                footprint.setBackground(context.getDrawable(R.drawable.form_radiobutton_nor));
                toolMark.setBackground(context.getDrawable(R.drawable.form_radiobutton_nor));
            }
        });

        footprint = (Button) findViewById(R.id.footprint);
        footprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handprint.setBackground(context.getDrawable(R.drawable.form_radiobutton_nor));
                footprint.setBackground(context.getDrawable(R.drawable.form_radiobutton_checked));
                toolMark.setBackground(context.getDrawable(R.drawable.form_radiobutton_nor));
            }
        });

        toolMark = (Button) findViewById(R.id.toolMark);
        toolMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handprint.setBackground(context.getDrawable(R.drawable.form_radiobutton_nor));
                footprint.setBackground(context.getDrawable(R.drawable.form_radiobutton_nor));
                toolMark.setBackground(context.getDrawable(R.drawable.form_radiobutton_checked));
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_vp5_subactivity, menu);
        return true;
    }
}
