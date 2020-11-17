package bank;		// 원본 소스용 bank라는 패키지를 만든다.

/* 고객의 계좌를 정의 : 업무가 신중해진다. 예민한 데이터를 많이 다룸. */
// 패키지에 넣은 클래스를 public으로 공개하지 않으면, 다른 어떠한 클래스도
// 클래스를 사용할 수 없다. 이건 보안이 아니라, 그냥 의미 없는 짓.
// 클래스는 쓰라고 만드는 것이기 때문에, public으로 공개하되,
// 그 안의 내용에 대해 보안처리 하면 된다.
// 따라서! public으로 공개해준다.
public class Account{
	/* 계좌에 들어갈 만한 속성들을 정의 */
	
	/*
	이 클래스의 접근 제한자가 어떤 작용을 하는지, 외부의 클래스에서 데이터에 접근하는 실습.
	public은 보안이라고 말할 수 없으므로, 빼고 생각하자.
	protected, default, private만 신경 쓴다.
	*/
	
	public String bank = "신한은행";		// 은행명
	protected String customer;		// 고객 이름
	String num = "022-388-85465";		// 계좌번호
	private int balance = 100000;		// 금액

	// private으로 선언된 변수는 절대 아무도 외부에서 접근할 수 없으므로,
	// 변수에 접근하려면, 메서드를 이용해야 한다.
	// 아래의 메서드는 public이므로 모든 객체가 접근 가능.
	// UseAccount에서 잔고를 수정하고 출력해보자
	public void setBalance(int balance){
		this.balance = balance;
	}


	// 잔고확인 메서드 정의
	// 이와 같이 private으로 선언된 변수의 값을 리턴하는 메서드를 가리켜 "getter"라고 한다.
	// 위의 setBalance처럼 private 변수의 값을 변경하는 메서드를 "setter"라고 한다.
	// getter와 setter는 아주 유명한 메서드 정의 기법이다.
	public int getBalance(){
		return balance;
	}
}