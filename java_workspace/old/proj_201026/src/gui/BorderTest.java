/*
각종 컴포넌트를 올려놓기 전에, 어떤 방식으로 올려놓을지를 결정하는 것을 배치라하며,
AWT에서 지원하는 다음과 같이 5가지 유형이 있다.
    1) FlowLayout : 행방샹으로 늘어서는 배치방식, 만일 공간이 부족하면 다음 행으로 내려간다.
        컨테이너의 크기에 따라 흐르듯 배치가 되므로, flow라는 뜻이 부여되었다.
    2) BorderLayout : 북, 동, 서, 남, 중앙 이라는 방위를 가지는 배치 방법.
    3) GridLayout : 격자. 즉, 바둑판 모양의 배치 방법.
    4) CardLayout : 하나의 면이 보일 때는 다른 면이 안보이는 카드 형식의 배치 방법.
    5) GridBagLayout : GridLayout을 픽셀단위로 보다 상세하게 제어할 수 있는 배치 방법.

    사실상 1,2,3번 만으로도 충분히 개발 가능하다.
*/
package gui;

import java.awt.Frame;
import java.awt.Button;
import java.awt.BorderLayout;

class BorderTest{
    public static void main(String[] args){
        // Frame 생성
        Frame frame = new Frame();


        // BorderLayout 학습
        Button bt_north = new Button("North");
        Button bt_south = new Button("South");
        Button bt_west = new Button("West");
        Button bt_east = new Button("East");
        Button bt_center = new Button("center");
        
        // [배치 방법을 결정하자]

        // 1.BorderLayout 적용
        frame.setLayout(new BorderLayout());

        // 버튼을 프레임에 붙이기
        //frame.add(bt_north, BorderLayout.NORTH);    // 북쪽에 버튼 부착.(상수는 대문자로 적는것이 원칙.)
        frame.add(bt_south, BorderLayout.SOUTH);    // 남쪽에 버튼 부착.
        //frame.add(bt_west, BorderLayout.WEST);    // 서쪽에 버튼 부착.
        frame.add(bt_east, BorderLayout.EAST);    // 동쪽에 버튼 부착.
        //frame.add(bt_center, BorderLayout.CENTER);    // 가운데에 버튼 부착.
        frame.add(bt_center);    // 센터는 명시하지 않아도 디플트이기 때문에, 생략해도 적용된다.
        
        // 프레임 세팅
        frame.setSize(250,200);
        frame.setVisible(true);
    }
}
