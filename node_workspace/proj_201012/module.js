/* 
    Node.js가 전세계적으로 열풍을 일으킨 가장 큰 이유?
        모듈(Module) 때문이다.

    모듈이란?
        javascript 라이브러리를 파일로 저장해놓은 단위
    node.js 모듈의 특징
        전 세계 많은 개발자들이 각자 자신이 개발한 모듈을 공유하고 있다.
        지금 이 순간에도 개로운 모듈이 계속 주가되고 있다.
    모듈의 유형
        1) 내장 모듈
            os 모듈
            url 모듈
            file system 모듈(★★★)
        2) 사용자 정의 모듈

*/
/* javascript와는 달리, 모듈을 가져올 때는 require() 함수를 이용한다. */
/* 
var os = require("os");     // 이미 내장된 모듈 중 os 모듈을 가져오기.

console.log(os.hostname()); // 컴퓨터 이름
console.log(os.cpus());     // CPU
 */

/* url 모듈 : url의 정보를 분석해주는 모듈 */
/* 
var url = require("url");
var result = url.parse("https://terms.naver.com/search.nhn?query=car");  // url을 분석하여, 그 결과를 result 변수에 담아보자
console.log("검색어는", result.query);
 */


/* 로컬상의 파일을 읽어오거나, 쓸 수 있는 모듈 */
var fs = require("fs");
/* 지정한 경로의 파일을 읽어서, 다 읽혀지면, 두번째 인수인 익명함수를 호출한다.
이렇게 특정 이벤트가 발생할 때, 시스템에 의해 역으로 호출된느 유형의 함수를 callback 함수라 한다. */
fs.readFile("./data/memo.txt", "utf-8", function(error, data){
    /* 콜백함수의 첫번째 매개변수(error) : 에러 정보를 담고 있응 객체 반환
    콜백함수의 두번째 매개변수(data) : 실제 읽어들인 파일의 내용을 담고 있다. */
    console.log("completed file read");
    console.log(data);
});




