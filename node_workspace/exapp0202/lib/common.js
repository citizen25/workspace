//사용자 정의 모듈
/*
1.하나의 함수를 모듈 자체로 만드는 법
module.exports.getMsg=function(){
    return "ha ha ha";
}
*/

//2.객체를 모듈로 정의 
var formatter={
    getCurrency:function(){
        return 5000;
    },
    getLocale:function(){
        return "Korea";
    }
};
module.exports = formatter;