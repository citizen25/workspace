var http = require("http");
var fs = require("fs");
var message = require("./httpentity.js");
var url=require("url");
var querystring = require("querystring");


var server = http.createServer(function(request, response){
    console.log("클라이언트 접속 감지", request.method);
    //response.writeHead(200, {"Content-Type":"text/html;charset=utf-8"});
    

    //html페이지를 요청하면..
    if(request.url=="/member/form"){
        fs.readFile("client.html", "utf-8", function(error, data){
            if(error){
                response.writeHead(500, {"Content-Type":"text/html;charset=utf-8"});              
                response.end("페이지 요청 에러");
            }else{
                response.writeHead(200, {"Content-Type":"text/html;charset=utf-8"});              
                response.end(data);
            }
        })
    }else if(request.url=="/member" && request.method=="GET"){
        //member의 목록요청 처리
        response.writeHead(200, {"Content-Type":"application/json;charset=utf-8"});
        var obj={};
        obj["id"]="scott";
        obj["name"]="tiger";
        response.end(JSON.stringify(obj));
    }else if(request.url=="/member" && request.method=="POST"){
        request.on("data", function(param){
            
            console.log("회원등록을 원하는군요.", JSON.parse(param));            

            response.writeHead(200, {"Content-Type":"application/json;charset=utf-8"});
            message.msg="등록성공";
            response.end(JSON.stringify(message));
        });
    }
});

server.listen(7777, function(){
    console.log("Server is running 8888 port...");
});

