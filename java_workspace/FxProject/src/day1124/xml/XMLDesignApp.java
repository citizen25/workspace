//AWT�� Swing�� ������ �ڹ� �ڵ�θ� ȭ�� �������� �����ؾ��ϹǷ�, ���������� ��ٷӴ�
//�̷��� ������ �ذ��ϱ� ���ؼ� ������ �ڵ�� ������ �и����� �����ϴ� ����� �̿��غ���
//FX�� �������� �ڹٻӸ� �ƴ϶�, XML�ε� �����ϰ� ������, ��� �������� ������ �ڹ��ڵ忡�� ���� ���� ���ƾ� �Ѵ�
//Ư���� ��츸 ����..

package day1124.xml;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class XMLDesignApp extends Application{

	public void start(Stage stage) throws Exception {
		//���� �������� �������� ����ϴ� xml���� �������� �����Ƿ�
		//xml�� �о ���� �ڹ� �ڵ�� �����;� �Ѵ�
		//DOM vs SAX parsing..? �̰� xmlȭ �� ������ �����͸� ������ �� ����ϴ� ���̰� �����ΰ��� ��� ����
		//�����ο� xml�� FX���� �����ϴ� xml �ؼ� ��ü�� �̿��ϸ� �ȴ�(��ü����)
		URL url = this.getClass().getClassLoader().getResource("day1124/xml/main.fxml");
		VBox parent = (VBox)FXMLLoader.load(url);
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
