package com.android.csiapp.Crime.createscene;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.csiapp.Crime.utils.PhotoAdapter;
import com.android.csiapp.Crime.utils.Priview_photo_Activity;
import com.android.csiapp.Databases.CrimeItem;
import com.android.csiapp.Databases.FlatProvider;
import com.android.csiapp.Databases.PhotoItem;
import com.android.csiapp.Databases.PositionProvider;
import com.android.csiapp.R;

import java.io.File;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateScene_FP3 extends Fragment {

    private Context context = null;
    private CrimeItem mItem;
    private PhotoItem mPositionItem;
    private int mEvent;

    List<PhotoItem> mPositionList;
    private ListView mPosition_List;
    private PhotoAdapter mPosition_Adapter;
    private ImageButton mAdd_Position;

    List<PhotoItem> mFlatList;
    private ListView mFlat_List;
    private PhotoAdapter mFlat_Adapter;
    private ImageButton mAdd_Flat;

    final int POSITION_DELETE = 3;
    final int FLAT_DELETE = 4;

    private PositionProvider mPositionProvider;
    private FlatProvider mFlatProvider;

    public CreateScene_FP3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_scene_fp3, container, false);
        CreateSceneActivity activity  = (CreateSceneActivity) getActivity();
        mItem = activity.getItem();
        mEvent = activity.getEvent();
        context = getActivity().getApplicationContext();

        initData();
        initView(view);

        mPositionProvider = new PositionProvider(context);
        mFlatProvider = new FlatProvider(context);

        return view;
    }

    private void initView(View view){
        mAdd_Position = (ImageButton) view.findViewById(R.id.add_position);
        mAdd_Position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(), CreateScene_FP3_PositionInformationActivity.class);
                it.putExtra("com.android.csiapp.Databases.CrimeItem", mItem);
                it.putExtra("Add","Position");
                it.putExtra("Event",1);
                startActivityForResult(it, 0);
            }
        });

        mPosition_List=(ListView) view.findViewById(R.id.position_listview);
        mPosition_Adapter = new PhotoAdapter(context, mPositionList);
        mPosition_List.setAdapter(mPosition_Adapter);
        mPosition_List.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
            {
                Intent it = new Intent(getActivity(), Priview_photo_Activity.class);
                it.putExtra("Path",mPositionList.get(position).getPhotoPath());
                startActivityForResult(it, 100);
            }
        });
        setListViewHeightBasedOnChildren(mPosition_List);

        mAdd_Flat = (ImageButton) view.findViewById(R.id.add_flat);
        mAdd_Flat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(), CreateScene_FP3_PositionInformationActivity.class);
                it.putExtra("com.android.csiapp.Databases.CrimeItem", mItem);
                it.putExtra("Add","Flat");
                it.putExtra("Event",1);
                startActivityForResult(it, 1);
            }
        });

        mFlat_List=(ListView) view.findViewById(R.id.flat_listview);
        mFlat_Adapter = new PhotoAdapter(context, mFlatList);
        mFlat_List.setAdapter(mFlat_Adapter);
        mFlat_List.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
            {
                Intent it = new Intent(getActivity(), Priview_photo_Activity.class);
                it.putExtra("Path",mFlatList.get(position).getPhotoPath());
                startActivityForResult(it, 100);
            }
        });
        setListViewHeightBasedOnChildren(mFlat_List);

        registerForContextMenu(mPosition_List);
        registerForContextMenu(mFlat_List);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        String delete = context.getResources().getString(R.string.list_delete);
        if (v.getId()==R.id.position_listview) {
            menu.add(0, POSITION_DELETE, 0, delete);
        }else if(v.getId()==R.id.flat_listview){
            menu.add(0, FLAT_DELETE, 0, delete);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case POSITION_DELETE:
                if(mEvent == 2) mPositionProvider.delete(mPositionList.get(info.position).getId());
                mPositionList.remove(info.position);
                setListViewHeightBasedOnChildren(mPosition_List);
                mPosition_Adapter.notifyDataSetChanged();
                return true;
            case FLAT_DELETE:
                if(mEvent == 2) mFlatProvider.delete(mFlatList.get(info.position).getId());
                mFlatList.remove(info.position);
                setListViewHeightBasedOnChildren(mFlat_List);
                mFlat_Adapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void initData(){
        mPositionList = mItem.getPosition();
        mFlatList = mItem.getFlat();
    }

    private void saveData(){
        mItem.setPosition(mPositionList);
        mItem.setFlat(mFlatList);
    }

    @Override
    public void onResume(){
        super.onResume();
        initData();
    }

    @Override
    public void onPause(){
        super.onPause();
        saveData();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if(requestCode == 0) {
                // 新增記事資料到資料庫
                PhotoItem positionItem = (PhotoItem) data.getSerializableExtra("com.android.csiapp.Databases.PhotoItem");
                int event = (int) data.getIntExtra("Event", 1);
                int position = (int) data.getIntExtra("Position", 0);
                if (mEvent == 2 && event == 1)
                    positionItem.setId(mPositionProvider.insert(positionItem));
                if (event == 1) {
                    mPositionList.add(positionItem);
                } else {
                    mPositionList.set(position, positionItem);
                }
                setListViewHeightBasedOnChildren(mPosition_List);
                mPosition_Adapter.notifyDataSetChanged();
            }else if(requestCode == 1){
                // 新增記事資料到資料庫
                PhotoItem flatItem = (PhotoItem) data.getSerializableExtra("com.android.csiapp.Databases.PhotoItem");
                int event = (int) data.getIntExtra("Event", 1);
                int position = (int) data.getIntExtra("Position", 0);
                if (mEvent == 2 && event == 1)
                    flatItem.setId(mFlatProvider.insert(flatItem));
                if (event == 1) {
                    mFlatList.add(flatItem);
                } else {
                    mFlatList.set(position, flatItem);
                }
                setListViewHeightBasedOnChildren(mFlat_List);
                mFlat_Adapter.notifyDataSetChanged();
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
