package day1124.layout;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ChatClient extends Application{
	
	public void start(Stage stage) throws Exception {

		FlowPane flow = new FlowPane();  //부분 레이아웃
		TextField t = new TextField();
		Button bt = new Button("ok");
		TextArea area = new TextArea();

		//스타일
		t.setPrefSize(400, 25);
		
		flow.getChildren().add(t);
		flow.getChildren().add(bt);		
		
		//전체 레이아웃
		BorderPane parent = new BorderPane();
		parent.setTop(flow);  //북쪽에 flow 부착
		parent.setCenter(area);  //센터에 area 부착
		
		
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
