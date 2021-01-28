var http=require("http"); //웹서버 모듈 가져오기 
var fs=require("fs"); //파일 시스템 객체(파일에 대한 입출력 처리)
var mysql=require("mysql"); // mysql 외부모듈 가져오기

//Node.js로 프레임웍이 지원되지만, 오늘은 아무런 지원없이 직접 개발..
var server = http.createServer(function(request, response){

    console.log("클라이언트의 요청 url 은 ", request.url);
    console.log("클라이언트의 요청 method 는 ", request.method);
    
    
    if(request.url=="/rest/board/form" && request.method=="GET"){//입력양식폼을 요청하면..
        fs.readFile("./main.html", "utf-8", function(error, data){
            response.writeHead(200,{"Content-Type":"text/html;charset=utf-8"});
            response.end(data); //응답 정보중 body를 구성한다!!
        });
    }else if(request.url=="/rest/board" && request.method=="GET"){//목록 요청 처리

        //mysql 연동 
        let con=mysql.createConnection({
            url:"localhost",
            user:"root",
            password:"1234",
            database:"android"
        });
        
        var sql="select * from board order by board_id desc";
        con.query(sql , function(error, record, fields){
            if(error){
                response.writeHead(500, {"Content-Type":"application/json;charset=utf-8"});                            
                response.end("목록 가져오기 error");
            }else{
                console.log("데이터베이스의 레코드는 ", record);
                response.writeHead(200, {"Content-Type":"application/json;charset=utf-8"});
                response.end(JSON.stringify(record)); //string 화시켜 전송
            }      

        });
        
    }else if(request.url=="/rest/board/23" && request.method=="GET"){//상세내용
        
    }else if(request.url=="/rest/board" && request.method=="POST"){//등록
        //클라이언트의 전송 파라미터(json)를 받자 
        request.on("data", function(param){
            var json = JSON.parse(param);
            console.log("클라이언트가  post로 전송한 데이터는 ", json);

            let con=mysql.createConnection({
                url:"localhost",
                user:"root",
                password:"1234",
                database:"android"
            });
            var sql="insert into board(title,writer,content) values(?,?,?)";

            con.query(sql, [json.title, json.writer, json.content ] , function(error, results, fields){
                if(error){
                    response.writeHead(500, {"Content-Type":"application/json;charset=utf-8"});                            
                    response.end("등록하기 실패 error");
                }else{
                    response.writeHead(200, {"Content-Type":"application/json;charset=utf-8"});                            
                    response.end("등록하기 성공");
                }                 
            });
        });
        
    }else if(request.url=="/rest/board" && request.method=="PUT"){//수정
        
    }else if(request.url=="/rest/board/45" && request.method=="DELETE"){//삭제
        
    }

    

    

    

     

    

});




/*접속자 감지 
server.on("connection", function(){
    console.log("클라이언트 요청 감지");
});
*/

server.listen(7777, function(){
    console.log("The Server is running 7777 port...");
});