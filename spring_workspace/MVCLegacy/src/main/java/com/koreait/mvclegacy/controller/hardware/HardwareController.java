package com.koreait.mvclegacy.controller.hardware;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.mvclegacy.model.domain.Hardware;
import com.koreait.mvclegacy.model.service.hardware.HardwareService;

@Controller
public class HardwareController {
	
	@Autowired
	private HardwareService hardwareService;
	
	@RequestMapping(value="/hardware/list", method=RequestMethod.GET)
	public ModelAndView getHardwareList() {
		ModelAndView mav = new ModelAndView();
		List<Hardware> hardwareList = hardwareService.selectAll();
		
		mav.addObject("hardwareList", hardwareList);
		mav.setViewName("hardware/list");
		
		return mav;
	}
	
	
}
