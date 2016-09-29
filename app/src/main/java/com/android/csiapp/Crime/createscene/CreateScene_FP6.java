package com.android.csiapp.Crime.createscene;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.csiapp.Databases.CrimeItem;
import com.android.csiapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateScene_FP6 extends Fragment {

    private Context context = null;
    private CrimeItem mItem;

    public CreateScene_FP6() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_scene_fp6, container, false);
        CreateSceneActivity activity  = (CreateSceneActivity) getActivity();
        mItem = activity.getItem();
        context = getActivity().getApplicationContext();
        return view;
    }
}
