/* mysql 연동하기 */
var mysql = require("mysql2");

/* 접속에 필요한 정보 */
let conStr = {
    url: "localhost",
    user: "root",
    password: "1234",
    database: "node"
}

/* 접속 */
var con = mysql.createConnection(conStr);

/* 반환된 con을 이용하여 쿼리문 수행 */
var sql = "insert into member(firstname,lastname,local,msg)"
sql += " values('simin','choe','dongjak','hi')"

con.query(sql, function(error, results, fields){
    if(error){
        console.log("등록 실패:", error);
    }else{
        console.log("등록 성공");
    }
});     // 쿼리 실행
