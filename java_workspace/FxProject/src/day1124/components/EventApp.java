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
		//액션이벤트, 키보드이벤트, 마우스이벤트 등 처리
		bt = new Button("button");
		t = new TextField();
		imageView = new ImageView("https://images.unsplash.com/photo-1533450718592-29d45635f0a9?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxzZWFyY2h8MXx8anBnfGVufDB8fDB8&auto=format&fit=crop&w=500&q=60");
		imageView.setFitWidth(200);
		imageView.setFitHeight(200);
		imageView.setPreserveRatio(true);
		
		//----이벤트 소스와 이벤트핸들러 연결----
		
	
		/*bt.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				System.out.println("Button Clicked");
			}
		});*/
		bt.setOnAction((e)->{
			System.out.println("Button Clicked");
		});  //객체가 보유한 메서드가 단 하나이므로, 함수형 프로그래밍 방식으로 진행해도 된다(lambda)
		
		
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
		

		FlowPane flow = new FlowPane(bt, t, imageView);  //가변형 인자로 선언도니 생성자이므로
			//매개변수의 갯수를 실행타임에 결정지을 수 있다
		showWindow(stage, flow);
		
		//test(3, 5, 6, 7);
	}
	
	public void test(int... x) {  //갯수를 호출자가 결정
		System.out.println("당신이 넘긴 매개변수의 수는 " + x.length);
		for(int v : x) {
			System.out.println("값은 " + v);
		}
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
