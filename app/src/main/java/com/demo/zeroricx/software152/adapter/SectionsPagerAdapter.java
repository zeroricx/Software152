package com.demo.zeroricx.software152.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.demo.zeroricx.software152.ScheduleFragment;
import com.demo.zeroricx.software152.ScoreFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private String mUserName;
    private String mPassWord;
    private ScoreFragment mScoreFragment;
    private ScheduleFragment mScheduleFragment;

    public SectionsPagerAdapter(FragmentManager fm, Context context, String userName, String passWord) {
        super(fm);
        mContext = context;
        mUserName = userName;
        mPassWord = passWord;
        mScheduleFragment = ScheduleFragment.newInstance(mContext,mUserName,mPassWord);
        mScoreFragment = ScoreFragment.newInstance(mContext,mUserName,mPassWord);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return mScheduleFragment;
            case 1:
                return mScoreFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "课表";
            case 1:
                return "成绩";
        }
        return null;
    }
}