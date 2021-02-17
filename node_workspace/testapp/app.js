var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
var WebSocket = require("ws");

var indexRouter = require('./routes/index');


var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', indexRouter);


//웹소켓 관련 
var clientArray=[];

var wserver = new WebSocket.Server({port:7575});

wserver.on("connection", function(socket){
  clientArray.push(socket);
  console.log("현재 접속자 ", clientArray.length, "명");
  
  socket.on("message", function(data){
    console.log("클라이언트의 메시지 ", data);
    
    
    for(var i=0;i<clientArray.length;i++){
      clientArray[i].send(data);
    }
  });

});



// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
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
