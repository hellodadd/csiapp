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

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateScene_FP8 extends Fragment {

    private Context context = null;
    private CrimeItem item;
    ImageButton Add_witness;
    private ListView listV;
    private ArrayAdapter adapter;

    public CreateScene_FP8() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_scene_fp8, container, false);
        CreateSceneActivity activity  = (CreateSceneActivity) getActivity();
        item = activity.getItem();
        context = getActivity().getApplicationContext();
        Add_witness = (ImageButton) view.findViewById(R.id.add_witness_button);
        Add_witness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(), CreateScene_FP8_AddWitnessActivity.class);
                startActivityForResult(it,0);
            }
        });

        //List<CrimeItem> items_list = new ArrayList<CrimeItem>();
        listV=(ListView) view.findViewById(R.id.listView);
        adapter = new ArrayAdapter(context, R.layout.listview);
        listV.setAdapter(adapter);
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent it = new Intent(getActivity(), CreateScene_FP8_AddWitnessActivity.class);
                startActivityForResult(it,0);
            }
        };
        listV.setOnItemClickListener(itemListener);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 0) {
                // 新增記事資料到資料庫
                listV.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "List", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
