/* 적군 정의 */
class Enemy{
    /* 적군의 이미지가 여러 형태이므로 매개변수로 받을 것. */
    constructor(src, x, y, width, height, velX, velY){
        this.img = document.createElement("img");

        this.src = src;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velX = velX;
        this.velY = velY;
        
        this.img.src = src;
        this.img.style.position = "absolute";
        this.img.style.left = x + "px";
        this.img.style.top = y + "px";

        this.img.style.width = width + "px";
        this.img.style.height = height + "px";

        wrapper.appendChild(this.img);
    }

    tick(){
        this.x += this.velX;
        this.y += this.velY;
    }
    
    render(){
        this.img.style.left = this.x + "px";
        this.img.style.top = this.y + "px";
    }

}