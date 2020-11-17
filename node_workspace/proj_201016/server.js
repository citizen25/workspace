/* 기존의 http 모듈만으로 구축했던 서버에는 기능상 부족한 점이 많았다.
    문제점 1) 이미지와 같은 정적파일에 대한 요청 처리가 미비.
    해결책) http 모듈은 아주 기본적인 서버구축 모듈이므로, 이모다 기능을 보강한 모듈로 확장해보자.
        http -> connect모듈(http보완:지금 사라짐) -> express모듈(connect보완) */
var http = require("http");
var fs = require("fs");
var express = require("express");   // http보다 훨씬 더 많은 기능이 보강된 모듈.
var static = require("serve-static");       // 정적 자원 처리 미들웨어.
var mysql = require("mysql");
var ejs = require("ejs");
var common = require("./common.js");    // 우리가 만든 모듈.
const { response } = require("express");

let conStr = {
    url: "localhost",
    user: "root",
    password: "1234",
    database: "node",
}
let con;

/* express 모듈은 미들웨어라 불리는 함수를 이용하여, 기존의 http모듈로는 할 수 없었던
추가된 기능들을 지원한다. (express는 완전 필수)
    미들웨어는 express 객체의 use() 메서드로 지정할 수 있다.
        사용 예) app.use(사용할 미들웨어);
    static() 미들웨어 - 오늘 사용할 미들웨어
        static = '정적인'.
        전산 분야에서 정적이라는 뜻은 프로그래밍 언어처럼 실행 시 변경이 가능한 것이 아니라,
        고정되어 있는 형태를 의미한다.
            ex) html, images, css 파일 등
            프로그래밍 언어가 아니기 때문에 실행타임에 변경 불가.
            -> 그래서 자바스크립트와 같은 프로그래밍 언어가 정적으로 제어하기 위해 등장. */
var app = express();    // express 객체 생성
// __dirname, __filename : 현재 실행중인 node.js 파일의 경로를 반환.
//console.log("현재 실행 중인 파일의 디렉토리 경로 : ", __dirname);

app.use(static(__dirname + "/static"));      // 정적 자원의 위치를 등록.

/* form 양식으로 전송 시, 처리 */
app.use(express.urlencoded({
    extended: true,
}));

/* 요청, 응답을 use() 메서드로 처리. */
/* post(매개변수1, 매개변수2) 메서드 : 매개변수가 2개 
    매개변수1 : 요청 uri
    매개변수2 : 실행 함수
    누군가 '매개변수1'을 요청하면, '매개변수2'를 실행하겠다. */
app.post("/notice/regist", function(request, response){
    //response.end("your http method is post");
    var title = request.body.title;
    var writer = request.body.writer;
    var content = request.body.content;
    console.log("제목:", title);
    console.log("작성자:", writer);
    console.log("내용:", content);

    /* mysql insert */
    var sql = "INSERT INTO notice(title, writer, content) VALUES(?, ?, ?);";

    con.query(sql, [title, writer, content], function(error, fields){
        if(error){
            console.log("insert 실패", error);
        }else{
            response.writeHead(200, {"Content-Type":"text/html;charset=utf-8"});
            response.end(common.getMsgURL("등록성공", "/notice/list"));
        }
    });
});

/* 목록 가져오기 */
app.get("/notice/list", function(request, response){
    var sql = "SELECT * FROM notice ORDER BY notice_id DESC";
    
    con.query(sql, function(error, record, fields){
        if(error){
            console.log("list error", error);
        }else{
            fs.readFile("./list.ejs", "utf-8", function(err, data){
                if(error){
                    console.log("list ejs reading error");
                }else{
                    response.writeHead(200, {"Content-Type":"text/html;charset=utf-8"});
                    response.end(ejs.render(data, {
                        noticeArray: record,
                    }));
                }
            });
        }
    });
});

/* 한건 가져오기 */
app.get("/notice/detail", function(request, response){
    /* get 방식의 파라미터 받기 */
    var notice_id = request.query.notice_id;
    //console.log(notice_id);
    var sql = "SELECT * FROM notice WHERE notice_id = ?";
    //response.end(sql);

    con.query(sql, [notice_id], function(error, record, fields){
        if(error){
            console.log("select error ", error);
        }else{
            fs.readFile("./detail.ejs", "utf-8", function(err, data){
                if(err){
                    console.log("detail.ejs reading error", err);
                }else{
                    response.writeHead(200, {"Content-Type":"text/html;charset=utf-8"});
                    response.end(ejs.render(data, {
                        notice:record[0],       // 한건
                    }));
                }
            });
        }
    });
});

/* 한건 수정 */
app.post("/notice/update", function(request, response){
    var notice_id = request.body.notice_id;
    var title = request.body.title;
    var writer = request.body.writer;
    var content = request.body.content;
    var sql = "UPDATE notice SET title=?, writer=?, content=? WHERE notice_id = ?";

    con.query(sql, [title, writer, content, notice_id], function(error, fields){
        if(error){
            console.log("update fail ", error);
        }else{
            response.writeHead(200, {"Content-Type":"text/html;charset=utf-8"});
            response.end(common.getMsgURL("수정 성공", "/notice/detail?notice_id=" + notice_id));
        }
    });
});

/* 한건 삭제 */
app.post("/notice/del", function(request, response){
    /* 파라미터 받기 (post 방식) */
    var notice_id = request.body.notice_id;
    var sql = "DELETE FROM notice WHERE notice_id = ?";     // 바인드 변수 사용.

    con.query(sql, [notice_id], function(error, fields){
        if(error){
            console.log("delete fail ", error);
        }else{
            /* 메시지 출력 후, list 요청 */
            response.writeHead(200, {"Content-Type":"text/html;charset=utf-8"});
            response.end(common.getMsgURL("삭제 성공", "/notice/list"));
        }
    });
});

/* 데이터베이스 접속 */
function connect(){
    con = mysql.createConnection(conStr);
}

var server = http.createServer(app);        // express 모듈을 이용한 서버.
server.listen(8888, function(){
    console.log("The Server using express is running at 8888 port...");
    connect();
});
