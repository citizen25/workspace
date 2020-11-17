/* 게임 주인공 비행기 정의 */
class Hero{

    /* 주인공이 태어날 때, 어떤 초기화 작업을 할지 결정짓는 메서드 == 생성자 메서드 */
    /* 아래의 생성자 함수를 호출하는 자는, 반드시 주인공 이미지가 붙을 부모 요소를 전달해야 한다.
    즉, container 변수값을 결정지어야 한다. */
    constructor(container, x, y, width, height){
        /* constructor 안에 선언된 변수는 현재 인스턴스의 소유! */
        /* this가 붙어있느 변수는 해당 인스턴스와 생명력을 같이 한다.
        즉, 해당 인스턴스마다 각각 고유하게 보유할 수 있는 변수
        언제 소멸되나? 인스턴스가 살아있는 한.. 계속 살아있는다. */
        this.babo = "바보";

        /* x, y는 매개변수이기 때문에 금방 죽어버린다.
        그래서 this.x, this.y를 이용해 만들어진 instance가 살아있는 한 x, y가 살아있을 수 있도록 한다.!! --> 중요!!!!! */
        this.x = x;
        this.y = y;

        /* 생성자 내에서는 반드시 변수명 앞에 this를 명시. 객체 안에서의 코드 작성이므로.. */
        this.img = document.createElement("img");  // <img/>
        this.img.src = "../images/shooting/plane.png";
        this.img.style.position = "absolute";
        this.img.style.left = x + "px";
        this.img.style.top = y + "px";
        this.img.style.width = width + "px";
        this.img.style.height = height + "px";
        /* 화면에 부착. 부모요소에 부착하자 */
        container.appendChild(this.img);
    }

    /* 비행기의 동작 방식을 결정짓는 메서드 */
    move(){

    }
}