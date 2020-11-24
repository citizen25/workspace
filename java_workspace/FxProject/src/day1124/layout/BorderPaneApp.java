package day1124.layout;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

//BorderPane은 AWT에서의 BorderLayout과 동일

public class BorderPaneApp extends Application{

	public void start(Stage stage) throws Exception {
		//BorderPane 생성
		BorderPane parent = new BorderPane();
		Button bt1 = new Button("Top");
		Button bt2 = new Button("Right");
		Button bt3 = new Button("Button");
		Button bt4 = new Button("Left");
		Button bt5 = new Button("Center");
		
		parent.setTop(bt1);
		parent.setRight(bt2);
		parent.setBottom(bt3);
		parent.setLeft(bt4);
		parent.setCenter(bt5);
		
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
