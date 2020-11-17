/* 
    node.js 자체적으로 지원하는 "전역변수"를 알아본다.
    1) __dirname : 현재 실행 중인 js 파일의 풀 경로 반환
    2) __filename : 파일명 반환

    node.js 자체적으로 지원하는 "전역객체"를 알아본다.
    1) console
    2) exports
    3) module
    4) process
    5) global
*/
/* 
console.log("지금 실행중인 파일명은", __filename);
console.log("지금 실행중인 파일의 디렉토리는", __dirname);
 */

/* console 객체 - 컴퓨터 성능 파악(cpu 속도 체크)*/
/* 
console.time("mycpu");  // 실행시 시작 시간을 출력해준다. 원하는 label을 넣어줄 수 있다.
for(var i=1; i<=1000000; i++){
}
console.log("100만번 수행 완료");
console.timeEnd("mycpu");   // 종료 시간을 출력해준다.
 */



/* process 객체 */
/* 
console.log(process.arch);  // cpu에 대한 정보
console.log(process.env);  // 컴퓨터 환경 정보
console.log(process.platform);
 */