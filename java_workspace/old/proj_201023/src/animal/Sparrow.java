/* ���� ���� */
package animal;

public class Sparrow extends Bird{
    String name = "�� ����";
    public void jjack(){
        System.out.println("±±");
    }
    /*
    �ڽ� Ŭ�������� �θ�� 100% ������ �޼��带 �����ϴ� ��� : �������̵� (Overriding).
    ��? �θ��� �޼��带 �ڽĿ��� ��� ����, �߰� �ϴ� ���� ���׷��̵带 �ϱ� ���ؼ�.
    */
    /* ����!
        �����ε� vs �������̵�
        �����ε� : ���� Ŭ���� ������ ����� ����� �޼������ ���� ���� ������ ����,
        �Ű����� ������ �ڷ������� �����ϸ� �ߺ� ���Ǹ� ������ �ִ°�.
        �������̵� : ��Ӱ��迡�� �ڽ��� �θ��� �޼��带 ������ �ϴ� ���. (=���׷��̵�)
    */
    public void fly(){  // ������ : �������̵�
        System.out.println("������ ���ƿ�");
    }
    public static void main(String[] args){
        Sparrow sp = new Sparrow();
        sp.fly();   // ������ �޼��� ȣ��

        Bird bird = new Bird();
        bird.fly();
        
        Bird bird2 = new Sparrow();
        bird2.fly();    // �����?


    }
}
