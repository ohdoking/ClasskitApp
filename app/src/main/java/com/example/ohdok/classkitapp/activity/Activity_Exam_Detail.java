package com.example.ohdok.classkitapp.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.example.ohdok.classkitapp.dao.Item_Exam_Detail;
import com.example.ohdok.classkitapp.R;
import com.example.ohdok.classkitapp.adapter.Adapter_Exam_Detail;

import java.util.ArrayList;


public class Activity_Exam_Detail extends AppCompatActivity {

    int grade, year;
    String YY, MM;

    String[] subjectName,rowCut;

    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_detail);

        Intent intent = getIntent();
        grade = intent.getIntExtra("grade", 3);
        year = intent.getIntExtra("year", 2015);
        MM = intent.getStringExtra("month");

        YY = Integer.toString(year % 2000);
        if (YY.length() == 1) YY = "0" + YY;

        initActionBar();

        int subjectNumber = getIntResourcesByName("exam" + grade + "_" + YY + MM + "_subject_number");
        subjectName = new String[subjectNumber];
        rowCut = new String[subjectNumber];


        for (int i=1 ; i<=subjectNumber ; i++) {
            subjectName[i-1] = getStringResourcesByName("exam" + grade + "_" + YY + MM + "_subject_" + i + "_name");
            rowCut[i-1] = "1등급 " + getStringResourcesByName("exam" + grade + "_" + YY + MM + "_subject_" + i + "_1_raw") + "점\n" +
                    "2등급 " + getStringResourcesByName("exam" + grade + "_" + YY + MM + "_subject_" + i + "_2_raw") + "점\n" +
                    "3등급 " + getStringResourcesByName("exam" + grade + "_" + YY + MM + "_subject_" + i + "_3_raw") + "점\n" +
                    "4등급 " + getStringResourcesByName("exam" + grade + "_" + YY + MM + "_subject_" + i + "_4_raw") + "점\n";
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
        mToolbar = (Toolbar) findViewById(R.id.exam_toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getStringResourcesByName("exam" + grade + "_" + YY + MM + "_name_full"));
    }

    public void initRecyclerView() {
        RecyclerView mRecyclerView = (RecyclerView)findViewById(R.id.exam_recyclerview);

        ArrayList<Item_Exam_Detail> mItemArray = new ArrayList<>();

        int startYear = getIntResourcesByName("exam" + grade + "_year_start");
        int endYear = getIntResourcesByName("exam" + grade + "_year_end");

        for(int i=0 ; i<subjectName.length ; i++) {
            Item_Exam_Detail mItem = new Item_Exam_Detail();
            mItem.setSubjectName(subjectName[i]);
            mItem.setRowCut(rowCut[i]);
            mItemArray.add(mItem);
        }

        LinearLayoutManager mLayoutManager = new GridLayoutManager(Activity_Exam_Detail.this, 2);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        Adapter_Exam_Detail mAdapter = new Adapter_Exam_Detail(getApplicationContext(), mItemArray);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        RecyclerViewHeader header = RecyclerViewHeader.fromXml(getApplicationContext(), R.layout.recyclerview_exam_detail_header);
        header.attachTo(mRecyclerView);

        TextView text = (TextView) header.findViewById(R.id.recyclerview_exam_detail_header_name);
        text.setText(getStringResourcesByName("exam" + grade + "_" + YY + MM + "_name_simple"));


        Button button = (Button) header.findViewById(R.id.recyclerview_exam_detail_header_button);
        if (year >= 2009) { //Download available
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(getStringResourcesByName("exam" + grade + "_" + YY + MM + "_url")));
                    startActivity(intent);
                }
            });
        }
        else button.setVisibility(View.GONE);

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
