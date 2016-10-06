package com.elim.learn.basic.concurrent.deadlock;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * 该类用于测试死锁，然后通过jconsole和jstack工具来查看死锁。
 * jmap工具可以用来查看内存使用情况，有很详细的信息
 * @author Elim
 * @date 2016年10月6日
 */
public class DeadLockTest1 {

	private Object lock1 = new Object();
	private Object lock2 = new Object();

	@Test
	public void test() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (lock1) {
					System.out.println("----getted lock1------");
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (lock2) {
						System.out.println("----getted lock2-----");
					}
				}
			}
		}).start();

		synchronized (lock2) {
			System.out.println("----getted lock2-----");
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (lock1) {
				System.out.println("----getted lock1-----");
			}
			
		}
		
	}

}
