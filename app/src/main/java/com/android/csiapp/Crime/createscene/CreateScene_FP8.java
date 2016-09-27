package com.android.csiapp.Crime.createscene;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.android.csiapp.Databases.CrimeItem;
import com.android.csiapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateScene_FP8 extends Fragment {

    private Context context = null;
    private CrimeItem item;
    private int event;
    ImageButton Add_witness;
    List<CrimeItem> witnessList;
    private ListView witness_list;
    private Adapter witness_adapter;

    public CreateScene_FP8() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_scene_fp8, container, false);
        CreateSceneActivity activity  = (CreateSceneActivity) getActivity();
        item = activity.getItem();
        event = activity.getEvent();
        context = getActivity().getApplicationContext();
        Add_witness = (ImageButton) view.findViewById(R.id.add_witness_button);
        Add_witness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(), CreateScene_FP8_AddWitnessActivity.class);
                it.putExtra("com.android.csiapp.CrimeItem", item);
                it.putExtra("Event",1);
                startActivityForResult(it,0);
            }
        });

        witnessList = new ArrayList<CrimeItem>();
        witness_list=(ListView) view.findViewById(R.id.listView);
        witness_adapter = new Adapter(context, witnessList,4);
        witness_list.setAdapter(witness_adapter);
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent it = new Intent(getActivity(), CreateScene_FP8_AddWitnessActivity.class);
                it.putExtra("com.android.csiapp.CrimeItem", item);
                it.putExtra("Event",2);
                startActivityForResult(it,0);
            }
        };
        witness_list.setOnItemClickListener(itemListener);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            item = (CrimeItem) data.getSerializableExtra("com.android.csiapp.CrimeItem");
            if (requestCode == 0) {
                // 新增記事資料到資料庫
                witnessList.add(item);
                witness_list.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "List", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
