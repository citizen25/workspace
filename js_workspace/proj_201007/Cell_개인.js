/* 달력을 구성하는 하나의 칸을 정의 */
class Cell{
    constructor(container, width, height, text){
        this.container = container;
        this.div = document.createElement("div");
        this.width = width;
        this.height = height;
        this.text = text;   // 박스 안에 작성할 내용. (숫자, 문자 모두 올 수 있음.)
        
        /* text 출력 */
        this.div.innerHTML = "<h4>" + text + "</h4>";
        
        /* 스타일 적용 */
        this.div.style.width = this.width + "px";
        this.div.style.height = this.height + "px";
        this.div.style.background = "white";
        this.div.style.border = "1px solid orange";
        this.div.style.boxSizing = "border-box";
        this.div.style.float = "left";

        this.container.appendChild(this.div);
    }
}