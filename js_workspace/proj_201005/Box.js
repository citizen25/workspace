/* 충돌 체크를 학습하기 위한 box 객체 정의 */
class Box{
    constructor(bg, x, y){
        /* 객체(class)가 보유한 인스턴스 변수들 */
        this.div = document.createElement("div");
        this.bg = bg;    // 인스턴스의 색 결정
        this.x = x;     // 인스턴스의 x좌표 결정
        this.y = y;     // 인스턴스의 y좌표 결정
        this.width = 100;
        this.height = 100;

        this.div.style.width = this.width + "px";
        this.div.style.height = this.height + "px";
        this.div.style.backgroundColor = this.bg;
        this.div.style.color = "white";

        this.div.style.position = "absolute";
        this.div.style.left = this.x + "px";
        this.div.style.top = this.y + "px";

        document.body.appendChild(this.div);    // 화면에 부착.
    }
}