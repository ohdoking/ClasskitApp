package com.example.ohdok.classkitapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ohdok.classkitapp.common.FoodViewHolder;
import com.example.ohdok.classkitapp.R;
import com.example.ohdok.classkitapp.dao.FoodData;

import java.util.ArrayList;

/**
 * Created by ohdok on 2016-07-31.
 */
public class FoodListAdapter extends BaseAdapter {

    private Context context;
    public ArrayList<FoodData> mData;
    //어떤 리스트인지?

    public FoodListAdapter(Context context){
        this.context = context;
        mData = new ArrayList<FoodData>();
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

    public void addItem(FoodData data){
        mData.add(data);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        FoodViewHolder holder;
        if(view == null){
            holder = new FoodViewHolder();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.food_listview_item,null);

            LinearLayout l = (LinearLayout) view.findViewById(R.id.food_layout);
            if(i%2 == 1) {
                l.setBackgroundColor(Color.parseColor("#eeeeee"));
            }
            holder.foodName = (TextView) view.findViewById(R.id.food_name);
            holder.food1 = (ImageView) view.findViewById(R.id.food_img1);
            holder.food2 = (ImageView) view.findViewById(R.id.food_img2);
            holder.food3 = (ImageView) view.findViewById(R.id.food_img3);
            holder.food4 = (ImageView) view.findViewById(R.id.food_img4);
            holder.food5 = (ImageView) view.findViewById(R.id.food_img5);

            view.setTag(holder);
        }
        else{
            holder = (FoodViewHolder)view.getTag();
        }

        FoodData mDataItem = mData.get(i);

        holder.foodName.setText(mDataItem.getFoodName());

        int count = 5;
        Log.i("ohdoking3",mDataItem.getImageList().size()+"");
        if(mDataItem.getImageList().size() < 5){
            count = mDataItem.getImageList().size();
        }
        for(int j = 0 ; j<count ;j++){
            if(j == 0){
                holder.food1.setBackgroundResource(mDataItem.getImageList().get(j));
            }else if(j == 1){
                holder.food2.setBackgroundResource(mDataItem.getImageList().get(j));
            }else if(j == 2){
                holder.food3.setBackgroundResource(mDataItem.getImageList().get(j));
            }else if(j == 3){
                holder.food4.setBackgroundResource(mDataItem.getImageList().get(j));
            }else if(j == 4){
                holder.food1.setBackgroundResource(mDataItem.getImageList().get(j));
            }
        }

        return view;
    }
}
