package com.demo.zeroricx.software152.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.zeroricx.software152.bean.Course;
import com.demo.zeroricx.software152.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyScoreAdapter extends RecyclerView.Adapter<MyScoreAdapter.ViewHolder> {
    ArrayList<Course> mValues;

    public MyScoreAdapter(ArrayList<Course> list) {
        mValues = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_score, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            holder.mXueQi.setText(mValues.get(position).getCourseTerm() + "-   -   -   -   -   -   -   -   -   -   -   (⊙_⊙)");
            holder.mJiDian.setText(mValues.get(position).getCourseScorePoint());
            holder.mKeChengMing.setText(mValues.get(position).getCourseName());
            holder.mPingShiFen.setText(mValues.get(position).getCourseDailyScor());
            holder.mQiMo.setText(mValues.get(position).getCourseEnd());
            holder.mXueFen.setText(mValues.get(position).getCourseStudyScore());
            holder.mZongPing.setText(mValues.get(position).getCourseToalScore());
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.xueqi)
        TextView mXueQi;
        @BindView(R.id.kechengming)
        TextView mKeChengMing;
        @BindView(R.id.pingshifen)
        TextView mPingShiFen;
        @BindView(R.id.qimo)
        TextView mQiMo;
        @BindView(R.id.zongping)
        TextView mZongPing;
        @BindView(R.id.xuefen)
        TextView mXueFen;
        @BindView(R.id.jidian)
        TextView mJiDian;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}
