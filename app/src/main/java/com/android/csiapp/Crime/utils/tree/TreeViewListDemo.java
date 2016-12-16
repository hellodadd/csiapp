package com.android.csiapp.Crime.utils.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.csiapp.Crime.utils.DictionaryInfo;
import com.android.csiapp.Crime.utils.UserInfo;
import com.android.csiapp.R;

/**
 * Demo activity showing how the tree view can be used.
 * 
 */
public class TreeViewListDemo extends AppCompatActivity {
    private enum TreeType implements Serializable {
        SIMPLE,
        FANCY,
        MULTIPLE
    }

    private final Set<String> selected = new HashSet<String>();

    String Title, Method;
    ArrayList<Integer> DEMO_NODES;
    ArrayList<String> mDicitonary;

    private static final String TAG = TreeViewListDemo.class.getSimpleName();
    private ListView listView;
    private int mLastCorrectPosition = -1;
    private TreeViewList treeView;
    //private static final int[] DEMO_NODES = new int[] { 0, 0, 1, 1, 1, 2, 2, 1,
    //        1, 2, 1, 0, 0, 0, 1, 2, 3, 2, 0, 0, 1, 2, 0, 1, 2, 0, 1 };
    private static final int LEVEL_NUMBER = 4;
    private TreeStateManager<String> manager = null;
    private FancyColouredVariousSizesAdapter fancyAdapter;
    private SimpleStandardAdapter simpleAdapter;
    private MultipleStandardAdapter multipleAdapter;
    private TreeType treeType;
    private boolean collapsible;

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_save:
                    msg += "Save";
                    Intent result = getIntent();
                    Set<String> selectedItem;
                    if(Method.equalsIgnoreCase("Single")) {
                        selectedItem = simpleAdapter.getSelected();
                    }else{
                        selectedItem = multipleAdapter.getSelected();
                    }
                    String selected = "";
                    boolean isFirst = true;
                    for(Iterator it = selectedItem.iterator();it.hasNext();){
                        if(isFirst){
                            isFirst = false;
                        }else {
                            selected = selected + "," ;
                        }
                        selected = selected + it.next();
                    }
                    Log.d("Anita","selected = "+selected);
                    if(!selected.contains(",") && Method.equalsIgnoreCase("Multiple")){
                        Toast.makeText(TreeViewListDemo.this, "需要选择两个以上的人员", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    result.putExtra("Select",selected);
                    setResult(Activity.RESULT_OK, result);
                    finish();
                    break;
                default:
                    break;
            }

            if (!msg.equals("")) {
                //Toast.makeText(CreateSceneActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_demo);
        TreeType newTreeType = null;
        boolean newCollapsible;

        DictionaryInfo info = new DictionaryInfo(getApplicationContext());
        UserInfo userinfo = new UserInfo(getApplication());

        String Key = getIntent().getStringExtra("Key");
        if(Key==null) Key = DictionaryInfo.mCaseTypeKey;
        String Selected = getIntent().getStringExtra("Selected");
        if(Selected==null) Selected = "";
        String Datainfo = getIntent().getStringExtra("DataInfo");
        if(Datainfo==null) Datainfo = "DictionaryInfo";

        if(Datainfo.equalsIgnoreCase("DictionaryInfo")){
            Title = info.getTitle(Key);
            Method = info.getMethod(Key);
            DEMO_NODES = DictionaryInfo.getNodes(Key);
            mDicitonary = DictionaryInfo.getDictKeyList(Key);
        }else{
            Title = userinfo.getTitle(Key);
            Method = userinfo.getMethod(Key);
            DEMO_NODES = UserInfo.getNodes(Key);
            mDicitonary = UserInfo.getLoginNameList(Key);
        }

        if(Method.equalsIgnoreCase("Single")){
            selected.add(Selected);
            newTreeType = TreeType.SIMPLE;
        }else{
            String[] item =  Selected.split(",");
            for(int i = 0;i<item.length;i++){
                selected.add(item[i].trim());
            }
            newTreeType = TreeType.MULTIPLE;
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView title = (TextView) findViewById(R.id.toolbar_title);
        title.setText(Title);
        toolbar.setTitle("");
        toolbar.setTitleTextColor(getApplicationContext().getResources().getColor(R.color.titleBar));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.btn_back_mini);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setOnMenuItemClickListener(onMenuItemClick);

        if (savedInstanceState == null) {
            manager = new InMemoryTreeStateManager<String>();
            final TreeBuilder<String> treeBuilder = new TreeBuilder<String>(manager);
            for (int i = 0; i < DEMO_NODES.size(); i++) {
                if(Datainfo.equalsIgnoreCase("DictionaryInfo")){
                    treeBuilder.sequentiallyAddNextNode(mDicitonary.get(i), DEMO_NODES.get(i), DictionaryInfo.getDictValue(Key, mDicitonary.get(i)));
                }else{
                    treeBuilder.sequentiallyAddNextNode(mDicitonary.get(i), DEMO_NODES.get(i), UserInfo.getUserName(mDicitonary.get(i)));
                }
            }
            Log.d(TAG, manager.toString());
            newCollapsible = true;
        } else {
            manager = (TreeStateManager<String>) savedInstanceState
                    .getSerializable("treeManager");
            if (manager == null) {
                manager = new InMemoryTreeStateManager<String>();
            }
            newTreeType = (TreeType) savedInstanceState
                    .getSerializable("treeType");
            if (newTreeType == null) {
                newTreeType = TreeType.SIMPLE;
            }
            newCollapsible = savedInstanceState.getBoolean("collapsible");
        }
        listView = (ListView) findViewById(R.id.mainListView);
        treeView = (TreeViewList) findViewById(R.id.mainTreeView);
        fancyAdapter = new FancyColouredVariousSizesAdapter(this, selected, manager, LEVEL_NUMBER);
        simpleAdapter = new SimpleStandardAdapter(this, selected, manager, LEVEL_NUMBER);
        multipleAdapter = new MultipleStandardAdapter(this, selected, manager, LEVEL_NUMBER);
        setTreeAdapter(newTreeType);
        setCollapsible(newCollapsible);
        manager.collapseChildren(null); // 先摺疊所有item
        registerForContextMenu(treeView);
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        outState.putSerializable("treeManager", manager);
        outState.putSerializable("treeType", treeType);
        outState.putBoolean("collapsible", this.collapsible);
        super.onSaveInstanceState(outState);
    }

    protected final void setTreeAdapter(final TreeType newTreeType) {
        this.treeType = newTreeType;
        switch (newTreeType) {
        case SIMPLE:
            treeView.setAdapter(simpleAdapter);
            break;
        case FANCY:
            treeView.setAdapter(fancyAdapter);
            break;
        case MULTIPLE:
            treeView.setAdapter(multipleAdapter);
            break;
        default:
            treeView.setAdapter(simpleAdapter);
        }
    }

    protected final void setCollapsible(final boolean newCollapsible) {
        this.collapsible = newCollapsible;
        treeView.setCollapsible(this.collapsible);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_createscene, menu);
        return true;
    }
}