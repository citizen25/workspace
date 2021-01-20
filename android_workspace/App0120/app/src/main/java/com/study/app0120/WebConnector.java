package com.study.app0120;

import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

// 스프링 웹 서버와 통신하기 위한 객체
public class WebConnector extends Thread {
    String TAG = this.getClass().getName();    // 클래스명을 담자. 로그 출력시 구분 태그명으로 쓰기 위해
    URL url;
    HttpURLConnection con;

    public void getData() {
        try {
            url = new URL("http://192.168.0.28:9999/rest/member");
            con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");

            // 서버에서 받은 응답 코드 확인
            int code = con.getResponseCode();
            Log.d(TAG, "서버로부터 받은 http 응답 코드 = " + code);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override    // 독립적으로 실행될 코드를 run에 작성한다
    public void run() {
        getData();
    }
}
