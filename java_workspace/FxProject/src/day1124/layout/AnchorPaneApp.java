package day1124.layout;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//Application�� ��ӹ޴� ����, �����ֱ� �޼��带 �̿��� �� �ִ�
public class AnchorPaneApp extends Application{
	

	//���� �����Ǳ� ������ �� ȣ��� (Application Thread : UI, Event ���� thread�� ���� ȣ��
	//start()�� ������ ȣ���. ���� ���� ������ �� �ַ� ������ ������ ���⼭ ó���ϸ� �ȴ�
	public void start(Stage stage) throws Exception {
		//stage? �� �ۿ��� ���� �����츦 stage�� �Ѵ�

		//������ �ȿ��� �ݵ�� ��(Scene)�̶� �θ��� �����̳�. ��, ���̾ƿ� ��ü�� �;� �Ѵ�
		//�ϳ��� ������ �ȿ��� �ϳ��� ���� �� �� �ִ�
		AnchorPane parent = new AnchorPane();  //absolute(������ġ)�� ��ġ�� ������ �� �ִ� pane;
		
		//��ư 3���� �����Ͽ� ������ġ�� ��ġ�غ���
		Button bt1 = new Button("��ư1");
		Button bt2 = new Button("��ư2");
		Button bt3 = new Button("��ư3");
		
		//��ư�� ��ġ ����
		bt1.setLayoutX(0);
		bt1.setLayoutY(50);

		bt2.setLayoutX(100);
		bt2.setLayoutY(50);
		
		bt3.setLayoutX(200);
		bt3.setLayoutY(50);
		
		
		parent.getChildren().add(bt1);
		parent.getChildren().add(bt2);
		parent.getChildren().add(bt3);
		
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
		launch(args);  //�� ����
	}

}
