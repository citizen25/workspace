package day1127.youtube;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class YoutubeApp extends Application{
	
	public void start(Stage stage) throws Exception {
		BorderPane borderPane = (BorderPane)FXMLLoader.load(this.getClass().getClassLoader().getResource("day1127/youtube/layout.fxml"));
		
		showWindow(stage, borderPane);
	}
	
	public void showWindow(Stage stage, Parent parent) {
		Scene s = new Scene(parent);  //씬 생성
		stage.setScene(s);  //생성된 씬을 윈도우에 적용
		stage.setWidth(800);  //너비
		stage.setHeight(700);  //높이
		stage.show();  //윈도우 보여주기		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
