package com.android.csiapp.Crime.createscene;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.android.csiapp.Crime.utils.WitnessListAdapter;
import com.android.csiapp.Databases.CrimeItem;
import com.android.csiapp.Databases.WitnessItem;
import com.android.csiapp.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateScene_FP8 extends Fragment {

    private Context context = null;
    private CrimeItem mItem;
    private WitnessItem mWitnessItem;
    private int mEvent;
    ImageButton mAddWitness;
    List<WitnessItem> mWitnessList;
    private ListView mWitness_list;
    private WitnessListAdapter mWitness_adapter;

    public CreateScene_FP8() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_scene_fp8, container, false);
        CreateSceneActivity activity  = (CreateSceneActivity) getActivity();
        mItem = activity.getItem();
        mEvent = activity.getEvent();
        context = getActivity().getApplicationContext();

        initView(view);

        return view;
    }

    private void initView(View view){
        mWitnessItem = new WitnessItem();
        mAddWitness = (ImageButton) view.findViewById(R.id.add_witness_button);
        mAddWitness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(), CreateScene_FP8_AddWitnessActivity.class);
                Bundle bundle = new Bundle();
                it.putExtra("com.android.csiapp.Databases.WitnessItem", mWitnessItem);
                it.putExtra("Event",1);
                startActivityForResult(it,0);
            }
        });

        mWitnessList = mItem.getWitness();
        Log.d("Anita","mWitnessList = "+mWitnessList.size());
        mWitness_list=(ListView) view.findViewById(R.id.listView);
        mWitness_adapter = new WitnessListAdapter(context, mWitnessList);
        mWitness_list.setAdapter(mWitness_adapter);
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                WitnessItem witenssItem = (WitnessItem) mWitness_adapter.getItem(position);
                Intent it = new Intent(getActivity(), CreateScene_FP8_AddWitnessActivity.class);
                it.putExtra("com.android.csiapp.Databases.WitnessItem", witenssItem);
                it.putExtra("Event",2);
                startActivityForResult(it,0);
            }
        };
        mWitness_list.setOnItemClickListener(itemListener);
        setListViewHeight(mWitness_list);
    }

    public void initData(){
        mWitnessList = mItem.getWitness();
    }

    public void saveData(){
        mItem.setWitness(mWitnessList);
    }

    @Override
    public void onResume(){
        super.onResume();
        initData();
        mWitness_adapter.notifyDataSetChanged();
    }

    @Override
    public void onPause(){
        super.onPause();
        saveData();
    }

    private void setListViewHeight(ListView lv) {
        ListAdapter la = lv.getAdapter();
        if(null == la) {
            return;
        }
        // calculate height of all items.
        int h = 0;
        final int cnt = la.getCount();
        for(int i=0; i<cnt; i++) {
            View item = la.getView(i, null, lv);
            item.measure(0, 0);
            h += item.getMeasuredHeight();
        }
        // reset ListView height
        ViewGroup.LayoutParams lp = lv.getLayoutParams();
        lp.height = h + (lv.getDividerHeight() * (cnt - 1));
        lv.setLayoutParams(lp);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            WitnessItem witenssItem = (WitnessItem) data.getSerializableExtra("com.android.csiapp.Databases.WitnessItem");
            int event = (int) data.getIntExtra("Event", 1);
            if (requestCode == 0) {
                // 新增記事資料到資料庫
                if(event == 1) {
                    Log.d("Anita","Before mWitnessList = "+mWitnessList.size());
                    mWitnessList.add(witenssItem);
                    Log.d("Anita","After mWitnessList = "+mWitnessList.size());
                }else{
                    int position = (int) data.getIntExtra("Position",0);
                    mWitnessList.set(position, witenssItem);
                }
                mWitness_adapter.notifyDataSetChanged();
            }
        }
    }
}
