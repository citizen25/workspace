package test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

// java standard에서는 Socket 통신(stateful:연결 지속됨) 뿐만 아니라,
// Web Server와와 HTTP 돝신(stateless:연결 끊어짐)도 지원한다
public class HttpTest {
	URL url;
	HttpURLConnection con;    // HTTP 통신 객체
	OutputStream os;

	public HttpTest() {
		try {
			url = new URL("http://localhost:9999/rest/member");    // 요청 주소
			con = (HttpURLConnection)url.openConnection();    // connection 객체 생성
			con.setRequestMethod("POST");    // post 방식
			con.setRequestProperty("Content-Type", "application/json;utf-8");    // htp 통신 시 header 정보 구성
			//웹서버에 요청 시작(출력 스트림을 얻어와 출력을 해야함)
			con.setDoOutput(true);
			os = con.getOutputStream();
			os.write(0);
			os.flush();
			
			int code = con.getResponseCode();
			System.out.println(code);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		new HttpTest();
	}
}
