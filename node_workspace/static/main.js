var http=require("http");
var express=require("express");
var static  = require("serve-static");//정적 자원 처리 전담 미들웨어!!

var app = express();
app.use(static(__dirname+"/static")); 

var server = http.createServer(app);

server.listen(8888, function(){
    console.log("The Server is running at 6666...");
});

