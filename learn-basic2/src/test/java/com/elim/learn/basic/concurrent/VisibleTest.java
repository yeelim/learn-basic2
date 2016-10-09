/**
 * 
 */
package com.elim.learn.basic.concurrent;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * 可见性测试
 * @author Elim
 * 2016年10月9日
 */
public class VisibleTest {

	private static final Logger LOGGER = Logger.getLogger(VisibleTest.class);
	
	private int a = 1;
	private int b = 1;
	
	@Test
	public void test() throws InterruptedException {
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				int i = a;
				int j = b;
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				int i2 = a;
				int j2 = b;
				//这里第二次读取的时候是存在从缓存中读取原来的那个1的可能性的。但是模拟的时候没有模拟出来
				LOGGER.info(i + "-------" + i2);
				LOGGER.info(j + "-------" + j2);
			}
		}, "Thread-1");
		
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				a = 2;
				b = 2;
			}
		}, "Thread-2");
		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();
	}
	
}
