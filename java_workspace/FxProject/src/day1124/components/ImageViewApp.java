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
		1) String�� url�� �����ϴ� ��
		url = "https://images.unsplash.com/photo-1533450718592-29d45635f0a9?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxzZWFyY2h8MXx8anBnfGVufDB8fDB8&auto=format&fit=crop&w=500&q=60";
		imageView = new ImageView(url);
		*/
		
		
		//2)Image ��ü�� �̿��ϴ� ���
		url = "https://images.unsplash.com/photo-1533450718592-29d45635f0a9?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxzZWFyY2h8MXx8anBnfGVufDB8fDB8&auto=format&fit=crop&w=500&q=60";
		Image img = new Image(url);
		imageView = new ImageView(img);
		
		//�̹����� ������ ��ü������ ����Ͽ� �����ϴ� ���
		imageView.setPreserveRatio(true);  //���� ����
		imageView.setFitWidth(300);
		imageView.setFitHeight(300);
		
		FlowPane parent = new FlowPane(imageView);  //add ��ſ� �̷� ��ĵ� ����
		
		showWindow(stage, parent);
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
