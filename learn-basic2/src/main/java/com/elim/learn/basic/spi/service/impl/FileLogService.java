package com.elim.learn.basic.spi.service.impl;

import com.elim.learn.basic.spi.service.LogService;

public class FileLogService implements LogService {

	@Override
	public void info(String msg) {
		//简单输出，加以区别
		System.out.println("----file-log----");
	}

}
