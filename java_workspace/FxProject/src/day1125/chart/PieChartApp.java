package day1125.chart;

import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

public class PieChartApp extends Application{
	
	PieChart pie;
	
	public void start(Stage stage) throws Exception {
		pie = new PieChart();
		
		PieChart.Data s1 = new PieChart.Data("안드로이드폰", 6);
			//안드로이드가 60% 차지
		PieChart.Data s2 = new PieChart.Data("아이폰", 3);
			//아이폰이 30% 차지
		PieChart.Data s3 = new PieChart.Data("윈도우폰", 1);
			//윈도우폰이 10% 차지
		
		//파이 차트에 적용
		pie.getData().add(s1);
		pie.getData().add(s2);
		pie.getData().add(s3);
		
		pie.setLegendSide(Side.LEFT);
		
		showWindow(stage, pie);
	}
	
	public void showWindow(Stage stage, Parent parent) {
		Scene s = new Scene(parent);  //씬 생성
		stage.setScene(s);  //생성된 씬을 윈도우에 적용
		stage.setWidth(500);  //너비
		stage.setHeight(500);  //높이
		stage.show();  //윈도우 보여주기		
	}
	
	public static void main(String[] args){
		launch(args);
	}

}
