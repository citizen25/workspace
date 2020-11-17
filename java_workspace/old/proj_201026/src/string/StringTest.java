package string;

class StringTest{
    public static void main(String[] args){
        /* String은 클래스다.
        하지만 우리 인간은 String을 너무 압도적으로 많이 사용하기때문에
        new 연산자에 의해 스트링을 생성하는 것은 너무 불편하다. 
        따라서 String에 한해서는 마치 일반 데이터 타입처럼 new를 쓰지 않는 표현식을 지원한다.
        이러한 String 생성방법을 암시적, 묵시적(implicit) 생성법이라 한다.
        묵시적 생성법에 의한 String은 상수풀에 의해 관리되어 진다.
        상수풀(Constant Pool) : heap영역에 존재. 데이터가 중복되는 상황에서 같은 데이터를 넣지 않고,
            참조 변수가 같은 데이터를 가리키도록 하는 것이 목적이다.(메모리 절약) 
            s1과 s2의 비교값이 true가 나오는 이유도 상수풀에 들어가 reference 변수값이 같기 때문에. */

        // 아래 코드의 수행 결과 예측해보기
        String s1 = "apple";
        String s2 = "apple";
        System.out.println(s1 == s2);  //결과는 뭐가 나올까? true

        /* String은 시작문자인 'S'가 대문자이므로 분명 객체형이다.
        우리의 개발환경에서 String.class가 어딘가에 있을 것이다.
        java se의 모든 api가 모여있는 압축된 파일 == rt.jar.
        C:\Program Files\Java\jre1.8.0_261\lib\rt.jar*/

        /* 아래와 같이 new 연산자에 의한 String 생성법을 명시적(explicit) 생성법이며,
        명시적 생성법은 상수풀에 생성되지 않으므로, new 할때마다 중복여부를 따지지 않고,
        무조건 생성된다. */

        String s3 = new String("korea");
        String s4 = new String("korea");
        System.out.println(s3 == s4);  //결과는 뭐가 나올까? false

        // 주소비교가 아닌, 객체 동일성 비교하는 법. (내용 비교)
        String k1 = "orange";
        String k2 = "orange";
        System.out.println(k1 == k2);  //주소 비교.
        System.out.println(k1.equals(k2));  //내용 비교.
    }
}
