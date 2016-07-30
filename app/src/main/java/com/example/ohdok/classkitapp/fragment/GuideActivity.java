package com.example.ohdok.classkitapp.fragment;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ohdok.classkitapp.R;

public class GuideActivity extends Fragment {

    public static GuideActivity newInstance() {
        GuideActivity fragment = new GuideActivity();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("안내방송");
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.BLACK);
        toolbar.setNavigationIcon(R.drawable.menu_icon_black);
// Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_guide, container, false);
    }

}
