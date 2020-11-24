package day1124.layout;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class TilePaneApp extends Application{

	public void start(Stage stage) throws Exception {
		TilePane parent = new TilePane(Orientation.VERTICAL, 10, 5);
		
		Button[] btn = new Button[100];
		for(int i=0; i<btn.length; i++) {
			btn[i] = new Button("bt" + i);
			btn[i].setPrefSize(50, 50);
			parent.getChildren().add(btn[i]);
		}
		
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
