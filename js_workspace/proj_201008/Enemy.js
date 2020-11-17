class Enemy extends GameObject{
    constructor(container, src, width, height, x, y, velX, velY){
        super(container, src, width, height, x, y, velX, velY);     // 부모를 먼저 초기화.
    }
}