/* �޼��� */
class ReturnType{
	String name;
	/* �޼��� �ۼ��� */
	public void setPrice(){
		int price = 500;
	}
	/* ��ȯ�� : �ش� ��ȯ���� �ڷ����� �״�� �������ָ� �ȴ�. */
	public int getPrice(){
		return 500;
	}
	public boolean getBool(){
		return false;
	}
	public char getChar(){
		return 'A';
	}
	public double getNum(){
		return 89.756;
	}

	public static void main(String[] args){
		ReturnType rt = new ReturnType();
		System.out.println(rt.getNum());
	}
}