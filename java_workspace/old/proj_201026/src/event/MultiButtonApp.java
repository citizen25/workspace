// 동일한 종류의 컴포넌트가 2개 이상일 때, 리스너 연결 방법
package event;

import java.awt.*;  //시간 관계상 * 사용.
import java.awt.event.*;

class MultiButtonApp extends Frame{
    Button bt1, bt2;

    public MultiButtonApp(){
        bt1 = new Button("버튼1");
        bt2 = new Button("버튼2");
        this.setLayout(new FlowLayout());  //버튼의 크기 제것으로 제대로 나오게 하기 위해.

        this.add(bt1);  //버튼 부착.
        this.add(bt2);  //버튼 부착.

        bt1.addActionListener(new MultiActionListener());  //버튼마다 리스너 연결
        bt2.addActionListener(new MultiActionListener());  //버튼마다 리스너 연결

        this.setSize(300, 400);
        this.setVisible(true);
    }

    public static void main(String[] args){
        new MultiButtonApp();
    }
}
