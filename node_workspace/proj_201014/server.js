/* 웹서버 구축하기 */
// 모듈이란? 기능을 모아놓은 자바스크립트 파일.
var http = require("http");     // http 내부 모듈 가져오기
var url = require("url");       // url 분석 모듈
var fs = require("fs");     // file system 모듈 (파일 읽기, 쓰기)
var mysql = require("mysql");   // mysql 외부 모듈
var ejs = require("ejs");   // node 서버에서만 실행 가능한 문서. html로 채워졌다고 하여, html 문서로 보면 안됨.

var con;    // mysql 접속 정보를 가진 객체. 이 객체로 sql 문을 수핼할 수 있다.

let conStr = {
    url: "localhost",
    user: "root", 
    password: "1234",
    database: "node",
};

/* 서버 객체 생성 */
var server = http.createServer(function(request, response){
    
    /* 클라이언트가 원하는 요청을 처리하려면, URL을 분석하여 URI 추출 시 조건을 분석하자. */
    var urlJson = url.parse(request.url, true);     // 분석 결과를 json으로 반환해줌
    console.log("URL 분석 결과는", urlJson);
    
    if(urlJson.pathname == "/"){
        fs.readFile("./index.html", "utf-8", function(error, data){
            if(error){
                console.log("index.html 읽기 실패", error);
            }else{
                response.writeHead(200, {"Content-Type":"text/html;charset=utf-8"});
                // 200이란? 처리성공 http 상태코드, 404 : 존재하지 않는 자원, 500 : 서버의 심각한 에거
                response.end(data);               
            }
        });
    }else if(urlJson.pathname == "/member/registForm"){
        fs.readFile("./registForm.html", "utf-8", function(error, data){
            if(error){
                console.log("registForm.html 읽기 실패", error);
            }else{
                response.writeHead(200, {"Content-Type":"text/html;charset=utf-8"});
                response.end(data);               
            }
        });
    }else if(urlJson.pathname == "/member/loginForm"){
        fs.readFile("./loginForm.html", "utf-8", function(error, data){
            if(error){
                console.log("loginForm.html 읽기 실패", error);
            }else{
                response.writeHead(200, {"Content-Type":"text/html;charset=utf-8"});
                response.end(data);               
            }
        });
    }else if(urlJson.pathname == "/member/regist"){
        /* mysql: insert */
        // 브라우저에서 전송한 파라미터를 먼저 받아야 한다.
        /* 회원정보는 member2 테이블에 넣고 */
        var sql = "insert into member2(uid, password, uname, phone, email, recieve, addr, memo)";
        sql += " values(?, ?, ?, ?, ?, ?, ?, ?)";       // 물음표는 바인드 변수를 의미.
        var param = urlJson.query;
        con.query(sql, [
            param.uid, 
            param.password, 
            param.uname, param.phone, 
            param.email, 
            param.recieve, 
            param.addr, 
            param.memo
        ], function(error, result, fields){
            if(error){
                console.log("회원정보 insert 실패", error);
            }else{
                /* 방금 insert된 회원의 pk를 조회해보자 */
                sql = "select last_insert_id() as member2_id";      // last_insert_id() -> 가장 마지막에 insert된 row의 pk를 가져오는 mysql의 함수
                
                con.query(sql, function(error, record, fields){
                    if(error){
                        console.log("pk 가져오기 실패", error);
                    }else{
                            // console.log("record : ", record);
                            var member2_id = record[0].member2_id;
                            /* 성공하면, 회원이 보유한 스킬 정보도 넣어주자. 스킬정보는 member_skill에 넣자 (배열의 길이만큼..) */
                            for(var i=0; i<param.skill_id.length; i++){
                                sql = "insert into member_skill(member2_id, skill_id) values(" + member2_id + ", " + param.skill_id[i] + ")";
                                // console.log("스킬 등록 쿼리 : ", sql);
                                /* 쿼리 실행 */
                                con.query(sql, function(err){
                                    if(err){
                                        alert("회원정보 등록 실패");
                                    }else{
                                        response.writeHead(200, {"Content-Type":"text/html;charset=utf-8"});
                                        response.end("회원정보 등록 완료");
                                    }
                                });
                            }
                        }
                    });    // select 쿼리문 수행.

                }
            }
        );
    }else if(urlJson.pathname == "/member/list"){
        /* 회원 목록 보여주기 */
        var sql = "select * from member2";
        con.query(sql, function(error, record, fields){
            // console.log("회원목록 : ", record);
            
            /* 응답 정보를 테이블로 구성하자 */
            var tag = "<table width='100%' border='1px'>";
            tag += "<tr>";
            tag += "<td>member2_id</td>";
            tag += "<td>uid</td>";
            tag += "<td>password</td>";
            tag += "<td>uname</td>";
            tag += "<td>phone</td>";
            tag += "<td>email</td>";
            tag += "<td>recieve</td>";
            tag += "<td>addr</td>";
            tag += "<td>memo</td>";
            tag += "</tr>";

            for(var i=0; i<record.length; i++){
                var member = record[i];      // 각각의 json을 끄집어 내자.
                tag += "<tr>";
                tag += "<td><a href='/member/detail?member2_id=" + member.member2_id + "'>" + member.member2_id + "</a></td>";
                tag += "<td>" + member.uid + "</td>";
                tag += "<td>" + member.password + "</td>";
                tag += "<td>" + member.uname + "</td>";
                tag += "<td>" + member.phone + "</td>";
                tag += "<td>" + member.email + "</td>";
                tag += "<td>" + member.recieve + "</td>";
                tag += "<td>" + member.addr + "</td>";
                tag += "<td>" + member.memo + "</td>";
                tag += "</tr>";
            }

            tag += "<tr>";
            tag += "<td colspan='9'><a href='/'>메인으로</a></td>";
            tag += "</tr>";
            tag += "</table>";

            response.writeHead(200, {"Content-Type":"text/html;charset=utf-8"});
            response.end(tag);
        });
    }else if(urlJson.pathname == "/member/detail"){   // 회원의 상세정보 요청
        var member2_id = urlJson.query.member2_id;

        /* mysql에서 데이터 가져오기 */
        var sql = "select * from member2 where member2_id=" + member2_id;
        con.query(sql, function(error, record, fields){
            if(error){
                console.log("회원 1건 조회 실패", error);
            }else{
                // console.log("3번 회원의 정보: ", record);
                var obj = record[0];        // 0번째에 들어있는 json 추출

                fs.readFile("./detail.ejs", "utf-8", function(error, data){
                    if(error){
                        console.log("detail.ejs 읽기 실패", error);
                    }else{
                        response.writeHead(200, {"Content-Type":"text/html;charset=utf-8"});
                        response.end(ejs.render(data, {
                            member: obj,    // json 자체를 보내버림.
                        }));
                    }
                });
            }
        });

    }else if(urlJson.pathname == "/fruit"){
        var f1 = "Apple";
        var f2 = "Orange";
        fs.readFile("./test.ejs", "utf-8", function(error, data){
            response.writeHead(200, {"Content-Type":"text/html;charset=utf-8"});
            /* ejs를 그냥 파일로 문자취급해서 보내면, 원본 코드까지 가버리기 때문에
            서버에서 실행을 한 후,그 결과를 보내야 한다. (jsp, php, asp의 원리) */
            response.end(ejs.render(data, {
                fruit: f1,
            }));
        });
    }
});

/* mysql 접속 */
function getConnection(){
    con = mysql.createConnection(conStr);     // json을 매개변수로 넣어주면 됨.
}

/* 서버 가동 */
server.listen(7878, function(){
    console.log("My Server is Running at 7878 port...")
    getConnection();
});





/* 
    http://localhost:7878/member/regist
    ?uid=
    &password=
    &uname=
    &phone=
    &email_id=
    &email_server=naver.com
    &addr=
    &memo=
    &skill_id=1
    &skill_id=3

*/
