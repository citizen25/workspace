package gui;

import java.awt.*;  //나쁜 짓이지만 편하다.. 이번만..

class LoginForm extends Frame{
    // has a 관계는 멤버변수가 객체혈일 때를 의미한다!
    Label la_id;
    Label la_pass;
    TextField t_id;
    TextField t_pass;
    Button bt_login;
    Button bt_regist;
    Panel p_center;  //센터에 그리드를 적용할 패널.
    Panel p_south;  //남쪽에 붙일 패널 : 버튼 2개

    public LoginForm(){
        /* 부품관계로 보유한 변수들을 모두 초기화시켜주자
            특히, 부품은 이 객체가 태어날 대 같이 태어나야 하므로, 생성자의 타이밍을 놓치지 말자.*/
        la_id = new Label("ID");
        la_pass = new Label("Password");
        t_id = new TextField(15);
        t_pass = new TextField(15);
        bt_login = new Button("Login");
        bt_regist = new Button("가입");
        p_center = new Panel();
        p_south = new Panel();

        /* 생성만 한 상태이고, 아직 조립이나 스타일 부여는 하지 않음.
            Frame은 개발자가 레이아웃을 지정하지 않으면, 디폴트가 BorderLayout이다. */
        //this.setLayout(new BorderLayout());  //필요없다. 디폴트가 이미 보더레이아웃이므로

        /* 처음보는 객체(Color)에 대한 대응법
            1. 뭐하는 객체인지 파악
            2. 메모리에 올리는 법 (일반, 추상, 인터페이스) */
        p_center.setBackground(new Color(153, 255, 0));

        // 센터 패널을 윈도우의 BorderLayout 센터에 넣자.
        this.add(p_center, BorderLayout.CENTER);  //두번째 인자는 빼도 된다.(default가 center)

        /* 상수는 직관성을 부여한 데이터이다. final로 더 이상 값을 변경할 수 없으며, 
            static으로 인스턴스간 공유가 가능하며, public으로 선언하여 모든 객체가 
            접근할 수 있도록 접근제한을 풀어놓은 데이터. */
        p_south.setBackground(Color.YELLOW);  //Color를 인간이 사용하기 쉬운 데이터로 사용해보자.
        this.add(p_south, BorderLayout.SOUTH);

        // p_center에 그리드 적용
        p_center.setLayout(new GridLayout(2, 2));  //2행 2열짜리 그리드 적용.
        p_center.add(la_id);  //id 라벨 부착 : 1행 1열
        p_center.add(t_id);  //id 텍스트 박스 부착 : 1행 2열
        p_center.add(la_pass);  //pass 라벨 부착 : 2행 1열
        p_center.add(t_pass);  //pass 텍스트 박스 부착 : 2행 2열



        // Panel은 아무런 배치 관리자를 적용하지 않으면 디폴트가 FlowLayout이다.
        p_south.add(bt_login);
        p_south.add(bt_regist);

        /* this의 정확한 의미 : reference 변수이다.
            단, 나 자신의 인스턴스의 주소값을 가진..
            해당 인스턴스가 자기 자신을 가리킬 때 사용한다.
            this는 (super도) 인스턴스가 태어나는 영역인 Heap에 존재한다. */
        this.setSize(400, 150);  //this는 생략해도 문제 없다.
        this.setVisible(true);
    }
    public static void main(String[] args){
        new LoginForm();  //extends Frame을 했기 때문에 Frame을 가져온 것과 같다.
    }
}
