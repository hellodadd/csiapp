package com.android.csiapp.Crime.listscene;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.csiapp.Crime.utils.DateTimePicker;
import com.android.csiapp.Databases.CrimeItem;
import com.android.csiapp.Databases.CrimeProvider;
import com.android.csiapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2016/10/14.
 */
public class ListSearchActivity extends AppCompatActivity {

    private Context context = null;
    private CrimeProvider mCrimeProvider;
    private List<CrimeItem> items_list;
    private ListView mListV;
    private ListAdapter mAdapter;

    private Button mCategory, mArea, mTime, mCategory_Search, mArea_Search, mTime_Search;
    private LinearLayout mCategoryLL, mAreaLL, mTimeLL;
    private List<String> mItem_Category, mItem_Area;
    private ListView mList_Catetype, mList_Area;
    private ListSearchAdapter mAdapter_Category, mAdapter_Area;
    private HashMap<Integer, Boolean> isSelected_Category, isSelected_Area;
    private DateTimePicker mStartTime, mEndTime;
    private long mStartTimeMills, mEndTimeMills;

    final int LIST_DELETE = 0;

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_delete:
                    msg += "Delete";
                    for (int i = 0; i < items_list.size(); i++) {
                        if(!items_list.get(i).getComplete().equalsIgnoreCase("2")) {
                            mCrimeProvider.delete(items_list.get(i).getId());
                        }else{
                            Toast.makeText(ListSearchActivity.this, "已上报数据无法删除", Toast.LENGTH_SHORT).show();
                        }
                    }
                    Intent result = getIntent();
                    setResult(Activity.RESULT_OK, result);
                    finish();
                    break;
            }

            if(!msg.equals("")) {
                //Toast.makeText(ListSearchActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_search);

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

        Collections.sort(items_list,
                new Comparator<CrimeItem>() {
                    public int compare(CrimeItem o1, CrimeItem o2) {
                        return String.valueOf(o1.getOccurredStartTime()).compareTo(String.valueOf(o2.getOccurredStartTime()));
                    }
                });
        Collections.reverse(items_list);

        mListV=(ListView)findViewById(R.id.listView);
        mAdapter = new ListAdapter(ListSearchActivity.this,items_list);
        mListV.setAdapter(mAdapter);

        mCategory = (Button) findViewById(R.id.categoryBtn);
        mCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mAdapter.filter(1,catetype[0],0,0);
                mListV.setVisibility(View.GONE);
                mCategoryLL.setVisibility(View.VISIBLE);
                mAreaLL.setVisibility(View.GONE);
                mTimeLL.setVisibility(View.GONE);

                mCategory.setBackgroundColor(Color.WHITE);
                mArea.setBackgroundColor(Color.GRAY);
                mTime.setBackgroundColor(Color.GRAY);
            }
        });

        mArea = (Button) findViewById(R.id.areaBtn);
        mArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mAdapter.filter(2,area[0],0,0);
                mListV.setVisibility(View.GONE);
                mCategoryLL.setVisibility(View.GONE);
                mAreaLL.setVisibility(View.VISIBLE);
                mTimeLL.setVisibility(View.GONE);

                mCategory.setBackgroundColor(Color.GRAY);
                mArea.setBackgroundColor(Color.WHITE);
                mTime.setBackgroundColor(Color.GRAY);
            }
        });

        mTime = (Button) findViewById(R.id.timeBtn);
        mTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mAdapter.filter(3,null, Calendar.getInstance().getTimeInMillis()-10000,Calendar.getInstance().getTimeInMillis()+10000);
                mListV.setVisibility(View.GONE);
                mCategoryLL.setVisibility(View.GONE);
                mAreaLL.setVisibility(View.GONE);
                mTimeLL.setVisibility(View.VISIBLE);

                mCategory.setBackgroundColor(Color.GRAY);
                mArea.setBackgroundColor(Color.GRAY);
                mTime.setBackgroundColor(Color.WHITE);
            }
        });

        mCategoryLL = (LinearLayout) findViewById(R.id.CategoryLL);
        mAreaLL = (LinearLayout) findViewById(R.id.AreaLL);
        mTimeLL = (LinearLayout) findViewById(R.id.TimeLL);

        mItem_Category = Arrays.asList(getResources().getStringArray(R.array.casetype));
        mList_Catetype = (ListView)findViewById(R.id.category_listView);
        isSelected_Category = new HashMap<Integer,Boolean>();
        mAdapter_Category = new ListSearchAdapter(ListSearchActivity.this, mItem_Category, isSelected_Category);
        mList_Catetype.setAdapter(mAdapter_Category);

        mCategory_Search = (Button) findViewById(R.id.clickCategoryBtn);
        mCategory_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSelected_Category = mAdapter_Category.getIsSelected();
                mAdapter.clearItem();
                for (int i = 0; i < isSelected_Category.size(); i++) {
                    if (isSelected_Category.get(i).equals(true)) {
                        mAdapter.filter(1,mItem_Category.get(i),0,0);
                    }
                }

                mListV.setVisibility(View.VISIBLE);
                mCategoryLL.setVisibility(View.GONE);
            }
        });

        mItem_Area = Arrays.asList(getResources().getStringArray(R.array.area));
        mList_Area = (ListView)findViewById(R.id.area_listView);
        isSelected_Area = new HashMap<Integer,Boolean>();
        mAdapter_Area = new ListSearchAdapter(ListSearchActivity.this,mItem_Area, isSelected_Area);
        mList_Area.setAdapter(mAdapter_Area);

        mArea_Search = (Button) findViewById(R.id.clickAreaBtn);
        mArea_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSelected_Area= mAdapter_Area.getIsSelected();
                mAdapter.clearItem();
                for (int i = 0; i < isSelected_Area.size(); i++) {
                    if (isSelected_Area.get(i).equals(true)) {
                        mAdapter.filter(2,mItem_Area.get(i),0,0);
                    }
                }

                mListV.setVisibility(View.VISIBLE);
                mAreaLL.setVisibility(View.GONE);
            }
        });

        mStartTime = (DateTimePicker) findViewById(R.id.startTime);
        mEndTime = (DateTimePicker) findViewById(R.id.endTime);

        mTime_Search = (Button) findViewById(R.id.clickTimeBtn);
        mTime_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapter.clearItem();
                mStartTimeMills = mStartTime.get().getTimeInMillis();
                mEndTimeMills = mEndTime.get().getTimeInMillis();
                mAdapter.filter(3,null,mStartTimeMills,mEndTimeMills);

                mListV.setVisibility(View.VISIBLE);
                mTimeLL.setVisibility(View.GONE);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_search, menu);
        return true;
    }
}
