package com.android.csiapp.Crime.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.android.csiapp.Crime.createscene.CreateScene_FP3_PositionInformationActivity;
import com.android.csiapp.R;

import java.io.File;

public class Priview_photo_Activity extends AppCompatActivity {

    private ImageView priview;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priview_photo);

        String path = getIntent().getStringExtra("Path");

        priview = (ImageView) findViewById(R.id.priview);

        if(path!=null){
            Bitmap bp = PhotoAdapter.loadBitmapFromFile(new File(path));
            Matrix matrix=new Matrix();
            matrix.postScale(1.0f,1.0f);
            Bitmap newBitmap=Bitmap.createBitmap(bp, 0, 0, bp.getWidth(), bp.getHeight(), matrix, true);
            priview.setImageBitmap(newBitmap);
        }

        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent result = getIntent();
                setResult(Activity.RESULT_OK, result);
                finish();
            }
        });
    }
}
