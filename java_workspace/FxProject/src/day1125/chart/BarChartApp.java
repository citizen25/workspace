//�ڷγ� ��Ȳ�� �������� ǥ��

package day1125.chart;

import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class BarChartApp extends Application{

	BarChart bar;
	CategoryAxis x;  //x������ ����� ����(����, �Ϲ�, �ƽþ�)
	NumberAxis y;  //y������ ����� ����(�ڷγ� ������ ��)
	
	public void start(Stage stage) throws Exception {
		x = new CategoryAxis();
		y = new NumberAxis();
		
		//x, y�࿡ label �ޱ�
		x.setLabel("Area");
		y.setLabel("People");
		
		//������ ���� XYChart.Series �̿�
		XYChart.Series s1 = new XYChart.Series();
		s1.setName("�ƽþ�");
		s1.getData().add(new XYChart.Data("20����", 20));
		
		XYChart.Series s2 = new XYChart.Series();
		s2.setName("����");
		s2.getData().add(new XYChart.Data("68����", 68));
		
		XYChart.Series s3 = new XYChart.Series();
		s3.setName("�Ϲ�");
		s3.getData().add(new XYChart.Data("300����", 300));
		
		bar = new BarChart(x, y);
		
		bar.setLegendSide(Side.RIGHT);
		
		//bar��Ʈ�� ������ ����
		bar.getData().addAll(s1, s2, s3);
		
		
		showWindow(stage, bar);
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
