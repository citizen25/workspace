
//�� 1���� ��� �� Ŭ���� ����

package day1116.pubapi;

public class Mountain {
	//��������δ� ���� ������ ������ �� ������ �մ� ��� �����͸� �� �ֱ⿣ �ʹ� ����
	//���ϴ� ���� ��� ��´�
	//��� ���� �̸��� �־�� �ϴϱ�, �� �̸��� �ش��ϴ� xml�±װ� ���� �������� -> mntnnm
	
	private int mntnid;  //���� ���� �ڵ�
	private String mntnnm;  //�� �̸� ���� ����
	private String mntninfopoflc;  //������ ������(������)
	private int mntninfohght;  //�� ����

	//����ȭ �Ѵ� (getter/setter)
	public int getMntnid() {
		return mntnid;
	}
	public void setMntnid(int mntnid) {
		this.mntnid = mntnid;
	}
	public String getMntnnm() {
		return mntnnm;
	}
	public void setMntnnm(String mntnnm) {
		this.mntnnm = mntnnm;
	}
	public String getMntninfopoflc() {
		return mntninfopoflc;
	}
	public void setMntninfopoflc(String mntninfopoflc) {
		this.mntninfopoflc = mntninfopoflc;
	}
	public int getMntninfohght() {
		return mntninfohght;
	}
	public void setMntninfohght(int mntninfohght) {
		this.mntninfohght = mntninfohght;
	}
}
