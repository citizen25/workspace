package use;

// ��������θ� ����.. �Ʒ�ó�� ����ϸ�, c: ��ζ����� ������ �߻�
// �� �ڹ� �ڵ�� window, linux, mac���� ��� ����Ǿ�� �ϹǷ�,
// Ư�� os�� �������� ��η� ����ϸ� �ȵȴ�.

// 
// os ȯ�溯�� - path : �����ϰ��� ������ ��� ��ġ.
//			classpath == �����ϰ��� Ŭ���� ������ ��� ��ġ.(C:\workspace\java_workspace\proj_201021\bin)

// JVM�� ������ �����ϱ� ���� classpath ���� Ȯ���Ѵ�. �� classpath�� �����ؼ� �����Ѵ�.


import fashion.down.Pants;		// ������ ���ؼ��� ����ϰ��� Ŭ������ ��ġ�� ����ؾ��Ѵ�. == bin�� ��ġ�� ����Ѵ�.

class UsePants{

	public static void main(String[] args){
		// ���� Ŭ�����ʹ� ���������� ������ �ִ�, �ٸ� ��Ű���� ����ִ� Ŭ���� ����غ���
		// js : <script src="���/���ϸ�"></script>
		Pants p = new Pants();		// ã�� ����..
		System.out.println(p);
	}
}