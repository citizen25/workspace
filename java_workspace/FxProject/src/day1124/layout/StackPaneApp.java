package day1124.layout;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

//StackPane : Stack������ ���� �� �ִ� ���̾ƿ�. AWT�� CardLayout�� ������ ���
//CardLayout : ������ ó���� ��������, �츮�� ������ ��ȯ ó���� ���� �����Ͽ� ����� �ʿ䰡 ������
public class StackPaneApp extends Application{

	public void start(Stage stage) throws Exception {
		StackPane parent = new StackPane();
		//��ư 4���� ���� ����
		Button bt1 = new Button("��ư1");
		Button bt2 = new Button("��ư2");
		Button bt3 = new Button("��ư3");
		Button bt4 = new Button("��ư4");
		
		//���� ���̴� ��ư�� ���� ũ�� ó��
		bt1.setPrefSize(100, 100);
		bt2.setPrefSize(150, 150);
		bt3.setPrefSize(200, 200);
		bt4.setPrefSize(250, 250);
		
		//��ư 4���� �־��
		parent.getChildren().add(bt4);
		parent.getChildren().add(bt3);
		parent.getChildren().add(bt2);
		parent.getChildren().add(bt1);
		
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
