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

        for(var i=0; i<enemyArray.length; i++){
                /* 총알이 한 tick씩 진행할 때마다 배열에 존재하는 모든 적군을 대상으로 충동했는지 여부 판단 */
                var result = collisionCheck(this.img, enemyArray[i].img);     // ../js/lib.js - 충돌검사 함수
            if(result){
                console.log("적군을 맞췄다!");
                
                /* 맞으면, 총알과 적군 모두 제거한다. */
                // 너죽고 = 적군 배열에서 요소 삭제 splice(index, 갯수), 화면에서 삭제 removeChild()
                var img = enemyArray[i].img;
                wrapper.removeChild(img);
                enemyArray.splice(0, 1);
                
                // 나죽자 = 총알 배열에서 요소 삭제 splice(index, 갯수), 화면에서 삭제 removeChild()
                wrapper.removeChild(this.img);
                var index = bulletArray.indexOf(this);      // 현재 총알의 인스턴스가 배열의 몇번째에 존재하는지 알아낸다.
                bulletArray.splice(index, 1);
            }
        }
    }

    /* 그래픽 처리 : 변화된 물리량을 화면에 표시 */
    render(){
        this.img.style.top = this.y + "px";
    }
}