package com.android.csiapp.Csi;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.csiapp.Item;
import com.android.csiapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Create_FragmentPage6 extends Fragment {

    private Context context = null;
    private Item item;

    public Create_FragmentPage6() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_fragmentpage6, container, false);
        CreateSceneActivity activity  = (CreateSceneActivity) getActivity();
        item = activity.getItem();
        context = getActivity().getApplicationContext();
        return view;
    }
}
