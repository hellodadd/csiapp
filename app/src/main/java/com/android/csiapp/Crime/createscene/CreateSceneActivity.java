package com.android.csiapp.Crime.createscene;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.csiapp.Databases.CrimeItem;
import com.android.csiapp.R;

import java.util.ArrayList;
import java.util.List;

public class CreateSceneActivity extends AppCompatActivity implements OnPageChangeListener, OnClickListener{

    private Context context = null;

    // 記事物件
    private CrimeItem item;
    private int event;

    private Button pageButton1, pageButton2, pageButton3, pageButton4, pageButton5, pageButton6, pageButton7, pageButton8;

    private TextView pageText1, pageText2, pageText3, pageText4, pageText5, pageText6, pageText7, pageText8;

    private ViewPager viewPager;
    private View page1, page2, page3, page4, page5, page6, page7, page8;
    private List pageList;

    private Drawable select_background;
    private Drawable background;

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_save:
                    msg += "Save";
                    Intent result = getIntent();
                    result.putExtra("com.android.csiapp.CrimeItem", item);
                    setResult(Activity.RESULT_OK, result);
                    break;
            }

            if (!msg.equals("")) {
                //Toast.makeText(CreateSceneActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            //Anita test
            if(item.getTime().isEmpty()) {
                Toast.makeText(CreateSceneActivity.this, "需要填写发案开始时间", Toast.LENGTH_SHORT).show();
            }else{
                finish();
            }
            //Anita test
            return true;
        }
    };

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_scene);

        context = this.getApplicationContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView title = (TextView) findViewById(R.id.toolbar_title);
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

        // 取得Intent物件
        Intent intent = getIntent();
        // 讀取Action名稱
        String action = intent.getAction();
        if (action.equals("com.android.csiapp.ADD_SCENE")) {
            item = new CrimeItem();
            event = 1;
        } else if(action.equals("com.android.csiapp.EDIT_SCENE")){
            title.setText(context.getResources().getString(R.string.title_activity_editscene));
            item = (CrimeItem) intent.getSerializableExtra("CrimeItem");
            event = 2;
        }else{
            item = new CrimeItem();
        }

        pageButton1 = (Button) this.findViewById(R.id.pagebutton1);
        pageButton2 = (Button) this.findViewById(R.id.pagebutton2);
        pageButton3 = (Button) this.findViewById(R.id.pagebutton3);
        pageButton4 = (Button) this.findViewById(R.id.pagebutton4);
        pageButton5 = (Button) this.findViewById(R.id.pagebutton5);
        pageButton6 = (Button) this.findViewById(R.id.pagebutton6);
        pageButton7 = (Button) this.findViewById(R.id.pagebutton7);
        pageButton8 = (Button) this.findViewById(R.id.pagebutton8);

        pageText1 = (TextView) this.findViewById(R.id.pageTextView1);
        pageText2 = (TextView) this.findViewById(R.id.pageTextView2);
        pageText3 = (TextView) this.findViewById(R.id.pageTextView3);
        pageText4 = (TextView) this.findViewById(R.id.pageTextView4);
        pageText5 = (TextView) this.findViewById(R.id.pageTextView5);
        pageText6 = (TextView) this.findViewById(R.id.pageTextView6);
        pageText7 = (TextView) this.findViewById(R.id.pageTextView7);
        pageText8 = (TextView) this.findViewById(R.id.pageTextView8);

        pageButton1.setOnClickListener(this);
        pageButton2.setOnClickListener(this);
        pageButton3.setOnClickListener(this);
        pageButton4.setOnClickListener(this);
        pageButton5.setOnClickListener(this);
        pageButton6.setOnClickListener(this);
        pageButton7.setOnClickListener(this);
        pageButton8.setOnClickListener(this);

        select_background = context.getResources().getDrawable(R.drawable.img_step_selected);
        background = context.getResources().getDrawable(R.drawable.img_step_nor);
        pageButton1.setBackground(select_background);
        pageText1.setVisibility(View.VISIBLE);

        viewPager = (ViewPager) this.findViewById(R.id.viewPager);
        CreateScene_FP1 MyFragmentPage1 = new CreateScene_FP1();
        CreateScene_FP2 MyFragmentPage2 = new CreateScene_FP2();
        CreateScene_FP3 MyFragmentPage3 = new CreateScene_FP3();
        CreateScene_FP4 MyFragmentPage4 = new CreateScene_FP4();
        CreateScene_FP5 MyFragmentPage5 = new CreateScene_FP5();
        CreateScene_FP6 MyFragmentPage6 = new CreateScene_FP6();
        CreateScene_FP7 MyFragmentPage7 = new CreateScene_FP7();
        CreateScene_FP8 MyFragmentPage8 = new CreateScene_FP8();
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(MyFragmentPage1);
        fragmentList.add(MyFragmentPage2);
        fragmentList.add(MyFragmentPage3);
        fragmentList.add(MyFragmentPage4);
        fragmentList.add(MyFragmentPage5);
        fragmentList.add(MyFragmentPage6);
        fragmentList.add(MyFragmentPage7);
        fragmentList.add(MyFragmentPage8);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener((ViewPager.OnPageChangeListener) this);
    }

    /**
     * Set up the {@link ActionBar}, if the API is available.
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_createscene, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.pagebutton1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.pagebutton2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.pagebutton3:
                viewPager.setCurrentItem(2);
                break;
            case R.id.pagebutton4:
                viewPager.setCurrentItem(3);
                break;
            case R.id.pagebutton5:
                viewPager.setCurrentItem(4);
                break;
            case R.id.pagebutton6:
                viewPager.setCurrentItem(5);
                break;
            case R.id.pagebutton7:
                viewPager.setCurrentItem(6);
                break;
            case R.id.pagebutton8:
                viewPager.setCurrentItem(7);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onPageSelected(int position) {
        pageButton1.setBackground(background);
        pageText1.setVisibility(View.GONE);
        pageButton2.setBackground(background);
        pageText2.setVisibility(View.GONE);
        pageButton3.setBackground(background);
        pageText3.setVisibility(View.GONE);
        pageButton4.setBackground(background);
        pageText4.setVisibility(View.GONE);
        pageButton5.setBackground(background);
        pageText5.setVisibility(View.GONE);
        pageButton6.setBackground(background);
        pageText6.setVisibility(View.GONE);
        pageButton7.setBackground(background);
        pageText7.setVisibility(View.GONE);
        pageButton8.setBackground(background);
        pageText8.setVisibility(View.GONE);
        switch (position){
            case 0:
                pageButton1.setBackground(select_background);
                pageText1.setVisibility(View.VISIBLE);
                pageButton2.setBackground(background);
                pageText2.setVisibility(View.GONE);
                break;
            case 1:
                pageButton1.setBackground(background);
                pageText1.setVisibility(View.GONE);
                pageButton2.setBackground(select_background);
                pageText2.setVisibility(View.VISIBLE);
                pageButton3.setBackground(background);
                pageText3.setVisibility(View.GONE);
                break;
            case 2:
                pageButton2.setBackground(background);
                pageText2.setVisibility(View.GONE);
                pageButton3.setBackground(select_background);
                pageText3.setVisibility(View.VISIBLE);
                pageButton4.setBackground(background);
                pageText4.setVisibility(View.GONE);
                break;
            case 3:
                pageButton3.setBackground(background);
                pageText3.setVisibility(View.GONE);
                pageButton4.setBackground(select_background);
                pageText4.setVisibility(View.VISIBLE);
                pageButton5.setBackground(background);
                pageText5.setVisibility(View.GONE);
                break;
            case 4:
                pageButton4.setBackground(background);
                pageText4.setVisibility(View.GONE);
                pageButton5.setBackground(select_background);
                pageText5.setVisibility(View.VISIBLE);
                pageButton6.setBackground(background);
                pageText6.setVisibility(View.GONE);
                break;
            case 5:
                pageButton5.setBackground(background);
                pageText5.setVisibility(View.GONE);
                pageButton6.setBackground(select_background);
                pageText6.setVisibility(View.VISIBLE);
                pageButton7.setBackground(background);
                pageText7.setVisibility(View.GONE);
                break;
            case 6:
                pageButton6.setBackground(background);
                pageText6.setVisibility(View.GONE);
                pageButton7.setBackground(select_background);
                pageText7.setVisibility(View.VISIBLE);
                pageButton8.setBackground(background);
                pageText8.setVisibility(View.GONE);
                break;
            case 7:
                pageButton7.setBackground(background);
                pageText7.setVisibility(View.GONE);
                pageButton8.setBackground(select_background);
                pageText8.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public CrimeItem getItem (){
        return item;
    }

    public int getEvent(){
        return event;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
