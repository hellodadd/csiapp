package com.android.csiapp.Crime.createscene;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.android.csiapp.Databases.CrimeItem;
import com.android.csiapp.Databases.EvidenceItem;
import com.android.csiapp.R;

import java.io.File;
import java.io.FileInputStream;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateScene_FP5 extends Fragment {
    private Context context = null;
    private CrimeItem mItem;
    private EvidenceItem mEvidenceItem;
    private ImageButton mAdd_Scene_Evidence;
    private ImageButton mScene_Evidence;

    public CreateScene_FP5() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_scene_fp5, container, false);
        CreateSceneActivity activity  = (CreateSceneActivity) getActivity();
        mItem = activity.getItem();
        context = getActivity().getApplicationContext();

        initView(view);

        return view;
    }

    private void initView(View view){
        mEvidenceItem = new EvidenceItem();
        mAdd_Scene_Evidence = (ImageButton) view.findViewById(R.id.add_scene_evidence);
        mAdd_Scene_Evidence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(), CreateScene_FP5_NewEvidenceActivity.class);
                it.putExtra("com.android.csiapp.Databases.EvidenceItem", mEvidenceItem);
                it.putExtra("Event",1);
                startActivityForResult(it, 0);
            }
        });
        mScene_Evidence = (ImageButton) view.findViewById(R.id.Scene_evidence);
        mScene_Evidence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(), CreateScene_FP5_NewEvidenceActivity.class);
                it.putExtra("com.android.csiapp.Databases.EvidenceItem", mEvidenceItem);
                it.putExtra("Event",2);
                startActivityForResult(it, 0);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("Photo","onActivityResult");
        if (resultCode == Activity.RESULT_OK && requestCode == 0) {
            EvidenceItem evidenceItem = (EvidenceItem) data.getSerializableExtra("com.android.csiapp.Databases.EvidenceItem");
            int event = (int) data.getIntExtra("Event", 1);
            mEvidenceItem = evidenceItem;
            String filepath = mEvidenceItem.getPhotoPath();
            if(filepath!=null){
                Log.d("Photo","onActivityResult, filepath: " + filepath);
                Bitmap Bitmap = loadBitmapFromFile(new File(filepath));
                BitmapDrawable bDrawable = new BitmapDrawable(context.getResources(), Bitmap);
                mScene_Evidence.setBackground(bDrawable);
                mScene_Evidence.setVisibility(View.VISIBLE);
            }
        }
    }

    private static Bitmap loadBitmapFromFile(File f) {
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
}
