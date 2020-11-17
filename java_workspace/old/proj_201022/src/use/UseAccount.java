package use;

/* 현재 클래스는 public으로 공개해야 할까?
	사용하는 쪽은 공개될 필요가 없다. 따라서 X.
	사용당하는 쪽만 공개하면 도니다.

	UseAccount(사용하려는 객체) ---> Account(사용당할 객체) : Account는 공개되어야 함. public.
*/

import bank.Account;		// bank 이전의 경로는 classpath에 등록되어 있다.
class UseAccount{
	public static void main(String[] args){
		Account acc = new Account();		// 계좌 클래스 생성(public이라 여기까지는 무조건 가능함.)
		
		/* Account의 속성을 접근제한자 별로 접근해본다. */


		System.out.println(acc.bank);	// bank의 은행명 == public. 무조건 접근 가능.

		//System.out.println(acc.customer);	// customer == protected.
		// customer는 protected로 선언되어 있으므로 상속관계에 있거나, 같은 패키지인 경우에만 접근 가능.
		// 서로 다른 패키지이므로 데이터 접근이 불가하다. 컴파일시 에러가 나옴.

		//System.out.println(acc.num);		// 계좌번호 num == default.
		// 계좌번호 num변수는 개발자가 아무것도 명시하지 않았다. 이런 접근제한자를 default라고 한다.
		// default 라는 키워드는 명시해서는 안된다. 그냥 냅둬야 한다.
		// default 접근제한자는 같은 패키지에 있는 클래스끼리만 접근을 허용하므로,
		// protected보다 한단계 더 까다롭다. (즉, 상속관계에 있더라고, 같은 패키지가 아니면 접근 불가);
		// 컴파일 시 Error : num is not public in Account, cannot be accessed from outside package.
		// "공개되어 있지 않으므로 외부에서 접근이 불가능합니다" 라는 메세지가 나온다.
		// 지금껏 실습했던 모든 클래스가 사실 default였고, 여태 같은 디렉토리에서 실습을 해왔기 때문에 지금까지
		// 이런 에러를 만나지 않았던것 뿐.

		//System.out.println(acc.balance);	// 계좌 잔액 balance == private.
		// balance는 가장 강력한 접근제한자인 private이 적용되어 있으므로, Account 클래스 스스로만 접근이 가능.
		// 따라서 우리는 Account 자신이 아니기 때문에 절대 사용할 수 없다.
		// 완전히 폐쇄적.
		// 컴파일 시 Error : balance has provat access in Account (private 접근제한자가 적용되어 있다는 의미).
		
		

		// public은 그냥 보안 자체가 없다.
		// private은 너무 강력해서 아무도 못 쓰는데 왜 만들었을까?
		// 캡슐화, 은닉화를 위해..
		//acc.balance = 10;		// 이 방법은 직접 접근이므로 불가능.
		acc.setBalance(10);			// 10원으로 수정. 이 방법은 메서드를 통한 접근이므로 가능.
		// 잔고가 수정되었고, 어떻게 확인할까? 메서드를 제공한다. getBalance 메서드
		// 마치 리모컨에 채널전환 버튼만 있는게 아니라 현재 채널 확인 버튼도 있듯이..
		System.out.println(acc.getBalance());



		/*
		정리
		접근 제한지는 자바에서 보안을 처리하기 위한 방법을 제공한다.
		특히 private의 활용도가 상당히 높다.(클래스 내에 선언되는 변수는 거의 private 하는 게 많다.)
		왜냐하면 데이터는 보호되어야 하기 때문에. (리모콘 처럼 내부 장치는 덮어놓아야 한다.)
		이 내부장치를 외부에서 접근하려면 접근 "방법"을 제공해주어야 하는데 그게 바로 getter/setter메서드이다.
		데이터를 private으로 보호하고 오직 getter/setter를 통해서 데이터를 간접제어할 수 있도혹
		클래스를 정의하는 기법을 가리켜 객체지행 OOP에서는 은닉화(=EnCaptualation) 캡슐화 라고 한다.

		근데, setter/getter를 어차피 제공하면 private으로 막아놓았던 변수로 외부의 주체가 접근하게 되는건데
		의미가 있을까?
		ex) 아래의 변수는 아무도 접근 못하게 일부러 보안을 강화했음.
		private int balance = 100000;
		그런데, 막상 getter/setter를 이용해서 외부의 침입자가 제어할 수 있다면 하나마나한 짓이 아닐까?
		아니다. 제한을 가하면 된다.

		누군가 계좌의 잔고를 0으로 바꾸려고 하는 악의를 가진 객체가 있다고 하자.
		acc.setBalance(0);		// 나쁜짓

		그러나 메서드에 뭔가 제한을 한다면...

		public void setBalance(int balance){
			if(balance > 0){		// 만일 나쁜 사람이 이상한 짓을 한다면..
				this.balance = balance;
			}
		}

		마치 리모콘에서 이용자가 쓸데없는 짓을 하면 기능에 제한을 가하듯..
		
		결론은 메서드를 통해 얼마든지 방어할 수 있다는 것.
		*/
	}

}