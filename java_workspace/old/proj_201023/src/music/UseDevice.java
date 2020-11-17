package music;

class UseDevice{
    public static void main(String[] args){
        /* 추상 클래스는 불완전한 클래스이므로, 인스턴스 생성이 불가. 
            미완성이라서..
            따라서 자식 객체가 완성하면 가능. 
            자식에 의해서 인스턴스화 될 수 있다. */
        //AudioDevice ad = new Speaker();
        riding.Wing wing = new Speaker();   // inline style.
        wing.fly();     // 자식꺼 호출한다.

        /*
        extends 건 implements 건 둘다 is a 다.
        따라서 서로간 같은 종류의 자료형으로 간주되며, 형변환도 지원.
        자식이 오버라이딩을 하면, 자식의 메서드를 최우선으로 호출한다.
        */
    }
}
