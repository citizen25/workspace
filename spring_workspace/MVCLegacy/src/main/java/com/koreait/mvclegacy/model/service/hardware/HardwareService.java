package com.koreait.mvclegacy.model.service.hardware;

import java.util.List;

import com.koreait.mvclegacy.model.domain.Hardware;

public interface HardwareService {
	abstract public List<Hardware> selectAll();
}
