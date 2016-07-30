package com.example.ohdok.classkitapp.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ohdok.classkitapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class FoodActivity extends Fragment {

    private ViewPager mViewPager;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private FragmentActivity myContext;

    public static FoodActivity newInstance() {
        FoodActivity fragment = new FoodActivity();
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_food2, container, false);
        getActivity().setTitle("식단조회");
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.BLACK);
        toolbar.setNavigationIcon(R.drawable.menu_icon_black);

        mSectionsPagerAdapter = new SectionsPagerAdapter(myContext.getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) view.findViewById(R.id.container);
        //lib
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout)view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        return view;
    }

    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_food, container, false);


            ListView listView = (ListView) rootView.findViewById(R.id.foodlist);
            ArrayList<String> arrayList = new ArrayList<String>();

            TextView month = (TextView) rootView.findViewById(R.id.month);
            TextView scount = (TextView) rootView.findViewById(R.id.scount);
            TextView kcount = (TextView) rootView.findViewById(R.id.kcount);

            //ImageView image1 = (ImageView) rootView.findViewById(R.id.image1);

            int i = getArguments().getInt(ARG_SECTION_NUMBER);

//            Calendar c = Calendar.getInstance();
//            System.out.println("Current time => " + c.getTime());
//
//            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
//            String formattedDate = df.format(c.getTime());

            java.util.Date today = new java.util.Date();
            SimpleDateFormat formatTime = new SimpleDateFormat("MMM dd", Locale.KOREAN);
            String formattedDate = formatTime.format(today);

            if(i == 1){
                arrayList.add("현미밥");
                arrayList.add("간장개장");
                arrayList.add("깍두기");
                arrayList.add("순대국");
                arrayList.add("김말이");
                arrayList.add("시금치");

                scount.setText("400");
                kcount.setText("2500");



                month.setText(formattedDate+" 조식 식단");

                //image1.setImageResource(R.drawable.timetable_icon);

            }
            else if(i == 2){
                arrayList.add("잡곡밥");
                arrayList.add("고추장 불고기");
                arrayList.add("배추김치");
                arrayList.add("된장국");
                arrayList.add("오이무침");
                arrayList.add("김 자반");

                scount.setText("150");
                kcount.setText("2000");
                month.setText(formattedDate+" 중식 식단");
            }
            else if(i == 3){
                arrayList.add("쌀밥");
                arrayList.add("삼겹살");
                arrayList.add("쌈장");
                arrayList.add("무국");
                arrayList.add("상추");
                arrayList.add("멸치자반");
                scount.setText("100");
                kcount.setText("3000");
                month.setText(formattedDate+" 석식 식단");
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, arrayList);
            listView.setAdapter(adapter);
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "조식";
                case 1:
                    return "중식";
                case 2:
                    return "석식";
            }
            return null;
        }



    }


}
