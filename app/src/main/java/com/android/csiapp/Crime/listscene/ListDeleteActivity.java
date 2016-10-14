package com.android.csiapp.Crime.listscene;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.csiapp.Databases.CrimeItem;
import com.android.csiapp.Databases.CrimeProvider;
import com.android.csiapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016/10/14.
 */
public class ListDeleteActivity extends AppCompatActivity {

    private Context context = null;
    private CrimeProvider mCrimeProvider;
    private List<CrimeItem> items_list;
    private ListView mListV;
    private ListAdapter mAdapter;

    final int LIST_DELETE = 0;

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_search_all:
                    msg += "Search";
                    break;
                case R.id.action_delete:
                    msg += "Delete";
                    break;
            }

            if(!msg.equals("")) {
                Toast.makeText(ListDeleteActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_delete);

        context = this.getApplicationContext();

        mCrimeProvider = new CrimeProvider(context);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setTitleTextColor(context.getResources().getColor(R.color.titleBar));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.btn_back_mini);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //What to do on back clicked
            }
        });
        toolbar.setOnMenuItemClickListener(onMenuItemClick);

        items_list = new ArrayList<CrimeItem>();
        items_list = mCrimeProvider.getAll();
        mListV=(ListView)findViewById(R.id.listView);
        mAdapter = new ListAdapter(ListDeleteActivity.this,items_list,true);
        mListV.setAdapter(mAdapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_delete, menu);
        return true;
    }
}
