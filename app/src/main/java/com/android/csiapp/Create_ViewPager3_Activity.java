package com.android.csiapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Create_ViewPager3_Activity extends Activity {

    private Context context = null;
    private Button position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_viewpager3);

        context = this.getApplicationContext();

        position = (Button) findViewById(R.id.position);
        position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Create_ViewPager3_Activity.this, Create_VP3_Position_Information_Activity.class);
                startActivity(it);
            }
        });
    }
}
