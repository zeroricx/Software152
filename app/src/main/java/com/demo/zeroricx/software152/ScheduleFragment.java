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

import com.demo.zeroricx.software152.adapter.MyScheduleAdapter;
import com.demo.zeroricx.software152.bean.ScheduleBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ScheduleFragment extends Fragment {
    @BindView(R.id.srl_schedule)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.rv_schedule)
    RecyclerView mRecyclerView;

    private Context mContext;
    private String mUserName;
    private String mPassword;
    private ArrayList<ScheduleBean> mValues;

    private Unbinder mUnbinder;
    private Document mDocument;
    private MyScheduleAdapter mMyScheduleAdapter;

    public ScheduleFragment() {
    }

    public static ScheduleFragment newInstance(Context context, String userName, String password) {
        ScheduleFragment fragment = new ScheduleFragment();
        fragment.setContext(context);
        fragment.setUserName(userName);
        fragment.setPassword(password);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mValues = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestData();
            }
        });
        requestData();
    }

    private void requestData() {
        Observable.create(new Observable.OnSubscribe<ArrayList<ScheduleBean>>() {
            @Override
            public void call(Subscriber<? super ArrayList<ScheduleBean>> subscriber) {
                try {
                    mDocument = Jsoup.connect("http://online.gxut.edu.cn/cmzx/StudentsMediaCentre.php/Index/login/")
                            .data("userName", mUserName)
                            .data("password", mPassword)
                            .timeout(5000)
                            .post();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (mDocument != null) {
                    Elements elements = mDocument.select("section.container").select("tr");
                    for (Element element : elements) {
                        ScheduleBean temp = new ScheduleBean();
                        for (int i = 0; i < element.select("td").size(); i++) {
                            temp.addData(i, element.select("td").get(i).text());
                        }
                        if (temp.getTime().equals("课程代码"))
                            break;
                        mValues.add(temp);
                    }
                }
                subscriber.onNext(mValues);
            }

        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArrayList<ScheduleBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ArrayList<ScheduleBean> scheduleBeen) {
                        if (mValues.size() != 0) {
                            mMyScheduleAdapter = new MyScheduleAdapter(mValues);
                            mRecyclerView.setAdapter(mMyScheduleAdapter);
                        }
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
        if (mMyScheduleAdapter != null)
            mMyScheduleAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public void setPassword(String password) {
        mPassword = password;
    }


}
