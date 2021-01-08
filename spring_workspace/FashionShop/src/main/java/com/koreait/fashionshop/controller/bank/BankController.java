package com.koreait.fashionshop.controller.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.fashionshop.model.bank.service.BankService;

@Controller
public class BankController {
	@Autowired
	private BankService bankService;

	@GetMapping("/bank/send")  //GET 방식의 mapping
	@ResponseBody
	public String send() {
		bankService.send(20);
		return "sending to my mother filnished!";
	}
}
