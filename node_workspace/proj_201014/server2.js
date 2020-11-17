/*
    Get / Post 전송을 이해하기 위한 서버 

    Get : 가져오다 (서버로부터 컨텐츠를 가져올 때 사용되는 요청 방식)
        html <a> 링크가 바로 get 방식의 요청이다.
        get 방식으로 데이터, 즉 파라미터를 전송하게 되면, 데이터가 노출되어 버린다.
        왜? http프로토콜의 헤더에 실어 나르므로 (편지봉투에 데이터를 작성하는 것과 같다.)
            또한 편지 봉투에 데이터를 전송하면, 편지지에 비해 전송할 수 있는 데이터의 용량이 작다.
        용도) 다른 url로 링크를 통해 파라미터 전송할 때. (뉴스기사 등..)

    Post : "보내다"라는 의미 (클라이언트가 서버에 데이터를 전송할 때 사용하는 방식)
        http 프로토콜의 body를 통해 데이터를 전송하기 때문에 마치 편지지에 데이터를 실어서 보내는 것과 같다.
        봉투(header)안에 담아서 보내기 때문에 데이터가 노출됮지 않는다.
        특징) 보낼 수 있는 데이터량에 한계가 없다.
        용도) 노출되면 위험한 데이터 전송 시 사용. (로그인요청, 회원가입 등..)
            파일(사진, 동영상) 등을 서버에 전송 시 사용.

        post 방식은 form 태그를 이용해서 요청할 수 있다.
*/
var http = require("http");
var url = require("url");
var querystring = require("querystring");       // get뿐만 아니라 post 까지 파싱이 가능.

var server = http.createServer(function(request, response){
    if(request.method == "GET"){
        response.writeHead(200, {"Content-Type":"text/html;charset=utf-8"})
        response.end("클라이언트가 GET 방식으로 요청했습니다.");

        /* get 방식으로 전달된 파라미터 받기 */
        var urlJson = url.parse(request.url, true);       // true 매개변수를 넘기면, 파라미터 정보를 json으로 변환.
        console.log("get url 분석 결과 : ", urlJson);
        
        var paramJson = urlJson.query;  // id, pass 담겨있다.
        console.log("ID : ", paramJson.id);
        console.log("Pass : ", paramJson.pass);
        
    }else if(request.method == "POST"){
        response.writeHead(200, {"Content-Type":"text/html;charset=utf-8"})
        response.end("클라이언트가 POST 방식으로 요청했습니다.");


        /* body로 전송된 데이터는 url 분석만으로는 해결이 안된다.
        따라서, post방식으로 전달된 데이터를 받기 위한 event를 감지해보자. */
        request.on("data", function(param){
            // var postParam = url.parse(new String(param).toString(), true);
            var postParam = querystring.parse(new String(param).toString());

            // console.log("post 전송된 파라미터 : ", postParam);
            console.log("ID : ", postParam.id);
            console.log("Pass : ", postParam.pass);
        });

        
    }
});

server.listen(9999, function(){
    console.log("My Server is running at 9999 port...");
});










