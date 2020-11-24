package day1124.components;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ImageViewApp extends Application{
	String url;
	ImageView imageView;
	
	public void start(Stage stage) throws Exception {
		/*
		1) String의 url로 생성하는 법
		url = "https://images.unsplash.com/photo-1533450718592-29d45635f0a9?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxzZWFyY2h8MXx8anBnfGVufDB8fDB8&auto=format&fit=crop&w=500&q=60";
		imageView = new ImageView(url);
		*/
		
		
		//2)Image 객체를 이용하는 방법
		url = "https://images.unsplash.com/photo-1533450718592-29d45635f0a9?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxzZWFyY2h8MXx8anBnfGVufDB8fDB8&auto=format&fit=crop&w=500&q=60";
		Image img = new Image(url);
		imageView = new ImageView(img);
		
		//이미지의 비율을 자체적으로 계산하여 유지하는 방법
		imageView.setPreserveRatio(true);  //비율 유지
		imageView.setFitWidth(300);
		imageView.setFitHeight(300);
		
		FlowPane parent = new FlowPane(imageView);  //add 대신에 이런 방식도 가능
		
		showWindow(stage, parent);
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
