//javaFX�� �̺�Ʈ ó���� ������ �� �ִ�  ��ü�� �����Ѵ� (�̸� ��Ʈ�ѷ����Ѵ�)
//Initializable �������̽��� �����ؾ� �Ѵ�

package day1127.youtube;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.web.WebView;

public class MainUIController implements Initializable{

	//�����ڰ� ���� lookup()�ϴ� ���� �ƴ϶�, �ý��ۿ����� �ڵ����� ��ü�� ����(injection)����
	//@�� ������̼��̶� �θ��� ������ �ּ��ε�, �� �ּ��� �츮�� �˰��մ� �Ϲ� �ּ����� �޸� ���α׷����� ���ȴ�
	//it has been supported since jdk 5
	
	@FXML
	TextField t_url;
	
	@FXML
	TextField t_title;
	
	@FXML
	Button bt;
	
	@FXML
	TilePane tilePane;
	
	//�� �޼���� ���� Ŭ������ ������ ��, �ڵ����� ȣ��Ǵ� �ʱ�ȭ �޼����̴�
	//URL�� �� �������α׷��� ��� ���� layout.xml�� ��ΰ� �Ѿ�´�
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("location ���� " + location); 
		System.out.println("resources ���� " + resources); 
		
		System.out.println("bt: " + bt);
		
		bt.setOnAction((e)->{
			//System.out.println("clicked");
			createThumb();
		});
		
		
		
	}
	
	//��Ʃ�� ����� �����ϱ�
	public void createThumb() {
		try {
			BorderPane borderPane = (BorderPane)FXMLLoader.load(this.getClass().getClassLoader().getResource("day1127/youtube/thumb.fxml"));
			//apply thumbnail before attaching���� ���� ��Ʃ��� ������� ��������
			WebView webView = (WebView)borderPane.lookup("#webView");
			Label la_title = (Label)borderPane.lookup("#la_title");	

			//webView.getEngine().load(t_url.getText());
			//StringBuffer sb = new StringBuffer();
			//sb.append("<iframe ");
			//sb.append("width=\"200\" height=\"155\" ");
			//sb.append("src=\"" + t_url.getText() + "\" ");
			//sb.append("frameborder=\"0\" ");
			//sb.append("allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" ");
			//sb.append("allowfullscreen>");
			//sb.append("</iframe>");
			
			webView.getEngine().loadContent(t_url.getText(), "text/html");
			la_title.setText(t_title.getText());
			
			//attach loaded thumbnail to tile container
			tilePane.getChildren().add(borderPane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
