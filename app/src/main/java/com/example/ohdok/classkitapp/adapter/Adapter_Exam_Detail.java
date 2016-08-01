package com.example.ohdok.classkitapp.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ohdok.classkitapp.dao.Item_Exam_Detail;
import com.example.ohdok.classkitapp.R;

import java.util.List;

public class Adapter_Exam_Detail extends RecyclerView.Adapter<Adapter_Exam_Detail.ViewHolder> {
    private List<Item_Exam_Detail> mItem;
    public Context mContext;

    public Adapter_Exam_Detail(Context mContext, List<Item_Exam_Detail> mItem) {
        this.mItem = mItem;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_exam_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Item_Exam_Detail item = mItem.get(position);

        holder.subjectName.setText(item.getSubjectName());
        holder.rowCut.setText(item.getRowCut());

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItem.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView subjectName, rowCut;
        public CardView mCardView;

        public ViewHolder(final View itemView) {
            super(itemView);

            subjectName = (TextView) itemView.findViewById(R.id.recyclerview_exam_detail_subject);
            rowCut = (TextView) itemView.findViewById(R.id.recyclerview_exam_detail_rowcut);
            mCardView = (CardView) itemView.findViewById(R.id.recyclerview_exam_cardview);
        }
    }
}