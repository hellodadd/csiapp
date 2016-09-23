package com.android.csiapp.Crime.createscene;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.csiapp.R;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateScene_FP5_NewEvidenceActivity extends AppCompatActivity {
    private Context context = null;
    private Uri LocalFileUri = null;
    private ImageView new_evidence;
    private Button handprint;
    private Button footprint;
    private Button toolMark;


    public static final int PHOTO_TYPE_NEW_EVIDENCE = 1;

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_camera:
                    msg += "Camera";
                    LocalFileUri = Uri.fromFile(getOutputMediaFile(PHOTO_TYPE_NEW_EVIDENCE));
                    takePhoto(LocalFileUri, PHOTO_TYPE_NEW_EVIDENCE);
                    break;
                case R.id.action_click:
                    msg += "Save";
                    Intent result = getIntent().putExtra("photo_uri", LocalFileUri.getPath());
                    setResult(Activity.RESULT_OK, result);
                    finish();
                    break;
            }

            if (!msg.equals("")) {
                Toast.makeText(CreateScene_FP5_NewEvidenceActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            //finish();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_scene_fp5_new_evidence);

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

        new_evidence = (ImageView) findViewById(R.id.new_evidence);

        handprint = (Button) findViewById(R.id.handprint);
        handprint.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                handprint.setBackground(context.getDrawable(R.drawable.form_radiobutton_checked));
                footprint.setBackground(context.getDrawable(R.drawable.form_radiobutton_nor));
                toolMark.setBackground(context.getDrawable(R.drawable.form_radiobutton_nor));
            }
        });

        footprint = (Button) findViewById(R.id.footprint);
        footprint.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                handprint.setBackground(context.getDrawable(R.drawable.form_radiobutton_nor));
                footprint.setBackground(context.getDrawable(R.drawable.form_radiobutton_checked));
                toolMark.setBackground(context.getDrawable(R.drawable.form_radiobutton_nor));
            }
        });

        toolMark = (Button) findViewById(R.id.toolMark);
        toolMark.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                handprint.setBackground(context.getDrawable(R.drawable.form_radiobutton_nor));
                footprint.setBackground(context.getDrawable(R.drawable.form_radiobutton_nor));
                toolMark.setBackground(context.getDrawable(R.drawable.form_radiobutton_checked));
            }
        });


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_fp5_subactivity, menu);
        return true;
    }

    public void takePhoto(Uri LocalFileUri, int PHOTO_TYPE) {
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        it.putExtra(MediaStore.EXTRA_OUTPUT, LocalFileUri);
        startActivityForResult(it, PHOTO_TYPE);
    }

    public static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Report");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == PHOTO_TYPE_NEW_EVIDENCE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "NEW_EVIDENCE_"+ timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }

    public static Bitmap loadBitmapFromFile(File f) {
        Bitmap b = null;
        BitmapFactory.Options option = new BitmapFactory.Options();
        // Bitmap sampling factor, size = (Original Size)/(inSampleSize)
        option.inSampleSize = 4;

        try {
            FileInputStream fis = new FileInputStream(f);
            b = BitmapFactory.decodeStream(fis, null, option);
            fis.close();

            // Rotate Bitmap by 90 degree
            Matrix matrix = new Matrix();
            matrix.setRotate(0, (float)b.getWidth()/2, (float)b.getHeight()/2);
            Bitmap resultImage = Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), matrix, true);

            return resultImage;
        } catch(Exception e) {
            return null;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String path = LocalFileUri.getPath();
        if (path != null) {
            Log.d("Camera", "Set image to PHOTO_TYPE_NEW_EVIDENCE");
            Bitmap Bitmap = loadBitmapFromFile(new File(path));
            BitmapDrawable bDrawable = new BitmapDrawable(context.getResources(), Bitmap);
            new_evidence.setBackground(bDrawable);
            new_evidence.setVisibility(View.VISIBLE);
        }
    }
}
