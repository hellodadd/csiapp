package com.android.csiapp.Crime.createscene;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.android.csiapp.Crime.utils.PhotoAdapter;
import com.android.csiapp.Databases.CrimeItem;
import com.android.csiapp.Databases.PhotoItem;
import com.android.csiapp.R;

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

    final int POSITION_DELETE = 3;

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

        return view;
    }

    private void initView(View view){
        mAdd_Position = (ImageButton) view.findViewById(R.id.add_position);
        mAdd_Position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(), CreateScene_FP3_PositionInformationActivity.class);
                it.putExtra("com.android.csiapp.Databases.CrimeItem", mItem);
                it.putExtra("Event",1);
                startActivityForResult(it, 0);
            }
        });

        mPosition_List=(ListView) view.findViewById(R.id.position_listview);
        mPosition_Adapter = new PhotoAdapter(context, mPositionList);
        mPosition_List.setAdapter(mPosition_Adapter);
        setListViewHeightBasedOnChildren(mPosition_List);

        registerForContextMenu(mPosition_List);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        String delete = context.getResources().getString(R.string.list_delete);
        if (v.getId()==R.id.position_listview) {
            menu.add(0, POSITION_DELETE, 0, delete);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case POSITION_DELETE:
                Log.d("Anita","mPositionList fg3 = "+mPositionList.size()+", position = "+info.position);
                mPositionList.remove(info.position);
                setListViewHeightBasedOnChildren(mPosition_List);
                mPosition_Adapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void initData(){
        mPositionList = mItem.getPosition();
    }

    private void saveData(){
        mItem.setPosition(mPositionList);
    }

    @Override
    public void onResume(){
        super.onResume();
        initData();
        mPosition_Adapter.notifyDataSetChanged();
    }

    @Override
    public void onPause(){
        super.onPause();
        saveData();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == 0) {
            // 新增記事資料到資料庫
            PhotoItem positionItem = (PhotoItem) data.getSerializableExtra("com.android.csiapp.Databases.PhotoItem");
            int event = (int) data.getIntExtra("Event", 1);
            int position = (int) data.getIntExtra("Position",0);
            if(event == 1) {
                mPositionList.add(positionItem);
            }else{
                mPositionList.set(position, positionItem);
            }
            setListViewHeightBasedOnChildren(mPosition_List);
            mPosition_Adapter.notifyDataSetChanged();
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
