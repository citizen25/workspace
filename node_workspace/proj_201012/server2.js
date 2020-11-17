/* http 모듈로 웹서버 구축하기 */
var http = require("http");
var fs = require("fs");     // File System 모듈, 파일을 읽거나 쓸 수 있는 모듈

/* 변경할 목적의 데이터가 아니라면, 상수로 선언하자
이때 사용되는 키워드가 바로 let 이다. */
let conStr = {
    user: "user0907",
    password: "1234",
    connectionString: "localhost/XE"
}   // 오라클에 접속할 때 필요한 정보.


/* 서버 객체를 생성 */
/* 서버는 클라이언트의 요청이 들어오면 반드시 응답을 해야한다. 이것이 http의 메커니즘이다.
만일 응답하지 않으면? 클라이언트는 서버의 응답을 기다리므로 무한 대기 상태에 빠진다. */
/* 서버 객체 생성시, 요청 정보와 응답 정보를 구성할 수 있는 request, response 객체가 매개변수로 전달될 수 있다. */
var server = http.createServer(function(request, response){
    /* request 객체 : 클라이언트의 요청 정보를 처리할 수 있다. 
    response 객체 : 클라이언트에게 전송할 응답 정보를 구성할 수 있다. */
    console.log("클라이언트의 요청을 받았습니다.");

    /* 컨텐츠 전송 (클라이언트의 브라우저가 받게될 내용) */
    /* http 상태코드 중 200은 정상 처리를 의미 (서버에서 클라이언트의 요청을 정상적으로 처리했다는 상태 코드 : W3C의 표준에 의해서 정해진 것.) 
        500은 심각한 서버의 에러, 404는 요청한 자원을 찾을 수 없을 때.*/

    response.writeHead(200, {"Content-Type":"text/html;charset=utf-8"});    // 편지 봉투 구성하기.
    /* 
    var tag = "";
    tag = tag + "<html>";
    tag = tag + "<head>";
    tag = tag + "</head>";
    tag = tag + "<body>";
    tag = tag + "<input type=\"text\" />";
    tag = tag + "<button>가입</button>";
    tag = tag + "</body>";
    tag = tag + "</html>";
    */

    /* 서버에 있는 파일을 읽어들여, 클라이언트에게 전송한다. */
    
    fs.readFile("./회원폼유효성체크.html", "utf-8", function(error, data){
        console.log(data);
        response.end(data);     // 클라이언트에게 응답 정보 전송
    });
    

    /* 이미지를 클라이언트에게 보내되, 파일을 읽어서 처리.
    이미지의 경우, content-type은 image/png, image/jpg, image/gif 등이다. */
    /* 
    response.writeHead(200, {"Content-Type":"image/jpg"});
    fs.readFile("../images/nation/7.jpg", function(error, data){
        response.end(data);
    });
 */
});
   

/* 접속자를 감지 */
/* 
server.on("connection", function(){
    console.log("접속자 감지");
});
 */




/* 서버 가동 */
/* 모든 네트워크 프로그램은 포트번호가 있어야 한다.
왜? 하나의 os 내에서 수많은 네트워크 프로그램 간에 충돌나기 않도록 하려고
IP: 아파트, port: 아파트의 호수
ex) oracle은 1521 포트, mysql은 3306 포트를 사용한다. */
server.listen(7777, function(){
    console.log("Server is running at 7777 port...");
});