var http = require("http");
var url = require("url");
var fs = require("fs");
var ejs = require("ejs");

var server = http.createServer(function(request, response){
    var urlJson = url.parse(request.url, true);
    console.log("request.url : ", urlJson);

    if(urlJson.pathname == "/"){
        fs.readFile("./ejs_review.ejs", "utf-8", function(error, data){
            if(error){
                console.log("ejs error:", error);
            }else{
                response.writeHead(200, {"Content-Type":"text/html;charset=utf-8"});
                response.end(ejs.render(data));
            }
        });
    }else if(urlJson.pathname == "/intro"){
        response.writeHead(200, {"Content-Type":"text/html;charset=utf-8"});
        response.end("소개 페이지");
    }else if(urlJson.pathname == "/rnn"){
        response.writeHead(200, {"Content-Type":"text/html;charset=utf-8"});
        response.end("RNN 페이지");
    }

});

server.listen(3093, function(){
    console.log("Server is running at 3093 port...")
});