/*
package com.example.ohdok.classkitapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;

import java.util.ArrayList;
import java.util.List;


public class Activity_Exam extends AppCompatActivity {

    private int ACTIVITY_EXAM_SIZE = 30;

    Toolbar mToolbar;
    public static ViewPager mViewPager;
    private SamplePagerAdapter adapter;

    private static SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        initActionBar();
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        mViewPager = (ViewPager) findViewById(R.id.exam_viewpager);
        adapter = new SamplePagerAdapter();

        mViewPager.setAdapter(adapter);

        TabLayout mTabLayout = (TabLayout) findViewById(R.id.exam_tab);
        mTabLayout.setBackgroundColor(getResources().getColor(R.color.white));
        mTabLayout.setTabTextColors(getResources().getColor(R.color.anothergray), getResources().getColor(R.color.colorPrimary));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);

        mViewPager.setCurrentItem(pref.getInt("settings_basic_grade", 1) - 1);
    }

    class SamplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return position+1 + "학년";
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view;

            view = getLayoutInflater().inflate(R.layout.page_exam, container, false);
            container.addView(view);

            initRecyclerView(view, position+1);

            return view;
        }

        */
/**
         * Destroy the item from the {@link ViewPager}. In our case this is
         * simply removing the {@link View}.
         *//*

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

    public void initActionBar() {
        mToolbar = (Toolbar) findViewById(R.id.exam_toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("조회할 시험 선택");
    }
    public String getStringResourcesByName(String name) {
        return getResources().getString(getResources().getIdentifier(name, "string", "com.example.ohdok.classkitapp"));
    }

    public void initRecyclerView(View view, int grade) {
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.exam_recyclerview);

        ArrayList<Item_Exam> mItemArray = new ArrayList<>();
        Log.d("", "exam" + grade + "_year_start");
        int startYear = Integer.parseInt(getStringResourcesByName("exam" + grade + "_year_start"));
        int endYear = Integer.parseInt(getStringResourcesByName("exam" + grade + "_year_end"));

        for(int i=endYear+1 ; i>=startYear+1 ; i--) {
            Item_Exam mItem = new Item_Exam();
            mItem.setName(Integer.toString(i) + "학년도");
            mItem.setSize(ACTIVITY_EXAM_SIZE);
            mItemArray.add(mItem);
        }

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(Activity_Exam.this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        Adapter_Exam mAdapter = new Adapter_Exam(getApplicationContext(), mItemArray, true, grade);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        RecyclerViewHeader header = RecyclerViewHeader.fromXml(getApplicationContext(), R.layout.recyclerview_exam_header);
        header.attachTo(mRecyclerView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
*/
