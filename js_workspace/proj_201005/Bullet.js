/* 총알 정의 */
class Bullet{
    constructor(src, x, y, width, height, velX, velY){
        this.img;

        this.src = src;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velX = velX;
        this.velY = velY;

        this.img = document.createElement("img");
        this.img.src = this.src;        // 폭탄, 총알 등등 웨폰이 여러가지가 있으므로 변수로 준다.
        this.img.style.position = "absolute";
        this.img.style.left = this.x + "px";
        this.img.style.top = this.y + "px";
        this.img.style.width = this.width + "px";
        this.img.style.height = this.height + "px";

        wrapper.appendChild(this.img);  // 화면에 부착.

    }

    /* 총알의 물리량 변화 */
    tick(){
        this.y += this.velY;
    }

    /* 그래픽 처리 : 변화된 물리량을 화면에 표시 */
    render(){
        this.img.style.top = this.y + "px";
    }
}