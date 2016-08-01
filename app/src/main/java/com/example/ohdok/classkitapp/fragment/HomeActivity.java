package com.example.ohdok.classkitapp.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.ohdok.classkitapp.R;
import com.example.ohdok.classkitapp.adapter.DashboardListAdapter;
import com.example.ohdok.classkitapp.dao.DashboardListData;
import com.panxw.android.imageindicator.AutoPlayManager;
import com.panxw.android.imageindicator.ImageIndicatorView;

public class HomeActivity extends Fragment {

    ListView guideListview;
    ListView planListview;

    ImageButton breakfastBtn;
    ImageButton lunchBtn;
    ImageButton dinnerBtn;

    DashboardListAdapter guideListAdapter;
    DashboardListAdapter planListAdapter;

    public static HomeActivity newInstance() {
        HomeActivity fragment = new HomeActivity();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("클래스킷");
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.menu_icon_white);
        toolbar.setTitleMarginStart(450);



        View view  = inflater.inflate(R.layout.content_home, container, false);
        guideListview = (ListView) view.findViewById(R.id.guide_list);
        planListview = (ListView) view.findViewById(R.id.plan_list);
        breakfastBtn = (ImageButton) view.findViewById(R.id.breakfast_btn);
        lunchBtn = (ImageButton) view.findViewById(R.id.lunch_btn);
        dinnerBtn = (ImageButton) view.findViewById(R.id.dinner_btn);


        if (savedInstanceState == null) {
            breakfastBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getFragmentManager().beginTransaction().replace(R.id.content_frame, new FoodActivity()).commit();
                }
            });
            lunchBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getFragmentManager().beginTransaction().replace(R.id.content_frame, new FoodActivity()).commit();
                }
            });
            dinnerBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getFragmentManager().beginTransaction().replace(R.id.content_frame, new FoodActivity()).commit();
                }
            });
        }


        ImageIndicatorView imageIndicatorView = (ImageIndicatorView) view.findViewById(R.id.indicate_view);
        final Integer[] resArray = new Integer[] { R.drawable.home_bg_img_01, R.drawable.home_bg_img_02 };
        imageIndicatorView.setupLayoutByDrawable(resArray);
        imageIndicatorView.setIndicateStyle(ImageIndicatorView.INDICATE_USERGUIDE_STYLE);
        imageIndicatorView.show();

        AutoPlayManager autoBrocastManager =  new AutoPlayManager(imageIndicatorView);
        autoBrocastManager.setBroadcastEnable(true);
        autoBrocastManager.setBroadCastTimes(3);//loop times
        autoBrocastManager.setBroadcastTimeIntevel(3 * 1000, 3 * 1000);//set first play time and interval
        autoBrocastManager.loop();

        guideListAdapter = new DashboardListAdapter(getActivity(), "guide");
        planListAdapter = new DashboardListAdapter(getActivity(), "plan");

        for (int i = 0; i < 3; i++) {
            DashboardListData data = new DashboardListData();
            if(i == 0){
                data.setState("new");
            }
            else{
                data.setState("old");
            }
            if(i==0){
                data.setContent("클래스킷 팀 상받았습니다.");
            }
            else if(i==1){
                data.setContent("유호재는 이연희다!");
            }
            else if(i==2){
                data.setContent("모두 수고하셨습니다.");
            }
            guideListAdapter.addItem(data);
        }

        for (int i = 0; i < 3; i++) {
            DashboardListData data = new DashboardListData();
            if(i == 1){
                data.setState("new");
            }
            else{
                data.setState("old");
            }
            if(i==0){
                data.setContent("클래스킷 팀 상받아서 회식합니다.");
            }
            else if(i==1){
                data.setContent("상준이가 스플래시화면 만들었다.");
            }
            else if(i==2){
                data.setContent("유니톤 7월 31일");
            }
            planListAdapter.addItem(data);
        }

        guideListview.setAdapter(guideListAdapter);
        planListview.setAdapter(planListAdapter);


        return view;
    }

}
