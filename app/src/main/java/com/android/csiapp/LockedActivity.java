package com.android.csiapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LockedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locked);

        Button mUserSignInButton = (Button) findViewById(R.id.user_sign_in_button);
        mUserSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }

    private void attemptLogin() {
        //Toast.makeText(LockedActivity.this, "你的裝置"+System.getProperty("ro.product.device"), Toast.LENGTH_SHORT).show();
        //if("Q5_LY".equalsIgnoreCase(System.getProperty("ro.product.device"))) {
            Intent it = new Intent(LockedActivity.this, MainActivity.class);
            startActivity(it);
            finish();
        //}else{
        //    Toast.makeText(LockedActivity.this, "裝置無法登入", Toast.LENGTH_SHORT).show();
        //}
    }
}
