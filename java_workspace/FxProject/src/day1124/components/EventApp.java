package day1124.components;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class EventApp extends Application{
	Button bt;
	TextField t;
	ImageView imageView;
	
	public void start(Stage stage) throws Exception {
		//�׼��̺�Ʈ, Ű�����̺�Ʈ, ���콺�̺�Ʈ �� ó��
		bt = new Button("button");
		t = new TextField();
		imageView = new ImageView("https://images.unsplash.com/photo-1533450718592-29d45635f0a9?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxzZWFyY2h8MXx8anBnfGVufDB8fDB8&auto=format&fit=crop&w=500&q=60");
		imageView.setFitWidth(200);
		imageView.setFitHeight(200);
		imageView.setPreserveRatio(true);
		
		//----�̺�Ʈ �ҽ��� �̺�Ʈ�ڵ鷯 ����----
		
	
		/*bt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				System.out.println("Button Clicked");
			}
		});*/
		bt.setOnAction((e)->{
			System.out.println("Button Clicked");
		});  //��ü�� ������ �޼��尡 �� �ϳ��̹Ƿ�, �Լ��� ���α׷��� ������� �����ص� �ȴ�(lambda)
		
		
		/*t.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				if(e.getCode() == KeyCode.ENTER) {
					System.out.println("Enter Released");
				}
			};
		});*/
		t.setOnKeyReleased((e)->{
			if(e.getCode() == KeyCode.ENTER) {
				System.out.println("Enter Released");
			}
		});
		
		
		imageView.setOnMouseReleased((e)->{
			System.out.println("Mouse Released");
		});
		

		FlowPane flow = new FlowPane(bt, t, imageView);  //������ ���ڷ� ���𵵴� �������̹Ƿ�
			//�Ű������� ������ ����Ÿ�ӿ� �������� �� �ִ�
		showWindow(stage, flow);
		
		//test(3, 5, 6, 7);
	}
	
	public void test(int... x) {  //������ ȣ���ڰ� ����
		System.out.println("����� �ѱ� �Ű������� ���� " + x.length);
		for(int v : x) {
			System.out.println("���� " + v);
		}
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
