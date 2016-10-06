package com.elim.learn.basic.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

import org.junit.Test;

import com.elim.learn.basic.spi.service.LogService;

public class SpiTest {

	@Test
	public void test() {
		ServiceLoader<LogService> serviceLoader = ServiceLoader.load(LogService.class);
		LogService logService = null;
		for (Iterator<LogService> iter = serviceLoader.iterator(); iter.hasNext(); ) {
			logService = iter.next();
			logService.info("Hello SPI");
		}
		//由于ServiceLoader是實現了java.util.Iterator接口的，也可以使用增強的for循環
		for (LogService service : serviceLoader ) {
			service.info("Hello SPI");
		}
	}
	
}
