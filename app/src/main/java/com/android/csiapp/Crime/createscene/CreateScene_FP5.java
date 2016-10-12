package com.android.csiapp.Crime.createscene;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
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

import com.android.csiapp.Crime.utils.EvidenceAdapter;
import com.android.csiapp.Crime.utils.PhotoAdapter;
import com.android.csiapp.Databases.CrimeItem;
import com.android.csiapp.Databases.EvidenceItem;
import com.android.csiapp.Databases.PhotoItem;
import com.android.csiapp.R;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateScene_FP5 extends Fragment {
    private Context context = null;
    private CrimeItem mItem;
    private EvidenceItem mEvidenceItem;
    private int mEvent;

    List<EvidenceItem> mEvidenceList;
    private ListView mEvidence_List;
    private EvidenceAdapter mEvidence_Adapter;
    private ImageButton mAdd_Scene_Evidence;

    final int EVIDENCE_DELETE = 7;

    public CreateScene_FP5() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_scene_fp5, container, false);
        CreateSceneActivity activity  = (CreateSceneActivity) getActivity();
        mItem = activity.getItem();
        mEvent = activity.getEvent();
        context = getActivity().getApplicationContext();

        initData();
        initView(view);

        return view;
    }

    private void initView(View view){
        mEvidenceItem = new EvidenceItem();
        mAdd_Scene_Evidence = (ImageButton) view.findViewById(R.id.add_scene_evidence);
        mAdd_Scene_Evidence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(), CreateScene_FP5_NewEvidenceActivity.class);
                it.putExtra("com.android.csiapp.Databases.EvidenceItem", mEvidenceItem);
                it.putExtra("Event",1);
                startActivityForResult(it, 0);
            }
        });

        mEvidence_List=(ListView) view.findViewById(R.id.evidence_listview);
        mEvidence_Adapter = new EvidenceAdapter(context, mEvidenceList);
        mEvidence_List.setAdapter(mEvidence_Adapter);
        setListViewHeightBasedOnChildren(mEvidence_List);
        AdapterView.OnItemClickListener itemListener1 = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(getActivity(), CreateScene_FP5_NewEvidenceActivity.class);
                mEvidenceItem = mEvidenceList.get(position);
                it.putExtra("com.android.csiapp.Databases.EvidenceItem", mEvidenceItem);
                it.putExtra("Event",2);
                it.putExtra("Position", position);
                startActivityForResult(it, 0);
            }
        };
        mEvidence_List.setOnItemClickListener(itemListener1);

        registerForContextMenu(mEvidence_List);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        String delete = context.getResources().getString(R.string.list_delete);
        if (v.getId()==R.id.evidence_listview) {
            menu.add(0, EVIDENCE_DELETE, 0, delete);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case EVIDENCE_DELETE:
                Log.d("Anita","mEvidenceList = "+mEvidenceList.size()+", position = "+info.position);
                mEvidenceList.remove(info.position);
                setListViewHeightBasedOnChildren(mEvidence_List);
                mEvidence_Adapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void initData(){
        mEvidenceList = mItem.getEvidenceItem();
    }

    private void saveData(){
        mItem.setEvidenceItem(mEvidenceList);
    }

    @Override
    public void onResume(){
        super.onResume();
        initData();
        mEvidence_Adapter.notifyDataSetChanged();
    }

    @Override
    public void onPause(){
        super.onPause();
        saveData();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("Photo","onActivityResult");
        if (resultCode == Activity.RESULT_OK && requestCode == 0) {
            EvidenceItem evidenceItem = (EvidenceItem) data.getSerializableExtra("com.android.csiapp.Databases.EvidenceItem");
            int event = (int) data.getIntExtra("Event", 1);
            int position = (int) data.getIntExtra("Position",0);
            if(event == 1) {
                mEvidenceList.add(evidenceItem);
            }else{
                mEvidenceList.set(position, evidenceItem);
            }
            setListViewHeightBasedOnChildren(mEvidence_List);
            mEvidence_Adapter.notifyDataSetChanged();
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
