package test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppMain extends Application{
	//javaFX ���ø����̼��� �����ϱ� ���ؼ��� �ݵ�� Application �߻� Ŭ������ ��ӹ޾ƾ� �Ѵ�.

	//FX Application�� �����ֱ� �޼��尡 �����ȴ�
	
	public AppMain() {
		System.out.println("AppMain() �޼��� ȣ�� by " + Thread.currentThread().getName());
	}
	
	//���ø����̼��� ������ �غ� �Ǹ� ȣ���ϴ� �޼���
	public void start(Stage stage) throws Exception {
		System.out.println("start() �޼��� ȣ�� by " + Thread.currentThread().getName());
		//�Ű������� ���� stage������ ���ø����̼��� �������̴�
		
		//���븦 �����Ѵ�(fx������ �����쿡 �ݵ�� �ϳ��� Scene�� �����ؾ���)
		VBox parent = new VBox();  //�������� Ŀ����Ʈ�� ��ġ�ϴ� ���̾ƿ� ��ü
			//FlowLayout �� ����� ��ü
		Scene s = new Scene(parent);  //Parent��? �θ�Ŭ������ �ǹ��ϴ� ���� �ƴ϶�, ��ü�� ���԰��迡�� �ٱ��� �����̳ʸ� �ǹ�
			//Swing�� �������ڸ�, ���̾ƿ� ��ü�� Parent�̴�
		
		Button bt = new Button("��ư�Դϴ�");
		bt.setPrefWidth(200);
		bt.setPrefHeight(40);
		
		//��ư�� parent�� �����ϱ�
		parent.getChildren().add(bt);
		
		TextField t = new TextField("test");
		
		parent.getChildren().add(t);
		
		stage.setScene(s);  //���� �����쿡 ����
		
		bt.setOnAction((e)->{
			System.out.println("clicked");
		});
		
		stage.setMaxHeight(500);
		stage.setMaxWidth(500);
		stage.show();  //������ ���̰� (==setVisible())
	}
	
	//���ø����̼� ���� ��, �ʱ�ȭ�� ����ϴ� �޼����̴� 
	//���� �ʱ�ȭ�� �� ���ٸ� �����Ǵ� �ʼ��� �ƴϴ�
	//������ : �ν��Ͻ��� �¾ �� ȣ��Ǵ� �޼���
	//init() : �¾ ���Ŀ� ����Ǵ� �޼���
	public void init() throws Exception {
		//�ڹ��� �������� �޼��� �� ���� �������� ������ ������ �˱� ���� �޼���
		
		System.out.println("init() �޼��� ȣ�� by " + Thread.currentThread().getName());
	}
	
	public void stop() throws Exception {
		System.out.println("stop() �޼��� ȣ�� by " + Thread.currentThread().getName());
	}
	
	public static void main(String[] args) {
		System.out.println("launch() �޼��� ȣ�� by " + Thread.currentThread().getName());
		launch(args);  //���� ���
	}

}


//launch() �޼��� ȣ�� �� main thread �����ǰ�
//start(), stop()�� ���� thread�� �ϳ� ���������. -> UI, event ��
//���� init()�� ���� thread�� �ϳ� �߰��ȴ�
//FX�� UI, event ���� Application thread�� �����Ѵ�


/*���ø����̼��� thread ȣ�� ����
	launch() �޼��� ȣ�� by main
	AppMain() �޼��� ȣ�� by JavaFX Application Thread
	init() �޼��� ȣ�� by JavaFX-Launcher
	start() �޼��� ȣ�� by JavaFX Application Thread
	stop() �޼��� ȣ�� by JavaFX Application Thread
*/
