package com.koreait.mvclegacy.model.repository.hardware;

import java.util.List;

import com.koreait.mvclegacy.model.domain.Hardware;

public interface HardwareDAO {
	abstract public List<Hardware> selectAll();
}
