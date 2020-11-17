/* 흑인 정의 
    상속 : BlackPerson 클래스는 Person 클래스의 코드까지 사용할 수 있도록 한다.*/
class BlackPerson extends Person{
    constructor(){
        /* this: 나를 가리키는 변수. 명칭이 정해져있는 예약어.
            super : 부모를 가리키는 변수. 명칭이 정해져있는 예약어.*/
        // this.name = "black bean";       // Error : 부모보다 시급한 초기화는 없기 때문에.. 부모의 탄생이 최우선된다. 부모가 존재해야 자식이 존재.
        /* 부모 생성자 호출 이유? 생물학적으도 당연. 자식의 초기화보다 최우선시 해야하는 작업은 부모의 존재. 즉, 초기화이므로. */
        // super();    // 부모의 constructor()
        super(5, 10);
        this.name = "black bean";

        this.color = "black";
        console.log("I am sub class(BlackPerson");
    }
}