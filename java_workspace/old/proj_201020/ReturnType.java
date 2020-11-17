/* 메서드 */
class ReturnType{
	String name;
	/* 메서드 작성법 */
	public void setPrice(){
		int price = 500;
	}
	/* 반환값 : 해당 반환갑의 자료형을 그대로 지정해주면 된다. */
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