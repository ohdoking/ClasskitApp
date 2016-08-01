package com.example.ohdok.classkitapp.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ohdok.classkitapp.R;
import com.example.ohdok.classkitapp.SwipeDismissListViewTouchListener;
import com.example.ohdok.classkitapp.SwipeDismissTouchListener;

import java.util.ArrayList;
import java.util.Arrays;

public class GuideActivity extends Fragment {

    private SectionsPagerAdapter2 mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private FragmentActivity myContext;


    public static GuideActivity newInstance() {
        GuideActivity fragment = new GuideActivity();
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

        getActivity().setTitle("안내방송");
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.BLACK);
        toolbar.setNavigationIcon(R.drawable.menu_icon_black);
// Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.activity_guide, container, false);

        mSectionsPagerAdapter = new SectionsPagerAdapter2(myContext.getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) view.findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout)view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);



        return view;
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment2 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number1";

        public PlaceholderFragment2() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment2 newInstance(int sectionNumber) {
            PlaceholderFragment2 fragment = new PlaceholderFragment2();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_broadcasting, container, false);
            ListView listview;

            listview = (ListView) rootView.findViewById(R.id.listview);
            final ArrayAdapter<String> mAdapter;

            String[] items;

            // Set up ListView example
            items = new String[20];
            for (int i = 0; i < items.length; i++) {
                items[i] = "Item " + (i + 1);
            }

            mAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1,
                    new ArrayList<String>(Arrays.asList(items)));
            listview.setAdapter(mAdapter);
            //ListView listView = getListView();

            // Create a ListView-specific touch listener. ListViews are given special treatment because
            // by default they handle touches for their list items... i.e. they're in charge of drawing
            // the pressed state (the list selector), handling list item clicks, etc.
            SwipeDismissListViewTouchListener touchListener =
                    new SwipeDismissListViewTouchListener(
                            listview,
                            new SwipeDismissListViewTouchListener.DismissCallbacks() {
                                @Override
                                public boolean canDismiss(int position) {
                                    return true;
                                }

                                @Override
                                public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                                    for (int position : reverseSortedPositions) {
                                        mAdapter.remove(mAdapter.getItem(position));
                                    }
                                    mAdapter.notifyDataSetChanged();
                                }
                            });
            listview.setOnTouchListener(touchListener);
            // Setting this scroll listener is required to ensure that during ListView scrolling,
            // we don't look for swipes.
            listview.setOnScrollListener(touchListener.makeScrollListener());

            // Set up normal ViewGroup example
            final ViewGroup dismissableContainer = (ViewGroup) rootView.findViewById(R.id.dismissable_container);
            for (int i = 0; i < items.length; i++) {
                final Button dismissableButton = new Button(this.getActivity());
                dismissableButton.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                dismissableButton.setText("Button " + (i + 1));
                dismissableButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(),
                                "Clicked " + ((Button) view).getText(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
                // Create a generic swipe-to-dismiss touch listener.
                dismissableButton.setOnTouchListener(new SwipeDismissTouchListener(
                        dismissableButton,
                        null,
                        new SwipeDismissTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(Object token) {
                                return true;
                            }

                            @Override
                            public void onDismiss(View view, Object token) {
                                dismissableContainer.removeView(dismissableButton);
                            }
                        }));
                dismissableContainer.addView(dismissableButton);
            }
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Toast.makeText(getActivity(), "Clicked " + mAdapter.getItem(position).toString(), Toast.LENGTH_SHORT).show();
                }
            });
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter2 extends FragmentPagerAdapter {

        public SectionsPagerAdapter2(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment2.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "교무실";
                case 1:
                    return "학년실";
                case 2:
                    return "방송실";
                case 3:
                    return "미술실";
            }
            return null;
        }
    }

}
