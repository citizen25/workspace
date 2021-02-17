var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
var WebSocket=require("ws"); //웹소켓 모듈 가져오기

//모듈은 사실 상 파일이다(함수 단위로 등록하기도 한다..)!! 
//아래의 두개의 변수는 ./routes/index.js 파일을 의미한다.
var mainRouter = require('./routes/index'); //메인 요청을 처리하는 컨트롤러 파일
var memberRouter = require('./routes/member'); //member 요청을처리하는 컨트롤러 파일
var boardRouter = require('./routes/board'); //board 요청을처리하는 컨트롤러 파일

var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');//내가 사용할 뷰 InterResourceViewResolver 등록.. jsp

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());

//정적 자원에 대한 요청이 들어오면, 이 요처응ㄹ 처리하는 별도의 컨트롤러는 두지 않는다
//자동으로 응답을 처리한다...(정적자원:서버에서 실행되지 않는 모든 자원,html,css,js,image )
app.use(express.static(path.join(__dirname, 'public')));//정적 자원의 위치
//spring framework  resoureces


//웹 요청 처리 
app.use('/', mainRouter);
app.use('/member', memberRouter);  // http://~~~~:7777/member
app.use('/board', boardRouter);  // http://~~~~:7777/board



//웹소켓 요청 처리 
var wserver = new WebSocket.Server({port:9999});
global.socketArray=[]; //접속자마다 대응되는 소켓을 배열에 모아놓자

//접속자 감지!!
wserver.on("connection", function(socket){
  console.log("접속자 감지!!");

  socketArray.push(socket);

  //클라이언트에게 메시지 전송하기!!
  //socket.send("접속을 축하드립니다.");
  //클라이언트가 보낸 메시지 청취 이벤트 
  socket.on("message", function(data){
    console.log("클라이언트 메시지", data);
    //BroadCasting...
    for(var i=0;i<socketArray.length;i++){
      socketArray[i].send(data);//받은 메시지를 그대로 다시 전송한다
    }
  });
  
});


// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// error handler : Spring 의 Exception Handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;
