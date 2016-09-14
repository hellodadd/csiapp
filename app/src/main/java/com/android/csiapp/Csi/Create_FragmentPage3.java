package com.android.csiapp.Csi;

import android.app.Activity;
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
import android.widget.Button;

import com.android.csiapp.Item;
import com.android.csiapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Create_FragmentPage3 extends Fragment {

    private Item item;
    private Button position;

    public Create_FragmentPage3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_fragmentpage3, container, false);
        CreateSceneActivity activity  = (CreateSceneActivity) getActivity();
        item = activity.getItem();
        position = (Button) view.findViewById(R.id.position);
        position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(), Create_FP3_NewPosition_Activity.class);
                startActivityForResult(it, 0);
            }
        });
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
        }
    }
}
