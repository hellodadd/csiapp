package com.android.csiapp.Csi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.csiapp.Csi_provider;
import com.android.csiapp.Item;
import com.android.csiapp.R;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private Context context = null;
    private Csi_provider csi_item;
    private ListView listV;
    private ItemAdapter adapter;

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_search:
                    msg += "Search";
                    break;
                case R.id.action_delete:
                    msg += "Search";
                    break;
                case R.id.action_upload:
                    msg += "Search";
                    break;
            }

            if(!msg.equals("")) {
                Toast.makeText(ListActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        context = this.getApplicationContext();

        csi_item = new Csi_provider(context);

        // 如果資料庫是空的，就建立一些範例資料
        // 這是為了方便測試用的，完成應用程式以後可以拿掉
        if (csi_item.getCount() == 0) {
            csi_item.sample();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(context.getResources().getString(R.string.title_activity_list));
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

        List<Item> items_list = new ArrayList<Item>();
        items_list = csi_item.getAll();
        listV=(ListView)findViewById(R.id.listView);
        adapter = new ItemAdapter(ListActivity.this,items_list);
        listV.setAdapter(adapter);
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // 讀取選擇的記事物件
                Item item = (Item) adapter.getItem(position);
                Intent intent = new Intent("com.android.csiapp.EDIT_SCENE");
                // 設定記事編號與記事物件
                intent.putExtra("Item", item);
                startActivityForResult(intent, 0);
            }
        };
        listV.setOnItemClickListener(itemListener);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Item item = (Item) data.getExtras().getSerializable("com.android.csiapp.Item");
            if (requestCode == 0) {
                // 新增記事資料到資料庫
                boolean result = csi_item.update(item);
                finish();
            }
        }
    }
}
