/* 적군 정의 */
class Enemy{
    /* 적군이 태어날 때 어떤 속성(특징)으로 태어날지 결정하는 생성자 */
    constructor(container, width, height, x, y, src){
        this.img = document.createElement("img");
        this.img.src = src;
        this.img.style.width = width + "px";
        this.img.style.height = height + "px";
        this.img.style.position = "absolute";
        this.img.style.left = x + "px";
        this.img.style.top = y + "px";
        container.appendChild(this.img);
    }

    /* 적군의 행동을 제어하는 메서드 */
    move(){

    }
}