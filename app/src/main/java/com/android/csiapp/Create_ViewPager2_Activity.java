package com.android.csiapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Create_ViewPager2_Activity extends Activity {

    private Context context = null;
    private Button newPeople;
    private Button newItem;
    private Button newTool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_viewpager2);

        context = this.getApplicationContext();
        newPeople = (Button) this.findViewById(R.id.Create2_add_people);
        newPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Create_ViewPager2_Activity.this, Create_VP2_NewPeople_Activity.class);
                startActivity(it);
            }
        });
        newItem = (Button) this.findViewById(R.id.Create2_add_item);
        newItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Create_ViewPager2_Activity.this, Create_VP2_NewItem_Activity.class);
                startActivity(it);
            }
        });
        newTool = (Button) this.findViewById(R.id.Create2_add_tool);
        newTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Create_ViewPager2_Activity.this, Create_VP2_NewTool_Activity.class);
                startActivity(it);
            }
        });
    }
}
