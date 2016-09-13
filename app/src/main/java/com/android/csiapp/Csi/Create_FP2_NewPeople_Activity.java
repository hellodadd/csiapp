package com.android.csiapp.Csi;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.csiapp.R;

public class Create_FP2_NewPeople_Activity extends AppCompatActivity {

    private Context context = null;
    private Button informant;
    private Button victim;
    private Button man;
    private Button woman;

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_click:
                    msg += "Save";
                    break;
            }

            if(!msg.equals("")) {
                Toast.makeText(Create_FP2_NewPeople_Activity.this, msg, Toast.LENGTH_SHORT).show();
            }
            finish();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_fp2_new_people);

        context = this.getApplicationContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(context.getResources().getString(R.string.title_activity_peopleinformation));
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

        informant = (Button) findViewById(R.id.informant);
        informant.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                informant.setBackground(context.getDrawable(R.drawable.form_radiobutton_checked));
                victim.setBackground(context.getDrawable(R.drawable.form_radiobutton_nor));
            }
        });

        victim = (Button) findViewById(R.id.victim);
        victim.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                informant.setBackground(context.getDrawable(R.drawable.form_radiobutton_nor));
                victim.setBackground(context.getDrawable(R.drawable.form_radiobutton_checked));
            }
        });

        man = (Button) findViewById(R.id.man);
        man.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                man.setBackground(context.getDrawable(R.drawable.form_radiobutton_checked));
                woman.setBackground(context.getDrawable(R.drawable.form_radiobutton_nor));
            }
        });

        woman = (Button) findViewById(R.id.woman);
        woman.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                man.setBackground(context.getDrawable(R.drawable.form_radiobutton_nor));
                woman.setBackground(context.getDrawable(R.drawable.form_radiobutton_checked));
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_fp2_subactivity, menu);
        return true;
    }
}
