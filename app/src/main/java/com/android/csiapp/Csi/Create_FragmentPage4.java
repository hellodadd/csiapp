package com.android.csiapp.Csi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.android.csiapp.Item;
import com.android.csiapp.R;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class Create_FragmentPage4 extends Fragment {
    private Context context = null;
    private Uri LocalFileUri = null;
    private Item item;
    private ImageButton add_position;
    private ImageButton add_like;
    private ImageButton add_important;
    private ImageButton position;
    private ImageButton like;
    private ImageButton important;

    public static final int PHOTO_TYPE_POSITION = 1;
    public static final int PHOTO_TYPE_LIKE = 2;
    public static final int PHOTO_TYPE_IMPORTANT = 3;

    public Create_FragmentPage4() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_fragmentpage4, container, false);
        CreateSceneActivity activity  = (CreateSceneActivity) getActivity();
        item = activity.getItem();
        context = getActivity().getApplicationContext();

        add_position = (ImageButton) view.findViewById(R.id.add_position_photo_imageButton);
        add_position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalFileUri = Uri.fromFile(getOutputMediaFile(PHOTO_TYPE_POSITION));
                takePhoto(LocalFileUri, PHOTO_TYPE_POSITION);
            }
        });
        position = (ImageButton) view.findViewById(R.id.Position_photo_imageButton);
        add_like = (ImageButton) view.findViewById(R.id.add_like_photo_imageButton);
        add_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalFileUri = Uri.fromFile(getOutputMediaFile(PHOTO_TYPE_LIKE));
                takePhoto(LocalFileUri, PHOTO_TYPE_LIKE);
            }
        });
        like = (ImageButton) view.findViewById(R.id.Like_photo_imageButton);
        add_important = (ImageButton) view.findViewById(R.id.add_important_photo_imageButton);
        add_important.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalFileUri = Uri.fromFile(getOutputMediaFile(PHOTO_TYPE_IMPORTANT));
                takePhoto(LocalFileUri, PHOTO_TYPE_IMPORTANT);
            }
        });
        important = (ImageButton) view.findViewById(R.id.Important_photo_imageButton);
        return view;
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
        if (type == PHOTO_TYPE_POSITION){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "POSITION_"+ timeStamp + ".jpg");
        } else if(type == PHOTO_TYPE_LIKE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "LIKE_"+ timeStamp + ".jpg");
        } else if (type == PHOTO_TYPE_IMPORTANT) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMPORTANT_"+ timeStamp + ".jpg");
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
        if (path != null && position != null && like != null && important != null) {
            Bitmap Bitmap = loadBitmapFromFile(new File(path));

            if (requestCode == PHOTO_TYPE_POSITION) {
                Log.d("Camera", "Set image to PHOTO_TYPE_POSITION");
                BitmapDrawable bDrawable = new BitmapDrawable(context.getResources(), Bitmap);
                position.setBackground(bDrawable);
                position.setVisibility(View.VISIBLE);
            } else if (requestCode == PHOTO_TYPE_LIKE) {
                Log.d("Camera", "Set image to PHOTO_TYPE_LIKE");
                BitmapDrawable bDrawable = new BitmapDrawable(context.getResources(), Bitmap);
                like.setBackground(bDrawable);
                like.setVisibility(View.VISIBLE);
            } else if (requestCode == PHOTO_TYPE_IMPORTANT) {
                Log.d("Camera", "Set image to PHOTO_TYPE_IMPORTANT");
                BitmapDrawable bDrawable = new BitmapDrawable(context.getResources(), Bitmap);
                important.setBackground(bDrawable);
                important.setVisibility(View.VISIBLE);
            }
        }
    }
}
