/* 게임에 등장할 모든 객체들이 가질 수 있는 보편적 특징들을 보유할 최상위 객체 정의 */
class GameObject{
    constructor(container, src, width, height, x, y, velX, velY){
        this.container = container;
        this.img = document.createElement("img");
        this.src = src;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.velX = velX;
        this.velY = velY;

        this.img.src = this.src;

        /* 스타일 적용 */
        this.img.style.width = width + "px";
        this.img.style.height = height + "px";
        this.img.style.position = "absolute";
        this.img.style.left = this.x + "px";
        this.img.style.top = this.y + "px";

        /* 화면에 부착 */
        this.container.appendChild(this.img);
    }

    /* 이 클래스를 상속받는 자식이 누구인지 모르므로..(주인공일지 아이템일지 적군, 벽돌일지..)
    그 내용을 채워넣을 수 없다. 현재 시점에서는 예측이 불가. 
        아래와 같이 부모 클래스에서 자식의 행동을 예측할 수 없는 경우,( 그 내용을 채워넣을 수 없는 경우) 메서드 명만 정의할 수 있는데
        이러한 메서드를 가리켜 불완전하다고 하여 추상메서드라 한다.
            자바스크립트는 완벽한 컴파일 언어가 아니기에, 추상메서드를 지원하지 않는다.
            따라서, 현재는 내용만 비워놓는다. */
    tick(){

    }

    render(){

    }
}