

package day1125.web;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class WebViewApp extends Application{
	WebView webView;  //HTML문서를 포함할 수 있는 컨테이너
	
	public void start(Stage stage) throws Exception {
		webView = new WebView();
		webView = new WebView();
		webView.getEngine().load("https://www.youtube.com/watch?v=wVbjkI6d5RI");
		webView.setPrefSize(1200, 900);
		
		showWindow(stage, webView);
		
	}
	
	public void showWindow(Stage stage, Parent parent) {
		Scene s = new Scene(parent);  //씬 생성
		stage.setScene(s);  //생성된 씬을 윈도우에 적용
		stage.setWidth(500);  //너비
		stage.setHeight(500);  //높이
		stage.show();  //윈도우 보여주기		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
