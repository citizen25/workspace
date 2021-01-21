package com.study.app0121;

// 자바도 html문서처럼, web server와 http 통신이 가능하다

import android.util.Log;

import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;


public class ConnectManager extends Thread {
    String TAG = this.getClass().getName();

    URL url;
    HttpURLConnection con;    // http 통신을 위한 객체(header + body를 구성하여 서버와 데이터를 주고받는 stateless한 통신)

    String requestUrl;
    String data;

    // 이 객체를 생성하는 자는 주소와 json 데이터를 넘겨야 한다
    public ConnectManager(String requestUrl, String data) {
        this.requestUrl = requestUrl;
        this.data = data;
    }

    public void requestByGet() {    // Get방식으로 요청을 시도하는 메서드
        BufferedReader buffr = null;

        try {
            url = new URL("http://192.168.0.26:8888/rest/member");    // 요청 주소
            con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");

            // 서버로부터 응답 데이터 가져오기
            buffr = new BufferedReader(new InputStreamReader(con.getInputStream()));    // 바이트 기반 스트림

            StringBuilder sb = new StringBuilder();    // 문자열을 누적할 객체
            String data = null;
            while(true) {
                data = buffr.readLine();    // 한줄 읽어들이기
                if(data == null) {break;}    // 읽어들일 데이터가 없다면 무한루프 종료
                sb.append(data);    // 읽어들인 문자열을 누적시키자
            }
            System.out.println("서버가 보낸 응답 데이터는 " + sb.toString());

            int code = con.getResponseCode();    // 서버로부터 받은 응답코드 반환 (이 시점에 이미 서버에 요청을 완료하고 응답도 받은 상태)
            System.out.println("서버로부터 받은 응답 코드는 " + code);
        } catch(MalformedURLException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if(buffr != null) {
                try {
                    buffr.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int requestByPost() {    // Post방식으로 요청을 시도하는 메서드 (중요)
        BufferedWriter buffw = null;    // 버퍼 처리된 문자기반 스트림
        int code = 0;    // 서버의 응답 코드

        try {
            url = new URL(requestUrl);
            con = (HttpURLConnection)url.openConnection();
            // 데이터 형식을 header에 첨가해줘야, 서버 측에서 json데이터가 전송되어 온 곳임을 안다.
            // 이게 바로 HTTP프로토콜 간의 약속이다
            con.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            con.setRequestMethod("POST");
            con.setDoOutput(true);    // 서버에 데이터를 출력하기 위해 필요한 옵션
            // 요청을 떠나기 전에 준비할 것이 있다면, 여기서 준비하자. json문자열을 준비하자
            // JSON객체 자체가 아닌 문자열로 준비하는 이유 : 문자열이여야 통신이 가능하므로
			/*
			StringBuilder sb = new StringBuilder();
			sb.append("{");
			sb.append("\"m_id\":\"batman\",");
			sb.append("\"m_pass\":\"1234\",");
			sb.append("\"m_name\":\"배트맨\"");
			sb.append("}");
			*/

            // 실행 중인 프로그램에서 서버로 데이터를 보내야하므로, 출력 스트림으로 바꾸자
            buffw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream(), "UTF-8"));
            buffw.write(data);    // 버퍼 보내기
            buffw.flush();    // 버퍼 비우기

            code = con.getResponseCode();    // 요청 + 응답이 발생
            System.out.println("서버로부터 받은 응답 코드는 " + code);
        } catch(MalformedURLException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if(buffw != null) {
                try {
                    buffw.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return code;    // 응답 코드 반환
    }

    @Override
    public void run() {
        int code = requestByPost();
        Log.d(TAG, "서버로부터 받은 응답 코드는 " + code);
    }
}