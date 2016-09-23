package com.android.csiapp.Crime.createscene;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.android.csiapp.Databases.CrimeItem;
import com.android.csiapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateScene_FP3 extends Fragment {

    private Context context = null;
    private CrimeItem item;
    private ImageButton add_position;
    private ImageButton position;

    public CreateScene_FP3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_scene_fp3, container, false);
        CreateSceneActivity activity  = (CreateSceneActivity) getActivity();
        item = activity.getItem();
        context = getActivity().getApplicationContext();
        add_position = (ImageButton) view.findViewById(R.id.add_position);
        add_position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(), CreateScene_FP3_PositionInformationActivity.class);
                startActivityForResult(it, 0);
            }
        });
        position = (ImageButton) view.findViewById(R.id.position);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("BaiduMap","onActivityResult");
        if (resultCode == Activity.RESULT_OK && requestCode == 0) {
            String filepath = data.getStringExtra("BaiduMap_ScreenShot");
            Log.d("BaiduMap","onActivityResult, filepath: " + filepath);
            Bitmap Bitmap = BitmapFactory.decodeFile(filepath);
            BitmapDrawable bDrawable = new BitmapDrawable(getActivity().getApplicationContext().getResources(), Bitmap);
            position.setBackground(bDrawable);
            position.setVisibility(View.VISIBLE);
        }
    }
}
