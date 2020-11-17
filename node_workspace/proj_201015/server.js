/*
    Get / Post
        Post : 로그인(노출x), 회원가입(노출x), 영상/이미지 업로드(양이 많아 body에 post해야하낟.)
        Get : 뉴스기사 파라미터 전달(링크, 데이터량이 얼마 안된다.)
*/

var http = require("http");
var url = require("url");
var fs = require("fs");
var mysql = require("mysql");
var ejs = require("ejs");
var qs = require("querystring");

var urlJson;
let con;

var server = http.createServer(function(request, response){
    /* 요청 구분 */
    urlJson = url.parse(request.url, true);
    // console.log("urlJson : ", urlJson);
    if(urlJson.pathname == "/"){    // 메인을 요청하면...
        fs.readFile("./index.html", "utf-8", function(error, data){
            if(error){
                console.log("index.html 읽기 실패 : ", error);
            }else{
                response.writeHead(200, {"Content-Type":"text/html;charset=utf-8"});
                response.end(data);
            }
        });
    }else if(urlJson.pathname == "/member/registForm"){     // 가입폼을 요청하면...
        registForm(request, response);
    }else if(urlJson.pathname == "/member/regist"){     // 가입을 요청하면...
        regist(request, response);
    }else if(urlJson.pathname == "/member/loginForm"){     // 로그인폼을 요청하면...
        
    }else if(urlJson.pathname == "/member/list"){     // 회원 목록을 요청하면...
        getList(request, response);
    }else if(urlJson.pathname == "/member/detail"){     // 회원 정보 보기를 요청하면...
        getDetail(request, response);
    }else if(urlJson.pathname == "/member/del"){     // 회원 정보 삭제를 요청하면...
        del(request, response);
    }else if(urlJson.pathname == "/member/edit"){     // 회원 정보 수정을 요청하면...
        update(request, response);
    }else if(urlJson.pathname == "/category"){     // 동물 구분 정보를 요청하면...
        getCategory(request, response);
    }
});

/* 데이터 베이스 연동인 경우엔 별도로 함수에 정의 */
function registForm(request, response){
    /* 회원가입폼은 디자인을 표현하기 위한 파일이므로, 기존에는 html로 충분했으나,
    보유기술은 DB의 데이터를 가져와서 반영해야 함로, ejs로 처리해야 한다. */
    var sql = "SELECT * FROM skill";
    con.query(sql, function(error, record, fields){
        if(error){
            console.log("skill 조회 실패", error);
        }else{
            console.log("skill record", record);
            /* registForm.ejs에게 json배열을 전달하자 */
            fs.readFile("./registForm.ejs", "utf-8", function(error, data){
                if(error){
                    console.log("registForm.ejs 읽기 실패", error);
                }else{
                    response.writeHead(200, {"Content-Type":"text/html;charset=utf-8"});
                    response.end(ejs.render(data, {
                        skillArray: record,
                    }));
                }
            });
        }
    });
}
function regist(request, response){
    /* post 방식으로 전송된, 파라미터 받기. */
    request.on("data", function(param){      // body로 날아오기 때문에 on으로 받아야 한다.
        /* url 모듈에 파싱을 처리하게 하지 말고, querystring 모듈로 처리한다. */
        // console.log("post param", new String(param).toString());
        var postParam = qs.parse(new String(param).toString());
        console.log("postParam", postParam);

        var sql = "INSERT INTO member2(uid, password, uname, phone, email, recieve, addr, memo)"
        sql += " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";   // 물음표 == 바인드 변수

        con.query(sql, [
            postParam.uid, 
            postParam.password, 
            postParam.uname, 
            postParam.phone, 
            postParam.email, 
            postParam.recieve,
            postParam.addr, 
            postParam.memo, 
        ], function(error, fields){
                if(error){
                    console.log("등록 실패", error);
                }else{
                    /* 목록 페이지 보여주기 - 등록 되었음을 alert()로 알려주고, /member/list로 재접속 */
                    response.writeHead(200, {"Content-Type":"text/html;charset=utf-8"});
                    var tag = "<script>";
                    tag += "alert('등록 성공');";
                    tag += "location.href='/member/list';";     // <a> tag와 동일한 효과
                    tag += "</script>";
                    response.end(tag);
                }
            }
        );
    });
}
function getList(request, response){
    /* 회원 목록 가져오기 */
    var sql = "SELECT * FROM member2";
    con.query(sql, function(error, record, fields){
        // console.log(record);
        if(error){
            console.log("조회 실패", error);
        }else{
            fs.readFile("./list.ejs", "utf-8", function(error, data){
                if(error){
                    console.log("list.ejs 읽기 실패", error);
                }else{
                    response.writeHead(200, {"Content-Type":"text/html;charset=utf-8"});
                    response.end(ejs.render(data, {
                        memberArray: record,
                    }));
                }
            });
        }
    });
}
function getDetail(request, response){
    // console.log("urlJson", urlJson);
    var member2_id = urlJson.query.member2_id;
    var sql = "SELECT * FROM member2 WHERE member2_id=" + member2_id;
    
    con.query(sql, function(error, record, fields){
        if(error){
            console.log("한건 조회 실패");
        }else{
            fs.readFile("./detail.ejs", "utf-8", function(err, data){
                if(err){
                    console.log("detail.ejs 읽기실패", err);
                }else{
                    response.writeHead(200, {"Content-Type":"text/html;charset=utf-8"});
                    response.end(ejs.render(data, {
                        member: record[0],
                    }));
                }
            });
        }
    });
}
function del(request, response){

    /* get 방식으로 전달된 파라미터 받기 */
    var member2_id = urlJson.query.member2_id;
    var sql = "DELETE FROM member2 WHERE member2_id=" + member2_id;

    /* error, fields : DML(insert, update, delete == 조작) 
       error, record, fields : select */

    con.query(sql, function(error, fields){
        if(error){
            console.log("삭제 살패", error);
        }else{
            /* alert 띄우고, 회원목록 보여주기 */
            response.writeHead(200, {"Content-Type":"text/html;charset=utf-8"});
            
            var tag = "<script>";
            tag += "alert('탈퇴 처리 완료');";
            tag += "location.href = 'member/list'";
            tag += "</script>";
            response.end(tag);
        }
    });
}
function update(request, response){
    /* post로 전송된 parameter들을 받자 */
    request.on("data", function(param){
        var postParam = qs.parse(new String(param).toString());
        
        /* 검증용 */
        // var sql = "UPDATE member2 SET phone='" + postParam.phone + "', email='" + postParam.email + "', addr='" + postParam.addr + "', ";
        // sql += "memo='" + postParam.memo + "', password='" + postParam.password + "', recieve='" + postParam.recieve + "' WHERE member2_id=" + postParam.member2_id;
        // console.log(sql);
        
        var sql = "UPDATE member2 SET phone=?, email=?, addr=?, ";
        sql += "memo=?, password=?, recieve=? WHERE member2_id=?";

        con.query(sql, [
                postParam.phone,
                postParam.email,
                postParam.addr,
                postParam.memo,
                postParam.password,
                postParam.receive,
                postParam.member2_id,
        ], function(error, fields){
                if(error){
                    console.log("수정 실패", error);
                }else{
                    /* alert 띄우고, 상세보기(detail) 보여주기 */
                    response.writeHead(200, {"Content-Type":"text/html;charset=utf-8"});
                    var tag = "<script>";
                    tag += "alert('수정 처리 완료');";
                    tag += "location.href = '/member/detail?member2_id=" + postParam.member2_id + "';";
                    tag += "</script>";
                    response.end(tag);
                }
            }
        );
    });
}

/* 동물의 종류 가져오기 */
function getCategory(request, response){
    var sql = "SELECT * FROM category";

    con.query(sql, function(error, record, fields){
        if(error){
            console.log("동물구분 목록 가져오기 실패", error);
        }else{
            fs.readFile("./animal.ejs", "utf-8", function(err, data){
                if(err){
                    console.log("animal.ejs 읽기 실패", err);
                }else{
                    response.writeHead(200, {"Content-Type":"text/html;charset=utf-8"});
                    response.end(ejs.render(data, {
                        categoryArray: record,
                    }));
                }
            });
        }
    });
}


/* mysql 접속 함수 */
function connect(){
    con = mysql.createConnection({
        url: "localhost", 
        user: "root",
        password: "1234",
        database: "node",
    });
}

server.listen(7788, function(){
    console.log("Server is running at 7788 port...")
    connect();
});









