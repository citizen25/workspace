package string;

/* �ڹ��� ��� ��ü�� ���� ���� �� ���� �ֻ��� ��ü�� �ΰ� �ִ�.
    Object ��ü. �����ڰ� ����� ������� �ʴ����, default�� �̹� ��ӵǾ� �ִ�.*/
class Duck{
    String name = "����";

    /* �Ʒ��� �޼���� Object Ŭ�����κ��� ��ӹ��� �޼����̸�,
    �� �޼���� ��ü�� ��Ʈ������ ��ȯ�ϰ��� �� �� �����Ѵ�.
    ��, �Ʒ��� �޼���� ��ü�� ����ϰ��� �� �� � �ڵ����� �����Ѵ�. 

    �Ʒ��� �޼���� Object�� �޼���������, �׽�Ʈ�� �����ϴ� ������ �˱����� 
    ��� overriding �� ��.*/
    public String toString(){
        System.out.println("toString() �޼��� �����մϴ�.");
        return "";
    }

    public static void main(String[] args){
        Duck d = new Duck();
        
        System.out.println(d);  //������ü�� �ƴ϶�, ������ �ּҰ��� ���´�.
        System.out.println(new Duck());  //���� ��ü�� ���?
    }
}
