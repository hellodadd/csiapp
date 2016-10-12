package com.android.csiapp.Crime.createscene;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.csiapp.Crime.utils.ClearableEditText;
import com.android.csiapp.Databases.CrimeItem;
import com.android.csiapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateScene_FP6 extends Fragment {

    private Context context = null;
    private CrimeItem mItem;
    private int mEvent;

    private ImageButton mAutoBtn;
    private TextView mOverview;

    public CreateScene_FP6() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_scene_fp6, container, false);
        CreateSceneActivity activity  = (CreateSceneActivity) getActivity();
        mItem = activity.getItem();
        mEvent = activity.getEvent();
        context = getActivity().getApplicationContext();

        initView(view);
        initData();

        return view;
    }

    private void initView(View view){
        mAutoBtn = (ImageButton) view.findViewById(R.id.automatic_generated_button);
        mAutoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auto_generated();
            }
        });
        mOverview = (TextView) view.findViewById(R.id.overview);
    }

    private void initData(){
        mOverview.setText(mItem.getOverview());
    }

    public void saveData(){
        mItem.setOverview(mOverview.getText().toString());
    }

    @Override
    public void onResume(){
        super.onResume();
        initData();
    }

    @Override
    public void onPause(){
        super.onPause();
        saveData();
    }

    private void auto_generated(){
        mOverview.setText("Todo");
        mItem.setOverview(mOverview.getText().toString());
    }
}
