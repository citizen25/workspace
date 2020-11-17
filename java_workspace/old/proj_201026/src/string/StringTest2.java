package string;

class StringTest2{
    public static void main(String[] args){
        // String은 불변(immutable)이다.
        String s1 = "korea";
        System.out.println(s1);  //korea

        s1 = s1 + " fighting..";
        System.out.println(s1);  //korea fighting

        // 문자열 상수는 수정불가라서 가리키는 주소값만 바뀔 뿐, 값은 상수풀에 100개가 올라간다.
        String str = "";
        for(int i=1; i<=100; i++){
            str += "줄넘기 " + i + "회\n";
        }
        System.out.println(str);

        /* 해결책)
            수정가능한 문자열 처리 객체
                StringBuffer, StringBuilder */
        // 밑의 코드는 단 하나의 자리를 차지한다.
        StringBuilder sb = new StringBuilder();
        sb.append("");
        for(int i=1; i<=100; i++){
            sb.append("줄넘기 " + i + "회\n");
        }
        System.out.println(sb);

    }
}
