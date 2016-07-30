package com.example.ohdok.classkitapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TimeTableActivity extends Fragment {

    public static TimeTableActivity newInstance() {
        TimeTableActivity fragment = new TimeTableActivity();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("시간표");
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.BLACK);
        toolbar.setNavigationIcon(R.drawable.menu_icon_black);


// Inflate the layout for this fragment
        return inflater.inflate(R.layout.content_timetable, container, false);
    }

}
