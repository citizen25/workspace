/* 클래스명은 대문자로 시작한다. (OOP개발자들 사이의 규칙, 관례) */
class Hero{
    /* 생성자 매서드라 하면, 객체의 인스턴스 생성시 new연산자에 의해 무조건 호출된다. 
    생성자 메서드를 제공하는 이유 : 모든 인스턴스가 같은 클래스(거푸집)로부터 생성되다보니, 모두 동일한 모습으로 태어난다.
    따라서, 각각의 인스턴스마다 개성있는 데이터로 초기화 되려면, 초기화시킬 수 있는 영역이 필요하며, 이 영역이 바로 생성자 메서드 영역이다. */
    constructor(x, y, width, height, velX, velY){
        /* 태어날 때, 이 객체의 속성을 작성하면 됨. */
        console.log("저 지금 태어나면서, 초기화 됩니다~!");

        /* this가 붙은 변수는 해당 인스턴스마다 보유하게 되며, 인스턴스가 소멸할 때까지 생명을 같이한다.
        이러한 변수를 가리켜 인스턴스 변수라 한다. */
        this.x = x;
        this.y = y;
        this.velX = velX;  // x축 속도
        this.velY = velY;  // y축 속도
        this.width = width;
        this.height = height;

        this.img = document.createElement("img");
        this.img.src = "../images/shooting/hero.png";
        this.img.style.position = "absolute";
        this.img.style.left = this.x + "px";
        this.img.style.top = this.y + "px";
        this.img.style.width = this.width + "px";
        this.img.style.height = this.height + "px";

        wrapper.appendChild(this.img);   // 화면에 부착 
    }


    /* 주인공의 물리량 변화 */
    tick(){
        this.x += this.velX;
        this.y += this.velY;
    }

    /* 화면에 그래픽 처리 */
    render(){
        this.img.style.left = this.x + "px";
        this.img.style.top = this.y + "px";
    }
}