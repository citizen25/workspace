/* 모든 인종을 아우를 수 있는 최상위 객체인 사람을 정의한다. 
    상위 객체일 수록 보편적인 특징을 가지고 있다.(우리의 현실과 동일) */
class Person{
    constructor(eye, arm){
        this.eye = eye;
        this.arm = arm;
        console.log("I an super class(Person)");
        console.log("my eye %d, arm %d", this.eye, this.arm);
    }
    work(){
        console.log("두발로 걸어요");
    }
    speak(){
        console.log("말을 해요");
    }
}