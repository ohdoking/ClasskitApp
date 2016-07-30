package com.example.ohdok.classkitapp.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.example.ohdok.classkitapp.Adapter_Exam;
import com.example.ohdok.classkitapp.Item_Exam;
import com.example.ohdok.classkitapp.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GradeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GradeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GradeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private int ACTIVITY_EXAM_SIZE = 30;

    Toolbar mToolbar;
    public static ViewPager mViewPager;
    private SamplePagerAdapter adapter;

    private static SharedPreferences pref;

    public GradeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GradeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GradeFragment newInstance(String param1, String param2) {
        GradeFragment fragment = new GradeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        getActivity().setTitle("등급컷 조회");
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.BLACK);
        toolbar.setNavigationIcon(R.drawable.menu_icon_black);

        View view = inflater.inflate(R.layout.fragment_grade, container, false);

        pref = PreferenceManager.getDefaultSharedPreferences(getContext());

        mViewPager = (ViewPager) view.findViewById(R.id.exam_viewpager);
        adapter = new SamplePagerAdapter();

        mViewPager.setAdapter(adapter);

        TabLayout mTabLayout = (TabLayout) view.findViewById(R.id.exam_tab);
        mTabLayout.setBackgroundColor(getResources().getColor(R.color.white));
        mTabLayout.setTabTextColors(getResources().getColor(R.color.anothergray), getResources().getColor(R.color.colorPrimary));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);

        mViewPager.setCurrentItem(pref.getInt("settings_basic_grade", 1) - 1);

        return view ;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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

            view = getActivity().getLayoutInflater().inflate(R.layout.page_exam, container, false);
            container.addView(view);

            initRecyclerView(view, position+1);

            return view;
        }

        /**
         * Destroy the item from the {@link ViewPager}. In our case this is
         * simply removing the {@link View}.
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

    public String getStringResourcesByName(String name) {
        Log.i("ohdoking2",name);
        return getActivity().getResources().getString(getActivity().getResources().getIdentifier(name, "string", "com.example.ohdok.classkitapp"));
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

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        Adapter_Exam mAdapter = new Adapter_Exam(getContext(), mItemArray, true, grade);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        RecyclerViewHeader header = RecyclerViewHeader.fromXml(getContext(), R.layout.recyclerview_exam_header);
        header.attachTo(mRecyclerView);

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
