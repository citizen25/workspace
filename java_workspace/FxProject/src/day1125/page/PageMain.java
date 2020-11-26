//FX���� ������ ��ȯ, ȭ�� ��ȯ

package day1125.page;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PageMain extends Application{
	String[] naviTitle = {"MAIN", "SHOPPING", "MEMBER", "LOGIN"};
	
	public static final int MAIN = 0;
	public static final int SHOPPING = 1;
	public static final int MEMBER = 2;
	public static final int LOGIN = 3;
	
	Pane[] page = new Pane[naviTitle.length];  //�迭ȭ���Ѿ� �ݺ������� ó�� ����
	Button[] btn = new Button[naviTitle.length]; 
	ToolBar bar;  //���̰��̼��� �����ϴ� �޴���
	BorderPane border;  //��ü ���̾ƿ�
	
	public void start(Stage stage) throws Exception {
		border = new BorderPane();
		bar = new ToolBar();  //���� ����

		//xml�� �����ε� ������ �ڹ� ��ü�� ��ȯ�ϱ� (�ȵ���̵忡���� �� ������ inflation�̶� ��)
		for(int i=0; i<page.length; i++) {
			page[i] = (Pane)FXMLLoader.load(this.getClass().getClassLoader().getResource("day1125/page/" + naviTitle[i].toLowerCase() + ".fxml"));			
		}		
		
		//�׺���̼� �����
		for(int i=0; i<btn.length; i++) {
			btn[i] = new Button(naviTitle[i]);
			
			btn[i].setId(Integer.toString(i));
			
			//��ư�� �׼� �̺�Ʈ ����
			btn[i].setOnAction((e)->{
				Button bt = (Button)e.getSource();  //�̺�Ʈ�� ����Ų ������Ʈ�� ���ؿ�
				System.out.println(bt.getId() + " clicked");			
				
				showPage(page[Integer.parseInt(bt.getId())]);
			});
		}
		
		//���ٿ� ��ư �ø���
		bar = new ToolBar(btn[Pages.MAIN.ordinal()]
			, btn[Pages.SHOPPING.ordinal()]
			, btn[Pages.MEMBER.ordinal()]
			, btn[Pages.LOGIN.ordinal()
	    ]);
		bar.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		border.setTop(bar);  //������ bar�� top������ ����
		
		//showPage(page[Pages.MEMBER.ordinal()]);
		
		showWindow(stage, border);
	}
	
	//������ ��ȯ �޼��� ���� : swing���� �츮�� ������ showPage()������ �޼��� ����
	public void showPage(Pane p) {
		//���������� �̸� ������ ������Ʈ(�ַ� �г�)�� ���̰ų� �� ���̰� �ϸ� ������,
		//FX������ ���������� �����ϰ� �����ϴ� ó���� ����
		
		//1)������ border�� ���� ������Ʈ�� �����Ѵٸ� ��� ����
		border.getChildren().removeAll(border.getChildren());
		//2)���ŵ� �ڸ��� ����
		border.setTop(bar);  //bar�� �ٽ� ����
		border.setCenter(p);  //xml���� �ε��� �����̳�.
			//setVisible(true), setVisible(false)�� �Ұ�
		
		
	}
	
	public void showWindow(Stage stage, Parent parent) {
		Scene s = new Scene(parent);  //�� ����
		stage.setScene(s);  //������ ���� �����쿡 ����
		stage.setWidth(800);  //�ʺ�
		stage.setHeight(700);  //����
		stage.show();  //������ �����ֱ�		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
