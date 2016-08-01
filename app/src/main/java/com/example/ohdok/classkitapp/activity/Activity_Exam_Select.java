package com.example.ohdok.classkitapp.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.example.ohdok.classkitapp.dao.Item_Exam;
import com.example.ohdok.classkitapp.R;
import com.example.ohdok.classkitapp.adapter.Adapter_Exam;

import java.util.ArrayList;


public class Activity_Exam_Select extends AppCompatActivity {

    private int ACTIVITY_EXAM_SELECT_SIZE = 20;

    Toolbar mToolbar;
    int grade, year;
    String[] examName;
    String[] MM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_select);

        initActionBar();

        Intent intent = getIntent();
        grade = intent.getIntExtra("grade", 1);
        year = intent.getIntExtra("year", 2015);


        String YY = Integer.toString(year % 2000);
        if (YY.length() == 1) YY = "0" + YY;
        Log.d("", "exam" + grade + "_" + YY + "_month");
        MM = getResources().getStringArray(getResources().getIdentifier("exam" + grade + "_" + YY + "_month", "array", "com.example.ohdok.classkitapp"));

        int examNumber = MM.length;


        examName = new String[examNumber];
        for (int i=0 ; i<examNumber ; i++) {
            Log.d("", "exam" + grade + "_" + YY + MM[i] + "_name_full");
            examName[i] = getStringResourcesByName("exam" + grade + "_" + YY + MM[i] + "_name_full");
        }

        initRecyclerView();
    }

    public int getIntResources(int id) {
        return Integer.parseInt(getResources().getString(id));
    }
    public int getIntResourcesByName(String name) {
        return Integer.parseInt(getResources().getString(getResources().getIdentifier(name, "string", "com.example.ohdok.classkitapp")));
    }

    public String getStringResources(int id) {
        return getResources().getString(id);
    }
    public String getStringResourcesByName(String name) {
        return getResources().getString(getResources().getIdentifier(name, "string", "com.example.ohdok.classkitapp"));
    }

    public void initActionBar() {
        mToolbar = (Toolbar) findViewById(R.id.exam_select_toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("조회할 시험 선택");
    }

    public void initRecyclerView() {
        RecyclerView mRecyclerView = (RecyclerView)findViewById(R.id.exam_select_recyclerview);

        ArrayList<Item_Exam> mItemArray = new ArrayList<>();

        for(int i=0 ; i<examName.length ; i++) {
            Item_Exam mItem = new Item_Exam();
            mItem.setName(examName[i]);
            mItem.setSize(ACTIVITY_EXAM_SELECT_SIZE);
            mItem.setYear(year);
            mItem.setMonth(MM);
            mItemArray.add(mItem);
        }

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(Activity_Exam_Select.this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        Adapter_Exam mAdapter = new Adapter_Exam(getApplicationContext(), mItemArray, false, grade);
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
