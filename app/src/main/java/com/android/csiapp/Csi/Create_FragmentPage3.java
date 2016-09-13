package com.android.csiapp.Csi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
                startActivity(it);
            }
        });
        return view;
    }
}
