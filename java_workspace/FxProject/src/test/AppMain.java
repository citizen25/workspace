package test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppMain extends Application{
	//javaFX 어플리케이션을 정의하기 위해서는 반드시 Application 추상 클래스를 상속받아야 한다.

	//FX Application은 생명주기 메서드가 지원된다
	
	public AppMain() {
		System.out.println("AppMain() 메서드 호출 by " + Thread.currentThread().getName());
	}
	
	//어플리케이션이 실행할 준비가 되면 호출하는 메서드
	public void start(Stage stage) throws Exception {
		System.out.println("start() 메서드 호출 by " + Thread.currentThread().getName());
		//매개변수로 받은 stage변수가 어플리케이션의 윈도우이다
		
		//무대를 정의한다(fx에서는 윈도우에 반드시 하나의 Scene이 존재해야함)
		VBox parent = new VBox();  //수직으로 커포넌트를 배치하는 레이아웃 객체
			//FlowLayout 과 비슷한 객체
		Scene s = new Scene(parent);  //Parent란? 부모클래스를 의미하는 것이 아니라, 객체간 포함관계에서 바깥쪽 컨테이너를 의미
			//Swing과 비유하자면, 레이아웃 객체가 Parent이다
		
		Button bt = new Button("버튼입니다");
		bt.setPrefWidth(200);
		bt.setPrefHeight(40);
		
		//버튼을 parent에 부착하기
		parent.getChildren().add(bt);
		
		TextField t = new TextField("test");
		
		parent.getChildren().add(t);
		
		stage.setScene(s);  //씬을 윈도우에 부착
		
		bt.setOnAction((e)->{
			System.out.println("clicked");
		});
		
		stage.setMaxHeight(500);
		stage.setMaxWidth(500);
		stage.show();  //윈도우 보이게 (==setVisible())
	}
	
	//어플리케이션 시작 전, 초기화를 담당하는 메서드이다 
	//따라서 초기화할 게 없다면 재정의는 필수가 아니다
	//생성자 : 인스턴스가 태어날 때 호출되는 메서드
	//init() : 태어난 직후에 실행되는 메서드
	public void init() throws Exception {
		//자바의 쓰레드의 메서드 중 현재 실행중인 쓰레드 정보를 알기 위한 메서드
		
		System.out.println("init() 메서드 호출 by " + Thread.currentThread().getName());
	}
	
	public void stop() throws Exception {
		System.out.println("stop() 메서드 호출 by " + Thread.currentThread().getName());
	}
	
	public static void main(String[] args) {
		System.out.println("launch() 메서드 호출 by " + Thread.currentThread().getName());
		launch(args);  //실행 명령
	}

}


//launch() 메서드 호출 시 main thread 생성되고
//start(), stop()을 위한 thread가 하나 만들어진다. -> UI, event 용
//또한 init()을 위한 thread도 하나 추가된다
//FX의 UI, event 등은 Application thread가 제어한다


/*어플리케이션의 thread 호출 순서
	launch() 메서드 호출 by main
	AppMain() 메서드 호출 by JavaFX Application Thread
	init() 메서드 호출 by JavaFX-Launcher
	start() 메서드 호출 by JavaFX Application Thread
	stop() 메서드 호출 by JavaFX Application Thread
*/
