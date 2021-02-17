var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
var WebSocket = require("ws");

var indexRouter = require('./routes/index');
var boardRouter = require('./routes/board');

var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', indexRouter);
app.use('/board', boardRouter);

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

//웹소켓 서버구축 및 요청 처리 
var wserver = new WebSocket.Server({port:9999});

//접속자를 저장해놓을 배열 선언 
global.socketArray=[];

//접속자 감지!!
wserver.on("connection",  function(socket){
    console.log("접속자 감지!!");
    socketArray.push(socket); //소켓을 접속자 명단에 추가!!
    console.log("현재 접속자 수 ", socketArray.length);

  //대화용 소켓으로 메시지 주고 받기!!
  socket.on("message", function(data){
    console.log("클라이언트 메시지 도착!",  data);
    
    //클라이언트의 메시지 프로토콜 분석 
    var json = JSON.parse(data);
    if(json.requestCode=="create"){ //글쓰기 요청이면..
       //가공할 작업있으면 여기서.. 
    }else if(json.requestCode=="read"){
      //가공할 작업있으면 여기서.. 
    }else if(json.requestCode=="update"){
      //가공할 작업있으면 여기서.. 
    }else if(json.requestCode=="delete"){
      //가공할 작업있으면 여기서.. 
    }
    //도착한 메시지를 다시 보내기!!(echo) , 단 대상은 모든 접속자에게!!(브로드케스팅)
    for(var i=0;i<socketArray.length;i++){
      socketArray[i].send(data);
    }
  });
});


// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;
