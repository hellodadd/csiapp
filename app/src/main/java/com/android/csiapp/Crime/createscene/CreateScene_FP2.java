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

import com.android.csiapp.Crime.utils.CrimeItemAdapter;
import com.android.csiapp.Crime.utils.LostItemAdapter;
import com.android.csiapp.Crime.utils.RelatedPeoeplAdapter;
import com.android.csiapp.Databases.CrimeItem;
import com.android.csiapp.Databases.CrimeToolItem;
import com.android.csiapp.Databases.LostItem;
import com.android.csiapp.Databases.RelatedPeopleItem;
import com.android.csiapp.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateScene_FP2 extends Fragment {

    private Context context = null;
    private CrimeItem mItem;
    private RelatedPeopleItem mRelatedPeopleItem;
    private LostItem mLostItem;
    private CrimeToolItem mCrimeToolItem;
    private int mEvent;
    private ImageButton mNewPeople;
    private ImageButton mNewItem;
    private ImageButton mNewTool;
    List<RelatedPeopleItem> mPeopleList;
    private ListView mPeople_List;
    private RelatedPeoeplAdapter mPeople_Adapter;
    List<LostItem> mItemList;
    private ListView mItem_List;
    private LostItemAdapter mItem_Adapter;
    List<CrimeToolItem> mToolList;
    private ListView mTool_List;
    private CrimeItemAdapter mTool_Adapter;

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

        initData();
        initView(view);

        return view;
    }

    private void initView(View view){
        mRelatedPeopleItem = new RelatedPeopleItem();
        mNewPeople = (ImageButton) view.findViewById(R.id.add_people);
        mNewPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(), CreateScene_FP2_NewPeopleActivity.class);
                it.putExtra("com.android.csiapp.Databases.RelatedPeopleItem", mRelatedPeopleItem);
                it.putExtra("Event",1);
                startActivityForResult(it,0);
            }
        });

        mLostItem = new LostItem();
        mNewItem = (ImageButton) view.findViewById(R.id.add_item);
        mNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(), CreateScene_FP2_NewItemActivity.class);
                it.putExtra("com.android.csiapp.Databases.LostItem", mLostItem);
                it.putExtra("Event",1);
                startActivityForResult(it,1);
            }
        });

        mCrimeToolItem = new CrimeToolItem();
        mNewTool = (ImageButton) view.findViewById(R.id.add_tool);
        mNewTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(), CreateScene_FP2_NewToolActivity.class);
                it.putExtra("com.android.csiapp.Databases.CrimeToolItem", mCrimeToolItem);
                it.putExtra("Event",1);
                startActivityForResult(it,2);
            }
        });

        mPeopleList = mItem.getReleatedPeople();
        Log.d("Anita","mPeopleList = "+mPeopleList.size());
        mPeople_List=(ListView) view.findViewById(R.id.people_listView);
        mPeople_Adapter = new RelatedPeoeplAdapter(context, mPeopleList);
        mPeople_List.setAdapter(mPeople_Adapter);
        AdapterView.OnItemClickListener itemListener1 = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RelatedPeopleItem relatedPeopleItem = (RelatedPeopleItem) mPeople_Adapter.getItem(position);
                Intent it = new Intent(getActivity(), CreateScene_FP2_NewPeopleActivity.class);
                it.putExtra("com.android.csiapp.Databases.RelatedPeopleItem", relatedPeopleItem);
                it.putExtra("Event",2);
                it.putExtra("Position", position);
                startActivityForResult(it,0);
            }
        };
        mPeople_List.setOnItemClickListener(itemListener1);

        mItemList = mItem.getLostItem();
        Log.d("Anita","mItemList = "+mItemList.size());
        mItem_List=(ListView) view.findViewById(R.id.item_listView);
        mItem_Adapter = new LostItemAdapter(context, mItemList);
        mItem_List.setAdapter(mItem_Adapter);
        AdapterView.OnItemClickListener itemListener2 = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LostItem lostItem = (LostItem) mItem_Adapter.getItem(position);
                Intent it = new Intent(getActivity(), CreateScene_FP2_NewItemActivity.class);
                it.putExtra("com.android.csiapp.Databases.LostItem", lostItem);
                it.putExtra("Event",2);
                it.putExtra("Position", position);
                startActivityForResult(it,1);
            }
        };
        mItem_List.setOnItemClickListener(itemListener2);

        mToolList = mItem.getCrimeTool();
        Log.d("Anita","mToolList = "+mToolList.size());
        mTool_List=(ListView) view.findViewById(R.id.tool_listView);
        mTool_Adapter = new CrimeItemAdapter(context, mToolList);
        mTool_List.setAdapter(mTool_Adapter);
        AdapterView.OnItemClickListener itemListener3 = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CrimeToolItem crimeToolItem = (CrimeToolItem) mTool_Adapter.getItem(position);
                Intent it = new Intent(getActivity(), CreateScene_FP2_NewToolActivity.class);
                it.putExtra("com.android.csiapp.Databases.CrimeToolItem", crimeToolItem);
                it.putExtra("Event",2);
                it.putExtra("Position", position);
                startActivityForResult(it,2);
            }
        };
        mTool_List.setOnItemClickListener(itemListener3);
    }

    public void initData(){
        mPeopleList = mItem.getReleatedPeople();
        mItemList = mItem.getLostItem();
        mToolList = mItem.getCrimeTool();
    }

    public void saveData(){
        mItem.setReleatedPeople(mPeopleList);
        mItem.setLostItem(mItemList);
        mItem.setCrimeTool(mToolList);
    }

    @Override
    public void onResume(){
        super.onResume();
        initData();
        mPeople_Adapter.notifyDataSetChanged();
        mItem_Adapter.notifyDataSetChanged();
        mTool_Adapter.notifyDataSetChanged();
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
            if (requestCode == 0) {
                // 新增記事資料到資料庫
                RelatedPeopleItem relatedPeopleItem = (RelatedPeopleItem) data.getSerializableExtra("com.android.csiapp.Databases.RelatedPeopleItem");
                int event = (int) data.getIntExtra("Event", 1);
                if(event == 1) {
                    Log.d("Anita","Before mPeopleList = "+mPeopleList.size());
                    mPeopleList.add(relatedPeopleItem);
                    Log.d("Anita","After mPeopleList = "+mPeopleList.size());
                }else{
                    int position = (int) data.getIntExtra("Position",0);
                    mPeopleList.set(position, relatedPeopleItem);
                }
                mPeople_Adapter.notifyDataSetChanged();
            }else if(requestCode == 1){
                // 新增記事資料到資料庫
                LostItem lostItem = (LostItem) data.getSerializableExtra("com.android.csiapp.Databases.LostItem");
                int event = (int) data.getIntExtra("Event", 1);
                if(event == 1) {
                    Log.d("Anita","Before mItemList = "+mItemList.size());
                    mItemList.add(lostItem);
                    Log.d("Anita","After mItemList = "+mItemList.size());
                }else{
                    int position = (int) data.getIntExtra("Position",0);
                    mItemList.set(position, lostItem);
                }
                mItem_Adapter.notifyDataSetChanged();
            }else{
                // 新增記事資料到資料庫
                CrimeToolItem crimeToolItem = (CrimeToolItem) data.getSerializableExtra("com.android.csiapp.Databases.CrimeToolItem");
                int event = (int) data.getIntExtra("Event", 1);
                if(event == 1) {
                    Log.d("Anita","Before mToolList = "+mToolList.size());
                    mToolList.add(crimeToolItem);
                    Log.d("Anita","After mToolList = "+mToolList.size());
                }else{
                    int position = (int) data.getIntExtra("Position",0);
                    mToolList.set(position, crimeToolItem);
                }
                mTool_Adapter.notifyDataSetChanged();
            }
        }
    }
}
