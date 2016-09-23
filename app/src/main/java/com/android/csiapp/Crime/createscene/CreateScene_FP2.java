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
public class CreateScene_FP2 extends Fragment {

    private Context context = null;
    private CrimeItem item;
    private ImageButton newPeople;
    private ImageButton newItem;
    private ImageButton newTool;
    private ListView people_list;
    private ArrayAdapter people_adapter;
    private ListView item_list;
    private ArrayAdapter item_adapter;
    private ListView tool_list;
    private ArrayAdapter tool_adapter;

    public CreateScene_FP2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_scene_fp2, container, false);
        CreateSceneActivity activity  = (CreateSceneActivity) getActivity();
        item = activity.getItem();
        context = getActivity().getApplicationContext();
        newPeople = (ImageButton) view.findViewById(R.id.add_people);
        newPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(), CreateScene_FP2_NewPeopleActivity.class);
                startActivityForResult(it,0);
            }
        });
        newItem = (ImageButton) view.findViewById(R.id.add_item);
        newItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(), CreateScene_FP2_NewItemActivity.class);
                startActivityForResult(it,1);
            }
        });
        newTool = (ImageButton) view.findViewById(R.id.add_tool);
        newTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(), CreateScene_FP2_NewToolActivity.class);
                startActivityForResult(it,2);
            }
        });

        //List<CrimeItem> items_list2 = new ArrayList<CrimeItem>();
        people_list=(ListView) view.findViewById(R.id.people_listView);
        people_adapter = new ArrayAdapter(context, R.layout.listview);
        people_list.setAdapter(people_adapter);
        AdapterView.OnItemClickListener itemListener1 = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent it = new Intent(getActivity(), CreateScene_FP2_NewPeopleActivity.class);
                startActivityForResult(it,0);
            }
        };
        people_list.setOnItemClickListener(itemListener1);

        //List<CrimeItem> items_list1 = new ArrayList<CrimeItem>();
        item_list=(ListView) view.findViewById(R.id.item_listView);
        item_adapter = new ArrayAdapter(context, R.layout.listview);
        item_list.setAdapter(item_adapter);
        AdapterView.OnItemClickListener itemListener2 = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent it = new Intent(getActivity(), CreateScene_FP2_NewItemActivity.class);
                startActivityForResult(it,0);
            }
        };
        item_list.setOnItemClickListener(itemListener2);

        //List<CrimeItem> items_list3 = new ArrayList<CrimeItem>();
        tool_list=(ListView) view.findViewById(R.id.tool_listView);
        tool_adapter = new ArrayAdapter(context, R.layout.listview);
        tool_list.setAdapter(tool_adapter);
        AdapterView.OnItemClickListener itemListener3 = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent it = new Intent(getActivity(), CreateScene_FP2_NewToolActivity.class);
                startActivityForResult(it,0);
            }
        };
        tool_list.setOnItemClickListener(itemListener3);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 0) {
                // 新增記事資料到資料庫
                people_list.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "PeopleList", Toast.LENGTH_SHORT).show();
            }else if(requestCode == 1){
                // 新增記事資料到資料庫
                item_list.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "ItemList", Toast.LENGTH_SHORT).show();
            }else{
                // 新增記事資料到資料庫
                tool_list.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "ToolList", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
