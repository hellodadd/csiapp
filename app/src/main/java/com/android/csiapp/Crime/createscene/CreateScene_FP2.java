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
public class CreateScene_FP2 extends Fragment {

    private Context context = null;
    private CrimeItem mItem;
    private int mEvent;
    private ImageButton mNewPeople;
    private ImageButton mNewItem;
    private ImageButton mNewTool;
    List<CrimeItem> mPeopleList;
    private ListView mPeople_List;
    private Adapter mPeople_Adapter;
    List<CrimeItem> mItemList;
    private ListView mItem_List;
    private Adapter mItem_Adapter;
    List<CrimeItem> mToolList;
    private ListView mTool_List;
    private Adapter mTool_Adapter;

    public CreateScene_FP2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_scene_fp2, container, false);
        CreateSceneActivity activity  = (CreateSceneActivity) getActivity();
        mItem = activity.getItem();
        mEvent = activity.getEvent();
        context = getActivity().getApplicationContext();
        mNewPeople = (ImageButton) view.findViewById(R.id.add_people);
        mNewPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent it = new Intent(getActivity(), CreateScene_FP2_NewPeopleActivity.class);
            it.putExtra("com.android.csiapp.CrimeItem", mItem);
            it.putExtra("Event",1);
            startActivityForResult(it,0);
            }
        });
        mNewItem = (ImageButton) view.findViewById(R.id.add_item);
        mNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent it = new Intent(getActivity(), CreateScene_FP2_NewItemActivity.class);
            it.putExtra("com.android.csiapp.CrimeItem", mItem);
            it.putExtra("Event",1);
            startActivityForResult(it,1);
            }
        });
        mNewTool = (ImageButton) view.findViewById(R.id.add_tool);
        mNewTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent it = new Intent(getActivity(), CreateScene_FP2_NewToolActivity.class);
            it.putExtra("com.android.csiapp.CrimeItem", mItem);
            it.putExtra("Event",1);
            startActivityForResult(it,2);
            }
        });

        mPeopleList = new ArrayList<CrimeItem>();
        mPeople_List=(ListView) view.findViewById(R.id.people_listView);
        mPeople_Adapter = new Adapter(context, mPeopleList,1);
        mPeople_List.setAdapter(mPeople_Adapter);
        AdapterView.OnItemClickListener itemListener1 = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent it = new Intent(getActivity(), CreateScene_FP2_NewPeopleActivity.class);
                it.putExtra("com.android.csiapp.CrimeItem", mItem);
                it.putExtra("Event",2);
                startActivityForResult(it,0);
            }
        };
        mPeople_List.setOnItemClickListener(itemListener1);

        mItemList = new ArrayList<CrimeItem>();
        mItem_List=(ListView) view.findViewById(R.id.item_listView);
        mItem_Adapter = new Adapter(context, mItemList,2);
        mItem_List.setAdapter(mItem_Adapter);
        AdapterView.OnItemClickListener itemListener2 = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent it = new Intent(getActivity(), CreateScene_FP2_NewItemActivity.class);
                it.putExtra("com.android.csiapp.CrimeItem", mItem);
                it.putExtra("Event",2);
                startActivityForResult(it,1);
            }
        };
        mItem_List.setOnItemClickListener(itemListener2);

        mToolList = new ArrayList<CrimeItem>();
        mTool_List=(ListView) view.findViewById(R.id.tool_listView);
        mTool_Adapter = new Adapter(context, mToolList,3);
        mTool_List.setAdapter(mTool_Adapter);
        AdapterView.OnItemClickListener itemListener3 = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent it = new Intent(getActivity(), CreateScene_FP2_NewToolActivity.class);
                it.putExtra("com.android.csiapp.CrimeItem", mItem);
                it.putExtra("Event",2);
                startActivityForResult(it,2);
            }
        };
        mTool_List.setOnItemClickListener(itemListener3);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            mItem = (CrimeItem) data.getSerializableExtra("com.android.csiapp.CrimeItem");
            if (requestCode == 0) {
                // 新增記事資料到資料庫
                mPeopleList.add(mItem);
                mPeople_List.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "PeopleList", Toast.LENGTH_SHORT).show();
            }else if(requestCode == 1){
                // 新增記事資料到資料庫
                mItemList.add(mItem);
                mItem_List.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "ItemList", Toast.LENGTH_SHORT).show();
            }else{
                // 新增記事資料到資料庫
                mToolList.add(mItem);
                mTool_List.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "ToolList", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
