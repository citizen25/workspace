/* http 서버 구축하기 - 필요한 모듈 가져오기 */
var http = require("http");
var fs = require("fs");
var url = require("url");   // url 정보를 해석해주는 모듈.

var mysql = require("mysql");   // 외부모듈이기 때문에 npm install으로 설치해야 한다.
var con;    // 접속 정보를 가진 객체 (이 객체를 이용하여 쿼리문을 수행할 수 있다.)

/* 서버 객체 생성 
request, response는 이미 node.js 자체적으로 존재하는 객체 */
var server = http.createServer(function(request, response){
    response.writeHead(200, {"Content-Type":"text/html;charset=utf-8"});    // 암기해야 한다.

    /* 클라이언트의 요청에 대한 응답 처리 (html문서를 주고받음) */
    
    /* 클라이언트의 요청 내용이 다양하므로, 각 요청을 처리할 조건문이 있어야 한다. 
        클라이언트가 원하는 것이 무엇인지부터 파악한다.*/
    // console.log("클라이언트의 요청 url", request.url);

    /* 파싱 시, true 옵션을 주면, 파라미터의 매개변수를 접근할 수 있는 json을 추가해준다. */
    var result = url.parse(request.url, true);    // url 모듈을 이용하여, 전체 주소를 대상으로 해석 시작.
    var paramObj = result.query;  // 파라미터를 담고있응 json 변환.

    /* parsing한 결과를 확인 */
    // console.log("파싱 결과 보고서 : ", result);
    // console.log("id:", paramObj.m_name, "\npass:", paramObj.m_pass);

    if(result.pathname == "/login"){
        console.log("mysql 연동하여 회원 존재 여부 확인");
        
        /* mysql 연동하여 select ~~문 실행 */
        var sql = "select * from hclass where id='" + paramObj.m_name + "' and pass='" + paramObj.m_pass + "'";
        console.log("sql:", sql);
        
        con.query(sql, function(error, record, fields){
            if(error){
                console.log("쿼리 실행 실패", error);
            }else{
                console.log("record: ", record);
                
                if(record.length > 0){
                    /* 레코드가 있을 때는 (배열의 길이가 1), 로그인 성공 메세지 */
                    // console.log("인증 성공");
                    response.end("<script>alert('인증 성공');</script>");
                }else{
                    /* 레코드가 없을 때는 (배열의 길이가 0), 로그인 실패 메세지 */
                    // console.log("인증 실패");
                    response.end("<script>alert('인증 실패');history.back();</script>");
                }
            }
        });    // 쿼리 실행

    }else if(result.pathname == "/apple"){
        console.log("사과를 드릴게요");
        response.end("사과를 드리겠습니다.")
    }else if(result.pathname == "/loginForm"){
        /* 우리가 제작한 loginForm.html을 로컬파일로 열면 안되고,
        모든 클라이언트가 인터넷 상의 주소로 접근하게 하기 위해서
        서버가 html 내용을 읽고, 그 내용을 클라이언트에게 응답정보로 보내야 한다. */
        fs.readFile("./loginForm.html", "utf-8", function(error, data){
            if(error){
                console.log("읽기 실패입니다 ㅜㅜ", error);
            }else{
                /* 읽기 성공이므로, 클라이언트의 응답 정보로 보내자. */
                /* HTTP 프로토콜로 데이터를 주고 받을 때는, 이미 정해진 규약이 있으므로.
                눈에 보이지 않는 수많은 설정 정보값들을 서버와 클라이언트간 교환한다. */
                response.end(data);
            }
        });
    }

    /* 전체 url 중에서도 uri만을 추출해보자
    따라서 전체 .url을 해석해야한다. 해석은 parsing이라 한다. */
    request.url.toUpperCase();

});

/* mysql 접속 */
function connectDB(){
    con = mysql.createConnection({
        url: "localhost",
        user: "root",
        password: "1234",
        database: "node",
    });
}

/* 서버 가동 */
server.listen(8888, function(){
    console.log("Server is running at 8888 port...");
    connectDB();        // 웹서버가 가동되면, mysql을 접속해놓자.
});
