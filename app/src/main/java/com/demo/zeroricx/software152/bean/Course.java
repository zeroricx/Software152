package com.demo.zeroricx.software152.bean;

import java.io.Serializable;

public class Course implements Serializable {
    private String data;
    private String studentNum;//学号
    private String studentName;//学生名字
    private String courseTerm;//学期
    private String courseName;//课程名字
    private String courseKind;//类别
    private String courseStudyScore;//学分
    private String courseDailyScor;//平时分
    private String courseMid;//期中
    private String courseEnd;//期末
    private String courseToalScore;//总评成绩
    private String courseExam;//考试性质
    private String courseScorePoint;//绩点
    private String courseCode;//课程代码
    private String courseHours;//课时

    public Course() {
        this.data = "";
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseStudyScore() {
        return courseStudyScore;
    }

    public String getCourseDailyScor() {
        return courseDailyScor;
    }

    public String getCourseEnd() {
        return courseEnd;
    }

    public String getCourseToalScore() {
        return courseToalScore;
    }

    public String getCourseTerm() {
        return courseTerm;
    }

    public String getCourseScorePoint() {
        return courseScorePoint;
    }

    public void addScoreData(String string) {
        this.data += string + "\n";
    }

    public void ParseToFields() {
        String[] lines = data.split("\n");
        this.studentNum = lines[0];
        this.studentName = lines[1];
        switch (lines[2]) {
            case "1":
                this.courseTerm = "① 上";
                break;
            case "2":
                this.courseTerm = "① 下";
                break;
            case "3":
                this.courseTerm = "② 上";
                break;
            case "4":
                this.courseTerm = "② 下";
                break;
        }
//        this.courseTerm = lines[2];
        this.courseName = lines[3];
        this.courseKind = lines[4];
        this.courseStudyScore = lines[5];
        this.courseDailyScor = lines[6];
        this.courseMid = lines[7];
        this.courseEnd = lines[8];
        this.courseToalScore = lines[9];
        this.courseExam = lines[10];
        this.courseScorePoint = lines[11];
        this.courseCode = lines[12];
        this.courseHours = lines[13];
    }

}
