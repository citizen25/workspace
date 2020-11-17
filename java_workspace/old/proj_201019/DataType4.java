class DataType4{
	public static void main(String[] args){
		short s = 7;
		byte b = 5;

		s = b;		// 가능.
		// b = s;		// 불가능.
		b = (short)s;		// 가능. 강제 형변환. 큰->작 : demotion
	}
}