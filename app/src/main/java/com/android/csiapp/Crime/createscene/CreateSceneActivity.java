package com.android.csiapp.Crime.createscene;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.android.csiapp.Crime.utils.BackAlertDialog;
import com.android.csiapp.Crime.utils.FragmentAdapter;
import com.android.csiapp.Crime.utils.SaveAlertDialog;
import com.android.csiapp.Databases.CrimeItem;
import com.android.csiapp.Databases.IdentifyProvider;
import com.android.csiapp.R;

import java.util.ArrayList;
import java.util.List;

public class CreateSceneActivity extends AppCompatActivity implements OnPageChangeListener, OnClickListener{

    private Context context = null;

    // 記事物件
    private CrimeItem mItem;
    private int mEvent;

    private Button mPageButton1, mPageButton2, mPageButton3, mPageButton4, mPageButton5, mPageButton6, mPageButton7, mPageButton8;

    private TextView mPageText1, mPageText2, mPageText3, mPageText4, mPageText5, mPageText6, mPageText7, mPageText8;

    private CreateScene_FP1 mMyFragmentPage1;
    private CreateScene_FP2 mMyFragmentPage2;
    private CreateScene_FP3 mMyFragmentPage3;
    private CreateScene_FP4 mMyFragmentPage4;
    private CreateScene_FP5 mMyFragmentPage5;
    private CreateScene_FP6 mMyFragmentPage6;
    private CreateScene_FP7 mMyFragmentPage7;
    private CreateScene_FP8 mMyFragmentPage8;

    private ViewPager mViewPager;

    private Drawable mSelect_background;
    private Drawable mBackground;

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";

            switch (menuItem.getItemId()) {
                case R.id.action_save:
                    msg += "Save";
                    saveData();
                    Intent result = getIntent();
                    if(mItem.checkInformation()){
                        mItem.setComplete("1");
                        result.putExtra("com.android.csiapp.CrimeItem", mItem);
                        setResult(Activity.RESULT_OK, result);
                        finish();
                    }else{
                        mItem.setComplete("0");
                        result.putExtra("com.android.csiapp.CrimeItem", mItem);
                        SaveAlertDialog dialog = new SaveAlertDialog(CreateSceneActivity.this);
                        dialog.onCreateDialog(result,true,mItem);
                        dialog.setOwnerActivity(CreateSceneActivity.this);
                    }
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
                //What to do on back clicked
                BackAlertDialog dialog = new BackAlertDialog(CreateSceneActivity.this);
                dialog.onCreateDialog(true,mItem);
                dialog.setOwnerActivity(CreateSceneActivity.this);
            }
        });
        toolbar.setOnMenuItemClickListener(onMenuItemClick);

        // 取得Intent物件
        Intent intent = getIntent();
        // 讀取Action名稱
        String action = intent.getAction();
        if (action.equals("com.android.csiapp.ADD_SCENE")) {
            mItem = new CrimeItem();
            mEvent = 1;
            SharedPreferences prefs1 = context.getSharedPreferences("LoginName", 0);
            String login_name = prefs1.getString("name", "");
            mItem.setLoginName(login_name);
            SharedPreferences prefs2 = context.getSharedPreferences("UnitCode", 0);
            String unit_code = prefs2.getString("code", "");
            mItem.setUnitCode(unit_code);
        } else if(action.equals("com.android.csiapp.EDIT_SCENE")){
            title.setText(context.getResources().getString(R.string.title_activity_editscene));
            mItem = (CrimeItem) intent.getSerializableExtra("CrimeItem");
            mEvent = 2;
        }else{
            mItem = new CrimeItem();
        }

        mPageButton1 = (Button) this.findViewById(R.id.pagebutton1);
        mPageButton2 = (Button) this.findViewById(R.id.pagebutton2);
        mPageButton3 = (Button) this.findViewById(R.id.pagebutton3);
        mPageButton4 = (Button) this.findViewById(R.id.pagebutton4);
        mPageButton5 = (Button) this.findViewById(R.id.pagebutton5);
        mPageButton6 = (Button) this.findViewById(R.id.pagebutton6);
        mPageButton7 = (Button) this.findViewById(R.id.pagebutton7);
        mPageButton8 = (Button) this.findViewById(R.id.pagebutton8);

        mPageText1 = (TextView) this.findViewById(R.id.pageTextView1);
        mPageText2 = (TextView) this.findViewById(R.id.pageTextView2);
        mPageText3 = (TextView) this.findViewById(R.id.pageTextView3);
        mPageText4 = (TextView) this.findViewById(R.id.pageTextView4);
        mPageText5 = (TextView) this.findViewById(R.id.pageTextView5);
        mPageText6 = (TextView) this.findViewById(R.id.pageTextView6);
        mPageText7 = (TextView) this.findViewById(R.id.pageTextView7);
        mPageText8 = (TextView) this.findViewById(R.id.pageTextView8);

        mPageButton1.setOnClickListener(this);
        mPageButton2.setOnClickListener(this);
        mPageButton3.setOnClickListener(this);
        mPageButton4.setOnClickListener(this);
        mPageButton5.setOnClickListener(this);
        mPageButton6.setOnClickListener(this);
        mPageButton7.setOnClickListener(this);
        mPageButton8.setOnClickListener(this);

        mSelect_background = context.getResources().getDrawable(R.drawable.img_step_selected);
        mBackground = context.getResources().getDrawable(R.drawable.img_step_nor);
        mPageButton1.setBackground(mSelect_background);
        mPageText1.setVisibility(View.VISIBLE);

        mViewPager = (ViewPager) this.findViewById(R.id.viewPager);
        mMyFragmentPage1 = new CreateScene_FP1();
        mMyFragmentPage2 = new CreateScene_FP2();
        mMyFragmentPage3 = new CreateScene_FP3();
        mMyFragmentPage4 = new CreateScene_FP4();
        mMyFragmentPage5 = new CreateScene_FP5();
        mMyFragmentPage6 = new CreateScene_FP6();
        mMyFragmentPage7 = new CreateScene_FP7();
        mMyFragmentPage8 = new CreateScene_FP8();
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(mMyFragmentPage1);
        fragmentList.add(mMyFragmentPage2);
        fragmentList.add(mMyFragmentPage3);
        fragmentList.add(mMyFragmentPage4);
        fragmentList.add(mMyFragmentPage5);
        fragmentList.add(mMyFragmentPage6);
        fragmentList.add(mMyFragmentPage7);
        fragmentList.add(mMyFragmentPage8);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
        mViewPager.setAdapter(fragmentAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOnPageChangeListener((ViewPager.OnPageChangeListener) this);
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
                mViewPager.setCurrentItem(0);
                break;
            case R.id.pagebutton2:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.pagebutton3:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.pagebutton4:
                mViewPager.setCurrentItem(3);
                break;
            case R.id.pagebutton5:
                mViewPager.setCurrentItem(4);
                break;
            case R.id.pagebutton6:
                mViewPager.setCurrentItem(5);
                break;
            case R.id.pagebutton7:
                mViewPager.setCurrentItem(6);
                break;
            case R.id.pagebutton8:
                mViewPager.setCurrentItem(7);
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
        mPageButton1.setBackground(mBackground);
        mPageText1.setVisibility(View.GONE);
        mPageButton2.setBackground(mBackground);
        mPageText2.setVisibility(View.GONE);
        mPageButton3.setBackground(mBackground);
        mPageText3.setVisibility(View.GONE);
        mPageButton4.setBackground(mBackground);
        mPageText4.setVisibility(View.GONE);
        mPageButton5.setBackground(mBackground);
        mPageText5.setVisibility(View.GONE);
        mPageButton6.setBackground(mBackground);
        mPageText6.setVisibility(View.GONE);
        mPageButton7.setBackground(mBackground);
        mPageText7.setVisibility(View.GONE);
        mPageButton8.setBackground(mBackground);
        mPageText8.setVisibility(View.GONE);
        switch (position){
            case 0:
                mPageButton1.setBackground(mSelect_background);
                mPageText1.setVisibility(View.VISIBLE);
                mPageButton2.setBackground(mBackground);
                mPageText2.setVisibility(View.GONE);
                break;
            case 1:
                mPageButton1.setBackground(mBackground);
                mPageText1.setVisibility(View.GONE);
                mPageButton2.setBackground(mSelect_background);
                mPageText2.setVisibility(View.VISIBLE);
                mPageButton3.setBackground(mBackground);
                mPageText3.setVisibility(View.GONE);
                break;
            case 2:
                mPageButton2.setBackground(mBackground);
                mPageText2.setVisibility(View.GONE);
                mPageButton3.setBackground(mSelect_background);
                mPageText3.setVisibility(View.VISIBLE);
                mPageButton4.setBackground(mBackground);
                mPageText4.setVisibility(View.GONE);
                break;
            case 3:
                mPageButton3.setBackground(mBackground);
                mPageText3.setVisibility(View.GONE);
                mPageButton4.setBackground(mSelect_background);
                mPageText4.setVisibility(View.VISIBLE);
                mPageButton5.setBackground(mBackground);
                mPageText5.setVisibility(View.GONE);
                break;
            case 4:
                mPageButton4.setBackground(mBackground);
                mPageText4.setVisibility(View.GONE);
                mPageButton5.setBackground(mSelect_background);
                mPageText5.setVisibility(View.VISIBLE);
                mPageButton6.setBackground(mBackground);
                mPageText6.setVisibility(View.GONE);
                break;
            case 5:
                mPageButton5.setBackground(mBackground);
                mPageText5.setVisibility(View.GONE);
                mPageButton6.setBackground(mSelect_background);
                mPageText6.setVisibility(View.VISIBLE);
                mPageButton7.setBackground(mBackground);
                mPageText7.setVisibility(View.GONE);
                break;
            case 6:
                mPageButton6.setBackground(mBackground);
                mPageText6.setVisibility(View.GONE);
                mPageButton7.setBackground(mSelect_background);
                mPageText7.setVisibility(View.VISIBLE);
                mPageButton8.setBackground(mBackground);
                mPageText8.setVisibility(View.GONE);
                break;
            case 7:
                mPageButton7.setBackground(mBackground);
                mPageText7.setVisibility(View.GONE);
                mPageButton8.setBackground(mSelect_background);
                mPageText8.setVisibility(View.VISIBLE);
                break;
            case 8:
                mPageButton8.setBackground(mBackground);
                mPageText8.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public CrimeItem getItem (){
        return mItem;
    }

    public int getEvent(){
        return mEvent;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void saveData(){
        int page = mViewPager.getCurrentItem();
        switch (page){
            case 0:
                mMyFragmentPage1.saveData();
                break;
            case 5:
                mMyFragmentPage6.saveData();
                break;
            case 6:
                mMyFragmentPage7.saveData();
                break;
            default:
                break;
        }
    }
}
