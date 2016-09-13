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
public class Create_FragmentPage2 extends Fragment {

    private Item item;
    private Button newPeople;
    private Button newItem;
    private Button newTool;

    public Create_FragmentPage2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_fragmentpage2, container, false);
        CreateSceneActivity activity  = (CreateSceneActivity) getActivity();
        item = activity.getItem();
        newPeople = (Button) view.findViewById(R.id.Create2_add_people);
        newPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(), Create_FP2_NewPeople_Activity.class);
                startActivity(it);
            }
        });
        newItem = (Button) view.findViewById(R.id.Create2_add_item);
        newItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(), Create_FP2_NewItem_Activity.class);
                startActivity(it);
            }
        });
        newTool = (Button) view.findViewById(R.id.Create2_add_tool);
        newTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(), Create_FP2_NewTool_Activity.class);
                startActivity(it);
            }
        });
        return view;
    }
}
