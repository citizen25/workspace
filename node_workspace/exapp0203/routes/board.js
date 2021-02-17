var express = require('express');
var router = express.Router();
var mysql = require("mysql");
var message = require("../lib/message");

var conStr={
  url:"localhost",
  user:"root",
  password:"1234",
  database:"android"
};

//모든 클라이언트에게 브로드 케스팅 
function broadCasting(message){
  for(var i=0;i<socketArray.length;i++){
    socketArray[i].send(JSON.stringify(message));
  }      
};

//목록 가져오기
router.get('/', function(request, response, next) {
  var con = mysql.createConnection(conStr);
  var sql="select * from board order by board_id desc";

  con.query(sql, function(error, result, fields){
    if(error){
      response.writeHead(500, {"Content-Type":"application/json;charset=utf-8'"});
      message.resultCode=500;
      message.msg="목록실패";
      response.end(JSON.stringify(message));
    }else{
      response.writeHead(200, {"Content-Type":"application/json;charset=utf-8'"});
      message.requestCode="read";
      message.resultCode=200;
      message.data=result;
      message.msg="목록가져오기 성공";
      response.end(JSON.stringify(message));
    }
    con.end();//접속끊기
  });
});

//상세보기
router.get('/:board_id', function(request, response, next) {
  var con = mysql.createConnection(conStr);
  var sql="select * from board where board_id=?";

  con.query(sql,[request.params.board_id] ,function(error, result, fields){
    if(error){
      response.writeHead(500, {"Content-Type":"application/json;charset=utf-8'"});
      message.resultCode=500;
      message.msg="상세보기 실패";
      response.end(JSON.stringify(message));
    }else{
      response.writeHead(200, {"Content-Type":"application/json;charset=utf-8'"});
      message.requestCode="read";
      message.resultCode=200;
      message.data=result;
      message.msg="한건 가져오기 성공";
      response.end(JSON.stringify(message));
    }
    con.end();//접속끊기
  });

});

//등록
router.post('/', function(request, response, next) {
  var con = mysql.createConnection(conStr);
  var sql="insert into board(title, writer ,content) values(?,?,?)";
  var title=request.body.title;
  var writer=request.body.writer;
  var content=request.body.content;

  con.query(sql,[title, writer, content] ,function(error, result, fields){
    if(error){
      response.writeHead(500, {"Content-Type":"application/json;charset=utf-8'"});
      message.resultCode=500;
      message.msg="등록실패";
      response.end(JSON.stringify(message));
    }else{
      response.writeHead(200, {"Content-Type":"application/json;charset=utf-8'"});
      message.requestCode="create";
      message.resultCode=200;
      message.data=result;
      message.msg="등록하기 성공";
      response.end(JSON.stringify(message));

      //새로운 글이 등록되었음을, 접속한 모든 클라이언트에게 브로드케스팅 하자!!
      message.requestCode="create";//글 등록이 발생했을을 알려줌..
      message.resultCode=200;
      message.msg="새로운 글 등록";
      broadCasting(message);
    }
    con.end();//접속끊기
  });

});

//수정
router.put('/', function(request, response, next) {
  var con = mysql.createConnection(conStr);
  var sql="update board set title=?, writer=?, content=? where board_id=?";
  var title=request.body.title;
  var writer=request.body.writer;
  var content=request.body.content;
  var board_id=request.body.board_id;

  con.query(sql,[title, writer, content, board_id] ,function(error, result, fields){
    if(error){
      response.writeHead(500, {"Content-Type":"application/json;charset=utf-8'"});
      message.resultCode=500;
      message.msg="수정실패";
      response.end(JSON.stringify(message));
    }else{
      response.writeHead(200, {"Content-Type":"application/json;charset=utf-8'"});
      message.requestCode="update";
      message.resultCode=200;
      message.data=result;
      message.msg="수정하기 성공";
      response.end(JSON.stringify(message));//웹요청에 대한 응답

      broadCasting(message);//웹소켓을 이용한 브로드케스팅
    }
    con.end();//접속끊기
  });

});

//삭제
router.delete('/:board_id', function(request, response, next) {
  var con = mysql.createConnection(conStr);
  var sql="delete from board where board_id=?";
  var board_id=request.params.board_id;

  con.query(sql,[board_id] ,function(error, result, fields){
    if(error){
      response.writeHead(500, {"Content-Type":"application/json;charset=utf-8'"});
      message.resultCode=500;
      message.msg="삭제실패";
      response.end(JSON.stringify(message));
    }else{
      response.writeHead(200, {"Content-Type":"application/json;charset=utf-8'"});
      message.requestCode="delete";
      message.resultCode=200;
      message.data=result;
      message.msg="삭제하기 성공";

      response.end(JSON.stringify(message));//웹요청 응답
      broadCasting(message);//웹소켓 브로드케스팅
    }
    con.end();//접속끊기
  });
});
module.exports = router;