package com.example.ohdok.classkitapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ohdok.classkitapp.activity.Activity_Exam_Detail;
import com.example.ohdok.classkitapp.activity.Activity_Exam_Select;
import com.example.ohdok.classkitapp.dao.Item_Exam;
import com.example.ohdok.classkitapp.R;

import java.util.List;

public class Adapter_Exam extends RecyclerView.Adapter<Adapter_Exam.ViewHolder> {
    private List<Item_Exam> mItem;
    public Context mContext;
    public boolean fromFirstActivity;
    int grade;

    public Adapter_Exam(Context mContext, List<Item_Exam> mItem, boolean fromFirstActivity, int grade) {
        this.mItem = mItem;
        this.mContext = mContext;
        this.fromFirstActivity = fromFirstActivity;
        this.grade = grade;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_exam, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Item_Exam item = mItem.get(position);

        holder.mTextView.setText(item.getName());
        holder.mTextView.setTextSize(item.getSize());

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                if (fromFirstActivity) {
                    int endYear = Integer.parseInt(mContext.getResources().getString(mContext.getResources().getIdentifier("exam" + grade + "_year_end", "string", "com.example.ohdok.classkitapp")));

                    i = new Intent(mContext, Activity_Exam_Select.class);
                    i.putExtra("grade", grade);
                    i.putExtra("year", endYear - position);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                }
                else {
                    i = new Intent(mContext, Activity_Exam_Detail.class);
                    i.putExtra("grade", grade);
                    i.putExtra("year", (item.getYear()));
                    i.putExtra("month", (item.getMonth()[position]));
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                }
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItem.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public CardView mCardView;

        public ViewHolder(final View itemView) {
            super(itemView);

            mTextView = (TextView) itemView.findViewById(R.id.recyclerview_exam_text);
            mCardView = (CardView) itemView.findViewById(R.id.recyclerview_exam_cardview);
        }
    }
}