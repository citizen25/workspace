

package day1125.web;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class WebViewApp extends Application{
	WebView webView;  //HTML������ ������ �� �ִ� �����̳�
	
	public void start(Stage stage) throws Exception {
		webView = new WebView();
		webView = new WebView();
		webView.getEngine().load("https://www.youtube.com/watch?v=wVbjkI6d5RI");
		webView.setPrefSize(1200, 900);
		
		showWindow(stage, webView);
		
	}
	
	public void showWindow(Stage stage, Parent parent) {
		Scene s = new Scene(parent);  //�� ����
		stage.setScene(s);  //������ ���� �����쿡 ����
		stage.setWidth(500);  //�ʺ�
		stage.setHeight(500);  //����
		stage.show();  //������ �����ֱ�		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
