package car;

/* extends로 Car를 상속받게 되고, 지금부터 Car의 자원에 접근가능하다. */
public class Truck extends Car{
	 public void dump(){
		System.out.println("물건을 대량으로 옮깁니다");
	 }
}