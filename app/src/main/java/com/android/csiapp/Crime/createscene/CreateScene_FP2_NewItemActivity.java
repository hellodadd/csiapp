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
import android.widget.Toast;

import com.android.csiapp.ClearableEditText;
import com.android.csiapp.Databases.CrimeItem;
import com.android.csiapp.R;

public class CreateScene_FP2_NewItemActivity extends AppCompatActivity {

    private Context context = null;
    private CrimeItem item;
    private int event;

    private ClearableEditText mName;
    private ClearableEditText mBrand;
    private ClearableEditText mAmount;
    private ClearableEditText mValue;
    private ClearableEditText mFeature;

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_click:
                    msg += "Save";
                    saveMessage();
                    Intent result = getIntent();
                    result.putExtra("com.android.csiapp.CrimeItem", item);
                    setResult(Activity.RESULT_OK, result);
                    break;
            }

            if (!msg.equals("")) {
                Toast.makeText(CreateScene_FP2_NewItemActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            finish();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_scene_fp2_new_item);

        context = this.getApplicationContext();
        item = (CrimeItem) getIntent().getSerializableExtra("com.android.csiapp.CrimeItem");
        event = (int) getIntent().getIntExtra("Event", 1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(context.getResources().getString(R.string.title_activity_lostitem));
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

        mName = (ClearableEditText) findViewById(R.id.item_name_editView);
        mBrand = (ClearableEditText) findViewById(R.id.brand_model_editView);
        mAmount = (ClearableEditText) findViewById(R.id.amount_editView);
        mValue = (ClearableEditText) findViewById(R.id.value_editView);
        mFeature = (ClearableEditText) findViewById(R.id.feature_description_editView);

        if(event == 2) {
            getMessage();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_fp2_subactivity, menu);
        return true;
    }

    public void getMessage(){
        mName.setText(item.getItemName());
        mBrand.setText(item.getItemBrand());
        mAmount.setText(item.getItemAmount());
        mValue.setText(item.getItemValue());
        mFeature.setText(item.getItemFeature());
    }

    public void saveMessage(){
        item.setItemName(mName.getText());
        item.setItemBrand(mBrand.getText());
        item.setItemAmount(mAmount.getText());
        item.setItemVlaue(mValue.getText());
        item.setItemFeatue(mFeature.getText());
    }
}
