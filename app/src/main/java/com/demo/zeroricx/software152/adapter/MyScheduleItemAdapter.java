package com.demo.zeroricx.software152.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.zeroricx.software152.R;
import com.demo.zeroricx.software152.bean.ScheduleBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyScheduleItemAdapter extends RecyclerView.Adapter<MyScheduleItemAdapter.ScheduleItemVH> {
    private ArrayList<ScheduleBean> mValues;
    private int mIndex;

    public MyScheduleItemAdapter(ArrayList<ScheduleBean> values, int index) {
        mValues = values;
        mIndex = index;
    }

    @Override
    public ScheduleItemVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_schedule_items, parent, false);
        return new ScheduleItemVH(view);
    }

    @Override
    public void onBindViewHolder(ScheduleItemVH holder, int position) {
        if (holder instanceof ScheduleItemVH) {
            holder.mTime.setText(mValues.get(position).getTime());
            holder.mNo.setText(mValues.get(position).getNo());
            holder.mCourseName.setText(mValues.get(position).getDays(mIndex));
        }

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ScheduleItemVH extends RecyclerView.ViewHolder {
        @BindView(R.id.schedule_item_tv_time)
        TextView mTime;
        @BindView(R.id.schedule_item_tv_no)
        TextView mNo;
        @BindView(R.id.schedule_item_tv_coursename)
        TextView mCourseName;

        public ScheduleItemVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
