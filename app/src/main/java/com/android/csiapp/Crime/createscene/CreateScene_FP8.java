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

        initData();
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
                it.putExtra("com.android.csiapp.Databases.WitnessItem", mWitnessItem);
                it.putExtra("Event",1);
                startActivityForResult(it,0);
            }
        });

        mWitness_list=(ListView) view.findViewById(R.id.listView);
        mWitness_adapter = new WitnessListAdapter(context, mWitnessList);
        mWitness_list.setAdapter(mWitness_adapter);
        setListViewHeightBasedOnChildren(mWitness_list);
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
    }

    private void initData(){
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            WitnessItem witenssItem = (WitnessItem) data.getSerializableExtra("com.android.csiapp.Databases.WitnessItem");
            int event = (int) data.getIntExtra("Event", 1);
            if (requestCode == 0) {
                // 新增記事資料到資料庫
                if(event == 1) {
                    mWitnessList.add(witenssItem);
                }else{
                    int position = (int) data.getIntExtra("Position",0);
                    mWitnessList.set(position, witenssItem);
                }
                setListViewHeightBasedOnChildren(mWitness_list);
                mWitness_adapter.notifyDataSetChanged();
            }
        }
    }

    private void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()获取ListView对应的Adapter
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }
}
