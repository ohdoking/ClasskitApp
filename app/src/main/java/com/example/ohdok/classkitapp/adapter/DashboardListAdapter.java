package com.example.ohdok.classkitapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ohdok.classkitapp.common.DashboardViewHolder;
import com.example.ohdok.classkitapp.R;
import com.example.ohdok.classkitapp.dao.DashboardListData;

import java.util.ArrayList;

/**
 * Created by ohdok on 2016-07-30.
 */
public class DashboardListAdapter extends BaseAdapter {

    private Context context;
    public ArrayList<DashboardListData> mData;
    //어떤 리스트인지?
    private String state;

    public DashboardListAdapter(Context context, String state){
        this.context = context;
        this.state = state;
        mData = new ArrayList<DashboardListData>();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void addItem(DashboardListData data){
        mData.add(data);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        DashboardViewHolder holder;
        if(view == null){
            holder = new DashboardViewHolder();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_item,null);

            holder.arrow = (ImageView) view.findViewById(R.id.arrow);
            holder.state = (TextView) view.findViewById(R.id.state);
            holder.content = (TextView) view.findViewById(R.id.content);

            view.setTag(holder);
        }
        else{
            holder = (DashboardViewHolder)view.getTag();
        }

        DashboardListData mDataItem = mData.get(i);

        if(mDataItem.getState().equals("new")){
            holder.state.setText("NEW");
            holder.state.setTextColor(Color.parseColor("#ff580e"));
        }
        else{
            holder.state.setText("공 통");
            holder.state.setTextColor(Color.parseColor("#2a85c6"));
        }
        holder.content.setText(mDataItem.getContent());
        holder.content.setTextColor(Color.parseColor("#5d5d5d"));

        if(state.equals("plan")){
            holder.arrow.setVisibility(View.VISIBLE);
        }
        else{
            holder.arrow.setVisibility(View.GONE);
        }

        return view;
    }
}
