/* animal category server */

let http = require("http");
let fs = require("fs");
let url = require("url");
let ejs = require("ejs");
let mysql = require("mysql");

var urlJson;

var server = http.createServer(function(request, response){
    // console.log(url.parse(request.url, true));
    urlJson = url.parse(request.url, true);

    if(urlJson.pathname == "/"){
        response.end("animal world!");
    }else if(urlJson.pathname == "/category"){
        getCategory(request, response);
    }else if(urlJson.pathname == "/animal"){
        getAnimal(request, response);
    }else{
        response.writeHead(404, {"Content-Type":"text/html;charset=utf-8"});
        response.end("존재하지 않는 페이지");
    }
    
});

/* 동물의 종류 가져오기 */
function getCategory(request, response){
    var sql = "SELECT * FROM category";

    con.query(sql, function(error, record, fields){
        if(error){
            console.log("category 가져오기 실패", error);
        }else{
            fs.readFile("./animal.ejs", "utf-8", function(err, data){
                if(err){
                    console.log("animal.ejs 읽기 실패", err);
                }else{
                    response.writeHead(200, {"Content-Type":"text/html;charset=utf-8"});
                    response.end(ejs.render(data, {
                        categoryArray: record,
                    }));
                }
            });
        }
    });
}

function getAnimal(request, response){
    /* mysql 연동 */
    /* 카테고리 가져오기 */
    var sql = "SELECT * FROM category";
    con.query(sql, function(error, record, fields){
        if(error){
            console.log("category 가져오기 실패", error);
        }else{
            var categoryRecord = record;     // 카테고리 목록 배열

            category_id = urlJson.query.category_id;     // get 방식의 category_id 파라미터 받기.
            sql = "SELECT * FROM animal WHERE category_id = " + category_id;
            con.query(sql, function(error, record, fields){
                if(error){
                    console.log("동물 목록 가져오기 실패", error);
                }else{

                    fs.readFile("./animal.ejs", "utf-8", function(err, data){
                        if(err){
                            console.log("animal.ejs 읽기 실패", err);
                        }else{
                            response.writeHead(200, {"Content-Type":"text/html;charset=utf-8"});
                            response.end(ejs.render(data, {
                                animalArray: record,
                                categoryArray: categoryRecord,
                                category_id: category_id,
                            }));
                        }
                    });
                }
            });
        }
    });

}

function connect(){
    con = mysql.createConnection({
        url: "localhost",
        user: "root",
        password: "1234",
        database: "node",
    });

}

server.listen(3093, function(){
    console.log("Animal Server is Running at port 3093...");
    connect();
});



