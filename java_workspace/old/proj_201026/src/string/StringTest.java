package string;

class StringTest{
    public static void main(String[] args){
        /* String�� Ŭ������.
        ������ �츮 �ΰ��� String�� �ʹ� �е������� ���� ����ϱ⶧����
        new �����ڿ� ���� ��Ʈ���� �����ϴ� ���� �ʹ� �����ϴ�. 
        ���� String�� ���ؼ��� ��ġ �Ϲ� ������ Ÿ��ó�� new�� ���� �ʴ� ǥ������ �����Ѵ�.
        �̷��� String ��������� �Ͻ���, ������(implicit) �������̶� �Ѵ�.
        ������ �������� ���� String�� ���Ǯ�� ���� �����Ǿ� ����.
        ���Ǯ(Constant Pool) : heap������ ����. �����Ͱ� �ߺ��Ǵ� ��Ȳ���� ���� �����͸� ���� �ʰ�,
            ���� ������ ���� �����͸� ����Ű���� �ϴ� ���� �����̴�.(�޸� ����) 
            s1�� s2�� �񱳰��� true�� ������ ������ ���Ǯ�� �� reference �������� ���� ������. */

        // �Ʒ� �ڵ��� ���� ��� �����غ���
        String s1 = "apple";
        String s2 = "apple";
        System.out.println(s1 == s2);  //����� ���� ���ñ�? true

        /* String�� ���۹����� 'S'�� �빮���̹Ƿ� �и� ��ü���̴�.
        �츮�� ����ȯ�濡�� String.class�� ��򰡿� ���� ���̴�.
        java se�� ��� api�� ���ִ� ����� ���� == rt.jar.
        C:\Program Files\Java\jre1.8.0_261\lib\rt.jar*/

        /* �Ʒ��� ���� new �����ڿ� ���� String �������� �����(explicit) �������̸�,
        ����� �������� ���Ǯ�� �������� �����Ƿ�, new �Ҷ����� �ߺ����θ� ������ �ʰ�,
        ������ �����ȴ�. */

        String s3 = new String("korea");
        String s4 = new String("korea");
        System.out.println(s3 == s4);  //����� ���� ���ñ�? false

        // �ּҺ񱳰� �ƴ�, ��ü ���ϼ� ���ϴ� ��. (���� ��)
        String k1 = "orange";
        String k2 = "orange";
        System.out.println(k1 == k2);  //�ּ� ��.
        System.out.println(k1.equals(k2));  //���� ��.
    }
}
