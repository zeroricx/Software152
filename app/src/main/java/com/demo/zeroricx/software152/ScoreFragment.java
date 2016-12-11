package com.demo.zeroricx.software152;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.zeroricx.software152.adapter.MyScoreAdapter;
import com.demo.zeroricx.software152.bean.Course;
import com.demo.zeroricx.software152.util.ParseHtml;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ScoreFragment extends Fragment {

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rc_content)
    RecyclerView mRecyclerView;

    private Context mContext;
    private MyScoreAdapter mMyScoreAdapter;
    private ArrayList<Course> mValues;
    private String mUserName;
    private String mPassWord;

    private Unbinder mUnbinder;

    public ScoreFragment() {
    }

    public static ScoreFragment newInstance(Context context, String userName, String passWord) {
        ScoreFragment fragment = new ScoreFragment();
        fragment.setContext(context);
        fragment.setUserName(userName);
        fragment.setPassWord(passWord);
        return fragment;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public void setPassWord(String passWord) {
        mPassWord = passWord;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_score, container, false);

        mUnbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mValues = new ArrayList<Course>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestData();
            }
        });
        requestData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }


    private void requestData() {
        Observable.create(new Observable.OnSubscribe<ArrayList<Course>>() {
            @Override
            public void call(Subscriber<? super ArrayList<Course>> subscriber) {
//                ArrayList<Course> temp;
                mValues = new ParseHtml("http://online.gxut.edu.cn/chengji/getScore.php"
                        , "username=" + mUserName + "&password=" + mPassWord).getCourses();
//                if (temp != null)
//                    mValues = temp;
                subscriber.onNext(mValues);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<Course>>() {
                               @Override
                               public void onCompleted() {
                                   mSwipeRefreshLayout.setRefreshing(false);
                               }

                               @Override
                               public void onError(Throwable e) {
                                   mSwipeRefreshLayout.setRefreshing(false);
                               }

                               @Override
                               public void onNext(ArrayList<Course> courses) {
                                   if (mValues.size() != 0) {
                                       mMyScoreAdapter = new MyScoreAdapter(mValues);
                                       mRecyclerView.setAdapter(mMyScoreAdapter);
                                   }
                                   mSwipeRefreshLayout.setRefreshing(false);
                               }
                           }
                );
        if (mMyScoreAdapter != null)
            mMyScoreAdapter.notifyDataSetChanged();
    }
}
