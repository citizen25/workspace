/* 
    Node.js에서 오라클과 연동해보자.
*/

let conStr = {
    user: "user0907",
    password: "1234",
    connectionString: "localhost/XE"
}

/* 오라클에 접속하려면, 접속을 담당하는 모듈을 사용해야 한다.
Node.js를 설치하면 아주 기본적인 모듈만 내장되어 있기 때문에 
개발에 필요한 모듈은 그때 그때 다운로드 받아 설치해야 한다. (Node.js가 인기있는 이유) */
var oracledb = require("oracledb");

/* 오라클에 접속을 시도해본다. */
oracledb.getConnection(conStr, function(error, con){
    if(error){
        console.log("접속 실패:", error);
    }else{
        console.log("접속 성공");

        /* 테이블에 데이터를 넣어보자! 
            접속 객체를 이용하여, Insert 쿼리를 날려보자 */
        insert(con);
        }
    });
    
function insert(con){
    var sql = "";
    sql += "insert into member2(member2_id, firstname, lastname, local, msg)"
    sql += " values(seq_member2.nextval, 'simin', 'choe', 'house', 'hi')"

    /* 쿼리 실행 */
    con.execute(sql, function(error, result, fields){
        if(error){
            console.log("입력 실패: ", error);
        }else{
            console.log("입력 성공");
        }
    });

    console.log("insert 할꺼임");    
}