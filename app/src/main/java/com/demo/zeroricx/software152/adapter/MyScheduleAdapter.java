package com.demo.zeroricx.software152.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
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


public class MyScheduleAdapter extends RecyclerView.Adapter<MyScheduleAdapter.ViewHolder> {
    private ArrayList<ScheduleBean> mValues;
    private ScheduleBean mScheduleBean;
    private Context mContext;

    public MyScheduleAdapter(ArrayList<ScheduleBean> values) {
        mValues = values;
        mScheduleBean = mValues.get(0);
        mValues.remove(0);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_item_schedule, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            holder.mDays.setText(mScheduleBean.getDays(position + 2));
            holder.mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            holder.mRecyclerView.setAdapter(new MyScheduleItemAdapter(mValues, position + 2));
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_days)
        TextView mDays;
        @BindView(R.id.rv_schedule_item)
        RecyclerView mRecyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
