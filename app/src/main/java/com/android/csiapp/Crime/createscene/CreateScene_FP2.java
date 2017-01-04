package com.android.csiapp.Crime.createscene;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.android.csiapp.Crime.utils.adapter.CrimeItemAdapter;
import com.android.csiapp.Crime.utils.adapter.LostItemAdapter;
import com.android.csiapp.Crime.utils.adapter.RelatedPeoeplAdapter;
import com.android.csiapp.Databases.CrimeItem;
import com.android.csiapp.Databases.CrimeToolItem;
import com.android.csiapp.Databases.CrimeToolProvider;
import com.android.csiapp.Databases.LostItem;
import com.android.csiapp.Databases.LostProvider;
import com.android.csiapp.Databases.RelatedPeopleItem;
import com.android.csiapp.Databases.RelatedPeopleProvider;
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

    private final int EVENT_NEW_PEOPLE = 0;
    private final int EVENT_NEW_ITEM = 1;
    private final int EVENT_NEW_TOOL = 2;

    private ImageButton mNewPeople, mNewItem, mNewTool;
    private ListView mPeople_List, mItem_List, mTool_List;

    private List<RelatedPeopleItem> mPeopleList;
    private RelatedPeoeplAdapter mPeople_Adapter;

    private List<LostItem> mItemList;
    private LostItemAdapter mItem_Adapter;

    private List<CrimeToolItem> mToolList;
    private CrimeItemAdapter mTool_Adapter;

    private final int EVENT_PEOPLE_DELETE = 100;
    private final int EVENT_ITEM_DELETE = 101;
    private final int EVENT_TOOL_DELETE = 102;

    private RelatedPeopleProvider mRelatedPeopelProvider;
    private LostProvider mLostProvider;
    private CrimeToolProvider mCrimeToolProvider;

    public CreateScene_FP2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_scene_fp2, container, false);
        CreateSceneActivity activity  = (CreateSceneActivity) getActivity();
        if(savedInstanceState == null){
            mItem = activity.getItem();
            mEvent = activity.getEvent();
        }else{
            mItem = (CrimeItem) savedInstanceState.getSerializable("CrimeItem");
            mEvent = (int) savedInstanceState.getSerializable("Event");
        }
        context = getActivity().getApplicationContext();

        initData();
        initView(view);

        mRelatedPeopelProvider = new RelatedPeopleProvider(context);
        mLostProvider = new LostProvider(context);
        mCrimeToolProvider = new CrimeToolProvider(context);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("CrimeItem",mItem);
        outState.putSerializable("Event",mEvent);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null) {
            savedInstanceState.putSerializable("CrimeItem", mItem);
            savedInstanceState.putSerializable("Event", mEvent);
        }
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
                startActivityForResult(it,EVENT_NEW_PEOPLE);
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
                startActivityForResult(it,EVENT_NEW_ITEM);
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
                startActivityForResult(it,EVENT_NEW_TOOL);
            }
        });

        mPeopleList = mItem.getReleatedPeople();
        mPeople_List=(ListView) view.findViewById(R.id.people_listView);
        mPeople_Adapter = new RelatedPeoeplAdapter(context, mPeopleList);
        mPeople_List.setAdapter(mPeople_Adapter);
        setListViewHeightBasedOnChildren(mPeople_List);
        AdapterView.OnItemClickListener itemListener1 = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RelatedPeopleItem relatedPeopleItem = (RelatedPeopleItem) mPeople_Adapter.getItem(position);
                Intent it = new Intent(getActivity(), CreateScene_FP2_NewPeopleActivity.class);
                it.putExtra("com.android.csiapp.Databases.RelatedPeopleItem", relatedPeopleItem);
                it.putExtra("Event",2);
                it.putExtra("Position", position);
                startActivityForResult(it,EVENT_NEW_PEOPLE);
            }
        };
        mPeople_List.setOnItemClickListener(itemListener1);

        mItemList = mItem.getLostItem();
        mItem_List=(ListView) view.findViewById(R.id.item_listView);
        mItem_Adapter = new LostItemAdapter(context, mItemList);
        mItem_List.setAdapter(mItem_Adapter);
        setListViewHeightBasedOnChildren(mItem_List);
        AdapterView.OnItemClickListener itemListener2 = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LostItem lostItem = (LostItem) mItem_Adapter.getItem(position);
                Intent it = new Intent(getActivity(), CreateScene_FP2_NewItemActivity.class);
                it.putExtra("com.android.csiapp.Databases.LostItem", lostItem);
                it.putExtra("Event",2);
                it.putExtra("Position", position);
                startActivityForResult(it,EVENT_NEW_ITEM);
            }
        };
        mItem_List.setOnItemClickListener(itemListener2);

        mToolList = mItem.getCrimeTool();
        mTool_List=(ListView) view.findViewById(R.id.tool_listView);
        mTool_Adapter = new CrimeItemAdapter(context, mToolList);
        mTool_List.setAdapter(mTool_Adapter);
        setListViewHeightBasedOnChildren(mTool_List);
        AdapterView.OnItemClickListener itemListener3 = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CrimeToolItem crimeToolItem = (CrimeToolItem) mTool_Adapter.getItem(position);
                Intent it = new Intent(getActivity(), CreateScene_FP2_NewToolActivity.class);
                it.putExtra("com.android.csiapp.Databases.CrimeToolItem", crimeToolItem);
                it.putExtra("Event",2);
                it.putExtra("Position", position);
                startActivityForResult(it,EVENT_NEW_TOOL);
            }
        };
        mTool_List.setOnItemClickListener(itemListener3);

        registerForContextMenu(mPeople_List);
        registerForContextMenu(mItem_List);
        registerForContextMenu(mTool_List);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        String delete = context.getResources().getString(R.string.list_delete);
        if (v.getId()==R.id.people_listView) {
            menu.add(0, EVENT_PEOPLE_DELETE, 0, delete);
        }else if(v.getId()==R.id.item_listView){
            menu.add(0, EVENT_ITEM_DELETE, 0, delete);
        }else if(v.getId()==R.id.tool_listView){
            menu.add(0, EVENT_TOOL_DELETE, 0, delete);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case EVENT_PEOPLE_DELETE:
                if(mEvent == 2) mRelatedPeopelProvider.delete(mPeopleList.get(info.position).getId());
                mPeopleList.remove(info.position);
                setListViewHeightBasedOnChildren(mPeople_List);
                mPeople_Adapter.notifyDataSetChanged();
                return true;
            case EVENT_ITEM_DELETE:
                if(mEvent == 2) mLostProvider.delete(mItemList.get(info.position).getId());
                mItemList.remove(info.position);
                setListViewHeightBasedOnChildren(mItem_List);
                mItem_Adapter.notifyDataSetChanged();
                return true;
            case EVENT_TOOL_DELETE:
                if(mEvent == 2) mCrimeToolProvider.delete(mToolList.get(info.position).getId());
                mToolList.remove(info.position);
                setListViewHeightBasedOnChildren(mTool_List);
                mTool_Adapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void initData(){
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == EVENT_NEW_PEOPLE) {
                // 新增記事資料到資料庫
                RelatedPeopleItem relatedPeopleItem = (RelatedPeopleItem) data.getSerializableExtra("com.android.csiapp.Databases.RelatedPeopleItem");
                int event = (int) data.getIntExtra("Event", 1);
                if(mEvent == 2 && event ==1) relatedPeopleItem.setId(mRelatedPeopelProvider.insert(relatedPeopleItem));
                if(event == 1) {
                    mPeopleList.add(relatedPeopleItem);
                }else{
                    int position = (int) data.getIntExtra("Position",0);
                    mPeopleList.set(position, relatedPeopleItem);
                }
                setListViewHeightBasedOnChildren(mPeople_List);
                mPeople_Adapter.notifyDataSetChanged();
            }else if(requestCode == EVENT_NEW_ITEM){
                // 新增記事資料到資料庫
                LostItem lostItem = (LostItem) data.getSerializableExtra("com.android.csiapp.Databases.LostItem");
                int event = (int) data.getIntExtra("Event", 1);
                if(mEvent == 2 && event ==1) lostItem.setId(mLostProvider.insert(lostItem));
                if(event == 1) {
                    mItemList.add(lostItem);
                }else{
                    int position = (int) data.getIntExtra("Position",0);
                    mItemList.set(position, lostItem);
                }
                setListViewHeightBasedOnChildren(mItem_List);
                mItem_Adapter.notifyDataSetChanged();
            }else if(requestCode == EVENT_NEW_TOOL){
                // 新增記事資料到資料庫
                CrimeToolItem crimeToolItem = (CrimeToolItem) data.getSerializableExtra("com.android.csiapp.Databases.CrimeToolItem");
                int event = (int) data.getIntExtra("Event", 1);
                if(mEvent == 2 && event ==1) crimeToolItem.setId(mCrimeToolProvider.insert(crimeToolItem));
                if(event == 1) {
                    mToolList.add(crimeToolItem);
                }else{
                    int position = (int) data.getIntExtra("Position",0);
                    mToolList.set(position, crimeToolItem);
                }
                setListViewHeightBasedOnChildren(mTool_List);
                mTool_Adapter.notifyDataSetChanged();
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
