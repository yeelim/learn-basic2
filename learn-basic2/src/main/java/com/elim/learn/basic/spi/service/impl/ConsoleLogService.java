package com.elim.learn.basic.spi.service.impl;

import com.elim.learn.basic.spi.service.LogService;

public class ConsoleLogService implements LogService {

	@Override
	public void info(String msg) {
		System.out.println("----console log ----");
	}

}
