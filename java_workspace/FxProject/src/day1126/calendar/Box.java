//달력에 사용되는 셀을 정의한다

package day1126.calendar;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Box extends Canvas{
	
	String title;
	int width; 
	int height;
	GraphicsContext context;
	
	public Box(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.setWidth(width);
		this.setHeight(height);
		
		//모든 컴포넌트는 그래픽 처리에 사용되는 객체를 가지고 있다
		context = this.getGraphicsContext2D();

		erase();
		
		renderText(title);
	}
	
	//현재 박스에 그려진 글씨 지우기
	public void erase() {
		context.setFill(Color.YELLOW);  //패인트통 물감색을 노란색으로
		context.fillRect(0, 0, 100, 100);  //노란색 채워진 사각형
		context.setStroke(Color.valueOf("#ff0000"));  //선의 색상
		context.strokeRect(0, 0, width, height);  //사각형 라인을 그린다
	}
	
	//현재 박스에 글씨 그리기
	public void renderText(String title) {
		this.title = title;
		context.setFill(Color.BLACK);  //페인트통 색 교체
		context.setFont(new Font(17));  //폰트 크기 설정
		context.fillText(title, 2, 20);
	}
	
}
