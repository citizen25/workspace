package com.koreait.mvclegacy.model.service.hardware;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.mvclegacy.model.domain.Hardware;
import com.koreait.mvclegacy.model.repository.hardware.HardwareDAO;

@Service
public class HardwareServiceImpl implements HardwareService {

	@Autowired
	private HardwareDAO hardwareDAO;
	
	@Override
	public List<Hardware> selectAll() {
		return hardwareDAO.selectAll();
	}

}
