package com.example.ohdok.classkitapp.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ohdok.classkitapp.R;
import com.example.ohdok.classkitapp.adapter.DashboardListAdapter;
import com.example.ohdok.classkitapp.adapter.FoodListAdapter;
import com.example.ohdok.classkitapp.dao.DashboardListData;
import com.example.ohdok.classkitapp.dao.FoodData;

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
        myContext = (FragmentActivity) activity;
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

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        return view;
    }

    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        public FoodListAdapter foodListAdapter;

        ImageView start1;
        ImageView start2;
        ImageView start3;
        ImageView start4;
        ImageView start5;

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

            TextView month = (TextView) rootView.findViewById(R.id.month);
            TextView scount = (TextView) rootView.findViewById(R.id.scount);
            TextView kcount = (TextView) rootView.findViewById(R.id.kcount);

            start1 = (ImageView) rootView.findViewById(R.id.star1);
            start2 = (ImageView) rootView.findViewById(R.id.star2);
            start3 = (ImageView) rootView.findViewById(R.id.star3);
            start4 = (ImageView) rootView.findViewById(R.id.star4);
            start5 = (ImageView) rootView.findViewById(R.id.star5);

            LinearLayout foodEvaluate = (LinearLayout) rootView.findViewById(R.id.food_evaluate);
            foodEvaluate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showLocationDialog();
                }
            });

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

            foodListAdapter = new FoodListAdapter(getActivity());
            if (i == 1) {

                scount.setText("400");
                kcount.setText("2500");

                month.setText(formattedDate + " 조식 식단");


                //image1.setImageResource(R.drawable.timetable_icon);
                for (int z = 0; z < 6; z++) {
                    FoodData data = new FoodData();
                    ArrayList<Integer> i2 = new ArrayList<Integer>();

                    if (z == 0) {
                        data.setFoodName("잡곡밥");
                        i2.add(R.drawable.tomato_icon);
                        i2.add(R.drawable.milk_icon);
                    } else if (z == 1) {
                        data.setFoodName("북어국");
                        i2.add(R.drawable.tomato_icon);
                        i2.add(R.drawable.buckwheat_icon);
                        i2.add(R.drawable.pork_icon);
                    } else if (z == 2) {
                        data.setFoodName("김말이");
                        i2.add(R.drawable.wheat_icon);
                        i2.add(R.drawable.milk_icon);
                    } else if (z == 3) {
                        data.setFoodName("떡볶이");
                        i2.add(R.drawable.tomato_icon);
                        i2.add(R.drawable.milk_icon);
                        i2.add(R.drawable.tomato_icon);
                        i2.add(R.drawable.buckwheat_icon);
                    } else if (z == 4) {
                        data.setFoodName("김치");
                        i2.add(R.drawable.tomato_icon);
                    } else if (z == 5) {
                        data.setFoodName("감자");
                        i2.add(R.drawable.tomato_icon);
                        i2.add(R.drawable.shrimp_icon);
                    }


                    data.setImageList(i2);
                    foodListAdapter.addItem(data);
                }

            } else if (i == 2) {

                scount.setText("150");
                kcount.setText("2000");
                month.setText(formattedDate + " 중식 식단");

                for (int z = 0; z < 4; z++) {
                    FoodData data = new FoodData();
                    ArrayList<Integer> i2 = new ArrayList<Integer>();
                    if (z == 0) {
                        data.setFoodName("콩밥");
                        i2.add(R.drawable.tomato_icon);
                        i2.add(R.drawable.milk_icon);
                    } else if (z == 1) {
                        data.setFoodName("순두부찌개");
                        i2.add(R.drawable.tomato_icon);
                        i2.add(R.drawable.milk_icon);
                        i2.add(R.drawable.tomato_icon);
                        i2.add(R.drawable.buckwheat_icon);

                    } else if (z == 2) {
                        data.setFoodName("두루치기");
                        i2.add(R.drawable.wheat_icon);
                        i2.add(R.drawable.milk_icon);
                    } else if (z == 3) {
                        data.setFoodName("양파튀김");
                        i2.add(R.drawable.tomato_icon);

                    } else if (z == 4) {
                        data.setFoodName("김치");
                        i2.add(R.drawable.tomato_icon);
                        i2.add(R.drawable.buckwheat_icon);
                        i2.add(R.drawable.pork_icon);
                    }

                    data.setImageList(i2);
                    foodListAdapter.addItem(data);
                }
            } else if (i == 3) {
                scount.setText("100");
                kcount.setText("3000");
                month.setText(formattedDate + " 석식 식단");

                for (int j = 0; j < 5; j++) {
                    FoodData data = new FoodData();

                    ArrayList<Integer> i2 = new ArrayList<Integer>();
                    if (j == 0) {
                        data.setFoodName("흰밥");
                        i2.add(R.drawable.milk_icon);
                        i2.add(R.drawable.wheat_icon);
                        i2.add(R.drawable.milk_icon);
                    } else if (j == 1) {
                        data.setFoodName("들개국수");
                        i2.add(R.drawable.tomato_icon);
                        i2.add(R.drawable.tomato_icon);
                    } else if (j == 2) {
                        data.setFoodName("마른안주");
                        i2.add(R.drawable.buckwheat_icon);
                        i2.add(R.drawable.pork_icon);
                    } else if (j == 3) {
                        data.setFoodName("깻잎");
                        i2.add(R.drawable.tomato_icon);
                    } else if (j == 4) {
                        data.setFoodName("고구마");
                        i2.add(R.drawable.tomato_icon);
                        i2.add(R.drawable.buckwheat_icon);
                    } else if (j == 5) {
                        data.setFoodName("불고기");
                        i2.add(R.drawable.tomato_icon);
                        i2.add(R.drawable.milk_icon);
                        i2.add(R.drawable.tomato_icon);
                        i2.add(R.drawable.buckwheat_icon);
                    }

                    data.setImageList(i2);
                    foodListAdapter.addItem(data);
                }

            }

//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, arrayList);

            listView.setAdapter(foodListAdapter);
            return rootView;
        }

        private void showLocationDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("식단의 평점");
//            builder.setMessage("식단의 평점을 매겨주세요");
            builder.setSingleChoiceItems(R.array.array_list,0,null);

            String positiveText = getString(android.R.string.ok);
            builder.setPositiveButton(positiveText,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                            Toast.makeText(getActivity(),selectedPosition+1+"",Toast.LENGTH_SHORT).show();

                            start1.setBackgroundResource(R.drawable.star_grey);
                            start2.setBackgroundResource(R.drawable.star_grey);
                            start3.setBackgroundResource(R.drawable.star_grey);
                            start4.setBackgroundResource(R.drawable.star_grey);
                            start5.setBackgroundResource(R.drawable.star_grey);

                            for(int j = 0; j < selectedPosition+1 ; j++){
                                if(j == 0){
                                    start1.setBackgroundResource(R.drawable.star_ylw);
                                }else if(j == 1){
                                    start2.setBackgroundResource(R.drawable.star_ylw);
                                }
                                else if(j == 2){
                                    start3.setBackgroundResource(R.drawable.star_ylw);
                                }
                                else if(j == 3){
                                    start4.setBackgroundResource(R.drawable.star_ylw);
                                }
                                else if(j == 4){
                                    start5.setBackgroundResource(R.drawable.star_ylw);
                                }
                            }
                        }
                    });

            String negativeText = getString(android.R.string.cancel);
            builder.setNegativeButton(negativeText,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // negative button logic
                            dialog.dismiss();
                        }
                    });

            AlertDialog dialog = builder.create();
            // display dialog
            dialog.show();
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
