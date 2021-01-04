package com.koreait.fashionshop.model.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Product {
	private int product_id;
	private int subcategory_id;
	private String product_name;
	private int price;
	private String brand;
	private String detail;
	private String filename;  //currentTRimeMills로 정할 예정
	
	//이미지 하나를 자동으로 처리하는 객체 선언
	//단 이름은, html의 업로드 컴포넌트 파라미터명과 일치시켜야 자동으로 매치시켜준다
	private MultipartFile repImg;  //대표 이미지
	private MultipartFile[] addImg;  //추가 이미지는 선택 사항이며, 동시에 배열이다
	
	private Color[] color;  //색상값들
	private Psize[] psize;  //사이즈

}

