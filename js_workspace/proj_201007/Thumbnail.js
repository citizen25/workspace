/* 영화 이미지 썸네일을 정의한다. */
class Thumbnail{
    constructor(container, width, height, bd, src, x){
        this.container = container; // 어디에 넣을지 컨테이너를 동적으로 받자
        this.width = width;
        this.height = height;
        this.bd = bd;    // border 두께
        this.x = x;     // div의 좌표
        this.src = src;
        var me = this;

        this.div = document.createElement("div");
        this.img = document.createElement("img");
        
        
        /* 스타일 부여 */
        this.div.style.position = "absolute";
        this.div.style.left = this.x + "px";
        this.div.style.boxSizing = "border-box";
        this.div.style.width = this.width + "px";
        this.div.style.height = this.height + "px";
        this.div.style.border = this.bd + "px solid white";
        
        this.img.style.width = this.width-(bd*2) + "px";
        this.img.style.height = this.height-(bd*2) + "px";
        this.img.src = this.src;


        /* 이미지에 이벤트 구현하기 */
        this.img.addEventListener("click", function(){
            // console.log("경로: ", this.src);
            
            /* 이벤트 구현시 사용되는 익명함수 내에서 this를 사용하게 되면
            객체의 인스턴스를 가리키게 되는 것이 아니라, 익명함수의 {}영역을 가리키게 된다. */
            
            /* 현재 객체가 배열의 몇번째인지 알아맞추기 */
            var index = thumbArray.indexOf(me);     // this는 객체로서의 자신을 뜻한다. 따라서 여기서 me 대신 this를 쓰면 Thumbnail이 아닌 이벤트의 익명함수 {}영역을 가리키게 된다.
            console.log("index:", index);
            getDetail(index);
            /* 포인터의 targetX값을 나의 좌표와 일치 */
            pointer.targetX = me.x;
        });

        /* 조립 */
        this.div.appendChild(this.img);     // div에 이미지 넣기
        this.container.appendChild(this.div);
    }
}