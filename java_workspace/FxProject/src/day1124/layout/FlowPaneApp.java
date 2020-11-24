package day1124.layout;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

//FlowPane은 AWT의 FlowLayout과 기능이 같다
//공간이 부족할 경우, 다음 행으로 컴포넌트들이 흘러 내려감

public class FlowPaneApp extends Application{

	public void start(Stage stage) throws Exception {
		//TilePane과 흡사하지만, TilePane은 자식 요소들의 크기가 모두 일정하다
		FlowPane parent = new FlowPane(Orientation.VERTICAL, 10, 10);
		
		Button bt1 = new Button("bt1");
		Button bt2 = new Button("bt2");
		Button bt3 = new Button("bt3");
		
		parent.getChildren().add(bt1);
		parent.getChildren().add(bt2);
		parent.getChildren().add(bt3);
		
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
