package com.demo.zeroricx.software152.util;

import com.demo.zeroricx.software152.bean.Course;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 13040 on 2016/7/31.
 */
public class ParseHtml {
    String DataString = null;
    Document document = null;
    ArrayList<Course> courses = null;

    public ParseHtml(String url, String requestNUM) {
        GetAllThings getAllThings = new GetAllThings(url, requestNUM);
        String temp = getAllThings.getData;

        while (temp.length() < 20) {
            temp = getAllThings.getData;
        }
        this.DataString = getAllThings.getData;
        getScores();
    }

    public ArrayList<Course> getCourses() {
        return courses;
    } //return a list of courses parsed from text

    private void getScores() {
        document = Jsoup.parse(DataString);
        courses = new ArrayList<Course>();
        Elements alScores = document.getElementsByClass("dg1-item");
        if (!alScores.isEmpty()) {
            Pattern pattern = Pattern.compile(">(.*?)<");
            for (Element element : alScores) {// 这里的element是每一项科目
                Elements sElements = element.getElementsByTag("font");
                // System.out.println("++++++++++++++++++++++++++++++++++");
                Course tmp = new Course();
                for (Element element2 : sElements) {
                    Matcher matcher = pattern.matcher(element2.toString());
                    if (matcher.find()) {
                        tmp.addScoreData(matcher.group().replace("<", "").replace(">", "").toString());
                        //System.out.println(matcher.group().replace("<", "").replace(">", ""));
                    }
                }
                tmp.ParseToFields();//解析
                courses.add(tmp);
            }
        }
    } //Parse the PHP text by jsoup..[core]..

    class GetAllThings {
        URL MainRequestUrl;
        URLConnection MainConn;
        OutputStreamWriter outputStreamWriter;
        InputStreamReader inputStreamReader;
        String getData = "";

        public GetAllThings(String Url, String requestNUM) {
            try {
                MainRequestUrl = new URL(Url);
                MainConn = MainRequestUrl.openConnection();
                this.setConnection();// 设置强求头
                this.WriteDataToRequest(requestNUM);// 写入data
                this.ReadFromServer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void ReadFromServer() {
            try {
                this.inputStreamReader = new InputStreamReader(MainConn.getInputStream(), "utf-8");
                String temp = null;

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                while ((temp = bufferedReader.readLine()) != null) {
                    getData += temp;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void WriteDataToRequest(String requestNumNPWD) {
            try {
                // String thingstoWrite = "username="+num+"&password="+pwd;
                this.outputStreamWriter = new OutputStreamWriter(MainConn.getOutputStream());
                this.outputStreamWriter.write(requestNumNPWD);
                this.outputStreamWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void setConnection() {
            this.MainConn.setDoOutput(true);
            this.MainConn.setRequestProperty("Accept",
                    "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            this.MainConn.setRequestProperty("Content-Length", "39");
            this.MainConn.setRequestProperty("Host", "online.gxut.edu.cn");
            this.MainConn.setRequestProperty("Origin", "http://online.gxut.edu.cn");
            this.MainConn.setRequestProperty("Referer", "http://online.gxut.edu.cn/chengji/");
            this.MainConn.setRequestProperty("Upgrade-Insecure-Requests", "1");
            this.MainConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 "
                    + "(KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36");

        }
    }//class to get a PHP text..
}
