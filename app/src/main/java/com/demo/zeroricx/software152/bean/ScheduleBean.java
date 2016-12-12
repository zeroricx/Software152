package com.demo.zeroricx.software152.bean;

/**
 * Created by Administrator on 2016/12/11.
 */

public class ScheduleBean {
    private String mTime; // 时间
    private String mNo; // 节次
    private String mMonday; // 星期一
    private String mTuesday; // 星期二
    private String mWednesday; // 星期三
    private String mThursay; // 星期四
    private String mFriday; // 星期五
    private String mSaturday; // 星期六
    private String mSunday; // 星期日

    public void addData(int index, String data) {
        switch (index) {
            case 0:
                setTime(data);
                break;
            case 1:
                setNo(data);
                break;
            case 2:
                setMonday(data);
                break;
            case 3:
                setTuesday(data);
                break;
            case 4:
                setWednesday(data);
                break;
            case 5:
                setThursay(data);
                break;
            case 6:
                setFriday(data);
                break;
            case 7:
                setSaturday(data);
                break;
            case 8:
                setSunday(data);
        }
    }

    public String getTime() {
        return mTime;
    }

    public String getNo() {
        return mNo;
    }

    public void setTime(String time) {

        mTime = time;
    }

    public void setNo(String no) {
        mNo = no;
    }

    public void setMonday(String monday) {
        mMonday = monday;
    }

    public void setTuesday(String tuesday) {
        mTuesday = tuesday;
    }

    public void setWednesday(String wednesday) {
        mWednesday = wednesday;
    }

    public void setThursay(String thursay) {
        mThursay = thursay;
    }

    public void setFriday(String friday) {
        mFriday = friday;
    }

    public void setSaturday(String saturday) {
        mSaturday = saturday;
    }

    public void setSunday(String sunday) {
        mSunday = sunday;
    }

    public String getDays(int index) {
        switch (index) {
            case 2:
                return mMonday;
            case 3:
                return mTuesday;
            case 4:
                return mWednesday;
            case 5:
                return mThursay;
            case 6:
                return mFriday;
            case 7:
                return mSaturday;
            case 8:
                return mSunday;
            default:
                return null;
        }
    }
}
