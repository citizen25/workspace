package com.koreait.bootproject0208;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.koreait.bootproject0208.test.Dog;

@SpringBootApplication
public class Bootproject0208Application {

	public static void main(String[] args) {
		SpringApplication.run(Bootproject0208Application.class, args);
	}
	
	// Spring 2.5 버전부터는 xml을 사용하지 않고, 자바 코드에서 Bean등을 생성 및 설정할 수 있는 방법을 제공한다
	// 이 때 사용되는 어노테이션이 @Bean == <bean id="" class="대상 클래스">
	@Bean    // 얘기 xml을 대용하게 된다
	public Dog dog() {    // BoardService <bean id="boardService" class="대상클래스">
		return new Dog();
	}


}
