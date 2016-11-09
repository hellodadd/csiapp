package com.android.csiapp.Crime.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.android.csiapp.Crime.utils.adapter.ParentAdapter;
import com.android.csiapp.Crime.utils.adapter.ParentAdapter.OnChildTreeViewClickListener;
import com.android.csiapp.Crime.utils.entity.ChildEntity;
import com.android.csiapp.Crime.utils.entity.ParentEntity;
import com.android.csiapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SelectAlertDialog extends AppCompatActivity implements OnChildTreeViewClickListener{

    private Context mContext;

    private ExpandableListView eList;

    private ArrayList<ParentEntity> parents;

    private ParentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_alert_dialog);
        mContext = this.getApplicationContext();
        loadData();
        initEList();
    }

    private void loadData() {
        //Need to modify
        HashMap<String,String> ParentData = new HashMap<>();
        HashMap<String, HashMap<String,List<String>>> Data = new HashMap<>();

        /*//test
        for(int i1 = 0; i1< 10; i1++){
            HashMap<String, List<String>> ChildsData = new HashMap<>();
            ParentData.put(String.valueOf(i1),"���������ղ�"+String.valueOf(i1)+"��");
            for(int j2 = 0;j2<8; j2++){
                List<String> ChildData = new ArrayList<>();
                ChildData.add("�l�������ղ�"+String.valueOf(j2)+"��");
                for(int k3 = 0;k3<5; k3++){
                    ChildData.add("�l���l���ղ�"+String.valueOf(k3)+"��");
                }
                ChildsData.put(String.valueOf(j2),ChildData);
            }
            Data.put(String.valueOf(i1),ChildsData);
        }
        //test*/

        parents = new ArrayList<ParentEntity>();
        for (int i = 0; i < ParentData.size(); i++) {
            ParentEntity parent = new ParentEntity();
            parent.setGroupName(ParentData.get(String.valueOf(i)));
            ArrayList<ChildEntity> childs = new ArrayList<ChildEntity>();
            for (int j = 0; j < Data.get(String.valueOf(i)).size(); j++) {
                ChildEntity child = new ChildEntity();
                child.setGroupName(Data.get(String.valueOf(i)).get(String.valueOf(j)).get(0));
                ArrayList<String> childNames = new ArrayList<String>();
                //ArrayList<Integer> childColors = new ArrayList<Integer>();
                for (int k = 1; k < Data.get(String.valueOf(i)).get(String.valueOf(j)).size(); k++) {
                    childNames.add(Data.get(String.valueOf(i)).get(String.valueOf(j)).get(k));
                }
                child.setChildNames(childNames);
                childs.add(child);
            }
            parent.setChilds(childs);
            parents.add(parent);
        }
    }

    private void initEList() {

        eList = (ExpandableListView) findViewById(R.id.eList);

        adapter = new ParentAdapter(mContext, parents);

        eList.setAdapter(adapter);

        adapter.setOnChildTreeViewClickListener(this);

        eList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if(adapter.getChildrenCount(groupPosition)==0) {
                    onClickPosition(groupPosition, -1, -1);
                }
                return false;
            }
        });

    }

    @Override
    public void onClickPosition(int parentPosition, int groupPosition,
                                int childPosition) {
        // do something
        String childName = "";
        if(childPosition!=-1 && groupPosition!=-1) {
            childName = parents.get(parentPosition).getChilds()
                    .get(groupPosition).getChildNames().get(childPosition)
                    .toString();
            Toast.makeText(
                    mContext, " parentPosition=" + parentPosition
                            + "   groupPosition=" + groupPosition
                            + "   childPosition=" + childPosition + "\n"
                            + childName, Toast.LENGTH_SHORT).show();
        }
    }
}
