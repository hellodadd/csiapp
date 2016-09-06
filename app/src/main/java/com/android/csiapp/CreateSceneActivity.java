package com.android.csiapp;

import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CreateSceneActivity extends AppCompatActivity {

    private Context context = null;
    private LocalActivityManager manager = null;

    private Button pageButton1;
    private Button pageButton2;
    private Button pageButton3;
    private Button pageButton4;
    private Button pageButton5;
    private Button pageButton6;
    private Button pageButton7;
    private Button pageButton8;

    private TextView pageText1;
    private TextView pageText2;
    private TextView pageText3;
    private TextView pageText4;
    private TextView pageText5;
    private TextView pageText6;
    private TextView pageText7;
    private TextView pageText8;

    private ViewPager viewPager;

    private Drawable select_background;
    private Drawable background;

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_save:
                    msg += "Save";
                    break;
            }

            if(!msg.equals("")) {
                Toast.makeText(CreateSceneActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            finish();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createscene);

        context = this.getApplicationContext();
        manager = new LocalActivityManager(this , true);
        manager.dispatchCreate(savedInstanceState);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(context.getResources().getString(R.string.title_activity_createscene));
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

        viewPager = (ViewPager) this.findViewById(R.id.viewPager);

        select_background = context.getResources().getDrawable(R.drawable.img_step_selected);
        background = context.getResources().getDrawable(R.drawable.img_step_nor);

        pageButton1.setBackground(select_background);
        pageText1.setVisibility(View.VISIBLE);

        //new 一個ArrayList<view> 來存放每個Page
        final ArrayList<View> arrayView = new ArrayList<View>();
        Intent intent1 = new Intent(context, Create_ViewPager1_Activity.class);
        Intent intent2 = new Intent(context, Create_ViewPager2_Activity.class);
        Intent intent3 = new Intent(context, Create_ViewPager3_Activity.class);
        Intent intent4 = new Intent(context, Create_ViewPager4_Activity.class);
        Intent intent5 = new Intent(context, Create_ViewPager5_Activtiy.class);
        Intent intent6 = new Intent(context, Create_ViewPager6_Activity.class);
        Intent intent7 = new Intent(context, Create_ViewPager7_Activity.class);
        Intent intent8 = new Intent(context, Create_ViewPager8_Activity.class);
        arrayView.add(getView("Create_ViewPager1_Activity", intent1));
        arrayView.add(getView("Create_ViewPager2_Activity", intent2));
        arrayView.add(getView("Create_ViewPager3_Activity", intent3));
        arrayView.add(getView("Create_ViewPager4_Activity", intent4));
        arrayView.add(getView("Create_ViewPager5_Activity", intent5));
        arrayView.add(getView("Create_ViewPager6_Activity", intent6));
        arrayView.add(getView("Create_ViewPager7_Activity", intent7));
        arrayView.add(getView("Create_ViewPager8_Activity", intent8));

        //new 一個ArrayList 來放每個Page 的 Title
        final ArrayList<String> titleArray = new ArrayList<String>();
        titleArray.add("Page1");
        titleArray.add("Page2");
        titleArray.add("Page3");
        titleArray.add("Page4");
        titleArray.add("Page5");
        titleArray.add("Page6");
        titleArray.add("Page7");
        titleArray.add("Page8");

        PagerAdapter apdter = new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return arrayView.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                ((ViewPager) container).removeView(arrayView.get(position));
            }

            @Override
            public CharSequence getPageTitle(int position) {
                // TODO Auto-generated method stub

                return titleArray.get(position);//這裡需回傳Title的名稱,position就是每個Page的index
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                ((ViewPager) container).addView(arrayView.get(position));
                return arrayView.get(position);
            }

        };
        viewPager.setAdapter(apdter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {//監聽當ViewPager被改變Page時

            private boolean isScrolling = false;
            private int lastValue = -1;

            @Override
            public void onPageSelected(int arg0) {
                // TODO Auto-generated method stub
                setImageAndText(arg0);
            }


            @Override
            public void onPageScrolled(int index, float arg1, int arg2) {
                //onPageScrolled
            }

            @Override
            public void onPageScrollStateChanged(int index) {
            }

            public void setImageAndText(int index){
                for(int i=0;i<8;i++){
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
                    switch (index){
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
            }
        });
    }
    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private View getView(String id, Intent intent) {
        return manager.startActivity(id, intent).getDecorView();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_createscene, menu);
        return true;
    }
}
